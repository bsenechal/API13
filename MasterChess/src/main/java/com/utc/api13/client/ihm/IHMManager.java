package com.utc.api13.client.ihm;

import com.utc.api13.client.data.interfaces.IClientToIHM;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IHMManager {

	private IClientToIHM myIClientToIHM ;
	private IHMFromDataImpl myIHMFromDataImpl; 
	
	
	public IHMManager () 
	{
		myIHMFromDataImpl = new IHMFromDataImpl(this);
	}
	
	
	public IClientToIHM getClientToIHM()
	{
		return this.myIClientToIHM;
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
