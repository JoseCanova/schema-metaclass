package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.rdbms.service.RdbmsSchemaMetaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoidTableClassificationTask implements TableClassificationTask {

	@Autowired
	protected RdbmsSchemaMetaClassService rdbmsSchemaMetaClassService;
	
	public VoidTableClassificationTask() {
	}
	
	@Override
	public <CR extends Base<?>> Optional<CR> evaluate(ClassificationData cd) {
		return TableClassificationTask.super.evaluate(cd);
	}
}
