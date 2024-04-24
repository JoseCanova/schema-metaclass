package org.nanotek.meta.model.rdbms.classification.data;

import schemacrawler.schema.PrimaryKey;

public record TableKey(PrimaryKey key) {
	
	public TableKey (final PrimaryKey key) {
		this.key = key;
	}
}