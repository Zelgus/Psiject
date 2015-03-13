package com.psiject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.psiject.dao.interfaces.IExpedienteDAO;
import com.psiject.entidades.Expediente;
import com.psiject.entidades.Usuario;

/**
 * The Class ExpedienteDAO.
 */
public class ExpedienteDAO extends AbstractDAO<Expediente, Long> implements IExpedienteDAO {

   /**
    * Instantiates a new expediente dao.
    */
   public ExpedienteDAO() {
      this.persistentClass = Expediente.class;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.IExpedienteDAO#findExpedientesCreadosPorUsuario(com.psiject.entidades.Usuario)
    */
   @Override
   public List<Expediente> findExpedientesCreadosPorUsuario(final Usuario usuario) throws Exception {

      List<Expediente> res = new ArrayList<Expediente>();

      try {

         final Session sesion = this.getSession();
         final Criteria criteria = sesion.createCriteria(Expediente.class);
         criteria.add(Restrictions.eq("usuarioCreador", usuario));
         res = criteria.list();
      } catch (final Exception ex) {
         throw new Exception(ex.getMessage(), ex);
      }
      return res;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.IExpedienteDAO#findExpedientesPorUsuario(com.psiject.entidades.Usuario)
    */
   @Override
   public List<Expediente> findExpedientesPorUsuario(final Usuario usuario) throws Exception {
      List<Expediente> res = new ArrayList<Expediente>();
      try {
         final Session sesion = this.getSession();
         final Criteria criteria = sesion.createCriteria(Expediente.class);
         criteria.add(Restrictions.eq("usuario", usuario));
         res = criteria.list();
      } catch (final Exception ex) {
         throw new Exception(ex.getMessage(), ex);
      }
      return res;
   }

}
