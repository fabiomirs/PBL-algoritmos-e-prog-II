package com.example.pbl.controller;

import com.example.pbl.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button loginButton1;

    @FXML
    private Button loginButton2;

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void toLoginadministrador(ActionEvent event) throws IOException {
        MainController.gotoScene("/LoginAdministradorView.fxml");
    }

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void toLoginbibliotecario(ActionEvent event) throws IOException {
        MainController.gotoScene("/LoginBibliotecarioView.fxml");
    }

    /***
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void toLoginleitor(ActionEvent event) throws IOException {
        MainController.gotoScene("/LoginLeitorView.fxml");

    }

}
