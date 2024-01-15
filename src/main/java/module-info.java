module com.example.pbl {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.pbl.model;
    opens com.example.pbl.dao.livro;
    opens com.example.pbl.dao.usuario;
    opens com.example.pbl.dao.administrador;
    opens com.example.pbl.dao.emprestimo;
    opens com.example.pbl.dao.bibliotecario;

    opens com.example.pbl to javafx.fxml;
    exports com.example.pbl;
}