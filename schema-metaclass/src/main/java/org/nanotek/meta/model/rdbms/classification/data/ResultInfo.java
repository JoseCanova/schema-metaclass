package org.nanotek.meta.model.rdbms.classification.data;

import org.nanotek.meta.Informed;
import org.nanotek.meta.Resulted;

public record ResultInfo<R, I>(R resulted , I informed) implements Resulted<R> , Informed<I>{
	
	public ResultInfo (R resulted , I informed) {
		this.resulted = resulted;
		this.informed = informed;
	}
}
