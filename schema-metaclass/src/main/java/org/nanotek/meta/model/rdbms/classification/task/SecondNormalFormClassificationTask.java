package org.nanotek.meta.model.rdbms.classification.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.IndexTypeEnum;
import org.nanotek.meta.model.rdbms.classification.data.SecondNormalFormClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexResult;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Column;
import schemacrawler.schema.Index;
import schemacrawler.schema.IndexColumn;
import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableConstraintColumn;


//TODO: Refactor all, review report datastructure.
@Component
public class SecondNormalFormClassificationTask implements TableClassificationTask<ClassificationData> {
	
	public SecondNormalFormClassificationTask() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Optional<SecondNormalFormClassificationResult> evaluate(ClassificationData cd) {
		
		List<Column> tableColumns = cd.schemaTable()
										.table()
										.filter(t ->t.getColumns() !=null && t.getColumns().size() > 0)
										.map(t -> t.getColumns())
										.orElse(new ArrayList<Column>());
		List<Index> uniqueTableIndexes  = retrieveUniqueTableIndexes(cd.schemaTable().table());
		return Optional.empty();
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
