/**
 * Sample Skeleton for 'RemoverLivroView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
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

public class RemoverLivroController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ConfirmarButton"
    private Button ConfirmarButton; // Value injected by FXMLLoader

    @FXML // fx:id="IdText"
    private Label IdText; // Value injected by FXMLLoader

    @FXML // fx:id="autorLabel"
    private Label autorLabel; // Value injected by FXMLLoader

    @FXML // fx:id="autorText"
    private Label autorText; // Value injected by FXMLLoader

    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="categoriaText"
    private Label categoriaText; // Value injected by FXMLLoader

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader

    @FXML // fx:id="idLabel"
    private Label idLabel; // Value injected by FXMLLoader

    @FXML // fx:id="isbnLabel"
    private Label isbnLabel; // Value injected by FXMLLoader

    @FXML // fx:id="isbnText"
    private Label isbnText; // Value injected by FXMLLoader

    @FXML // fx:id="limiteLabel"
    private Label limiteLabel; // Value injected by FXMLLoader

    @FXML // fx:id="situaLabel"
    private Label situaLabel; // Value injected by FXMLLoader

    @FXML // fx:id="situacaoText"
    private Label situacaoText; // Value injected by FXMLLoader

    @FXML // fx:id="situação"
    private Label situação; // Value injected by FXMLLoader

    @FXML // fx:id="tituloLabel"
    private Label tituloLabel; // Value injected by FXMLLoader

    @FXML // fx:id="tituloText"
    private Label tituloText; // Value injected by FXMLLoader

    /***
     *
     * @param event
     */
    @FXML
    void Confirmar(ActionEvent event) {
        if (idField.getText().isEmpty()){
            situação.setText("O campo de id está vazio.");
            return;
        }
        try{
            int id = Integer.parseInt(idField.getText());
            Livro livroEscolhido = DAO.getLivroDAO().buscarporId(id);
            DAO.getLivroDAO().delete(livroEscolhido);
            MainController.goBack();
        }catch (NumberFormatException e){
            situação.setText("Não foi possível excluir o livro: Informações incorretas.");
        } catch (LivroException e) {
            situação.setText("Não foi possível concluir a exclusão.");
        }

    }

    /***
     *
     * @param event
     */
    @FXML
    void buscando(ActionEvent event) {
        if (idField.getText().isEmpty()){
            situação.setText("O campo de id está vazio.");
            return;
        }
        try{
            int id = Integer.parseInt(idField.getText());
            Livro livroEscolhido = DAO.getLivroDAO().buscarporId(id);

            tituloText.setText(livroEscolhido.getTitulo());
            autorText.setText(livroEscolhido.getAutor());
            situacaoText.setText(livroEscolhido.getStatusLivro());
            IdText.setText(livroEscolhido.getId().toString());
            categoriaText.setText(livroEscolhido.getCategoria());
            isbnText.setText(livroEscolhido.getCodigoIsbn().toString());

        }catch (NumberFormatException e){
            situação.setText("Não foi possível excluir o livro: Informações incorretas.");
        } catch (LivroException e) {
            situação.setText("Não foi possível concluir a exclusão.");
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
        assert ConfirmarButton != null : "fx:id=\"ConfirmarButton\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert IdText != null : "fx:id=\"IdText\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert autorLabel != null : "fx:id=\"autorLabel\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert autorText != null : "fx:id=\"autorText\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert categoriaText != null : "fx:id=\"categoriaText\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert isbnLabel != null : "fx:id=\"isbnLabel\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert isbnText != null : "fx:id=\"isbnText\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert limiteLabel != null : "fx:id=\"limiteLabel\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert situaLabel != null : "fx:id=\"situaLabel\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert situacaoText != null : "fx:id=\"situacaoText\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert situação != null : "fx:id=\"situação\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert tituloLabel != null : "fx:id=\"tituloLabel\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";
        assert tituloText != null : "fx:id=\"tituloText\" was not injected: check your FXML file 'RemoverLivroView.fxml'.";

    }

}
