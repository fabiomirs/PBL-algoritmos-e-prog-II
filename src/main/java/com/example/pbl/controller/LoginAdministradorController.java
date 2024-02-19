package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.AdmException;
import com.example.pbl.model.Administrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginAdministradorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginerrado;

    @FXML
    private Label loginerrado2;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void toLogin(ActionEvent event) throws IOException{
        // Limpar as mensagens de erro
        loginerrado.setText("");
        loginerrado2.setText("");

        String login = usernameField.getText();
        String senha = passwordField.getText();

        // Verifica se o campo de ID está vazio
        if (login.isEmpty()) {
            loginerrado.setText("Por favor, insira o ID.");
            return;
        }

        boolean idValido = false;
        boolean senhaValida = false;

        try {
            int id = Integer.parseInt(login);
            idValido = true;

            Administrador admEncontrado = DAO.getAdmDAO().buscarporId(id);

            if (admEncontrado != null && admEncontrado.getSenhaAcesso().equals(Integer.parseInt(senha))) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdmView.fxml"));
                Parent root = loader.load();
                AdmController admController = loader.getController();
                admController.initData(id);

                // Carrega a cena da próxima tela
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                return;
            } else {
                if (admEncontrado == null) {
                    loginerrado.setText("Administrador não encontrado.");
                } else {
                    loginerrado2.setText("Senha incorreta.");
                }
            }
        } catch (NumberFormatException e) {
            loginerrado.setText("O ID deve ser um número inteiro.");
        } catch (AdmException ex) {
            throw new RuntimeException(ex);
        }

        try {
            Integer.parseInt(senha);
            senhaValida = true;
        } catch (NumberFormatException ex) {
            if (!idValido) {
                loginerrado.setText("Senha incorreta.");
            } else {
                loginerrado2.setText("Senha deve ser um número.");
            }
        }

        if (!idValido && !senhaValida) {
            loginerrado.setText("ID e senha incorretos.");
        } else if (!idValido) {
            loginerrado2.setText("");
        } else if (!senhaValida) {
            loginerrado.setText("");
        }
    }

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void tobackLogin(ActionEvent event) throws IOException {
        MainController.gotoScene("/LoginView.fxml");
    }

    /***
     * O método é chamado quando a inicialização do FXMLLoader é completa.
     */
    @FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginAdministradorView.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginAdministradorView.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'LoginAdministradorView.fxml'.";

        // Adiciona um ouvinte para os campos de entrada para limpar as mensagens de erro quando algo novo é digitado
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginerrado.setText("");
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginerrado2.setText("");
        });
    }
}
