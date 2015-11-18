package com.utc.api13.client;

import com.utc.api13.client.com.interfaces.InterfaceFromDataImpl;
import com.utc.api13.client.data.ClientToCommImpl;
import com.utc.api13.client.data.ClientToIHMImpl;
import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.commun.entities.UserEntity;

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

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		DataClientManager dataClientManager = new DataClientManager(new ClientToCommImpl(), new ClientToIHMImpl(),
				new InterfaceFromDataImpl(), new UserEntity());

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
}
