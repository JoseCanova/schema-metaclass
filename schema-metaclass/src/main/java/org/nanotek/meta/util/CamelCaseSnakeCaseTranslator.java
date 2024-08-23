package org.nanotek.meta.util;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A "better of worlds" camelToSnake and SnakeToCamel, 
 * it does handle (not designed for) with malformed camel or snake cases.
 */
public record CamelCaseSnakeCaseTranslator(String origin) implements Supplier<String>{

	public static String from(String origin) {
		return new CamelCaseSnakeCaseTranslator(origin).get();
	}
	
	public String get() {
		return camelToSnake();
	}
	
	private String camelToSnake() {
		return Optional
				.ofNullable(origin)
				.map(str1 -> {
					String name = str1;
					Pattern pattern = Pattern.compile("([A-Z]+)");
					Matcher matcher = pattern.matcher(name);
					while (matcher.find()) {
						int pos = matcher.start();
						String res = "";
						if (pos == 0) {
							 res = str1.substring(pos, pos+1).toLowerCase();
						}else {
							 res = "_" + str1.substring(pos, pos+1).toLowerCase();
						}
						String str2 =  name.replaceFirst("[A-Z]", res);
						name=str2;
					}
					return name;
				}).orElse(origin);
	}
	
}
