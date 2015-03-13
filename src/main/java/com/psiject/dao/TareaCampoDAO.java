package com.psiject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.psiject.dao.interfaces.ITareaCampoDAO;
import com.psiject.entidades.Campo;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.TareaCampo;

/**
 * The Class TareaCampoDAO.
 */
public class TareaCampoDAO extends AbstractDAO<TareaCampo, Long> implements ITareaCampoDAO {

   /**
    * Instantiates a new tarea campo dao.
    */
   public TareaCampoDAO() {
      this.persistentClass = TareaCampo.class;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.ITareaCampoDAO#findTareaCampo(com.psiject.entidades.Tarea,
    *      com.psiject.entidades.Campo)
    */
   @Override
   public TareaCampo findTareaCampo(final Tarea tarea, final Campo campo) throws Exception {

      TareaCampo res = null;
      try {
         final Session sesion = this.getSession();
         final Criteria criteria = sesion.createCriteria(TareaCampo.class);
         criteria.add(Restrictions.eq("tarea", tarea));
         criteria.add(Restrictions.eq("campo", campo));
         final List<TareaCampo> tc = criteria.list();
         if (tc != null && !(tc.isEmpty())) {
            res = tc.get(0);
         }
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
    * @see com.psiject.dao.interfaces.ITareaCampoDAO#findCamposPorTarea(com.psiject.entidades.Tarea)
    */
   @Override
   public List<Campo> findCamposPorTarea(final Tarea tarea) throws Exception {
      final List<Campo> res = new ArrayList<Campo>();
      try {

         final Session sesion = this.getSession();

         final Criteria criteria = sesion.createCriteria(TareaCampo.class);
         criteria.add(Restrictions.eq("tarea", tarea));
         final List<TareaCampo> tc = criteria.list();
         for (final TareaCampo aux : tc) {
            if (!(res.contains(aux.getCampo()))) {
               res.add(aux.getCampo());
            }
         }
      } catch (final Exception ex) {
         throw new Exception(ex.getMessage(), ex);
      }
      return res;
   }

   @Override
   public List<Tarea> findTareasPorCampo(final Campo campo) throws Exception {
      final List<Tarea> res = new ArrayList<Tarea>();

      try {
         final Session sesion = this.getSession();

         final Criteria criteria = sesion.createCriteria(TareaCampo.class);
         criteria.add(Restrictions.eq("campo", campo));
         final List<TareaCampo> tc = criteria.list();
         for (final TareaCampo aux : tc) {
            if (!(res.contains(aux.getTarea()))) {
               res.add(aux.getTarea());
            }
         }
      } catch (final Exception ex) {
         throw new Exception(ex.getMessage(), ex);
      }
      return res;
   }

}
