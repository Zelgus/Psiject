package com.psiject.dao.interfaces;

import java.util.List;

import com.psiject.entidades.Expediente;
import com.psiject.entidades.ExpedientePsicologo;
import com.psiject.entidades.Usuario;

/**
 * The Interface IExpedientePsicologoDAO.
 */
public interface IExpedientePsicologoDAO extends IGenericDAO<ExpedientePsicologo, Long> {

   /**
    * Find expediente psicologo.
    * 
    * @param expediente
    *           the expediente
    * @param psicologo
    *           the psicologo
    * @return the expediente psicologo
    * @throws Exception
    */
   public ExpedientePsicologo findExpedientePsicologo(Expediente expediente, Usuario psicologo) throws Exception;

   /**
    * Find expediente accesibles por usuario.
    * 
    * @param psicologo
    *           the psicologo
    * @return the list
    * @throws Exception
    */
   public List<Expediente> findExpedientesAccesiblesPorPsicologo(Usuario psicologo) throws Exception;

   /**
    * Find usuarios con acceso por expediente.
    * 
    * @param expediente
    *           the expediente
    * @return the list
    * @throws Exception
    */
   public List<Usuario> findPsicologosConAccesoPorExpediente(Expediente expediente) throws Exception;

}