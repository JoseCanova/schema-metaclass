package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class FirstNormalFormTableClassificationTask implements TableClassificationTask<ClassificationDataPair> {

	@Autowired
	private VoidTableClassificationTask voidTableClassificationTask ;
	
	public FirstNormalFormTableClassificationTask() {}
	
	@Override
	public Optional<ClassificationResult> evaluate(ClassificationDataPair cd) {
		Pair<ClassificationData,ClassificationData> cdPair = cd.classificationDataPair();
		Optional<ClassificationResult> ocr1 = voidTableClassificationTask.evaluate(cdPair.getFirst());
		Optional<ClassificationResult> ocr2 = voidTableClassificationTask.evaluate(cdPair.getSecond());
		Optional<Boolean> preConditionResult = verifyPreCondition(ocr1 , ocr2);
		return null;
	}

	private Optional<Boolean> verifyPreCondition(Optional<ClassificationResult> ocr1, Optional<ClassificationResult> ocr2) {
		return Optional.of(ocr1.isEmpty() && ocr2.isEmpty());
	}
}
