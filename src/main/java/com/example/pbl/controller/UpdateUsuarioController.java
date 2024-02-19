/**
 * Sample Skeleton for 'UpdateUsuarioView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateUsuarioController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addressField"
    private TextField telefoneField; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField enderecoField; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="phoneField"
    private TextField statusField; // Value injected by FXMLLoader

    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

    @FXML
    private Label situação;

    @FXML
    void cancelOperation(ActionEvent event) {
        MainController.goBack();
    }

    @FXML
    void saveClient(ActionEvent event) {
        if (idField.getText().isEmpty() || nameField.getText().isEmpty() || enderecoField.getText().isEmpty() || telefoneField.getText().isEmpty()
        || statusField.getText().isEmpty()) {
            situação.setText("Não foi possível atualizar os dados: Preencha todos os campos!");
            return;
        }
        try {
            int id = Integer.parseInt(idField.getText());
            int telefone = Integer.parseInt(telefoneField.getText());

            Usuario userEscolhido = DAO.getUsuarioDAO().buscarporId(id);

            userEscolhido.setTelefone(telefone);
            userEscolhido.setNome(nameField.getText());
            userEscolhido.setNumIdentificacao(id);
            userEscolhido.setStatusConta(statusField.getText());
            userEscolhido.setEndereco(enderecoField.getText());

            DAO.getUsuarioDAO().update(userEscolhido);

            situação.setText("Dados atualizados com sucesso!");
            MainController.goBack();

        } catch (NumberFormatException e){
            situação.setText("O id deve ser um valor numérico.");
        } catch (UsuarioException e) {
            situação.setText("Usuário não foi encontrado.");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'UpdateUsuarioView.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'UpdateUsuarioView.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'UpdateUsuarioView.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'UpdateUsuarioView.fxml'.";

    }

}
