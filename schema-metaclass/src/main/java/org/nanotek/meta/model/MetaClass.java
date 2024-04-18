package org.nanotek.meta.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * TODO: refactor code of definition for IdPrimaryKey classification.
 */
@Document
@JsonInclude(value = Include.NON_NULL)
public class MetaClass<K extends MetaClass<K>> extends MetaBase<K,String>  implements IClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730971114783577367L;

	@JsonProperty("className")
	protected String className; 
	
	protected List<MetaClassAttribute> metaAttributes = new ArrayList<>();

	@JsonIgnore
	protected MetaClassClassifier classifier;
	
	/*
	 * @JsonIgnore private boolean hasPrimraryKey;
	 * 
	 * @JsonIgnore protected List<MetaRelationClass> metaRelationsClasses;
	 */
	protected MetaIdentity identity;
	
	public MetaClass() {
		super();
		classifier = new MetaClassClassifier ();
	}

	public MetaClass(String className, 
			List<MetaClassAttribute> metaAttributes) {
		super();
		this.className = className;
		this.metaAttributes = metaAttributes;
		classifier = new MetaClassClassifier ();
	}

	
	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public List<MetaClassAttribute> getMetaAttributes() {
		return metaAttributes;
	}

	@Override
	public boolean  addMetaAttribute(MetaClassAttribute attr) {
		return metaAttributes.add(attr);
	}

	public boolean isHasPrimaryKey() {
		return  metaAttributes !=null && metaAttributes.stream().filter(a -> a.isId()).count() > 0;
	}
	
	public void addMetaRelationClass(MetaRelationClass mrc) {
		this.classifier.addMetaRelationClass(mrc);
		
	}

	@JsonIgnore
	public List<MetaRelationClass> getMetaRelationsClasses() {
		return this.classifier.getMetaRelationsClasses();
	}

	public MetaIdentity getIdentity() {
		return identity;
	}

	public void setIdentity(MetaIdentity identity) {
		this.identity = identity;
	}

	public void setMetaAttributes(List<MetaClassAttribute> metaAttributes) {
		this.metaAttributes = metaAttributes;
	}

	public void setMetaRelationsClasses(List<MetaRelationClass> metaRelationsClasses) {
		this.classifier.setMetaRelationsClasses(metaRelationsClasses);
	}
	
	public MetaClassClassifier getClassifier() {
		return classifier;
	}

	public void setClassifier(MetaClassClassifier classifier) {
		this.classifier = classifier;
	}
}
