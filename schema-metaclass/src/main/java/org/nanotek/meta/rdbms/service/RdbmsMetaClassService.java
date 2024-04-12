package org.nanotek.meta.rdbms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaDataAttribute;
import org.nanotek.meta.model.RdbmsClass;
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
	
	public List<schemacrawler.schema.Table> getSchemaTables(){
		Catalog  catalog;
		List<schemacrawler.schema.Table> tables = new ArrayList<schemacrawler.schema.Table>();
		try {
			Connection connection = defaultDataSource.getConnection();
			catalog = SchemaCrawlerUtility.getCatalog(connection, schemaCrawlerOptions);
			catalog.getTables().forEach(t ->tables.add(t));;
		} catch (Exception e) {
			throw new SchemaMetaClassException(e);
		}
		return tables;
	}
	
	public MetaClass createMetaClass(schemacrawler.schema.Table t) {
		MetaClass meta = new MetaClass();
		meta.getRdbmsClass().setTable(t);
		meta.setClassName(t.getFullName());	
		return meta;
	}
	
	public MetaClass populateMetaClassAttributes(MetaClass metaClass) {
		final MetaClass mc = metaClass;
		RdbmsClass rc = mc.getRdbmsClass();
		List<Column> lc = rc.getTable().getColumns();
		
		lc.forEach(c -> {
			MetaDataAttribute md = new MetaDataAttribute();
			md.setClazz(c.getColumnDataType().getTypeMappedClass());
			md.setColumnName(c.getName());
			String fieldName = columnNameTranslationStrategy.processNameTranslationStrategy(c.getName());
			md.setFieldName(fieldName);
		});
		return mc;
	}

}
