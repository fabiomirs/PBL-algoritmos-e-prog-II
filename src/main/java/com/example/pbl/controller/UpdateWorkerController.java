/**
 * Sample Skeleton for 'UpdateWorkerView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.AdmException;
import com.example.pbl.exceptions.BibliotecarioExcpetion;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Administrador;
import com.example.pbl.model.Bibliotecario;
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateWorkerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addressField"
    private TextField addressField; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField cargoField; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

    @FXML
    private Label situação;

    /***
     *
     * @param event
     */
    @FXML
    void cancelOperation(ActionEvent event) {
        MainController.goBack();
    }

    /***
     *
     * @param event
     */
    @FXML
    void salvarOperador(ActionEvent event) {
        if (idField.getText().isEmpty() || nameField.getText().isEmpty() || cargoField.getText().isEmpty() || addressField.getText().isEmpty()){
            situação.setText("Não foi possível atualizar os dados: Preencha todos os campos!");
            return;
        }
        try {
            int id = Integer.parseInt(idField.getText());
            int senha = Integer.parseInt(addressField.getText());

            if (cargoField.getText().equalsIgnoreCase("bibliotecario")) {
                Bibliotecario bibliotecario = DAO.getBibliotecarioDAO().buscarporId(id);
                if (bibliotecario != null) {
                    // Atualiza os dados do bibliotecário escolhido
                    bibliotecario.setNome(nameField.getText());
                    bibliotecario.setSenhaAcesso(senha);
                    DAO.getBibliotecarioDAO().update(bibliotecario);
                    situação.setText("Dados do bibliotecário atualizados com sucesso!");
                    MainController.goBack();
                } else {
                    situação.setText("Bibliotecário não encontrado.");
                }
            } else if (cargoField.getText().equalsIgnoreCase("administrador")) {
                Administrador administrador = DAO.getAdmDAO().buscarporId(id);
                if (administrador != null) {
                    // Atualiza os dados do administrador escolhido
                    administrador.setNome(nameField.getText());
                    administrador.setSenhaAcesso(senha);
                    DAO.getAdmDAO().update(administrador);
                    situação.setText("Dados do administrador atualizados com sucesso!");
                    MainController.goBack();
                } else {
                    situação.setText("Administrador não encontrado.");
                }
            } else {
                situação.setText("Cargo inválido! O cargo deve ser 'bibliotecario' ou 'administrador'.");
            }
        } catch (NumberFormatException e){
            situação.setText("O id e a senha devem ser valores numéricos.");
        } catch (BibliotecarioExcpetion | AdmException e) {
            situação.setText("Erro ao atualizar os dados do usuário: " + e.getMessage());
        }
    }

    /***
     * O método é chamado quando a inicialização do FXMLLoader é completa.
     */
    @FXML
    void initialize() {
        assert addressField != null : "fx:id=\"addressField\" was not injected: check your FXML file 'UpdateWorkerView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'UpdateWorkerView.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'UpdateWorkerView.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'UpdateWorkerView.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'UpdateWorkerView.fxml'.";

    }

}
