package org.aem.csp.core.workflow;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.dam.api.Asset;

@Component(service = WorkflowProcess.class, property = { "process.label=Machine Learning Inference" })
public class MLInferenceWorkflow implements WorkflowProcess {


    private static final Logger log = LoggerFactory.getLogger(MLInferenceWorkflow.class);

//    @Reference
//    private AwsS3Service s3;  
    
    @Override
    public final void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {


		String path = item.getWorkflowData().getPayload().toString();
		log.info("payload: " + path);
		System.out.println("Payload:"+path);
		ResourceResolver resourceResolver = session.adaptTo(ResourceResolver.class);
		//AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
		
		Resource resource = resourceResolver.getResource(path);
		Asset asset = resource.adaptTo(Asset.class);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://127.0.0.1:5000/infer?data=/we-retail/en/products/apparel/coats/Coats_women.jpg");
		try {
			httpClient.execute(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		try {
//			s3.addAssets(asset);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

    }
}
