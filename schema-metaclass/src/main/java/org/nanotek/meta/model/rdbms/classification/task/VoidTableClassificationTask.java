package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.TableTypeEnum;
import org.nanotek.meta.model.rdbms.classification.data.VoidTableClassificationResult;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Table;

@Component
public class VoidTableClassificationTask implements TableClassificationTask<ClassificationData> {

	@Autowired
	protected SchemaCrawlerService schemaCrawlerService;
	
	public VoidTableClassificationTask() {
	}
	
	@Override
	public Optional<ClassificationResult<?>> evaluate(ClassificationData cd) {
			 return Optional.ofNullable(cd)
					.filter(cda -> cda.tableColumns().columns().equals(Optional.empty()) 
								|| cda.key().opkey().equals(Optional.empty()))
					.map(cda -> buildClassificationResult(cda.schemaTable().table()))
					.map(ocr -> ocr.get())
					.map(cr ->  ClassificationResult.class.cast(cr));
	}

	
	private  Optional<ClassificationResult<?>> buildClassificationResult(Optional<Table> oTable) {
		return oTable.map(t -> new VoidTableClassificationResult(t.getFullName()));
	}

}
