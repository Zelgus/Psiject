package com.psiject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.psiject.dao.interfaces.IComentarioDAO;
import com.psiject.entidades.Comentario;
import com.psiject.entidades.TareaCampo;

/**
 * The Class ComentarioDAO.
 */
public class ComentarioDAO extends AbstractDAO<Comentario, Long> implements IComentarioDAO {

   /**
    * Instantiates a new comentario dao.
    */
   public ComentarioDAO() {
      this.persistentClass = Comentario.class;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.IComentarioDAO#findComentariosPorTareaCampo(com.psiject.entidades.TareaCampo)
    */
   @Override
   public List<Comentario> findComentariosPorTareaCampo(TareaCampo tareaCampo) throws Exception {
      List<Comentario> res = new ArrayList<Comentario>();
      try {
         Session sesion = getSession();
         Criteria criteria = sesion.createCriteria(Comentario.class);
         criteria.add(Restrictions.eq("tareaCampo", tareaCampo));
         res = criteria.list();
      } catch (Exception e) {
         throw new Exception(e.getMessage(), e);
      }

      return res;
   }

}
