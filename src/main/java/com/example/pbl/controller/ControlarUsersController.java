/**
 * Sample Skeleton for 'ControlarUsersView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControlarUsersController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="attOperaButton"
    private Button attOperaButton; // Value injected by FXMLLoader

    @FXML // fx:id="atualizaLeitor"
    private Button atualizaLeitor; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="cadLeitor"
    private Button cadLeitor; // Value injected by FXMLLoader

    @FXML // fx:id="cadastrarOpera"
    private Button cadastrarOpera; // Value injected by FXMLLoader

    @FXML // fx:id="removeLeitorButton"
    private Button removeLeitorButton; // Value injected by FXMLLoader

    @FXML // fx:id="removeOperador"
    private Button removeOperador; // Value injected by FXMLLoader

    @FXML
    void attLeitor(ActionEvent event) throws IOException {
        MainController.gotoScene("/UpdateUsuarioView.fxml");
    }

    @FXML
    void attOperador(ActionEvent event) throws IOException {
        MainController.gotoScene("/UpdateWorkerView.fxml");
    }

    @FXML
    void cadastrarLeitor(ActionEvent event) throws IOException {
        MainController.gotoScene("/NewUsuarioView.fxml");
    }

    @FXML
    void cadastrarOperador(ActionEvent event) throws IOException {
        MainController.gotoScene("/NewWorkerView.fxml");
    }

    @FXML
    void removeLeitor(ActionEvent event) throws IOException {
        MainController.gotoScene("/RemoverUsuarioView.fxml");
    }

    @FXML
    void removeOperadorOperation(ActionEvent event) throws IOException {
        MainController.gotoScene("/RemoverWorkerView.fxml");
    }

    @FXML
    void voltar(ActionEvent event) {
        MainController.goBack();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert attOperaButton != null : "fx:id=\"attOperaButton\" was not injected: check your FXML file 'ControlarUsersView.fxml'.";
        assert atualizaLeitor != null : "fx:id=\"atualizaLeitor\" was not injected: check your FXML file 'ControlarUsersView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ControlarUsersView.fxml'.";
        assert cadLeitor != null : "fx:id=\"cadLeitor\" was not injected: check your FXML file 'ControlarUsersView.fxml'.";
        assert cadastrarOpera != null : "fx:id=\"cadastrarOpera\" was not injected: check your FXML file 'ControlarUsersView.fxml'.";
        assert removeLeitorButton != null : "fx:id=\"removeLeitorButton\" was not injected: check your FXML file 'ControlarUsersView.fxml'.";
        assert removeOperador != null : "fx:id=\"removeOperador\" was not injected: check your FXML file 'ControlarUsersView.fxml'.";

    }

}
