package org.nanotek.meta.rdbms.service;

import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaIdentity;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Table;

@Service
public class RbdmsMetaIdentityService {

	public RbdmsMetaIdentityService() {
	}
	
	public MetaClass prepareIdentity(final MetaClass metaClass) {
		Table t  = metaClass.getRdbmsClass().getTable();
		metaClass.setTableName(t.getFullName());
		MetaIdentity mi = new MetaIdentity(t.getPrimaryKey());
		metaClass.setIdentity(mi);
		return metaClass;
	}

}
