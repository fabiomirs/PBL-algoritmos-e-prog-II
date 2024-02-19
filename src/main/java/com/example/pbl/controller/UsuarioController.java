/**
 * Sample Skeleton for 'UsuarioView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.EmprestimoException;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Emprestimo;
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UsuarioController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="BuscarButton"
    private Button BuscarButton; // Value injected by FXMLLoader

    @FXML // fx:id="IdText"
    private Label IdText; // Value injected by FXMLLoader

    @FXML // fx:id="RenovarButton"
    private Button RenovarButton; // Value injected by FXMLLoader

    @FXML // fx:id="ReservaButton"
    private Button ReservaButton; // Value injected by FXMLLoader

    @FXML // fx:id="SairButton"
    private Button SairButton; // Value injected by FXMLLoader

    @FXML // fx:id="endercolabel"
    private Label endercolabel; // Value injected by FXMLLoader

    @FXML // fx:id="endercotext"
    private Label endercotext; // Value injected by FXMLLoader

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

    @FXML // fx:id="telLabel"
    private Label telLabel; // Value injected by FXMLLoader

    @FXML // fx:id="telText"
    private Label telText; // Value injected by FXMLLoader

    @FXML // fx:id="usernameLabel"
    private Label usernameLabel; // Value injected by FXMLLoader

    @FXML
    private Label indicaMulta;

    private int userId;

    public void initData(int userId) {
        this.userId = userId;
        try {
            // Busca o usuário pelo ID
            Usuario usuario = DAO.getUsuarioDAO().buscarporId(userId);
            // Atualiza a interface do usuário com os dados do usuário
            if (usuario != null) {
                Emprestimo emprestimo = DAO.getEmprestimoDAO().buscarporId(usuario.getNumIdentificacao());

                if (emprestimo!=null){
                    Integer valor = DAO.getEmprestimoDAO().calcularMulta(emprestimo);
                    if (valor!=0){
                        indicaMulta.setText("O usuário está multado em: " + valor);
                    }
                }
                usernameLabel.setText(usuario.getNome());
                nametext.setText(usuario.getNome());
                endercotext.setText(usuario.getEndereco());
                telText.setText(usuario.getTelefone().toString());
                situaText.setText(usuario.getStatusConta());
                limText.setText(usuario.getLimRenovacao().toString());
                IdText.setText(usuario.getNumIdentificacao().toString());
            }
        } catch (UsuarioException e) {
            e.printStackTrace();
        } catch (EmprestimoException ignored) {

        }
    }

    @FXML
    void Sair(ActionEvent event) throws IOException{
        MainController.gotoScene("/LoginView.fxml");
    }

    @FXML
    void goToBuscarLivros(ActionEvent event) throws IOException {
        MainController.gotoScene("/BuscarLivrosView.fxml");
    }

    @FXML
    void goToRenovar(ActionEvent event) throws IOException {
        MainController.gotoScene("/RenovarEmprestimoView.fxml");
    }

    @FXML
    void goToReserva(ActionEvent event) throws IOException {
        MainController.gotoScene("/ReservarLivroView.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert BuscarButton != null : "fx:id=\"BuscarButton\" was not injected: check your FXML file 'UsuarioView.fxml'.";
        assert RenovarButton != null : "fx:id=\"RenovarButton\" was not injected: check your FXML file 'UsuarioView.fxml'.";
        assert ReservaButton != null : "fx:id=\"ReservaButton\" was not injected: check your FXML file 'UsuarioView.fxml'.";
        assert SairButton != null : "fx:id=\"SairButton\" was not injected: check your FXML file 'UsuarioView.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'UsuarioView.fxml'.";

    }

}
