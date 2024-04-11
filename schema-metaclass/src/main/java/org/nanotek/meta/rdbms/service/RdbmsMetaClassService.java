package org.nanotek.meta.rdbms.service;

import java.util.List;

import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaDataAttribute;
import org.nanotek.meta.model.RdbmsClass;
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Column;



@Service
public class RdbmsMetaClassService {

	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy; 
	
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
