package org.nanotek.meta.rdbms.service;

import static org.nanotek.meta.constants.SystemStaticMessageSource.NONOK;

import java.util.ArrayList;
import java.util.List;

import org.nanotek.meta.constants.LocaleContext;
import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.rdbms.RdbmsClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.rdbms.exception.SchemaMetaClassException;
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.tools.utility.SchemaCrawlerUtility;

/**
 * @deprecated
 */
@Service
public class RdbmsMetaClassService {

	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy; 
	
	
	@Autowired 
	SchemaCrawlerOptions schemaCrawlerOptions;
	
	@Autowired
	SystemStaticMessageSource messageSource;
	
	@Autowired
	SchemaCrawlerDataSourceService schemaCrawlerDataSourceService;
	
	public List<schemacrawler.schema.Table> getSchemaTables(){
		Catalog  catalog;
		List<schemacrawler.schema.Table> tables = new ArrayList<schemacrawler.schema.Table>();
		try {
			catalog = SchemaCrawlerUtility
						.getCatalog(schemaCrawlerDataSourceService.getDatabaseConnectionSource(), schemaCrawlerOptions);
			catalog.getTables().forEach(t ->tables.add(t));
		} catch (Exception e) {
			throw new SchemaMetaClassException(messageSource.getMessage(NONOK , new Object[]{}, LocaleContext.getCurrentLocale()) , e.getCause()) ;
		}
		return tables;
	}
	
	public MetaClass createMetaClass(schemacrawler.schema.Table t) {
		RdbmsMetaClass meta = new RdbmsMetaClass(t.getName() , t.getName());
		meta.getRdbmsClass().setTable(t);
		meta.setClassName(t.getFullName());	
		return meta;
	}
	
	public MetaClass populateMetaClassAttributes(RdbmsMetaClass metaClass) {
		final RdbmsMetaClass mc = metaClass;
		RdbmsClass rc = mc.getRdbmsClass();
		List<Column> lc = rc.getTable().getColumns();
		
		lc.forEach(c -> {
			RdbmsMetaClassAttribute md = new RdbmsMetaClassAttribute();
			md.setClazz(c.getColumnDataType().getTypeMappedClass().getName());
			md.setColumnName(c.getName());
			String fieldName = columnNameTranslationStrategy.processNameTranslationStrategy(c.getName());
			md.setFieldName(fieldName);
		});
		return mc;
	}

}
