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
import schemacrawler.schema.TableConstraintColumn;


//TODO: prepare the evaluations of the method for unit test.
@Component
public class SecondNormalFormClassificationTask implements TableClassificationTask<ClassificationData> {
	
	public SecondNormalFormClassificationTask() {
	}
	
	//TODO: Change Result Data Structure to Suport a better Data Structure
	//TODO: Generate bad case scenarios to refine algorithm.
	@Override
	public Optional<ClassificationResult<?>> evaluate(ClassificationData cd) {
		
			Optional<List<Column>> theTableColumns = cd.schemaTable().table().map(t -> t.getColumns());
		
			Map <String , Column> theKeyMap   = evaluateTheTableColumns(theTableColumns , cd);
			
			Map <String , Column> theIndexMap=  evaluateIndexColumns(theTableColumns,cd);
			
			ArrayListValuedHashMap<String,Column>  theMapList = prepareKeyIndexMap(theKeyMap , theIndexMap);
			
			SecondNormalFormClassificationResult theClassificationResult=null;
			if (theMapList.keySet().size() == theTableColumns.get().size()) {
//				TableIndexResult thePrimaryKeyResult  = mountPrimaryKeyTableIndexResult(cd);
				List<TableIndexResult> uniqueIndexTableIndexResult =  mountUniqueKeysTableIndexResultList(cd);
				theClassificationResult = new SecondNormalFormClassificationResult(cd.schemaTable().table().get().getName() , uniqueIndexTableIndexResult , theMapList);
			}
		return Optional.ofNullable(theClassificationResult);
	}


	private List<TableIndexResult> mountUniqueKeysTableIndexResultList(ClassificationData cd) {
		List<TableIndexResult> uniqueIndexTableIndexResult = new ArrayList<TableIndexResult> ();
		
		cd
		.tableIndexes()
		.indexes()
		.filter(i -> i!=null)
		.get()
		.stream()
		.filter(i->
			i.isUnique()
		)
		.forEach(ui -> {
			Optional.ofNullable(ui
			.getColumns())
			.ifPresent(cl -> { 
				List<Column> uiIndexList = cl.stream().map(ic -> Column.class.cast(ic)).collect(Collectors.toList());
				TableIndexResult tir = new TableIndexResult(cd.schemaTable().table().get().getName() , IndexTypeEnum.UNIQUE_INDEX , uiIndexList);
				uniqueIndexTableIndexResult.add(tir);
			});
			
		});
		return uniqueIndexTableIndexResult;
	}

	private TableIndexResult mountPrimaryKeyTableIndexResult(ClassificationData cd) {
		List<Column> pkeyColumns = cd.key()
		.opkey()
		.map( k-> k.getConstrainedColumns())
		.get()
		.stream()
		.map(c -> Column.class.cast(c))
		.collect(Collectors.toList());
		
		return new TableIndexResult(cd.schemaTable().table().get().getName() , IndexTypeEnum.PRIMARY_KEY , pkeyColumns);
	}

	private Map<String, Column> evaluateIndexColumns(Optional<List<Column>> theTableColumns, ClassificationData cd) {
		return theTableColumns
				.get()
				.stream()
				.map(c -> verifyColumnOnTableIndexes(c,cd.tableIndexes().indexes()))
				.map(e -> e.get())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	private Map<String, Column> evaluateTheTableColumns(Optional<List<Column>> theTableColumns, ClassificationData cd) {
		return  theTableColumns
				.get()
				.stream()
				.map(c -> verifyColumnOnPrimaryKey(c,cd.key().opkey()))
				.map(e -> e.get())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@SuppressWarnings("unchecked")
	private ArrayListValuedHashMap<String, Column> prepareKeyIndexMap(Map<String, Column> theKeyMap, Map<String, Column> theIndexMap) {
		int initialCapacity = theKeyMap.size() + theIndexMap.size() + 1;
		ArrayListValuedHashMap<String,Column> theMapList = new ArrayListValuedHashMap<String,Column>(initialCapacity, 16);
		
		
		theKeyMap
		.keySet()
		.stream()
		.forEach(s ->{
			Column c = theKeyMap.get(s);
			theMapList.put(s , c);
		});
		
		theIndexMap
		.keySet()
		.stream()
		.forEach(s ->{
			Optional
			.ofNullable(theMapList.get(s))
			.ifPresentOrElse(l -> l.add(theIndexMap.get(s)) , new Runnable() {

				@Override
				public void run() {
					theMapList.put(s, theIndexMap.get(s));
				}
				
			});
		});
		
		return theMapList;
	}

	//TODO: Refactor this method.
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
		.map(cc -> createIndexMapEntry(c.getName(),cc));
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
