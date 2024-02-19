/**
 * Sample Skeleton for 'NewEmprestimoView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.EmprestimoException;
import com.example.pbl.exceptions.LivroException;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Emprestimo;
import com.example.pbl.model.Livro;
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewEmprestimoController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="idLivro"
    private TextField idLivro; // Value injected by FXMLLoader

    @FXML // fx:id="idUser"
    private TextField idUser; // Value injected by FXMLLoader

    @FXML // fx:id="infoidLivro"
    private Label infoidLivro; // Value injected by FXMLLoader

    @FXML // fx:id="infoidUser"
    private Label infoidUser; // Value injected by FXMLLoader

    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

    @FXML // fx:id="situação"
    private Label situação; // Value injected by FXMLLoader

    @FXML
    void cancelOperation(ActionEvent event) {
        MainController.goBack();
    }

    @FXML
    void saveEmprestimo(ActionEvent event) {
        // Verifica se os campos de entrada estão vazios
        if (idUser.getText().isEmpty() || idLivro.getText().isEmpty()) {
            situação.setText("Não foi possível fazer o empréstimo: Preencha todos os campos!");
            return;
        }

        try {
            int idusuario = Integer.parseInt(idUser.getText());
            int idlivro = Integer.parseInt(idLivro.getText());

            Livro livroEmprestimo = DAO.getLivroDAO().buscarporId(idlivro);
            Usuario usuarioEmprestimo = DAO.getUsuarioDAO().buscarporId(idusuario);

            Emprestimo emprestimoFeito = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livroEmprestimo, usuarioEmprestimo));

            situação.setText("Empréstimo feito com sucesso!");
            MainController.goBack();
        } catch (NumberFormatException e) {
            situação.setText("Não foi possível fazer o empréstimo: Informações incorretas.");
        } catch (LivroException | UsuarioException | EmprestimoException e) {
            situação.setText("Informações incorretas." + e.getMessage());
        }
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'NewEmprestimoView.fxml'.";
        assert idLivro != null : "fx:id=\"idLivro\" was not injected: check your FXML file 'NewEmprestimoView.fxml'.";
        assert idUser != null : "fx:id=\"idUser\" was not injected: check your FXML file 'NewEmprestimoView.fxml'.";
        assert infoidLivro != null : "fx:id=\"infoidLivro\" was not injected: check your FXML file 'NewEmprestimoView.fxml'.";
        assert infoidUser != null : "fx:id=\"infoidUser\" was not injected: check your FXML file 'NewEmprestimoView.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'NewEmprestimoView.fxml'.";

    }

}
