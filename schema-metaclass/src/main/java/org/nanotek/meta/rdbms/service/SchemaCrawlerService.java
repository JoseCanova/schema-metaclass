package org.nanotek.meta.rdbms.service;

import static org.nanotek.meta.constants.SystemStaticMessageSource.NONOK;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.nanotek.meta.constants.LocaleContext;
import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.rdbms.exception.SchemaMetaClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.tools.utility.SchemaCrawlerUtility;

@Service
public class SchemaCrawlerService {

	@Autowired
	private DataSource defaultDataSource;

	@Autowired 
	SchemaCrawlerOptions schemaCrawlerOptions;
	
	@Autowired
	SystemStaticMessageSource messageSource;
	
	public Optional<Collection<Table>> getCatalogTables(){
		Catalog  catalog;
		try {
			Connection connection = defaultDataSource.getConnection();
			catalog = SchemaCrawlerUtility.getCatalog(connection, schemaCrawlerOptions);
		} catch (Exception e) {
			throw new SchemaMetaClassException(messageSource.getMessage(NONOK , new Object[]{}, LocaleContext.getCurrentLocale()) , e.getCause()) ;
		}
		return Optional.ofNullable(catalog.getTables());
	}
	
	public Optional<List<Column>> getTableColumns(Optional<Table> oTable){
		return oTable.map(t -> t.getColumns());
	}
}
