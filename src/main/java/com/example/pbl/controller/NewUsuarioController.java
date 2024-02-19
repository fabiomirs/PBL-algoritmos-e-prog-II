/**
 * Sample Skeleton for 'NewUsuarioView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.model.Livro;
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewUsuarioController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField enderecoField; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

    @FXML // fx:id="statusField"
    private TextField statusField; // Value injected by FXMLLoader

    @FXML // fx:id="teleField"
    private TextField teleField; // Value injected by FXMLLoader

    @FXML
    private TextField idField;

    @FXML
    private Label idlabel;

    @FXML
    private Label situação;

    @FXML
    void cancelOperation(ActionEvent event) {
        MainController.goBack();
    }

    @FXML
    void salvarUser(ActionEvent event) {
        // Verifica se os campos de entrada estão vazios
        if (nameField.getText().isEmpty() || enderecoField.getText().isEmpty() || statusField.getText().isEmpty() ||
                teleField.getText().isEmpty() || idField.getText().isEmpty() ) {
            situação.setText("Não foi possível criar o usuário: Preencha todos os campos!");
        }

        try {
            int idnum = Integer.parseInt(idField.getText());

            int numTel = Integer.parseInt(teleField.getText());

            Usuario usuario = DAO.getUsuarioDAO().create(new Usuario(nameField.getText(),enderecoField.getText(), Integer.parseInt(teleField.getText()),
                    Integer.parseInt(idField.getText()),statusField.getText() ));

            situação.setText("Usuário criado com sucesso!");
            MainController.goBack();
        } catch (NumberFormatException e) {
            situação.setText("Não foi possível criar o Usuário!");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'NewUsuarioView.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'NewUsuarioView.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'NewUsuarioView.fxml'.";
        assert statusField != null : "fx:id=\"statusField\" was not injected: check your FXML file 'NewUsuarioView.fxml'.";
        assert teleField != null : "fx:id=\"teleField\" was not injected: check your FXML file 'NewUsuarioView.fxml'.";

    }

}
