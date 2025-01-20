package org.nanotek.meta.model.rdbms.classification.data;

import java.io.Serializable;
import java.util.List;

import org.nanotek.meta.model.IdBase;

public class ClassificationResultReport<K extends ClassificationResult<K> , ID extends Serializable> implements IdBase<K , ID> {

	private static final long serialVersionUID = -6715722546481228677L;

	private ID id; 
	
	private String tableName; 
	
	private List<K> classificationResult;
	
	@Override
	public ID getId() {
		return id;
	}

}
