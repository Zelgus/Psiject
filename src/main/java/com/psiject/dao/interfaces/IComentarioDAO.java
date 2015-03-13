package com.psiject.dao.interfaces;

import java.util.List;

import com.psiject.entidades.Comentario;
import com.psiject.entidades.TareaCampo;

/**
 * The Interface IComentarioDAO.
 */
public interface IComentarioDAO extends IGenericDAO<Comentario, Long> {

   /**
    * Find comentarios por tarea campo.
    * 
    * @param tareaCampo
    *           the tarea campo
    * @return the list
    * @throws Exception
    */
   public List<Comentario> findComentariosPorTareaCampo(TareaCampo tareaCampo) throws Exception;

}