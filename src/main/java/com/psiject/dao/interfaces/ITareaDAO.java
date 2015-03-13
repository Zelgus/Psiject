package com.psiject.dao.interfaces;

import java.util.List;

import com.psiject.entidades.Expediente;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.Usuario;

/**
 * The Interface ITareaDAO.
 */
public interface ITareaDAO extends IGenericDAO<Tarea, Long> {

   /**
    * Find tareas por expediente.
    * 
    * @param expediente
    *           the expediente
    * @return the list
    * @throws Exception
    */
   public List<Tarea> findTareasPorExpediente(Expediente expediente) throws Exception;

   /**
    * Find tareas por creador.
    * 
    * @param creador
    *           the creador
    * @return the list
    * @throws Exception
    */
   public List<Tarea> findTareasPorCreador(Usuario creador) throws Exception;

   /**
    * Find tareas por expediente y creador.
    * 
    * @param expediente
    *           the expediente
    * @param creador
    *           the creador
    * @return the list
    * @throws Exception
    */
   public List<Tarea> findTareasPorExpedienteYCreador(Expediente expediente, Usuario creador) throws Exception;

}