package org.nanotek.meta.model;

import java.util.ArrayList;
import java.util.List;

import org.nanotek.Base;
import org.nanotek.meta.validation.MetaClassDefaultValidationGroup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@MappedSuperclass
@JsonInclude(value = Include.NON_NULL)
@AllArgsConstructor
public class MetaClass<K extends MetaClass<K,C,T> , C extends Classifier<?>,T extends MetaClassAttribute<?>> extends MetaBase<K,String>  implements IClass {

	private static final long serialVersionUID = -6730971114783577367L;

	@Id
	private String id;
	
	@JsonProperty("className")
	@NotEmpty(groups= {MetaClassDefaultValidationGroup.class})
	protected String className; 
	
	@NotEmpty(groups= {MetaClassDefaultValidationGroup.class})
	protected List<T> metaAttributes = new ArrayList<>();

	@JsonIgnore
	@NotNull(groups= {MetaClassDefaultValidationGroup.class})
	protected C classifier;
	
	@NotNull(groups= {MetaClassDefaultValidationGroup.class})
	protected MetaIdentity identity;
	
	public MetaClass() {
		super();
	}

	public MetaClass(String id) {
		super(id);
	}

	
	public MetaClass(@NotEmpty(groups = MetaClassDefaultValidationGroup.class) String className,
			@NotEmpty(groups = MetaClassDefaultValidationGroup.class) List<T> metaAttributes,
			@NotNull(groups = MetaClassDefaultValidationGroup.class) MetaIdentity identity) {
		super();
		this.className = className;
		this.metaAttributes = metaAttributes;
		this.identity = identity;
		postConstruct();
	}

//	public MetaClass(String className, 
//			List<T> metaAttributes) {
//		super();
//		this.className = className;
//		this.metaAttributes = Optional.ofNullable(metaAttributes).orElse(new ArrayList<>());
//		postConstruct();
//	}

	
	@SuppressWarnings("unchecked")
	protected void postConstruct() {
		this.classifier = (@NotNull(groups = MetaClassDefaultValidationGroup.class) C) Base.newInstance(MetaClassClassifier.class).get();
	}
	
	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	public List<T> getMetaAttributes() {
		return metaAttributes;
	}

	public boolean  addMetaAttribute(T attr) {
		return metaAttributes.add(attr);
	}

	/*
	 * public void addMetaRelationClass(MetaRelationClass mrc) {
	 * this.classifier.addMetaRelationClass(mrc);
	 * 
	 * }
	 * 
	 * @JsonIgnore public List<MetaRelationClass> getMetaRelationsClasses() { return
	 * this.classifier.getMetaRelationsClasses(); }
	 */
	public MetaIdentity getIdentity() {
		return identity;
	}

	public void setIdentity(MetaIdentity identity) {
		this.identity = identity;
	}

	public void setMetaAttributes(List<T> metaAttributes) {
		this.metaAttributes = metaAttributes;
	}


	public C getClassifier() {
		return classifier;
	}

	public void setClassifier(C classifier) {
		this.classifier = classifier;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
