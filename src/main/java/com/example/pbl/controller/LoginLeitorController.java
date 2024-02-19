package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Usuario;
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

public class LoginLeitorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField telefoneField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label Usernoexiste;

    @FXML
    private Label erroId;

    /***
     *
     * @param event
     * @throws IOException
     * @throws UsuarioException
     */
    @FXML
    void toLogin(ActionEvent event) throws IOException, UsuarioException {
        // Obtém o login do usuário a partir do campo de entrada
        String login = usernameField.getText();

        // Obtém a senha do usuário a partir do campo de entrada
        String telefone = telefoneField.getText();

        boolean idValido = false;
        boolean telefoneValido = false;

        try {
            // Verifica se o ID fornecido é um número válido
            int id = Integer.parseInt(login);
            idValido = true;

            // Busca o usuário no banco de dados com base no login
            Usuario usuarioEncontrado = DAO.getUsuarioDAO().buscarporId(id);

            if (usuarioEncontrado != null && usuarioEncontrado.getTelefone().equals(Integer.parseInt(telefone))) {
                // Usuário encontrado e telefone correspondente, redireciona para a cena de usuário
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UsuarioView.fxml"));
                Parent root = loader.load();
                UsuarioController usuarioController = loader.getController();
                usuarioController.initData(id);

                // Carrega a cena da próxima tela
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                return; // Retorna para evitar a exibição de outras mensagens de erro
            } else {
                // Usuário não encontrado ou senha incorreta
                Usernoexiste.setText("Usuário não encontrado.");
            }
        } catch (NumberFormatException e) {
            // Trata o caso em que o ID fornecido não é um número válido
            erroId.setText("O id deve ser um número!");
        } catch (UsuarioException ex) {
            // Trata exceção caso o usuário não seja encontrado
            Usernoexiste.setText("Usuário não encontrado.");
        }

        try {
            // Verifica se o telefone fornecido é um número válido
            Integer.parseInt(telefone);
            telefoneValido = true;
        } catch (NumberFormatException ex) {
            // Se ocorrer uma exceção, significa que o telefone não é um número válido
            telefoneValido = false;
            Usernoexiste.setText("Telefone incorreto.");
        }

        // Verifica se tanto o ID quanto o telefone são inválidos
        if (!idValido && !telefoneValido) {
            Usernoexiste.setText("ID e telefone incorretos.");
        } else if (!idValido) {
            // Se apenas o ID for inválido, limpa a mensagem de erro do telefone
            Usernoexiste.setText("");
        } else if (!telefoneValido) {
            // Se apenas o telefone for inválido, limpa a mensagem de erro do ID
            erroId.setText("");
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
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginLeitorView.fxml'.";
        assert telefoneField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginLeitorView.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'LoginLeitorView.fxml'.";

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            erroId.setText(""); // Limpa a mensagem de erro
        });

        telefoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            Usernoexiste.setText(""); // Limpa a mensagem de erro
        });
    }


}
