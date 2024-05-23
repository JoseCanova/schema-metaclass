package org.nanotek.meta.model.rdbms.classification.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.IndexTypeEnum;
import org.nanotek.meta.model.rdbms.classification.data.ResultInfo;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexResult;
import org.nanotek.meta.model.rdbms.classification.data.result.SecondNormalFormClassificationResult;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Column;
import schemacrawler.schema.Index;
import schemacrawler.schema.Table;


@Component
public class SecondNormalFormClassificationTask implements TableClassificationTask<ClassificationData , SecondNormalFormClassificationResult> {
	
	public SecondNormalFormClassificationTask() {
	}
	
	@Override
	public Optional<SecondNormalFormClassificationResult> evaluate(ClassificationData cd) {
		
		List<Column> tableColumns = cd.schemaTable()
										.table()
										.filter(t ->t.getColumns() !=null && t.getColumns().size() > 0)
										.map(t -> t.getColumns())
										.orElse(new ArrayList<Column>());
		
		List<Index> uniqueTableIndexes  = retrieveUniqueTableIndexes(cd.schemaTable().table());
		
		Map<String,List<Index>> columnsIndexResult = tableColumns
		.stream()
		.map(cc -> mountColumnIndexResult(cc , uniqueTableIndexes))
		.map(ir -> Map.entry(ir.informed(), ir.resulted()))
		.filter(erl -> erl.getValue() != null && erl.getValue().size() > 0)
		.collect(Collectors.toMap(Map.Entry::getKey , Map.Entry::getValue));
		TableIndexResult theResult = new TableIndexResult (IndexTypeEnum.UNIQUE_INDEX , columnsIndexResult);
		return evaluateTableIndexResult(cd.schemaTable().table() , theResult);
	}

	
	private Optional<SecondNormalFormClassificationResult> evaluateTableIndexResult(Optional<Table> oTable, TableIndexResult theResult) {
		if (theResult.columnsIndexResult().size() == oTable.get().getColumns().size())
			return Optional.of(new SecondNormalFormClassificationResult(oTable.get().getName(),  theResult));
		else 
			return Optional.empty();
	}

	private ResultInfo<List<Index> , String> mountColumnIndexResult(Column cc, List<Index> uniqueTableIndexes) {
		return  new ResultInfo<List<Index> , String>( uniqueTableIndexes
		.stream()
		.filter(i -> indexContainsColumn(cc.getName() , i))
		.collect(Collectors.toList()),cc.getName() );
	}

	private boolean indexContainsColumn(String columnName  , Index i) {
		return 
		i.getColumns()
		.stream()
		.filter(ci -> ci.getName().equalsIgnoreCase(columnName))
		.count() >= 1;
	}

	private List<Index> retrieveUniqueTableIndexes(Optional<Table> table) {
		Collection<Index> indexCollection =  retrieveTableIndexes(table.get());
		return  indexCollection
				.stream()
				.filter(i-> i.isUnique()).collect(Collectors.toList());
	}

	private Collection<Index> retrieveTableIndexes(Table table) {
		return Optional.ofNullable(table.getIndexes()).orElse(new ArrayList<>());
	}

}
