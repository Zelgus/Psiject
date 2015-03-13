package com.psiject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.psiject.dao.interfaces.ITareaDAO;
import com.psiject.entidades.Expediente;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.Usuario;

/**
 * The Class TareaDAO.
 */
public class TareaDAO extends AbstractDAO<Tarea, Long> implements ITareaDAO {

   /**
    * Instantiates a new tarea dao.
    */
   public TareaDAO() {
      this.persistentClass = Tarea.class;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.ITareaDAO#findTareasPorExpediente(com.psiject.entidades.Expediente)
    */
   @Override
   public List<Tarea> findTareasPorExpediente(final Expediente expediente) throws Exception {
      List<Tarea> res = new ArrayList<Tarea>();
      try {
         final Session sesion = this.getSession();
         final Criteria criteria = sesion.createCriteria(Tarea.class);
         criteria.add(Restrictions.eq("expediente", expediente));
         res = criteria.list();
      } catch (final Exception e) {
         throw new Exception(e.getMessage(), e);
      }

      return res;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.ITareaDAO#findTareasPorExpedienteYCreador(com.psiject.entidades.Expediente,
    *      com.psiject.entidades.Usuario)
    */
   @Override
   public List<Tarea> findTareasPorExpedienteYCreador(final Expediente expediente, final Usuario creador) throws Exception {
      List<Tarea> res = new ArrayList<Tarea>();
      try {
         final Session sesion = this.getSession();
         final Criteria criteria = sesion.createCriteria(Tarea.class);
         criteria.add(Restrictions.eq("expediente", expediente));
         criteria.add(Restrictions.eq("usuarioCreador", creador));
         res = criteria.list();
      } catch (final Exception e) {
         throw new Exception(e.getMessage(), e);
      }

      return res;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.ITareaDAO#findTareasPorCreador(com.psiject.entidades.Usuario)
    */
   @Override
   public List<Tarea> findTareasPorCreador(final Usuario creador) throws Exception {
      List<Tarea> res = new ArrayList<Tarea>();
      try {
         final Session sesion = this.getSession();
         final Criteria criteria = sesion.createCriteria(Tarea.class);
         criteria.add(Restrictions.eq("usuarioCreador", creador));
         res = criteria.list();
      } catch (final Exception e) {
         throw new Exception(e.getMessage(), e);

      }

      return res;
   }

}
