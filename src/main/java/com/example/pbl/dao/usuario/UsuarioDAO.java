package com.example.pbl.dao.usuario;
import com.example.pbl.dao.CRUD;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Usuario;

/**
 * A interface UsuarioDAO define operações específicas para acesso a dados relacionados aos usuários.
 */
public interface UsuarioDAO extends CRUD<Usuario, UsuarioException> {
    void alteraParaPastaTeste();

    void alteraParaPastaPrincipal();
}
