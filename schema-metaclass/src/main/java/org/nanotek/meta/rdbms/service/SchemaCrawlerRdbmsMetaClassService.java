package org.nanotek.meta.rdbms.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.model.TableClassName;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.model.rdbms.table.RdbmsSchemaTable;
import org.nanotek.meta.repository.RdbmsMetaClassRepository;
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.nanotek.meta.util.SnakeCaseFluentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import reactor.core.publisher.Mono;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

//TODO: implement a "symbolic package name" and the classname strategy for camel case.
//TODO: implement a metaclass method to provide information about a single metaclass present on "metaclass list".
//TODO: implement persistence on relational db for model_relation classes.
public class SchemaCrawlerRdbmsMetaClassService 
extends MetaClassPersistenceService<RdbmsMetaClassRepository , RdbmsMetaClass,String>{

	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy; 
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Autowired
	SystemStaticMessageSource messageSource;
	
	@Autowired
	SchemaCrawlerRdbmsMetaClassAttributeService schemaCrawlerRdbmsMetaClassAttributeService;
	
	public SchemaCrawlerRdbmsMetaClassService(
			RdbmsMetaClassRepository repository) {
		super(repository);
	}
	
	//TODO:Verify pagination for large set of TableClasses if necessary.
	public List<TableClassName> getTableClassNameList(){
			return getMetaClassList()
						.stream()
						.map(mtc -> new TableClassName(mtc.getTableName(),mtc.getClassName()))
						.collect(Collectors.toList());
	}
	
	//TODO:refactor method for jpa or jdbc template.
	public List<RdbmsMetaClass> retrieveMetaClassList(){
		List<RdbmsMetaClass> metaClassList = null;
		if(repository.count()<=0) {
			metaClassList = persistMetaClassList(getMetaClassList());			
		}
		return Optional
			.ofNullable(metaClassList)
			.orElse(repository.findAll());
	}
	
	public RdbmsMetaClass findByTableClassName(TableClassName tcn) {
		return repository.findOne(getExample(tcn)).orElseThrow();
	}
	
	private Example<RdbmsMetaClass> getExample(TableClassName tcn) {
		return Example.of(Optional
		.ofNullable(tcn)
		.map(tc -> {
			return new RdbmsMetaClass(tcn.tableName(),tcn.className() , null);
		}).orElseThrow());
	}

	public List<RdbmsMetaClass> persistMetaClassList(List<RdbmsMetaClass> theList){
		return theList
		.stream()
		.map(r -> repository.save(r))
		.collect(Collectors.toList());
	}	
	
	public List<RdbmsMetaClass> getMetaClassList(){
		return getCatalogTables()
				.stream()
					.map(t -> createMetaClass(t))
					.collect(Collectors.toList());
		
	}
	
	//TODO: Prepare service to populate foreign key - relation attributes.
	//TODO: Prepare a properties to manage the case when to use snake_case converter
	//TODO: Create a class to manage removal of special characteres as being done on previous version.
	private RdbmsMetaClass createMetaClass(RdbmsSchemaTable schemaTable) {
		Table table = schemaTable.getSchemaTable();
		String tableName = Optional.ofNullable(table.getName()).orElse(table.getFullName());
		String className = tableName.substring(0, 1).toUpperCase().concat(SnakeCaseFluentConverter.from(tableName).substring(1));
		RdbmsMetaClass metaClass = new RdbmsMetaClass(tableName , className , table);
		populateMetaClassAttributes(metaClass);
		populateMetaClassForeignKeys(metaClass);
		return metaClass;
	}
	
	private void populateMetaClassForeignKeys(RdbmsMetaClass metaClass) {
	}

	private void populateMetaClassAttributes(RdbmsMetaClass metaClass) {
		List<RdbmsMetaClassAttribute> attributes =  schemaCrawlerRdbmsMetaClassAttributeService.generateMetaAttributes(metaClass);
		metaClass.setMetaAttributes(attributes);
		//schemaCrawlerRdbmsMetaClassAttributeService.saveMetaAttributes(metaClass);
		//TODO:Refactor moving attribute creation to its own service.
		/*
		 * lc.forEach(c -> { RdbmsMetaClassAttribute md = new RdbmsMetaClassAttribute();
		 * md.setClazz(c.getColumnDataType().getTypeMappedClass().getName());
		 * md.setColumnName(c.getName());
		 * md.setFieldName(SnakeToCamelCaseTranslator.from(c.getName())); var attributes
		 * = c.getAttributes(); verifyAttributes(c,attributes);
		 * md.setPartOfId(c.isPartOfPrimaryKey()); md.setPartOfIndex(c.isPartOfIndex());
		 * md.setPartOfForeignKey(c.isPartOfForeignKey());
		 * metaClass.addMetaAttribute(md); });
		 */
		processMetaAttributesIds(metaClass);
	}
	
	//TODO: implement method and generate a "rationale for the attribute id`s" otherwise cancel method call
	private void processMetaAttributesIds(RdbmsMetaClass metaClass) {
	}


	//TODO:Inspect attributes to check additional information on column.
	private void verifyAttributes(Column c, Map<String, Object> attributes) {
		System.out.println(attributes);
	}

	private Stream<Table> toStream(Collection<Table> ct) {
		return ct.stream();
	}

	private Collection<RdbmsSchemaTable> getCatalogTables(){
		return schemaCrawlerService.getRdbmsMetaclassTable();
	}

	public Mono<RdbmsMetaClass> getRdbmsMetaClass(Mono<TableClassName> tableClassNameMono) {
		return tableClassNameMono
					.flatMap(tcn -> getRdbmsMetaClass(tcn)
							.onErrorMap(o -> new RuntimeException("" , o)
					));
	}

	private Mono<RdbmsMetaClass> getRdbmsMetaClass(TableClassName tcn) {
		return Mono
				.just(getMetaClassList().stream()
				.filter(c -> c.getClassName().equals(tcn.className()) && c.getTableName().equals(tcn.tableName()))
				.findAny()).flatMap(Mono::justOrEmpty);
	}

	public List<RdbmsMetaClass> findByClassName(String className) {
		return repository.findByClassName(className);
	}

}
