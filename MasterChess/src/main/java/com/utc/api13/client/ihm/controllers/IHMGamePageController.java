package com.utc.api13.client.ihm.controllers;

import javax.swing.SwingUtilities;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.models.ChessBoardNode;

import javafx.embed.swing.SwingNode;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class IHMGamePageController {
	private IHMManager IHMManager;
	private AppClient mainApp;
	private IClientDataToIHM myIClientToIHM;

	@FXML
	Label chatLabel, otherPlayerLoginLabel, otherPlayerTimeLabel, playerLoginLabel, playerTimeLabel,
			numberObserversLabel;
	@FXML
	ImageView iconChat, iconObservers;
	@FXML
	Button excludeChatButton, sendTextButton, abandonButton, quitGameButton;
	@FXML
	TextArea chatTextArea, sendTextArea;
	@FXML
	StackPane chessBoardStackPane;

	public IHMGamePageController() {
		initialize();
	}

	public void initialize() {

	}

	@FXML
	private void onExcludeChatClicked(Event event) {

	}

	@FXML
	private void onSendTextClicked(Event event) {

	}

	@FXML
	private void onAbandonClicked(Event event) {

	}

	@FXML
	private void onQuitGameClicked(Event event) {

	}

	public void setControllerContext(IHMManager ihmManager) {
		this.IHMManager = ihmManager;
		if (ihmManager != null)
			this.myIClientToIHM = IHMManager.getIClientDataToIHM();
		setListenersOnLoad();
		setBindingsOnLoad();
	}

	public void setListenersOnLoad() {

	}

	public void setBindingsOnLoad() {
	}

	public void setMainApp(AppClient app) {
		this.mainApp = app;
		final ChessBoardNode cb = new ChessBoardNode();

		/**
		 * JFrame f = new JFrame("Chess"); f.add(cb.getGui());
		 * f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 * f.setLocationByPlatform(true);
		 * 
		 * // ensures the frame is the minimum size it needs to be // in order
		 * display the components within it f.pack(); // ensures the minimum
		 * size is enforced. f.setMinimumSize(f.getSize()); f.setVisible(true);
		 */
		final SwingNode swingNode = new SwingNode();

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				swingNode.setContent(cb.getGui());
			}
		});
		chatTextArea.setDisable(true);

		chessBoardStackPane.getChildren().add(swingNode);

	}

}
