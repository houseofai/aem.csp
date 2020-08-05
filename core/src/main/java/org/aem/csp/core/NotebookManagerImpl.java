package org.aem.csp.core;

import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = NotebookManager.class)
public class NotebookManagerImpl implements NotebookManager {

	private static String NB_EXT = ".ipynb";
	private static String API = "/api/contents";

	private static final Logger log = LoggerFactory.getLogger(NotebookManagerImpl.class);

	@Reference
	private ContentScienceService csService;
	
	private CloseableHttpClient client = HttpClientBuilder.create().build();

	@Override
	public void create(String path, String name) throws Exception  {
		
		String url = getFullUrl("");
		log.info("Creating notebook {}", url);
		
		HttpPost post = new HttpPost(url);

		JSONObject params = new JSONObject();
		params.put("type", "notebook");
		post.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));

		HttpResponse response = client.execute(post);
		
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);

		JSONObject json_response = new JSONObject(content);
		String oldNbName = json_response.getString("name");
		
		rename(".", oldNbName, name);
	}
	
	public void rename(String path, String oldNbName, String newName) throws Exception {

		// Rename the notebook
		String patchUrl = getFullUrl(oldNbName);
		HttpPatch patch = new HttpPatch(patchUrl);
		
		JSONObject params = new JSONObject();
		params.put("name", newName+NB_EXT);
		params.put("path", path+"/"+newName+NB_EXT);
		patch.setEntity(new StringEntity(params.toString(), StandardCharsets.UTF_8));
		
		HttpResponse response = client.execute(patch);
	}

	private String getFullUrl(String path) {
		return getBaseUrl()+path+"?"+getTokenParam();
	}
	
	private String getBaseUrl() {
		return csService.getUrl()+API+"/";
	}
	
	private String getTokenParam() {
		return "token="+csService.getToken();
	}
}
