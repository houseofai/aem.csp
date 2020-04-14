package org.aem.csp.core;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Content Science Service Configuration")
public @interface ContentScienceConfiguration {
	
	@AttributeDefinition(
	        name = "URL",
	        description = "Jupyter Notebook URL",
	        type = AttributeType.STRING, required = true, defaultValue = "http://localhost:8888")
	String url();
	
	@AttributeDefinition(
	        name = "Token",
	        description = "Access Token",
	        type = AttributeType.STRING)
	String token();
}
