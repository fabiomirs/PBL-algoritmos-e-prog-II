/**
 * Sample Skeleton for 'RelatoriosView.fxml' Controller Class
 */

package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.MainController;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.EmprestimoException;
import com.example.pbl.exceptions.LivroException;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class RelatoriosController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="atrasadosLabel"
    private Label atrasadosLabel; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="reservadosLabel"
    private Label reservadosLabel; // Value injected by FXMLLoader

    @FXML // fx:id="reservadosLabel"
    private Label emprestadosLabel; // Value injected by FXMLLoader

    @FXML // fx:id="selecionarUser"
    private Button selecionarUser; // Value injected by FXMLLoader

    @FXML
    private Label Labelaviso;

    @FXML
    private TableColumn<Livro, Integer> colId;

    @FXML
    private TableColumn<Livro, String> colTitulo;

    @FXML
    private TableView<Livro> tabelaLivros;

    @FXML
    void SelecionarUser(ActionEvent event) throws IOException {
        MainController.gotoScene("/HistoricoUserView.fxml");
    }

    @FXML
    void voltar(ActionEvent event) {
        MainController.goBack();
    }

    @FXML
    void initialize() {
        try {
            // Chamando os métodos para obter os resultados
            List<Emprestimo> emprestimosAtrasados = DAO.getEmprestimoDAO().emprestimosAtrasados();
            List<Livro> livrosComReserva = DAO.getLivroDAO().livrosComReserva();
            List<Emprestimo> emprestimosEmAberto = DAO.getEmprestimoDAO().LivrosEmprestados();

            // Atualizando os labels com os resultados
            atrasadosLabel.setText(" " + emprestimosAtrasados.size());
            reservadosLabel.setText(" " + livrosComReserva.size());
            emprestadosLabel.setText(" " + emprestimosEmAberto.size());

            // Preenchendo a tabela com os livros populares
            ObservableList<Livro> livrosPopularesObservable = FXCollections.observableArrayList(DAO.getEmprestimoDAO().livrosMaisPopulares());
            tabelaLivros.setItems(livrosPopularesObservable);

            // Configurando as colunas da tabela
            colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        } catch (EmprestimoException | LivroException e) {
            Labelaviso.setText("As informações ainda não estão disponíveis!");
            // Atualizando os labels com os resultados
            atrasadosLabel.setText(" Indisponível");
            reservadosLabel.setText(" Indisponível");
            emprestadosLabel.setText(" Indisponível");
        }
    }

}
