/**
 * Sample Skeleton for 'RenovarEmprestimoView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.EmprestimoException;
import com.example.pbl.model.Emprestimo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RenovarEmprestimoController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="RenovarButton"
    private Button RegistrarButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="idUser"
    private TextField idUser; // Value injected by FXMLLoader

    @FXML // fx:id="infoidUser"
    private Label infoidUser; // Value injected by FXMLLoader

    @FXML // fx:id="situação"
    private Label situação; // Value injected by FXMLLoader

    /***
     *
     * @param event
     */
    @FXML
    void RenovarEmprestimo(ActionEvent event) {
        if (idUser.getText().isEmpty()){
            situação.setText("Não foi possível renovar o livro: Preencha todos os campos!");
            return;
        }
        try{
            int idusuario = Integer.parseInt(idUser.getText());
            Emprestimo emprestimoRealizado = DAO.getEmprestimoDAO().buscarporId(idusuario);
            DAO.getEmprestimoDAO().renovarEmprestimo(emprestimoRealizado);
            situação.setText("Renovação feita com sucesso! Você tem mais 7 dias com o livro!");
            MainController.goBack();
        } catch (NumberFormatException e) {
            situação.setText("Não foi possível fazer a renovação: Id incorreto.");
        }
        catch (EmprestimoException e) {
            situação.setText("Não foi possível fazer a renovação:" + e.getMessage());
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
        assert RegistrarButton != null : "fx:id=\"RegistrarButton\" was not injected: check your FXML file 'RenovarEmprestimoView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'RenovarEmprestimoView.fxml'.";
        assert idUser != null : "fx:id=\"idUser\" was not injected: check your FXML file 'RenovarEmprestimoView.fxml'.";
        assert infoidUser != null : "fx:id=\"infoidUser\" was not injected: check your FXML file 'RenovarEmprestimoView.fxml'.";
        assert situação != null : "fx:id=\"situação\" was not injected: check your FXML file 'RenovarEmprestimoView.fxml'.";

    }

}
