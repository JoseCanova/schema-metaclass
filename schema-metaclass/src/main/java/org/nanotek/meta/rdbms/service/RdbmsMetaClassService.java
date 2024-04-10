package org.nanotek.meta.rdbms.service;

import org.nanotek.meta.model.MetaClass;
import org.springframework.stereotype.Service;



@Service
public class RdbmsMetaClassService {

	
	public MetaClass createMetaClass(schemacrawler.schema.Table t) {
		MetaClass meta = new MetaClass();
		meta.getRdbmsClass().setTable(t);
		meta.setClassName(t.getFullName());	
		return meta;
	}
	
}
