<<<<<<< HEAD:MasterChess/src/main/java/com/utc/api13/client/App.java
package com.utc.api13.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	public static Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		//ici, loader tous les fichiers FXML
		//on utilise un FXML par écran 
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ressourcesIHM/FXML/????.fxml"));

		Pane root = (Pane) fxmlLoader.load();
		
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("joli2.css").toExternalForm());
		stage.setTitle("Traduction");
		stage.setScene(scene);
		stage.show();
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
=======
package com.utc.api13;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	public static Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		//ici, loader tous les fichiers FXML
		//on utilise un FXML par écran 
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ressourcesIHM/FXML/????.fxml"));

		Pane root = (Pane) fxmlLoader.load();
		
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("joli2.css").toExternalForm());
		stage.setTitle("Traduction");
		stage.setScene(scene);
		stage.show();
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
>>>>>>> refs/remotes/origin/master:MasterChess/src/main/java/com/utc/api13/App.java
