package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.BibliotecarioExcpetion;
import com.example.pbl.model.Bibliotecario;
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

public class LoginBibliotecarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    @FXML
    private Label userNoexiste;

    @FXML
    private Label Senhaerrada;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void toLogin(ActionEvent event) throws IOException {
        // Limpar as mensagens de erro
        userNoexiste.setText("");
        Senhaerrada.setText("");

        // Obtém o login do usuário a partir do campo de entrada
        String login = usernameField.getText();

        // Obtém a senha do usuário a partir do campo de entrada
        String senha = passwordField.getText();

        // Verifica se ambos os campos estão vazios
        if (login.isEmpty() && senha.isEmpty()) {
            userNoexiste.setText("Por favor, insira o login.");
            Senhaerrada.setText("Por favor, insira a senha.");
            return;
        }

        boolean idValido = false;
        boolean senhaValida = false;

        // Verifica se o campo de ID está vazio
        if (login.isEmpty()) {
            userNoexiste.setText("Por favor, insira o login.");
            return;
        }

        // Verifica se o campo de senha está vazio
        if (senha.isEmpty()) {
            Senhaerrada.setText("Por favor, insira a senha.");
            return;
        }

        try {
            // Verifica se o ID fornecido é um número válido
            int id = Integer.parseInt(login);
            idValido = true;

            // Busca o bibliotecário no banco de dados com base no login
            Bibliotecario bibliotecarioEncontrado = DAO.getBibliotecarioDAO().buscarporId(id);

            if (bibliotecarioEncontrado != null && bibliotecarioEncontrado.getSenhaAcesso().equals(Integer.parseInt(senha))) {
                // Bibliotecário encontrado e senha correspondente, redireciona para a cena de bibliotecário
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/BibliotecarioView.fxml"));
                Parent root = loader.load();
                BibliotecarioController bibliotecarioController = loader.getController();
                bibliotecarioController.initData(id);

                // Carrega a cena da próxima tela
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                return; // Retorna para evitar a exibição de outras mensagens de erro
            } else {
                // Bibliotecário não encontrado ou senha incorreta
                if (bibliotecarioEncontrado == null) {
                    userNoexiste.setText("Bibliotecário não encontrado.");
                } else {
                    Senhaerrada.setText("Senha incorreta.");
                }
            }
        } catch (NumberFormatException e) {
            // Trata o caso em que o ID fornecido não é um número válido
            userNoexiste.setText("ID deve ser um número.");
        } catch (BibliotecarioExcpetion e) {
            // Trata exceção caso o bibliotecário não seja encontrado
            userNoexiste.setText("Bibliotecário não encontrado.");
        }

        // Verifica se tanto o ID quanto a senha são inválidos
        if (!idValido || !senhaValida) {
            userNoexiste.setText("ID ou senha incorretos.");
        }
    }

    @FXML
    void tobackLogin(ActionEvent event) throws IOException {
        MainController.gotoScene("/LoginView.fxml");
    }

    @FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginBibliotecarioView.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginBibliotecarioView.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'LoginBibliotecarioView.fxml'.";

        // Adiciona ouvintes de evento para limpar a mensagem de erro quando o usuário começa a digitar nos campos
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            userNoexiste.setText(""); // Limpa a mensagem de erro
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            Senhaerrada.setText(""); // Limpa a mensagem de erro
        });
    }
}
