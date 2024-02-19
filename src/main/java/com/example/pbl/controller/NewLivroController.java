package com.example.pbl.controller;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.LivroException;
import com.example.pbl.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewLivroController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="anoPublicacao"
    private TextField anoPublicacao; // Value injected by FXMLLoader

    @FXML // fx:id="autor"
    private TextField autor; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="categoria"
    private TextField categoria; // Value injected by FXMLLoader

    @FXML // fx:id="editora"
    private TextField editora; // Value injected by FXMLLoader

    @FXML // fx:id="isbn"
    private TextField isbn; // Value injected by FXMLLoader

    @FXML // fx:id="localização"
    private TextField localização; // Value injected by FXMLLoader

    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

    @FXML // fx:id="titulo"
    private TextField titulo; // Value injected by FXMLLoader

    @FXML // fx:id="situação"
    private Label situação; // Value injected by FXMLLoader

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void cancelOperation(ActionEvent event) throws IOException {
        MainController.goBack();
    }

    /***
     *
     * @param event
     */
    @FXML
    void saveLivro(ActionEvent event) {
        // Verifica se os campos de entrada estão vazios
        if (titulo.getText().isEmpty() || editora.getText().isEmpty() || isbn.getText().isEmpty() ||
                localização.getText().isEmpty() || autor.getText().isEmpty() || anoPublicacao.getText().isEmpty() ||
                categoria.getText().isEmpty()) {
            situação.setText("Não foi possível criar o livro: Preencha todos os campos!");
        }

        try {
            int isbnNumber = Integer.parseInt(isbn.getText());

            Livro livrocriado = DAO.getLivroDAO().create(new Livro(titulo.getText(), editora.getText(), isbnNumber, localização.getText(),
                    autor.getText(), anoPublicacao.getText(), categoria.getText()));

            situação.setText("Livro criado com sucesso!");
            MainController.goBack();
        } catch (NumberFormatException e) {
            situação.setText("Não foi possível criar o livro: ISBN inválido!");
        }
    }

    /***
     * O método é chamado quando a inicialização do FXMLLoader é completa.
     */
    @FXML
    void initialize() {
        assert anoPublicacao != null : "fx:id=\"anoPublicacao\" was not injected: check your FXML file 'NewLivroView.fxml'.";
        assert autor != null : "fx:id=\"autor\" was not injected: check your FXML file 'NewLivroView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'NewLivroView.fxml'.";
        assert categoria != null : "fx:id=\"categoria\" was not injected: check your FXML file 'NewLivroView.fxml'.";
        assert editora != null : "fx:id=\"editora\" was not injected: check your FXML file 'NewLivroView.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'NewLivroView.fxml'.";
        assert localização != null : "fx:id=\"localização\" was not injected: check your FXML file 'NewLivroView.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'NewLivroView.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'NewLivroView.fxml'.";


    }

}
