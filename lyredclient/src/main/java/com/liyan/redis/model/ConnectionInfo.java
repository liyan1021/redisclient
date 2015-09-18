package com.liyan.redis.model;

public class ConnectionInfo {
	
	private String connectionName ; 
	private String host ; 
	private int port ; 
	private String auth ;
	
	public String getConnectionName() {
		return connectionName;
	}
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	@Override
	public String toString() {
		return "ConnectionInfo [connectionName=" + connectionName + ", host=" + host + ", port=" + port + ", auth="
				+ auth + "]";
	}
	
	
}
