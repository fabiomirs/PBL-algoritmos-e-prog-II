package com.example.pbl.dao.bibliotecario;
import com.example.pbl.dao.CRUD;
import com.example.pbl.exceptions.BibliotecarioExcpetion;
import com.example.pbl.model.Bibliotecario;

/**
 * A interface BibliotecarioDAO define operações específicas para acesso a dados relacionados aos bibliotecários.
 */
public interface BibliotecarioDAO extends CRUD<Bibliotecario, BibliotecarioExcpetion> {
    void alteraParaPastaTeste();

    void alteraParaPastaPrincipal();
}
