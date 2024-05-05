package org.nanotek.meta.model.rdbms.classification.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.SecondNormalFormClassificationResult;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Column;
import schemacrawler.schema.Index;
import schemacrawler.schema.IndexColumn;
import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.TableConstraintColumn;


//TODO: prepare the evaluations of the method for unit test.
@Component
public class SecondNormalFormClassificationTask implements TableClassificationTask<ClassificationData> {
	
	public SecondNormalFormClassificationTask() {
	}
	
	//TODO:Finish the second iteration with indexes. 
	//TODO: Generate bad case scenarios to refine algorithm.
	@Override
	public Optional<ClassificationResult<?>> evaluate(ClassificationData cd) {
		
			Optional<List<Column>> theTableColumns = cd.schemaTable().table().map(t -> t.getColumns());
		
			Map <String , Column> theKeyMap   = theTableColumns
			.get()
			.stream()
			.map(c -> verifyColumnOnPrimaryKey(c,cd.key().opkey()))
			.map(e -> e.get())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			
			Map <String , Column> theIndexMap=  theTableColumns
			.get()
			.stream()
			.map(c -> verifyColumnOnTableIndexes(c,cd.tableIndexes().indexes()))
			.map(e -> e.get())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			
			theKeyMap.putAll(theIndexMap);
			SecondNormalFormClassificationResult theClassificationResult=null;
			if (theKeyMap.size() == theTableColumns.get().size())
				theClassificationResult = new SecondNormalFormClassificationResult(cd.schemaTable().table().get().getName() , null);
		return Optional.ofNullable(theClassificationResult);
	}


	private Optional<Map.Entry<String, Column>> verifyColumnOnTableIndexes(Column c, Optional<Collection<Index>> indexes) {
		List<IndexColumn> a = new ArrayList<IndexColumn>();
		return indexes
		.get()
		.stream()
		.filter(i ->i.isUnique())
		.collect(Collectors.toList())
		.stream()
		.map(i -> i.getColumns())
		.collect(Collectors.toList())
		.stream()
		.reduce(a , (b,cc) -> addAllColumns(a,cc))
		.stream()
		.filter(cc->c.getName().equalsIgnoreCase(cc.getName()))
		.map(cc -> createIndexMapEntry(c.getName(),cc))
		.findFirst().get();
	}

	private Optional<Map.Entry<String,Column>> createIndexMapEntry(String name, IndexColumn cc) {
		return Optional.ofNullable(Map.entry(name, cc) );
	}

	private List<IndexColumn> addAllColumns(List<IndexColumn> a, List<IndexColumn> cc) {
		a.addAll(cc);
		return a;
	}

	private Optional<Map.Entry<String,Column>> verifyColumnOnPrimaryKey(Column c, Optional<PrimaryKey> opkey) {
		return opkey
		.get()
		.getConstrainedColumns()
		.stream()
		.filter(cc -> c.getName().equalsIgnoreCase(c.getName()))
		.findFirst()
		.map(ccc -> createMapEntry(c.getName(), ccc));
	}

	private Map.Entry<String,Column>  createMapEntry(String name, TableConstraintColumn ccc) {
		return Map.entry(name , ccc);
	}

}
