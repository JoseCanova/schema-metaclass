package org.nanotek.meta.rdbms.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.model.TableClassName;
import org.nanotek.meta.model.rdbms.RdbmsClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.repository.RdbmsMetaClassRepository;
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.nanotek.meta.util.SnakeToCamelCaseTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

//TODO: implement a "symbolic package name" and the classname strategy for camel case.
//TODO: implement a metaclass method to provide information about a single metaclass present on "metaclass list".
//TODO: implement persistence on mongodb for model_relation classes.
@Component
public class SchemaCrawlerRdbmsMetaClassService {

	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy; 
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Autowired
	SystemStaticMessageSource messageSource;
	
	@Autowired
	RdbmsMetaClassRepository metaClassRepository;
	
	public SchemaCrawlerRdbmsMetaClassService() {
	}

	//TODO:Verify pagination for large set of TableClasses if necessary.
	public List<TableClassName> getTableClassNameList(){
			return getMetaClassList()
						.stream()
						.map(mtc -> new TableClassName(mtc.getTableName(),mtc.getClassName()))
						.collect(Collectors.toList());
	}
	
	public List<RdbmsMetaClass> retrieveMetaClassList(){
		List<RdbmsMetaClass> metaClassList = null;
		if(metaClassRepository.count()<=0) {
			metaClassList = persistMetaClassList(getMetaClassList());			
		}
		return Optional
			.ofNullable(metaClassList)
			.orElse(metaClassRepository.findAll());
	}
	
	public RdbmsMetaClass findByTableClassName(TableClassName tcn) {
		return metaClassRepository.findOne(getExample(tcn)).orElseThrow();
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
		.map(r -> metaClassRepository.save(r))
		.collect(Collectors.toList());
	}	
	
	public List<RdbmsMetaClass> getMetaClassList(){
		return getCatalogTables()
					.map(ct -> toStream(ct))
					.orElseThrow()
					.map(t -> createMetaClass(t))
					.collect(Collectors.toList());
		
	}
	
	//TODO: refactor this method for a fluent constructor.
	private RdbmsMetaClass createMetaClass(Table table) {
		String tableName = Optional.ofNullable(table.getName()).orElse(table.getFullName());
		RdbmsMetaClass metaClass = new RdbmsMetaClass(tableName , tableName , table);
		populateMetaClassAttributes(metaClass);
		return metaClass;
	}

	private void populateMetaClassAttributes(RdbmsMetaClass metaClass) {
		final RdbmsMetaClass mc = metaClass;
		RdbmsClass rc = mc.getRdbmsClass();
		List<Column> lc = rc.getTable().getColumns();
		lc.forEach(c -> {
			RdbmsMetaClassAttribute md = new RdbmsMetaClassAttribute();
			md.setClazz(c.getColumnDataType().getTypeMappedClass().getName());
			md.setColumnName(c.getName());
			var originalFieldName = columnNameTranslationStrategy.processNameTranslationStrategy(c.getName());
			md.setFieldName(SnakeToCamelCaseTranslator.from(originalFieldName));
			var attributes = c.getAttributes();
			verifyAttributes(c,attributes);
			md.setId(c.isPartOfPrimaryKey());
			md.setPartOfIndex(c.isPartOfIndex());
			md.setPartOfForeignKey(c.isPartOfForeignKey());
			metaClass.addMetaAttribute(md);
		});
		processMetaAttributesIds(metaClass);
	}
	
	private void processMetaAttributesIds(RdbmsMetaClass metaClass) {
	}


	//TODO:Inspect attributes to check additional information on column.
	private void verifyAttributes(Column c, Map<String, Object> attributes) {
		System.out.println(attributes);
	}

	private Stream<Table> toStream(Collection<Table> ct) {
		return ct.stream();
	}

	private Optional<Collection<Table>> getCatalogTables(){
		return schemaCrawlerService.getCatalogTables();
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
		return metaClassRepository.findByClassName(className);
	}
	
//	public Mono<RdbmsMetaClass> findByClassName(TableClassName tcn){
//	
//	Flux<Person> result = template.query(Person.class)
//			  .matching(query(where("age").lt(50).and("accounts.balance").gt(1000.00d)))
//			  .all();
//	}
}
