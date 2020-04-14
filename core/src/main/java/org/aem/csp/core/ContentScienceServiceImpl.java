package org.aem.csp.core;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = ContentScienceService.class)
@Designate(ocd = ContentScienceConfiguration.class)
public class ContentScienceServiceImpl implements ContentScienceService {

    private final Logger logger = LoggerFactory.getLogger(ContentScienceService.class);
	
    private ContentScienceConfiguration conf;

	@Activate
	public void activate(ContentScienceConfiguration conf) throws Exception {

		this.conf = conf;
	}

	@Override
	public String getUrl() {
		return conf.url();
	}
}
