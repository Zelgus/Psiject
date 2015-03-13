package com.psiject.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.psiject.dao.interfaces.ICampoDAO;
import com.psiject.entidades.Campo;

/**
 * The Class CampoDAO.
 */
public class CampoDAO extends AbstractDAO<Campo, Long> implements ICampoDAO {

   /**
    * Instantiates a new campo dao.
    */
   public CampoDAO() {
      this.persistentClass = Campo.class;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.ICampoDAO#findByNombre(java.lang.String)
    */
   @Override
   public Campo findByNombre(final String nombreCampo) throws Exception {
      Campo res = null;
      try {
         final Session sesion = this.getSession();

         final Criteria criteria = sesion.createCriteria(Campo.class);
         criteria.add(Restrictions.eq("nombre", nombreCampo));
         final List<Campo> campo = criteria.list();
         if (campo != null && !(campo.isEmpty())) {
            res = campo.get(0);
         }
      } catch (final Exception ex) {
         throw new Exception(ex.getMessage(), ex);
      }
      return res;
   }

}
