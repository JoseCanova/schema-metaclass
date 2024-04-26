package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoidTableClassificationTask implements TableClassificationTask {

	@Autowired
	protected SchemaCrawlerService schemaCrawlerService;
	
	public VoidTableClassificationTask() {
	}
	
	@Override
	public <CR extends Base<?>> Optional<CR> evaluate(ClassificationData cd) {
		return Optional.ofNullable(cd).map(c -> {
					if (cd.tableColumns().columns().equals(Optional.empty()) 
							|| cd.key().opkey().equals(Optional.empty())) 
						return null;
					else 
						return null;
				});
	}
}
