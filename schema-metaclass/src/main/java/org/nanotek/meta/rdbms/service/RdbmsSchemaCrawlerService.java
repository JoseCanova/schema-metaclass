package org.nanotek.meta.rdbms.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.model.rdbms.RdbmsClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

@Component
public class RdbmsSchemaCrawlerService {

	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy; 
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Autowired
	SystemStaticMessageSource messageSource;
	
	public RdbmsSchemaCrawlerService() {
	}

	
	public List<?> getMetaClassList(){
		Optional<Collection<Table>> ocTables = getCatalogTables();

		
		List<?> metaClassList =  ocTables
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
			metaClass.addMetaAttribute(md);
		});
	}
	
	private Stream<Table> toStream(Collection<Table> ct) {
		return ct.stream();
	}


	private Optional<Collection<Table>> getCatalogTables(){
		return schemaCrawlerService.getCatalogTables();
	}
	
}
