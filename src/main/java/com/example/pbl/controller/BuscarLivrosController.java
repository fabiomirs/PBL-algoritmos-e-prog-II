package com.example.pbl.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.LivroException;
import com.example.pbl.model.Livro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuscarLivrosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private Button button;

    @FXML
    private Button backbutton;

    @FXML
    private TextField busca;

    @FXML
    private Label labelErro;

    @FXML
    private Label labelErro1;

    @FXML
    private TableView<Livro> tableView;

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

    @FXML
    void select(ActionEvent event){

    }

    /***
     *
     * @param event
     */
    @FXML
    void buscando(ActionEvent event) {
        String escolha = busca.getText();
        String s = comb.getSelectionModel().getSelectedItem();

        if (s == null || s.isEmpty()) {
            labelErro.setText("Selecione o modo de busca!!!");
            return;
        }

        if (escolha.isEmpty()) {
            labelErro1.setText("Digite algo para buscar.");
            return;
        }

        List<Livro> listalivros = new ArrayList<>();

        try {
            if ("Categoria".equals(s)) {
                listalivros = DAO.getLivroDAO().buscarPorCategoria(escolha);
            } else if ("Título".equals(s)) {
                listalivros = DAO.getLivroDAO().buscarporTitulo(escolha);
            } else if ("Autor".equals(s)) {
                listalivros = DAO.getLivroDAO().buscarporAutor(escolha);
            } else if ("ISBN".equals(s)) {
                try {
                    int isbn = Integer.parseInt(escolha);
                    listalivros = DAO.getLivroDAO().buscarPorIsbn(isbn);
                } catch (NumberFormatException e) {
                    labelErro1.setText("ISBN deve ser um número.");
                    return;
                }
            }
        } catch (LivroException e) {
            labelErro1.setText("Erro ao buscar livros: " + e.getMessage());
            return;
        }

        if (listalivros.isEmpty()) {
        } else {
            // Configura os itens na TableView
            ObservableList<Livro> data = FXCollections.observableArrayList(listalivros);
            tableView.setItems(data);
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
    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Título", "Autor", "ISBN", "Categoria");
        comb.setItems(list);

        // Configura as colunas da TableView
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("codigoIsbn"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacao"));
        colLocal.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colsituação.setCellValueFactory(new PropertyValueFactory<>("statusLivro"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));


        // Adiciona ouvintes de evento para limpar a mensagem de erro quando o usuário começa a digitar nos campos
        comb.itemsProperty().addListener((observable, oldValue, newValue) -> {
            labelErro.setText(""); // Limpa a mensagem de erro
        });

        busca.textProperty().addListener((observable, oldValue, newValue) -> {
            labelErro1.setText(""); // Limpa a mensagem de erro
        });
    }
}
