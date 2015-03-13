package com.psiject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.psiject.dao.interfaces.IExpedientePsicologoDAO;
import com.psiject.entidades.Expediente;
import com.psiject.entidades.ExpedientePsicologo;
import com.psiject.entidades.Usuario;

/**
 * The Class ExpedientePsicologoDAO.
 */
public class ExpedientePsicologoDAO extends AbstractDAO<ExpedientePsicologo, Long> implements IExpedientePsicologoDAO {

   /**
    * Instantiates a new expediente psicologo dao.
    */
   public ExpedientePsicologoDAO() {
      this.persistentClass = ExpedientePsicologo.class;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.IExpedientePsicologoDAO#findExpedientePsicologo(com.psiject.entidades.Expediente,
    *      com.psiject.entidades.Usuario)
    */
   public ExpedientePsicologo findExpedientePsicologo(Expediente expediente, Usuario psicologo) throws Exception {
      ExpedientePsicologo res = null;
      try {
         Session sesion = getSession();
         Criteria criteria = sesion.createCriteria(ExpedientePsicologo.class);
         criteria.add(Restrictions.eq("expediente", expediente));
         criteria.add(Restrictions.eq("usuario", psicologo));
         List<ExpedientePsicologo> ep = criteria.list();
         if (ep != null && !(ep.isEmpty())) {
            res = ep.get(0);
         }
      } catch (Exception e) {
         throw new Exception(e.getMessage(), e);
      }

      return res;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.IExpedientePsicologoDAO#findExpedienteAccesiblesPorUsuario(com.psiject.entidades.Usuario)
    */
   public List<Expediente> findExpedientesAccesiblesPorPsicologo(Usuario psicologo) throws Exception {
      List<Expediente> res = new ArrayList<Expediente>();
      try {
         Session sesion = getSession();
         Criteria criteria = sesion.createCriteria(ExpedientePsicologo.class);
         criteria.add(Restrictions.eq("usuario", psicologo));
         List<ExpedientePsicologo> ep = criteria.list();
         for (ExpedientePsicologo aux : ep) {
            if (!(res.contains(aux.getUsuario()))) {
               res.add(aux.getExpediente());
            }
         }
      } catch (Exception e) {
         throw new Exception(e.getMessage(), e);
      }
      return res;
   }

   /**
    * Find usuarios con acceso por expediente.
    * 
    * @param expediente
    *           the expediente
    * @return the list
    * @throws Exception
    */
   public List<Usuario> findPsicologosConAccesoPorExpediente(Expediente expediente) throws Exception {
      List<Usuario> res = new ArrayList<Usuario>();
      try {
         Session sesion = getSession();
         Criteria criteria = sesion.createCriteria(ExpedientePsicologo.class);
         criteria.add(Restrictions.eq("expediente", expediente));
         List<ExpedientePsicologo> ep = criteria.list();
         for (ExpedientePsicologo aux : ep) {
            if (!(res.contains(aux.getUsuario()))) {
               res.add(aux.getUsuario());
            }
         }
      } catch (Exception e) {
         throw new Exception(e.getMessage(), e);
      }
      return res;
   }

}
