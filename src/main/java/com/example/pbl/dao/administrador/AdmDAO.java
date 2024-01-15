package com.example.pbl.dao.administrador;
import com.example.pbl.dao.CRUD;
import com.example.pbl.exceptions.AdmException;
import com.example.pbl.model.Administrador;


/**
 * A interface AdmDAO define operações específicas para acesso a dados relacionados aos administradores.
 */
public interface AdmDAO extends CRUD<Administrador, AdmException> {
    void alteraParaPastaTeste();

    void alteraParaPastaPrincipal();
}
