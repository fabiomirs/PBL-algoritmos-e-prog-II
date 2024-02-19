/**
 * Sample Skeleton for 'HistoricoUserView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.EmprestimoException;
import com.example.pbl.model.Emprestimo;
import com.example.pbl.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoricoUserController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="busca"
    private TextField busca; // Value injected by FXMLLoader

    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader

    @FXML
    private TableColumn<Livro, String> colLocal;

    @FXML
    private TableColumn<Livro, Integer> colID;

    @FXML
    private TableColumn<Livro, String> colsituação;

    @FXML
    private TableColumn<Livro, String> colAno;

    @FXML
    private TableColumn<Livro, String> colTitulo;

    @FXML
    private TableColumn<Livro, String> colAutor;

    @FXML
    private TableColumn<Livro, Integer> colIsbn;

    @FXML
    private TableColumn<Livro, String> colCategoria;

    @FXML // fx:id="labelErro1"
    private Label labelErro1; // Value injected by FXMLLoader

    @FXML // fx:id="tableView"
    private TableView<Livro> tableView; // Value injected by FXMLLoader

    /***
     *
     * @param event
     */
    @FXML
    private void buscando(ActionEvent event) {
        String input = busca.getText();
        List<Livro> listalivros = new ArrayList<>();
        try {
            int userId = Integer.parseInt(input);
            List<Emprestimo> historicoEmprestimos = DAO.getEmprestimoDAO().historicoEmprestimosUsuario(userId);
            for (Emprestimo emprestimo : historicoEmprestimos) {
                Livro livro = emprestimo.getLivro();
                listalivros.add(livro);
            }
            if (listalivros.isEmpty()) {
            } else {
                // Configura os itens na TableView
                ObservableList<Livro> data = FXCollections.observableArrayList(listalivros);
                tableView.setItems(data);
            }
        } catch (NumberFormatException e) {
            labelErro1.setText("Por favor, insira um número válido.");
        } catch (EmprestimoException e) {
            labelErro1.setText("Não há histórico de empréstimos para o usuário com o ID fornecido.");
        }
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
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        // Configura as colunas da TableView
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("codigoIsbn"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacao"));
        colLocal.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colsituação.setCellValueFactory(new PropertyValueFactory<>("statusLivro"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));

        busca.textProperty().addListener((observable, oldValue, newValue) -> {
            labelErro1.setText(""); // Limpa a mensagem de erro
        });

    }

}
