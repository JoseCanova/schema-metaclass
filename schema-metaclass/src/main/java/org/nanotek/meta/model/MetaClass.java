package org.nanotek.meta.model;

import java.util.ArrayList;
import java.util.List;

import org.nanotek.meta.validation.MetaClassDefaultValidationGroup;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/*
 * TODO: refactor code of definition for IdPrimaryKey classification.
 */
@Document
@JsonInclude(value = Include.NON_NULL)
public class MetaClass<K extends MetaClass<K,C> , C extends Classifier<?>> extends MetaBase<K,String>  implements IClass {

	private static final long serialVersionUID = -6730971114783577367L;

	@JsonProperty("className")
	@NotEmpty(groups= {MetaClassDefaultValidationGroup.class})
	protected String className; 
	
	@NotEmpty(groups= {MetaClassDefaultValidationGroup.class})
	protected List<MetaClassAttribute> metaAttributes = new ArrayList<>();

	@JsonIgnore
	@NotNull(groups= {MetaClassDefaultValidationGroup.class})
	protected C classifier;
	
	@NotNull(groups= {MetaClassDefaultValidationGroup.class})
	protected MetaIdentity identity;
	
	public MetaClass() {
		super();
	}

	public MetaClass(String className, 
			List<MetaClassAttribute> metaAttributes) {
		super();
		this.className = className;
		this.metaAttributes = metaAttributes;
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

	public void setMetaAttributes(List<MetaClassAttribute> metaAttributes) {
		this.metaAttributes = metaAttributes;
	}

}
