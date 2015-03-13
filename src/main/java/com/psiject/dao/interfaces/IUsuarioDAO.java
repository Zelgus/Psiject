package com.psiject.dao.interfaces;

import java.util.List;

import com.psiject.entidades.Usuario;

/**
 * The Interface IUsuarioDAO.
 */
public interface IUsuarioDAO extends IGenericDAO<Usuario, Long> {

   /**
    * Find by usuario.
    * 
    * @param usuario
    *           the usuario
    * @return the usuario
    * @throws Exception
    */
   public Usuario findByUsuario(String usuario) throws Exception;

   /**
    * Find psicologos.
    * 
    * @return the list
    * @throws Exception
    */
   public List<Usuario> findPsicologos() throws Exception;

   public Usuario findByCorreo(String correo) throws Exception;;
}