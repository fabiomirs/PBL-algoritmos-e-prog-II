package dao.administrador;
import dao.CRUD;
import exceptions.AdmException;
import model.Administrador;


/**
 * A interface AdmDAO define operações específicas para acesso a dados relacionados aos administradores.
 */
public interface AdmDAO extends CRUD<Administrador, AdmException> {
    void alteraParaPastaTeste();

    void alteraParaPastaPrincipal();
}
