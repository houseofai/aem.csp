package org.aem.csp.core;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = ContentScienceService.class)
@Designate(ocd = ContentScienceServiceImpl.Config.class)
public class ContentScienceServiceImpl implements ContentScienceService {

	private final Logger log = LoggerFactory.getLogger(getClass());

    @ObjectClassDefinition(name = "Content Science Service Configuration")
    public static @interface Config {
        
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
    
    private String url;
    private String token;
    
    
	@Activate
	public void activate(Config conf) throws Exception {
		log.info("$$$ Configuration service: "+conf.url());
		this.url = conf.url();
		this.token = conf.token();
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public String getToken() {
		return this.token;
	}
}
