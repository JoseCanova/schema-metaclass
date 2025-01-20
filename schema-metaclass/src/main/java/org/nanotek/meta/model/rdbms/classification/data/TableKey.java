package org.nanotek.meta.model.rdbms.classification.data;

import java.util.Optional;

import schemacrawler.schema.PrimaryKey;

public record TableKey(Optional<PrimaryKey> opkey) {
	
	public TableKey (final Optional<PrimaryKey> opkey) {
		this.opkey = opkey;
	}
}