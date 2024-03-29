/**
 * Sample Skeleton for 'GerenciarAcervoView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GerenciarAcervoController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="atualizaLivro"
    private Button atualizaLivro; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="registraLivro"
    private Button registraLivro; // Value injected by FXMLLoader

    @FXML // fx:id="removeLivroButton"
    private Button removeLivroButton; // Value injected by FXMLLoader

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void attLivro(ActionEvent event) throws IOException {
        MainController.gotoScene("/UpdateLivroView.fxml");
    }

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void cadastrarLivro(ActionEvent event) throws IOException {
        MainController.gotoScene("/NewLivroView.fxml");
    }

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void DeletarLivro(ActionEvent event) throws IOException {
        MainController.gotoScene("/RemoverLivroView.fxml");
    }

    /***
     *
     * @param event
     */
    @FXML
    void voltar(ActionEvent event) {
        MainController.goBack();
    }

    /***
     * O método é chamado quando a inicialização do FXMLLoader é completa.
     */
    @FXML
    void initialize() {
        assert atualizaLivro != null : "fx:id=\"atualizaLivro\" was not injected: check your FXML file 'GerenciarAcervoView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'GerenciarAcervoView.fxml'.";
        assert registraLivro != null : "fx:id=\"registraLivro\" was not injected: check your FXML file 'GerenciarAcervoView.fxml'.";
        assert removeLivroButton != null : "fx:id=\"removeLivroButton\" was not injected: check your FXML file 'GerenciarAcervoView.fxml'.";

    }

}
