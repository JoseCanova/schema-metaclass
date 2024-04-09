package org.nanotek.meta.model;

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
