package com.utc.api13.client.ihm.controllers;

import java.io.IOException;

import javax.swing.JFrame;

import com.utc.api13.client.ihm.ClientToIHMImpl;
import com.utc.api13.client.ihm.models.ChessBoardNode;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IHMGamePageController {
	ClientToIHMImpl clientToIHM ;
	@FXML
	Label chatLabel, otherPlayerLoginLabel, otherPlayerTimeLabel, playerLoginLabel, playerTimeLabel, numberObserversLabel;
	@FXML
	ImageView iconChat, iconObservers;
	@FXML
	Button excludeChatButton, sendTextButton, abandonButton, quitGameButton;
	@FXML
	TextArea chatTextArea, sendTextArea;
	@FXML
	Pane chessBoardPane;
	
	public IHMGamePageController() { 
		clientToIHM = new ClientToIHMImpl(); 
		initialize(); 
	}
	
	public void initialize() {
		ChessBoardNode cb =new ChessBoardNode();
		JFrame f = new JFrame("ChessChamp");
        f.add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
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
	
}
