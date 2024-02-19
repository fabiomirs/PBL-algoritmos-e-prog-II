/**
 * Sample Skeleton for 'UpdateLivroView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.LivroException;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Livro;
import com.example.pbl.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateLivroController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="anoField"
    private TextField anoField; // Value injected by FXMLLoader

    @FXML // fx:id="autorField"
    private TextField autorField; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="categoriaField"
    private TextField categoriaField; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField editoraField; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="isbnField"
    private TextField isbnField; // Value injected by FXMLLoader

    @FXML // fx:id="localField"
    private TextField localField; // Value injected by FXMLLoader

    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

    @FXML // fx:id="tituloField"
    private TextField tituloField; // Value injected by FXMLLoader

    @FXML // fx:id="situação"
    private Label situação; // Value injected by FXMLLoader

    /***
     *
     * @param event
     */
    @FXML
    void cancelOperation(ActionEvent event) {
        MainController.goBack();
    }

    /***
     *
     * @param event
     */
    @FXML
    void saveLivro(ActionEvent event) {
        if (idField.getText().isEmpty() || tituloField.getText().isEmpty() || anoField.getText().isEmpty() || autorField.getText().isEmpty()
                || categoriaField.getText().isEmpty() || isbnField.getText().isEmpty() || editoraField.getText().isEmpty() || localField.getText().isEmpty()) {
            situação.setText("Não foi possível atualizar os dados: Preencha todos os campos!");
            return;
        }
        try {
            int id = Integer.parseInt(idField.getText());
            int isbn = Integer.parseInt(isbnField.getText());

            Livro livroEscolhido = DAO.getLivroDAO().buscarporId(id);

            livroEscolhido.setAutor(autorField.getText());
            livroEscolhido.setTitulo(tituloField.getText());
            livroEscolhido.setCategoria(categoriaField.getText());
            livroEscolhido.setCodigoIsbn(isbn);
            livroEscolhido.setEditora(editoraField.getText());
            livroEscolhido.setAnoPublicacao(anoField.getText());
            livroEscolhido.setLocalizacao(localField.getText());

            DAO.getLivroDAO().update(livroEscolhido);

            situação.setText("Dados atualizados com sucesso!");
            MainController.goBack();

        } catch (NumberFormatException e){
            situação.setText("O id e o isbn deve ser valores numéricos.");
        } catch (LivroException e) {
            situação.setText("Livro não foi encontrado.");
        }
    }

    /***
     * O método é chamado quando a inicialização do FXMLLoader é completa.
     */
    @FXML
    void initialize() {
        assert anoField != null : "fx:id=\"anoField\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";
        assert autorField != null : "fx:id=\"autorField\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";
        assert categoriaField != null : "fx:id=\"categoriaField\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";
        assert isbnField != null : "fx:id=\"isbnField\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";
        assert localField != null : "fx:id=\"localField\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";
        assert tituloField != null : "fx:id=\"tituloField\" was not injected: check your FXML file 'UpdateLivroView.fxml'.";

    }

}
