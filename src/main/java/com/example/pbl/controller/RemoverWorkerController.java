/**
 * Sample Skeleton for 'RemoverWorkerView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.AdmException;
import com.example.pbl.exceptions.BibliotecarioExcpetion;
import com.example.pbl.model.Administrador;
import com.example.pbl.model.Bibliotecario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RemoverWorkerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ConfirmarButton"
    private Button ConfirmarButton; // Value injected by FXMLLoader

    @FXML // fx:id="IdText"
    private Label IdText; // Value injected by FXMLLoader

    @FXML // fx:id="SenhaLabel"
    private Label SenhaLabel; // Value injected by FXMLLoader

    @FXML // fx:id="SenhaText"
    private Label SenhaText; // Value injected by FXMLLoader

    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="cargoLabel"
    private Label cargoLabel; // Value injected by FXMLLoader

    @FXML // fx:id="cargoText"
    private Label cargoText; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="idField1"
    private TextField idField1; // Value injected by FXMLLoader

    @FXML // fx:id="idLabel"
    private Label idLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nameLabel"
    private Label nameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nomeText"
    private Label nomeText; // Value injected by FXMLLoader

    @FXML // fx:id="situação"
    private Label situação; // Value injected by FXMLLoader

    /***
     *
     * @param event
     */
    @FXML
    void Confirmar(ActionEvent event) {
        // Verifica se os campos de entrada estão vazios
        if (idField.getText().isEmpty() || idField1.getText().isEmpty()) {
            situação.setText("Não foi possível remover o usuário: Preencha todos os campos!");
            return;
        }

        try {
            int id = Integer.parseInt(idField.getText());
            String cargo = idField1.getText();

            if (cargo.equalsIgnoreCase("administrador")) {
                Administrador adm = DAO.getAdmDAO().buscarporId(id);
                DAO.getAdmDAO().delete(adm);
                situação.setText("Administrador excluido com sucesso!");
                MainController.goBack();
            } else if (cargo.equalsIgnoreCase("bibliotecario")) {
                Bibliotecario bibliotecario = DAO.getBibliotecarioDAO().buscarporId(id);
                DAO.getBibliotecarioDAO().delete(bibliotecario);
                situação.setText("Bibliotecário excluido com sucesso!");
                MainController.goBack();
            } else {
                situação.setText("Cargo inválido! O cargo deve ser 'administrador' ou 'bibliotecario'.");
            }

        } catch (NumberFormatException | AdmException | BibliotecarioExcpetion e) {
            situação.setText("Não foi possível excluir o funcionário!");
        }
    }

    /***
     *
     * @param event
     */
    @FXML
    void buscando(ActionEvent event) {
        // Verifica se os campos de entrada estão vazios
        if (idField.getText().isEmpty() || idField1.getText().isEmpty()) {
            situação.setText("Não foi possível remover o usuário: Preencha todos os campos!");
            return;
        }

        try {
            int id = Integer.parseInt(idField.getText());
            String cargo = idField1.getText();

            if (cargo.equalsIgnoreCase("administrador")) {
                Administrador administrador = DAO.getAdmDAO().buscarporId(id);

                nomeText.setText(administrador.getNome());
                cargoText.setText(administrador.getCargo());
                SenhaText.setText(administrador.getSenhaAcesso().toString());
                IdText.setText(administrador.getNumIdentificacao().toString());

            } else if (cargo.equalsIgnoreCase("bibliotecario")) {
                Bibliotecario bibliotecario = DAO.getBibliotecarioDAO().buscarporId(id);

                nomeText.setText(bibliotecario.getNome());
                cargoText.setText(bibliotecario.getCargo());
                SenhaText.setText(bibliotecario.getSenhaAcesso().toString());
                IdText.setText(bibliotecario.getNumIdentificacao().toString());

            } else {
                situação.setText("Cargo inválido! O cargo deve ser 'administrador' ou 'bibliotecario'.");
            }

        } catch (NumberFormatException | AdmException | BibliotecarioExcpetion e) {
            situação.setText("Não foi possível excluir o funcionário!");
        }
    }

    /***
     *
     * @param event
     */
    @FXML
    void cancelOperation(ActionEvent event) {
        MainController.goBack();
    }

    /***
     * O método é chamado quando a inicialização do FXMLLoader é completa.
     */
    @FXML
    void initialize() {
        assert ConfirmarButton != null : "fx:id=\"ConfirmarButton\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert IdText != null : "fx:id=\"IdText\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert SenhaLabel != null : "fx:id=\"SenhaLabel\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert SenhaText != null : "fx:id=\"SenhaText\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert cargoLabel != null : "fx:id=\"cargoLabel\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert cargoText != null : "fx:id=\"cargoText\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert idField1 != null : "fx:id=\"idField1\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert nomeText != null : "fx:id=\"nomeText\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";
        assert situação != null : "fx:id=\"situação\" was not injected: check your FXML file 'RemoverWorkerView.fxml'.";

    }

}
