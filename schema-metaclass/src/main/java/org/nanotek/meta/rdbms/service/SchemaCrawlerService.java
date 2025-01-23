package org.nanotek.meta.rdbms.service;

import static org.nanotek.meta.constants.SystemStaticMessageSource.NONOK;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.meta.constants.LocaleContext;
import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.SchemaTable;
import org.nanotek.meta.model.rdbms.classification.data.TableColumns;
import org.nanotek.meta.model.rdbms.classification.data.TableForeignKeys;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexes;
import org.nanotek.meta.model.rdbms.classification.data.TableKey;
import org.nanotek.meta.model.rdbms.table.RdbmsSchemaTable;
import org.nanotek.meta.rdbms.exception.SchemaMetaClassException;
import org.nanotek.meta.util.RdbmsSchemaTableBuilder;
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
public class SchemaCrawlerService {


	@Autowired 
	SchemaCrawlerOptions schemaCrawlerOptions;
	
	@Autowired
	SystemStaticMessageSource messageSource;
	
	@Autowired
	SchemaCrawlerDataSourceService schemaCrawlerDataSourceService;
	
	//TODO:need a check if catalogtables is not empty
	public Collection<RdbmsSchemaTable> getRdbmsMetaclassTable(){
		return getCatalogTables()
				.map(col -> col.stream()
						.map(t->Table.class.cast(t)).map(t->RdbmsSchemaTableBuilder.from(t))).get()
				.collect(Collectors.toList());
	}
	
	public Optional<Collection<Table>> getCatalogTables(){
		Catalog  catalog;
		try {
			catalog = SchemaCrawlerUtility.getCatalog(
					schemaCrawlerDataSourceService.getDatabaseConnectionSource(), 
					schemaCrawlerOptions);
		} catch (Exception e) {
			throw new SchemaMetaClassException(messageSource.getMessage(NONOK , 
										new Object[]{}, 
										LocaleContext.getCurrentLocale()) , 
										e.getCause()) ;
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
		    						new SchemaTable(oTable),
		    						new TableKey(   Optional.ofNullable(t.getPrimaryKey())),
		    						new TableColumns( Optional.ofNullable(t.getColumns())),
		    						new TableForeignKeys( Optional.ofNullable(t.getForeignKeys())),
		    						new TableIndexes(Optional.ofNullable(t.getIndexes()))
		    		);});
	}
	
	public Map <Table,ClassificationData>  getCatalogClassificationMap(Optional<Collection<Table>> oTables){
		return oTables
		.get()
		.stream()
		.map(t ->buildClassificationDataEntry(t))
		.collect(Collectors.toMap(Map.Entry::getKey , Map.Entry::getValue));
	}


	public List<Pair<ClassificationData,ClassificationData>> getDataPairList ( Map <Table,ClassificationData> dataMap){
		List<Pair<ClassificationData,ClassificationData>> resultList = new ArrayList<Pair<ClassificationData,ClassificationData>>();
		dataMap
		.values()
		.forEach(v -> {
			resultList.addAll(dataMap
			.values()
			.stream()
			.filter(vv -> !vv.equals(v))
			.map(vv -> Pair.of)(v,vv))
			.collect(Collectors.toList()));
		});
		return resultList;
	}
	
	private Map.Entry<Table,ClassificationData> buildClassificationDataEntry(Table table) {
		return  new AbstractMap.SimpleEntry<>(table, buildClassicationData(table));
	}
	
	private ClassificationData buildClassicationData(Table table) {
		Table theTable = Table.class.cast(table);
		return new ClassificationData(
				new SchemaTable(Optional.ofNullable(theTable)),
				new TableKey(Optional.ofNullable(theTable.getPrimaryKey())),
				new TableColumns(Optional.ofNullable(theTable.getColumns())),
				new TableForeignKeys(Optional.ofNullable(theTable.getForeignKeys())),
				new TableIndexes(Optional.ofNullable(theTable.getIndexes()))
				);
	}

}
