package org.aem.csp.core.workflow;

import org.aem.csp.core.NotebookManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.wcm.api.Page;

@Component(service = WorkflowProcess.class, property = { "process.label=Content Science notebook Sync" })
public class NotebookSync implements WorkflowProcess {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Reference
	private NotebookManager nbm;

	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {

		log.info("### Launching Content Science Workflow");
		ResourceResolver resourceResolver = session.adaptTo(ResourceResolver.class);

		String path = item.getWorkflowData().getPayload().toString();
		Resource resource = resourceResolver.getResource(path);
		Page page = resource.adaptTo(Page.class);
		
		String name = page.getName();
		log.info("### Creating page "+name);
		
		try {
			nbm.create("", name);
		} catch (Exception e) {
			log.error("Error on creating notebook: ", e);
			e.printStackTrace();
		}
	}
}