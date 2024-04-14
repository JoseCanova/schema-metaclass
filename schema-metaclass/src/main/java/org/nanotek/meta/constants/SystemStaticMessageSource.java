package org.nanotek.meta.constants;

import java.util.Locale;

import org.springframework.context.support.StaticMessageSource;

public class SystemStaticMessageSource extends StaticMessageSource{

	public static String NONOK = "NONOK";
	
	public SystemStaticMessageSource() {
		super();
		addMessage(NONOK, Locale.US, "General System Error");
	}
	
}
