package org.nanotek.meta.rdbms.service;

import static org.nanotek.meta.constants.SystemStaticMessageSource.NONOK;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.nanotek.meta.constants.LocaleContext;
import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaClassAttribute;
import org.nanotek.meta.model.rdbms.RdbmsClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.exception.SchemaMetaClassException;
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.tools.utility.SchemaCrawlerUtility;


@Service
public class RdbmsMetaClassService {

	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy; 
	
	@Autowired
	DataSource defaultDataSource;
	
	@Autowired 
	SchemaCrawlerOptions schemaCrawlerOptions;
	
	@Autowired
	SystemStaticMessageSource messageSource;
	
	public List<schemacrawler.schema.Table> getSchemaTables(){
		Catalog  catalog;
		List<schemacrawler.schema.Table> tables = new ArrayList<schemacrawler.schema.Table>();
		try {
			Connection connection = defaultDataSource.getConnection();
			catalog = SchemaCrawlerUtility.getCatalog(connection, schemaCrawlerOptions);
			catalog.getTables().forEach(t ->tables.add(t));;
		} catch (Exception e) {
			throw new SchemaMetaClassException(messageSource.getMessage(NONOK , new Object[]{}, LocaleContext.getCurrentLocale()) , e.getCause()) ;
		}
		return tables;
	}
	
	public MetaClass createMetaClass(schemacrawler.schema.Table t) {
		RdbmsMetaClass meta = new RdbmsMetaClass();
		meta.getRdbmsClass().setTable(t);
		meta.setClassName(t.getFullName());	
		return meta;
	}
	
	public MetaClass populateMetaClassAttributes(RdbmsMetaClass metaClass) {
		final RdbmsMetaClass mc = metaClass;
		RdbmsClass rc = mc.getRdbmsClass();
		List<Column> lc = rc.getTable().getColumns();
		
		lc.forEach(c -> {
			MetaClassAttribute md = new MetaClassAttribute();
			md.setClazz(c.getColumnDataType().getTypeMappedClass());
			md.setColumnName(c.getName());
			String fieldName = columnNameTranslationStrategy.processNameTranslationStrategy(c.getName());
			md.setFieldName(fieldName);
		});
		return mc;
	}

}
