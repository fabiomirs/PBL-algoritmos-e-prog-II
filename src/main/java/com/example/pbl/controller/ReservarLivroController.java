/**
 * Sample Skeleton for 'ReservarLivroView.fxml' Controller Class
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
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReservarLivroController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ReservarButton"
    private Button ReservarButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="idUser"
    private TextField idUser; // Value injected by FXMLLoader

    @FXML // fx:id="idlivro"
    private TextField idlivro; // Value injected by FXMLLoader

    @FXML // fx:id="infoidLivro"
    private Label infoidLivro; // Value injected by FXMLLoader

    @FXML // fx:id="infoidUser"
    private Label infoidUser; // Value injected by FXMLLoader

    @FXML // fx:id="situação"
    private Label situação; // Value injected by FXMLLoader

    @FXML
    void ReservarLivro(ActionEvent event) {
        if (idUser.getText().isEmpty() || idlivro.getText().isEmpty()){
            situação.setText("Não foi possível reservar o livro: Preencha todos os campos!");
            return;
        }
        try{
            int idusuario = Integer.parseInt(idUser.getText());
            int identificaLivro = Integer.parseInt(idlivro.getText());
            Usuario usuario = DAO.getUsuarioDAO().buscarporId(idusuario);
            DAO.getLivroDAO().realizarReserva(usuario, identificaLivro);
            situação.setText("Reserva feita com sucesso!");
            MainController.goBack();
        } catch (NumberFormatException e) {
            situação.setText("Não foi possível fazer a reserva: a entrada deve ser numérica.");
        } catch (UsuarioException e) {
            situação.setText("Não foi possível fazer a reserva: Id do usuário incorreto.");
        } catch (LivroException e) {
            situação.setText("Não é possível reservar este livro.");
        }
    }

    @FXML
    void cancelOperation(ActionEvent event) {
        MainController.goBack();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ReservarButton != null : "fx:id=\"ReservarButton\" was not injected: check your FXML file 'ReservarLivroView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'ReservarLivroView.fxml'.";
        assert idUser != null : "fx:id=\"idUser\" was not injected: check your FXML file 'ReservarLivroView.fxml'.";
        assert idlivro != null : "fx:id=\"idlivro\" was not injected: check your FXML file 'ReservarLivroView.fxml'.";
        assert infoidLivro != null : "fx:id=\"infoidLivro\" was not injected: check your FXML file 'ReservarLivroView.fxml'.";
        assert infoidUser != null : "fx:id=\"infoidUser\" was not injected: check your FXML file 'ReservarLivroView.fxml'.";
        assert situação != null : "fx:id=\"situação\" was not injected: check your FXML file 'ReservarLivroView.fxml'.";

    }

}
