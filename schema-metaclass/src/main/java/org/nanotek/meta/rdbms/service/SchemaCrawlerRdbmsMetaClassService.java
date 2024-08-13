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
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

//TODO: change visibility of postConstruct auxiliary methods upon implementation of JpaRdbmsMetaClass.
//TODO: implement persistence on mongodb for model_relation classes.
@Component
public class SchemaCrawlerRdbmsMetaClassService {

	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy; 
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Autowired
	SystemStaticMessageSource messageSource;
	
	public SchemaCrawlerRdbmsMetaClassService() {
	}

	
	public List<TableClassName> getTableClassNameList(){
			return getMetaClassList()
						.stream()
						.map(mtc -> new TableClassName(mtc.getTableName(),mtc.getClassName()))
						.collect(Collectors.toList());
	}
	
	//TODO: implement this method to allow persistence over a metaclass model.
	public List<RdbmsMetaClass> persistMetaClassList(List<RdbmsMetaClass> theList){
		List<RdbmsMetaClass> metaClassList = theList;
		return metaClassList;
	}	
	
	public List<RdbmsMetaClass> getMetaClassList(){
		Optional<Collection<Table>> ocTables = getCatalogTables();

		
		List<RdbmsMetaClass> metaClassList =  ocTables
					.map(ct -> toStream(ct))
					.orElseThrow()
					.map(t -> createMetaClass(t))
					.collect(Collectors.toList());
		
		return metaClassList;
	}
	
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
			md.setClazz(c.getColumnDataType().getTypeMappedClass());
			md.setColumnName(c.getName());
			String fieldName = columnNameTranslationStrategy.processNameTranslationStrategy(c.getName());
			md.setFieldName(fieldName);
			Map<String,Object> attributes = c.getAttributes();
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
	
}
