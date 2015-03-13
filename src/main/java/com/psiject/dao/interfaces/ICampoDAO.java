package com.psiject.dao.interfaces;

import com.psiject.entidades.Campo;

/**
 * The Interface ICampoDAO.
 */

public interface ICampoDAO extends IGenericDAO<Campo, Long> {

   /**
    * Find by nombre.
    * 
    * @param nombreCampo
    *           the nombre campo
    * @return the campo
    * @throws Exception
    */
   public Campo findByNombre(String nombreCampo) throws Exception;

}