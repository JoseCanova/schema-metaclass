package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.TableTypeEnum;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoidTableClassificationTask implements TableClassificationTask<ClassificationData> {

	@Autowired
	protected SchemaCrawlerService schemaCrawlerService;
	
	public VoidTableClassificationTask() {
	}
	
	@Override
	public <CR extends ClassificationResult<CR>> Optional<CR> evaluate(ClassificationData cd) {
			 return Optional.ofNullable(cd)
					.filter(cda -> cda.tableColumns().columns().equals(Optional.empty()) 
								|| cda.key().opkey().equals(Optional.empty()))
					.map(cda -> buildClassificationResult());
	}

	
	private <CR extends ClassificationResult<CR>> CR buildClassificationResult() {
		// TODO Finish implementation of ClassificationResult.
		return null;
	}
}
