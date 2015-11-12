package com.utc.api13; 

public class AppIHMController {
	//ajouter référence sur le model
	//& tous les éléments graphiques précédés de l'annotation @FXML
	//& tous les listeners
	ClientToIHMImpl clientToIHM ; //erreur normale, résolue à l'intégration avec les fichiers de Data
	
	public AppIHMController() {
	//initialiser les listeners et la référence sur le modele 
		clientToIHM = new ClientToIHMImpl(); 
	}
	
	public void initialize() {
		//bindings
	}
}