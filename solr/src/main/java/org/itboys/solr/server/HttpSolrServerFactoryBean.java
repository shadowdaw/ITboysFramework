package org.itboys.solr.server;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.itboys.solr.config.HttpSolrServerConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * 根据 HttpSolrServerConfig 创建 HttpSolrServer 的对象
 * @author Administrator
 *
 */
public class HttpSolrServerFactoryBean implements FactoryBean<HttpSolrServer> {

	private HttpSolrServerConfig httpSolrServerConfig;
	
	@Override
	public HttpSolrServer getObject() throws Exception {
		HttpSolrServer readServer = new HttpSolrServer(httpSolrServerConfig.getUrl());
		readServer.setSoTimeout(httpSolrServerConfig.getSoTimeout());
		readServer.setConnectionTimeout(httpSolrServerConfig.getConnectionTimeout());
		readServer.setDefaultMaxConnectionsPerHost(httpSolrServerConfig.getDefaultMaxConnectionsPerHost());
		readServer.setMaxTotalConnections(httpSolrServerConfig.getMaxTotalConnections());
		readServer.setFollowRedirects(httpSolrServerConfig.isFollowRedirects()); 
		readServer.setAllowCompression(httpSolrServerConfig.isAllowCompression());
		readServer.setMaxRetries(httpSolrServerConfig.getMaxRetries());
		return readServer;
	}

	@Override
	public Class<HttpSolrServer> getObjectType() {
		return HttpSolrServer.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setHttpSolrServerConfig(HttpSolrServerConfig httpSolrServerConfig) {
		this.httpSolrServerConfig = httpSolrServerConfig;
	}

}
