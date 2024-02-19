/**
 * Sample Skeleton for 'BibliotecarioView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.BibliotecarioExcpetion;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Bibliotecario;
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BibliotecarioController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="BuscarButton"
    private Button BuscarButton; // Value injected by FXMLLoader

    @FXML // fx:id="DevolverButton"
    private Button DevolverButton; // Value injected by FXMLLoader

    @FXML // fx:id="EmprestimoButton"
    private Button EmprestimoButton; // Value injected by FXMLLoader

    @FXML // fx:id="IdText"
    private Label IdText; // Value injected by FXMLLoader

    @FXML // fx:id="RegistrarButton"
    private Button RegistrarButton; // Value injected by FXMLLoader

    @FXML // fx:id="SenhaLabel"
    private Label SenhaLabel; // Value injected by FXMLLoader

    @FXML // fx:id="SenhaText"
    private Label SenhaText; // Value injected by FXMLLoader

    @FXML // fx:id="cargoLabel"
    private Label cargoLabel; // Value injected by FXMLLoader

    @FXML // fx:id="cargoText"
    private Label cargoText; // Value injected by FXMLLoader

    @FXML // fx:id="idLabel"
    private Label idLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nameLabel"
    private Label nameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nomeText"
    private Label nomeText; // Value injected by FXMLLoader

    @FXML // fx:id="signoutButton"
    private Button signoutButton; // Value injected by FXMLLoader

    @FXML // fx:id="usernameLabel"
    private Label usernameLabel; // Value injected by FXMLLoader

    private int userId;

    public void initData(int userId) {
        this.userId = userId;
        try {
            Bibliotecario bibliotecario = DAO.getBibliotecarioDAO().buscarporId(userId);
            if (bibliotecario != null) {
                usernameLabel.setText(bibliotecario.getNome());
                nomeText.setText(bibliotecario.getNome());
                cargoText.setText(bibliotecario.getCargo());
                SenhaText.setText(bibliotecario.getSenhaAcesso().toString());
                IdText.setText(bibliotecario.getNumIdentificacao().toString());
            }
        } catch (BibliotecarioExcpetion e) {
            e.printStackTrace();
        }
    }

    @FXML
    void BuscarLivros(ActionEvent event) throws IOException {
        MainController.gotoScene("/BuscarLivrosView.fxml");
    }

    @FXML
    void Devolução(ActionEvent event) throws IOException {
        MainController.gotoScene("/DevolucaoView.fxml");
    }

    @FXML
    void EmprestimodeLivro(ActionEvent event) throws IOException {
        MainController.gotoScene("/NewEmprestimoView.fxml");
    }

    @FXML
    void RegistrarLivro(ActionEvent event) throws IOException {
        MainController.gotoScene("/NewLivroView.fxml");
    }

    @FXML
    void quit(ActionEvent event) throws IOException{
        MainController.gotoScene("/LoginView.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert BuscarButton != null : "fx:id=\"BuscarButton\" was not injected: check your FXML file 'BibliotecarioView.fxml'.";
        assert DevolverButton != null : "fx:id=\"DevolverButton\" was not injected: check your FXML file 'BibliotecarioView.fxml'.";
        assert EmprestimoButton != null : "fx:id=\"EmprestimoButton\" was not injected: check your FXML file 'BibliotecarioView.fxml'.";
        assert RegistrarButton != null : "fx:id=\"RegistrarButton\" was not injected: check your FXML file 'BibliotecarioView.fxml'.";
        assert signoutButton != null : "fx:id=\"signoutButton\" was not injected: check your FXML file 'BibliotecarioView.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'BibliotecarioView.fxml'.";

    }

}
