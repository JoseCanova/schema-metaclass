package org.nanotek.meta.util;

import org.springframework.stereotype.Service;

@Service
public class ColumnNameTranslationStrategy {

	
	public String processNameTranslationStrategy(String name) {
		String newName = name.replaceAll("\\_[$&%.]+", "_");
		return newName;
	}
}
