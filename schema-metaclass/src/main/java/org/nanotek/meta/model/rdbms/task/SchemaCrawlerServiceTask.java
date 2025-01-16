package org.nanotek.meta.model.rdbms.task;

import java.util.Optional;

import org.nanotek.meta.classification.SchemaCrawlerTask;
import org.nanotek.meta.model.rdbms.schema.result.SchemaCrawlerResult;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;

public class SchemaCrawlerServiceTask<R extends Record > implements SchemaCrawlerTask<R>{

	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	public SchemaCrawlerServiceTask() {
	}
	
	//TODO:review implementation since "normalization analysis scope" will be moved from 
	//this module.
	@Override
	public Optional<SchemaCrawlerResult<?>> evaluate(R cd) {
		return Optional.of(new SchemaCrawlerResult<>(schemaCrawlerService.getCatalogTables()));
	}

}
