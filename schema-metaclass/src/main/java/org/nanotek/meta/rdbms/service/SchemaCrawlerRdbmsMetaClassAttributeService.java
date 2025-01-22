package org.nanotek.meta.rdbms.service;

import java.util.Map;

import org.nanotek.meta.model.rdbms.RdbmsClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.repository.RdbmsMetaClassRepository;
import org.nanotek.meta.util.SnakeToCamelCaseTranslator;
import org.springframework.beans.factory.annotation.Autowired;

import schemacrawler.schema.Column;

public class SchemaCrawlerRdbmsMetaClassAttributeService {

	
	@Autowired 
	RdbmsMetaClassRepository repository;
	
	public void saveMetaAttribute(RdbmsMetaClass metaClass) {
		generateMetaAttributes(metaClass);
	}

	private void generateMetaAttributes(RdbmsMetaClass metaClass) {
		
		var rc = metaClass.getRdbmsClass();
		var lc = rc.getSchemaTable().getColumns();
		lc.forEach(c -> {
			var md = createMetaAttribute(c);
			md.setClazz(c.getColumnDataType().getTypeMappedClass().getName());
			md.setColumnName(c.getName());
			md.setFieldName(SnakeToCamelCaseTranslator.from(c.getName()));
			var attributes = c.getAttributes();
			verifyAttributes(c,attributes);
			md.setIsId(c.isPartOfPrimaryKey());
			md.setPartOfIndex(c.isPartOfIndex());
			md.setPartOfForeignKey(c.isPartOfForeignKey());
			metaClass.addMetaAttribute(md);
		});
		
	}

	private RdbmsMetaClassAttribute createMetaAttribute(Column c) {
		RdbmsMetaClassAttribute md = new RdbmsMetaClassAttribute();
		md.setClazz(c.getColumnDataType().getTypeMappedClass().getName());
		md.setColumnName(c.getName());
		md.setFieldName(SnakeToCamelCaseTranslator.from(c.getName()));
		var attributes = c.getAttributes();
		verifyAttributes(c,attributes);
		md.setIsId(c.isPartOfPrimaryKey());
		md.setPartOfIndex(c.isPartOfIndex());
		md.setPartOfForeignKey(c.isPartOfForeignKey());
		return md;
	}

	private void verifyAttributes(Column c, Map<String, Object> attributes) {
	}

	private void generateMetaAttribute(RdbmsMetaClass metaClass) {
	}
}
