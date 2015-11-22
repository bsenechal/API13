package com.utc.api13.client.ihm;

import com.utc.api13.client.data.interfaces.IClientDataToIHM;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IHMManager {

	private IClientDataToIHM myIClientToIHM ;
	private ClientIHMToDataImpl myClientIHMToDataImpl; 
	
	
	public IHMManager () 
	{
		myClientIHMToDataImpl = new ClientIHMToDataImpl(this);
	}
	
	
	public IClientDataToIHM getIClientDataToIHM()
	{
		return this.myIClientToIHM;
	}

	
	public void setIClientDataToIHM(IClientDataToIHM dataInterface)
	{
		myIClientToIHM = dataInterface;
	}
	
	public ClientIHMToDataImpl getClientIHMToDataImpl()
	{
		return myClientIHMToDataImpl;
	}
	
}
