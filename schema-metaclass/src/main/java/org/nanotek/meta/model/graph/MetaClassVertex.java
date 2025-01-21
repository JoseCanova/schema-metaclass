package org.nanotek.meta.model.graph;

import org.nanotek.meta.model.IClass;
import org.nanotek.meta.model.IDataAttribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaClassVertex {

	IClass metaClass; 
	
	IDataAttribute metaAttribute;
	
}
