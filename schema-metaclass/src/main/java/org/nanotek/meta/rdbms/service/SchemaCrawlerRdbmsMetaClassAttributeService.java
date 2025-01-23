package org.nanotek.meta.rdbms.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.repository.RbdmsMetaAttributeRepository;
import org.nanotek.meta.util.SnakeToCamelCaseTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Column;

@Service
public class SchemaCrawlerRdbmsMetaClassAttributeService {

	
	@Autowired 
	RbdmsMetaAttributeRepository repository;
	
	public void saveMetaAttributes(RdbmsMetaClass metaClass) {
		var metaAttributes = generateMetaAttributes(metaClass);
		metaAttributes.stream()
		.forEach(ma -> repository.save(ma));
		metaClass.setMetaAttributes(metaAttributes);
	}

	private List<RdbmsMetaClassAttribute> generateMetaAttributes(RdbmsMetaClass metaClass) {
		
		var rc = metaClass.getRdbmsClass();
		var lc = rc.getSchemaTable().getColumns();
		return lc.stream().map(c -> createMetaAttribute(c)
		).collect(Collectors.toList());
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