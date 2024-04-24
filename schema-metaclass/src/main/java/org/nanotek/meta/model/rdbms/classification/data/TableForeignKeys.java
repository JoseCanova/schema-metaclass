package org.nanotek.meta.model.rdbms.classification.data;

import java.util.Collection;
import java.util.List;

import schemacrawler.schema.*;

public record TableForeignKeys(Collection<ForeignKey> keys) {

	public TableForeignKeys (final Collection<ForeignKey> keys) {
		this.keys = keys;
	}
	
}
