package org.nanotek.meta.rdbms.service;

import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaIdentity;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Table;

@Service
public class RbdmsMetaIdentityService {

	public RbdmsMetaIdentityService() {
	}
	
	public MetaClass prepareIdentity(final RdbmsMetaClass metaClass) {
		Table t  = metaClass.getRdbmsClass().getTable();
		metaClass.setTableName(t.getFullName());
		MetaIdentity mi = new MetaIdentity(t.getPrimaryKey());
		metaClass.setIdentity(mi);
		return metaClass;
	}

}
