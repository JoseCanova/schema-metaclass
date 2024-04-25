package org.nanotek.meta.rdbms.service;

import static org.nanotek.meta.constants.SystemStaticMessageSource.NONOK;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.nanotek.meta.constants.LocaleContext;
import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.rdbms.exception.SchemaMetaClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.tools.utility.SchemaCrawlerUtility;

@Service
public class RdbmsSchemaClassificationService {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SystemStaticMessageSource messageSource;
	
	@Autowired 
	SchemaCrawlerOptions schemaCrawlerOptions;
	
	public RdbmsSchemaClassificationService() {
	}
	
	public List<Table> getSchemaTables(){
		List<Table> tables = new ArrayList<Table>();
		try {
			Catalog  catalog;
			Connection connection = dataSource.getConnection();
			catalog = SchemaCrawlerUtility.getCatalog(connection, schemaCrawlerOptions);
			catalog.getTables().forEach(t ->tables.add(t));;
		}catch (SQLException se) {
			throw new SchemaMetaClassException(messageSource.getMessage(NONOK , new Object[]{}, LocaleContext.getCurrentLocale()) , se.getCause()) ;
		}
		return tables;
	}

}