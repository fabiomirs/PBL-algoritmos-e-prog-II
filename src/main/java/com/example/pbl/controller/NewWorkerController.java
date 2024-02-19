/**
 * Sample Skeleton for 'NewWorkerView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.model.Administrador;
import com.example.pbl.model.Bibliotecario;
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewWorkerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addressField"
    private TextField addressField; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="cargoField"
    private TextField cargoField; // Value injected by FXMLLoader

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
    void saveOperador(ActionEvent event) {
        // Verifica se os campos de entrada estão vazios
        if (nameField.getText().isEmpty() || cargoField.getText().isEmpty() || addressField.getText().isEmpty()) {
            situação.setText("Não foi possível criar o usuário: Preencha todos os campos!");
            return;
        }

        try {
            String nome = nameField.getText();
            String cargo = cargoField.getText();
            int address = Integer.parseInt(addressField.getText());

            if (cargo.equalsIgnoreCase("administrador")) {
                Administrador adm = DAO.getAdmDAO().create(new Administrador(nome, cargo, address));
                situação.setText("Administrador criado com sucesso!");
            } else if (cargo.equalsIgnoreCase("bibliotecario")) {
                Bibliotecario bibliotecario = DAO.getBibliotecarioDAO().create(new Bibliotecario(nome, cargo, address));
                situação.setText("Bibliotecário criado com sucesso!");
            } else {
                situação.setText("Cargo inválido! O cargo deve ser 'administrador' ou 'bibliotecario'.");
            }

            MainController.goBack();
        } catch (NumberFormatException e) {
            situação.setText("Não foi possível criar o Usuário: Senha deve ser um número!");
        }
    }

    /***
     * O método é chamado quando a inicialização do FXMLLoader é completa.
     */
    @FXML
    void initialize() {
        assert addressField != null : "fx:id=\"addressField\" was not injected: check your FXML file 'NewWorkerView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'NewWorkerView.fxml'.";
        assert cargoField != null : "fx:id=\"cargoField\" was not injected: check your FXML file 'NewWorkerView.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'NewWorkerView.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'NewWorkerView.fxml'.";

    }

}
