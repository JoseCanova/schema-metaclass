package org.nanotek.meta.model.rdbms.classification.data;

import schemacrawler.schema.PrimaryKey;

record Key(PrimaryKey key) {
	
	public Key (PrimaryKey key) {
		this.key = key;
	}
}