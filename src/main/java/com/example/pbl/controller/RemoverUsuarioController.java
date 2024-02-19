/**
 * Sample Skeleton for 'RemoverUsuarioView.fxml' Controller Class
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

public class RemoverUsuarioController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ConfirmarButton"
    private Button ConfirmarButton; // Value injected by FXMLLoader

    @FXML // fx:id="IdText"
    private Label IdText; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="endercolabel"
    private Label endercolabel; // Value injected by FXMLLoader

    @FXML // fx:id="endercotext"
    private Label endercotext; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="idLabel"
    private Label idLabel; // Value injected by FXMLLoader

    @FXML // fx:id="limText"
    private Label limText; // Value injected by FXMLLoader

    @FXML // fx:id="limiteLabel"
    private Label limiteLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nameLabel"
    private Label nameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nametext"
    private Label nametext; // Value injected by FXMLLoader

    @FXML // fx:id="situaLabel"
    private Label situaLabel; // Value injected by FXMLLoader

    @FXML // fx:id="situaText"
    private Label situaText; // Value injected by FXMLLoader

    @FXML // fx:id="situação"
    private Label situação; // Value injected by FXMLLoader

    @FXML // fx:id="telLabel"
    private Label telLabel; // Value injected by FXMLLoader

    @FXML // fx:id="telText"
    private Label telText; // Value injected by FXMLLoader

    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader

    @FXML
    void cancelOperation(ActionEvent event) {
        MainController.goBack();
    }

    @FXML
    void buscando(ActionEvent event) {
        if (idField.getText().isEmpty()){
            situação.setText("O campo de id está vazio.");
            return;
        }
        try{
            int id = Integer.parseInt(idField.getText());
            Usuario usuario = DAO.getUsuarioDAO().buscarporId(id);

            nametext.setText(usuario.getNome());
            endercotext.setText(usuario.getEndereco());
            telText.setText(usuario.getTelefone().toString());
            situaText.setText(usuario.getStatusConta());
            limText.setText(usuario.getLimRenovacao().toString());
            IdText.setText(usuario.getNumIdentificacao().toString());

        }catch (NumberFormatException e){
            situação.setText("Não foi possível excluir o leitor: Informações incorretas.");
        }
        catch (UsuarioException e) {
            situação.setText("Não foi possível concluir a exclusão.");
        }
    }

    @FXML
    void Confirmar(ActionEvent event) {
        if (idField.getText().isEmpty()){
            situação.setText("O campo de id está vazio.");
            return;
        }
        try{
            int id = Integer.parseInt(idField.getText());
            Usuario userEscolhido = DAO.getUsuarioDAO().buscarporId(id);
            DAO.getUsuarioDAO().delete(userEscolhido);
            MainController.goBack();

        }catch (NumberFormatException e){
            situação.setText("Não foi possível excluir o leitor: Informações incorretas.");
        }
        catch (UsuarioException e) {
            situação.setText("Não foi possível concluir a exclusão.");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ConfirmarButton != null : "fx:id=\"ConfirmarButton\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert IdText != null : "fx:id=\"IdText\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert endercolabel != null : "fx:id=\"endercolabel\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert endercotext != null : "fx:id=\"endercotext\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert limText != null : "fx:id=\"limText\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert limiteLabel != null : "fx:id=\"limiteLabel\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert nametext != null : "fx:id=\"nametext\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert situaLabel != null : "fx:id=\"situaLabel\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert situaText != null : "fx:id=\"situaText\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert situação != null : "fx:id=\"situação\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert telLabel != null : "fx:id=\"telLabel\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";
        assert telText != null : "fx:id=\"telText\" was not injected: check your FXML file 'RemoverUsuarioView.fxml'.";

    }

}
