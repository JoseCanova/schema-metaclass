package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.collections4.Predicate;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.result.FirstNormalFormClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.result.VoidTableClassificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.Table;

//TODO: implement verification of "duplicate values" m which means scan on indexes that will not allow 
//duplicate values.
//@Component
public class FirstNormalFormClassificationTask implements TableClassificationTask<ClassificationDataPair , FirstNormalFormClassificationResult> {

	@Autowired
	private VoidTableClassificationTask voidTableClassificationTask ;
	
	public FirstNormalFormClassificationTask() {}
	
	@Override
	public  Optional<FirstNormalFormClassificationResult> evaluate(ClassificationDataPair cd) {
		Pair<ClassificationData,ClassificationData> cdPair = cd.classificationDataPair();
		Optional<VoidTableClassificationResult> ocr1 = voidTableClassificationTask.evaluate(cdPair.getFirst());
		Optional<VoidTableClassificationResult> ocr2 = voidTableClassificationTask.evaluate(cdPair.getSecond());
		Pair<Optional<VoidTableClassificationResult>, Optional<VoidTableClassificationResult>> voidResultPair= Pair.of(ocr1, ocr2);
		
		return Optional
			.ofNullable(ClassificationResultPairPredicate.of().evaluate(voidResultPair))
			.filter(rp -> rp==true)
			.map(r -> verifyFirstNormalForm(cd)).orElse(Optional.empty());
	}

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
		.filter(fk -> fk.getPrimaryKeyTable().getFullName().equals(firstTable.get().getFullName()))
		.map(fk ->new FirstNormalFormClassificationResult(fk.getParent().getFullName(),firstTable.get().getFullName() , fk.getConstrainedColumns())).findAny();
		 }else 
			 return Optional.empty();
	}

}

class ClassificationResultPairPredicate implements Predicate<Pair<Optional<VoidTableClassificationResult>, Optional<VoidTableClassificationResult>>>{

	private ClassificationResultPairPredicate() {}
	
	
	
	public static ClassificationResultPairPredicate of() {
		return new ClassificationResultPairPredicate();
	}


	@Override
	public boolean evaluate(Pair<Optional<VoidTableClassificationResult>, Optional<VoidTableClassificationResult>> pair) {
		return pair
				.getFirst().isEmpty() && pair.getSecond().isEmpty();
	}
	
}