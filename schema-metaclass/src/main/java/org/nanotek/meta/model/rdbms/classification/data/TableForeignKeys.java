package org.nanotek.meta.model.rdbms.classification.data;

import java.util.List;

import schemacrawler.schema.*;

public record TableForeignKeys(List<ForeignKey> keys) {

	public TableForeignKeys (final List<ForeignKey> keys) {
		this.keys = keys;
	}
	
}
