package org.nanotek.meta.model.rdbms.classification.data;

public record Result<R, I>(R resultee , I info) {
	
	public Result (R resultee , I info) {
		this.resultee = resultee;
		this.info = info;
	}
}
