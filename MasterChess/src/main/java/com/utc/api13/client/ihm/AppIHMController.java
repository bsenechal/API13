package com.utc.api13.client.ihm; 

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import com.utc.api13.client.data.interfaces.ClientToIHMImpl;

public class AppIHMController {
	//ajouter référence sur le model
	//& tous les éléments graphiques précédés de l'annotation @FXML
	//& tous les listeners
	ClientToIHMImpl clientToIHM ; //erreur normale, résolue à l'intégration avec les fichiers de Data
	@FXML
	BorderPane mainBorderPane; 
	
	public AppIHMController() {
	//initialiser les listeners et la référence sur le modele 
		clientToIHM = new ClientToIHMImpl(); 
	}
	
	public void initialize() {
		//bindings
	}
}