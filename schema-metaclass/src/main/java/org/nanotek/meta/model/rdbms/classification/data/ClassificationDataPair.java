package org.nanotek.meta.model.rdbms.classification.data;

import org.springframework.data.util.Pair;

public record ClassificationDataPair(Pair<ClassificationData,ClassificationData> classificationDataPair) {

	public ClassificationDataPair(Pair<ClassificationData,ClassificationData> classificationDataPair) {
		this.classificationDataPair=classificationDataPair;
	}
	
}
