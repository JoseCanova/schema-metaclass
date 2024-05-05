package org.nanotek.meta.model.rdbms.classification.task;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

import schemacrawler.schema.Column;


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
		
		    Map<?,?> kcMap=	cd
			.key()
			.opkey()
			.map(k ->{
				ImmutableList<Column> ilc = ImmutableList.copyOf(k.getConstrainedColumns());
				return ilc
				.stream()
				.map(c -> 
					theTableColumns
					.get()
					.stream()
					.filter(c1 ->c1.getName().equalsIgnoreCase(c.getName()))
					.findFirst()
					.map(cc-> getEntry(c,cc))
				).collect(Collectors.toList())
				.stream()
					.map(ok->ok.get())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}).get();
		    cd
			    .tableIndexes()
			    .indexes()
			    .filter(ic -> ic.size() > 0)
			    .get()
			    .stream()
			    .filter(idx -> idx.isUnique())
			    .forEach(ui ->{
			    	ImmutableList<Column> ilc = ImmutableList.copyOf(ui.getColumns());
			    });
		return TableClassificationTask.super.evaluate(cd);
	}


	private Map.Entry<?, ?> getEntry(Column c, Column cc) {
		return Map.entry(c, cc);
	}
	
}
