package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Collection;
import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.FirstNormalFormClassificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.Table;

@Component
public class FirstNormalFormClassificationTask implements TableClassificationTask<ClassificationDataPair> {

	@Autowired
	private VoidTableClassificationTask voidTableClassificationTask ;
	
	public FirstNormalFormClassificationTask() {}
	
	@Override
	public Optional<FirstNormalFormClassificationResult> evaluate(ClassificationDataPair cd) {
		Pair<ClassificationData,ClassificationData> cdPair = cd.classificationDataPair();
		Optional<ClassificationResult> ocr1 = voidTableClassificationTask.evaluate(cdPair.getFirst());
		Optional<ClassificationResult> ocr2 = voidTableClassificationTask.evaluate(cdPair.getSecond());
		Optional<Boolean> preConditionResult = verifyPreCondition(ocr1 , ocr2);
		preConditionResult
		.filter(r->r == false)
		.isPresent();
		return verifyFirstNormalForm(cd);
		
	}

	//TODO:Refactor method for return value
	private  Optional<FirstNormalFormClassificationResult> verifyFirstNormalForm(ClassificationDataPair cdp) {
		Pair<ClassificationData,ClassificationData> cdp1 = cdp.classificationDataPair();
		Optional<Table> firstTable = cdp1
										.getFirst()
										.key()
										.opkey()
										.map(k->k.getParent());
		 Optional<Collection<ForeignKey>> oColKey = cdp1.getSecond().foreignKeys().keys();
		 if (oColKey.isPresent() && oColKey
					.filter(c ->c.size()>0).isPresent()) {
		 return oColKey
		.filter(c ->c.size()>0)
		.get()
		.stream()
		.filter(fk -> fk.getReferencingTable().getFullName().equals(firstTable.get().getFullName()))
		.map(fk ->new FirstNormalFormClassificationResult(fk.getParent().getFullName(),firstTable.get().getFullName())).findAny();
		 }else 
			 return Optional.empty();
	}

	private Optional<Boolean> verifyPreCondition(Optional<ClassificationResult> ocr1, Optional<ClassificationResult> ocr2) {
		return Optional.of(ocr1.isEmpty() && ocr2.isEmpty());
	}
}
