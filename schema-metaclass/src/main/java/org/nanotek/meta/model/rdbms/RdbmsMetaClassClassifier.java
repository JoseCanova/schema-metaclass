package org.nanotek.meta.model.rdbms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.meta.model.MetaRelationClass;
import org.nanotek.meta.model.rdbms.classification.MetaClassClassifier;

public class RdbmsMetaClassClassifier extends MetaClassClassifier<RdbmsMetaClass>  {

	private static final long serialVersionUID = -9021748714452535636L;

	protected boolean hasPrimaryKey;

	protected List<MetaRelationClass> metaRelationsClasses;
	
	public RdbmsMetaClassClassifier() {
		super();
		metaRelationsClasses = new ArrayList<MetaRelationClass>();
	}

	public boolean isHasPrimaryKey() {
		return hasPrimaryKey;
	}

	public void setHasPrimaryKey(boolean hasPrimaryKey) {
		this.hasPrimaryKey = hasPrimaryKey;
	}
	
	public void hasPrimaryKey(boolean b) {
		this.hasPrimaryKey = b;
	}

	public List<MetaRelationClass> getMetaRelationsClasses() {
		return metaRelationsClasses;
	}

	public void setMetaRelationsClasses(List<MetaRelationClass> metaRelationsClasses) {
		this.metaRelationsClasses = metaRelationsClasses;
	}
	
	public void addMetaRelationClass(MetaRelationClass mrc) {
		this.metaRelationsClasses.add(mrc);
		
	}

	//TODO: implement classification Class.
	@Override
	public <CR extends Base<?>> Optional<CR> classify(RdbmsMetaClass classified) {
		return null;
	}
	
}
