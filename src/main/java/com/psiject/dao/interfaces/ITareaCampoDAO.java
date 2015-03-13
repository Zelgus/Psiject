package com.psiject.dao.interfaces;

import java.util.List;

import com.psiject.entidades.Campo;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.TareaCampo;

/**
 * The Interface ITareaCampoDAO.
 */
public interface ITareaCampoDAO extends IGenericDAO<TareaCampo, Long> {

   /**
    * Find tarea campo.
    * 
    * @param tarea
    *           the tarea
    * @param campo
    *           the campo
    * @return the tarea campo
    * @throws Exception
    */
   public TareaCampo findTareaCampo(Tarea tarea, Campo campo) throws Exception;

   /**
    * Find campos por tarea.
    * 
    * @param tarea
    *           the tarea
    * @return the list
    * @throws Exception
    */
   public List<Campo> findCamposPorTarea(Tarea tarea) throws Exception;

   public List<Tarea> findTareasPorCampo(final Campo campo) throws Exception;

}