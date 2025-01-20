package org.nanotek.meta.model.rdbms.schema.result;

import java.util.Optional;

public record SchemaCrawlerResult<K>(Optional<K> resulted) {

	public SchemaCrawlerResult(Optional<K> resulted) {
		this.resulted = resulted;
	}
	
}
