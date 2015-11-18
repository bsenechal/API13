package com.utc.api13.client.ihm;

import com.utc.api13.client.data.interfaces.IClientToIHM;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IHMManager extends Application {
	public static Stage stage;
	private IClientToIHM myIClientToIHM ;
	private IHMFromDataImpl myIHMFromDataImpl; 
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		// ici, loader tous les fichiers FXML
		// on utilise un FXML par écran
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/welcomePage.fxml"));

		Pane root = (Pane) fxmlLoader.load();

		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("/css/masterCSS.css").toExternalForm());
		stage.setTitle("Traduction");
		stage.setScene(scene);
		stage.show();
	}
	
	
	public IHMManager () 
	{
		myIHMFromDataImpl = new IHMFromDataImpl(this);
	}
	
	public void launchAppIHM(String[] args)
	{
		launch(args);
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
