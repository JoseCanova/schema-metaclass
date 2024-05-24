package org.nanotek.meta.classification;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.schema.result.SchemaCrawlerResult;

public interface SchemaCrawlerTask<R extends Record > extends Task<R , SchemaCrawlerResult<?>> {

	@Override
	default Optional<SchemaCrawlerResult<?>> evaluate(R cd) {
		return Optional.empty();
	}
	
}
