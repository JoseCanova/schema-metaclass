package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.result.VoidTableClassificationResult;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Table;

//@Component
public class VoidTableClassificationTask implements TableClassificationTask<ClassificationData,VoidTableClassificationResult> {

	@Autowired
	protected SchemaCrawlerService schemaCrawlerService;
	
	public VoidTableClassificationTask() {
	}
	
	@Override
	public Optional<VoidTableClassificationResult> evaluate(ClassificationData cd) {
			 return Optional.ofNullable(cd)
					.filter(cda -> cda.tableColumns().columns().equals(Optional.empty()) 
								|| cda.key().opkey().equals(Optional.empty()))
					.map(cda -> buildClassificationResult(cda.schemaTable().table()))
					.map(ocr -> ocr.get());
	}

	
	private  Optional<VoidTableClassificationResult> buildClassificationResult(Optional<Table> oTable) {
		return oTable.map(t -> new VoidTableClassificationResult(t.getFullName()));
	}

}
