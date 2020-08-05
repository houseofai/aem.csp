package org.aem.csp.core.models;

import javax.annotation.PostConstruct;
import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import org.aem.csp.core.ContentScienceService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class)
public class ContentScienceModel {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@ValueMapValue(name = PROPERTY_RESOURCE_TYPE, injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(values = "No resourceType")
	protected String resourceType;

	@OSGiService
	private ContentScienceService csService;

    @PostConstruct
    protected void init() {
    	log.info("##### Content Science:"+csService.getUrl());
    	log.info("##### Resource type:"+resourceType);
	}

	public String getUrl() {
		return csService.getUrl();
	}
}
