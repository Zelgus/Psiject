package com.psiject.dao.interfaces;

import java.util.List;

import com.psiject.entidades.Expediente;
import com.psiject.entidades.Usuario;

/**
 * The Interface IExpedienteDAO.
 */
public interface IExpedienteDAO extends IGenericDAO<Expediente, Long> {

   /**
    * Find expedientes creados por usuario.
    * 
    * @param usuario
    *           the usuario
    * @return the list
    * @throws Exception
    */
   public List<Expediente> findExpedientesCreadosPorUsuario(Usuario usuario) throws Exception;

   /**
    * Find expedientes por usuario.
    * 
    * @param usuario
    *           the usuario
    * @return the list
    * @throws Exception
    */
   public List<Expediente> findExpedientesPorUsuario(Usuario usuario) throws Exception;
}