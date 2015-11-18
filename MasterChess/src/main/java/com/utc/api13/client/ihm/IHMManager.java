package com.utc.api13.client.ihm;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientToIHM;

public class IHMManager {
	
	private IClientToIHM myIClientToIHM ;
	private AppClient myAppClient;
	private IHMFromDataImpl myIHMFromDataImpl; 
	
	public IHMManager () 
	{
		myAppClient = new AppClient();
		myIHMFromDataImpl = new IHMFromDataImpl(this);
	}
	
	public void LaunchAppIHM(String[] args)
	{
		myAppClient.launch(args);
	}
	
	public IClientToIHM getClientToIHM()
	{
		return this.myIClientToIHM;
	}
	
	public AppClient getAppClient()
	{
		return this.myAppClient;
	}
	
	public void setClientToIHM(IClientToIHM dataInterface)
	{
		myIClientToIHM = dataInterface;
	}
	
	public IHMFromDataImpl getIHMFromDataImpl()
	{
		return myIHMFromDataImpl;
	}
	
}
