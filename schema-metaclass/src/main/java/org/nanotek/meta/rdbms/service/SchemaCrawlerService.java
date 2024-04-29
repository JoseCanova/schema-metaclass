package org.nanotek.meta.rdbms.service;

import static org.nanotek.meta.constants.SystemStaticMessageSource.NONOK;

import java.sql.Connection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.nanotek.meta.constants.LocaleContext;
import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.TableColumns;
import org.nanotek.meta.model.rdbms.classification.data.TableForeignKeys;
import org.nanotek.meta.model.rdbms.classification.data.TableKey;
import org.nanotek.meta.rdbms.exception.SchemaMetaClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.tools.utility.SchemaCrawlerUtility;

@Service
public class SchemaCrawlerService<K> {

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
	
	public Optional<PrimaryKey> getTablePrimaryKey(Optional<Table> oTable){
		return oTable.map(t -> t.getPrimaryKey());
	}
	
	public Optional<ClassificationData> getTableClassificationData(Optional<Table> oTable) {
		    return oTable.map(t ->{
		    				return new ClassificationData(
		    						new TableKey(   Optional.ofNullable(t.getPrimaryKey())),
		    						new TableColumns( Optional.ofNullable(t.getColumns())),
		    						new TableForeignKeys( Optional.ofNullable(t.getForeignKeys()))
		    		);});
	}
	
	public Map <Table,ClassificationData>  getCatalogClassificationMap(Optional<Collection<Table>> oTables){
		return oTables
		.get()
		.stream()
		.map(t ->buildClassificationDataEntry(t))
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}


	
	private Map.Entry<Table,ClassificationData> buildClassificationDataEntry(Table table) {
		return  new AbstractMap.SimpleEntry<>(table, buildClassicationData(table));
	}
	
	private ClassificationData buildClassicationData(Table table) {
		Table theTable = Table.class.cast(table);
		return new ClassificationData(
				new TableKey(Optional.ofNullable(theTable.getPrimaryKey())),
				new TableColumns(Optional.ofNullable(theTable.getColumns())),
				new TableForeignKeys(Optional.ofNullable(theTable.getForeignKeys()))
				);
	}

}
