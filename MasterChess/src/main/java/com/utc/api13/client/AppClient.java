package com.utc.api13.client;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.client.data.DataClientManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class AppClient extends Application {
	public static Stage stage;
//	private static final Logger LOGGER = Logger.getLogger(AppClient.class);

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

	public static void main(String[] args) {
		ComClientManager comClientManager = null;
		
		try {
			comClientManager = new ComClientManager("localhost", 80);
			
			DataClientManager dataClientManager = new DataClientManager();
			dataClientManager.setIClientToData(comClientManager.getClientToDataImpl());
			
			comClientManager.setIClientToComm(dataClientManager.getClientToCommImpl());
			
		} catch (InterruptedException e) {
			// TODO : Faire une vrai gestion d'erreur
			System.out.println("erreur"); 
		}
		finally{
			if (comClientManager != null){
				comClientManager.close();
			}
		}
	}
}
