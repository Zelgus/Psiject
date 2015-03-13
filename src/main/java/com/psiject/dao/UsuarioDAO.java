package com.psiject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.psiject.dao.interfaces.IUsuarioDAO;
import com.psiject.entidades.Usuario;

/**
 * The Class UsuarioDAO.
 */
public class UsuarioDAO extends AbstractDAO<Usuario, Long> implements IUsuarioDAO {

   /**
    * Instantiates a new usuario dao.
    */
   public UsuarioDAO() {
      this.persistentClass = Usuario.class;
   }

   /**
    * {@inheritDoc}
    * 
    * @throws Exception
    * 
    * @see com.psiject.dao.interfaces.IUsuarioDAO#findByUsuario(java.lang.String)
    */
   @Override
   public Usuario findByUsuario(final String usuario) throws Exception {
      Usuario res = null;

      try {
         final Session sesion = this.getSession();

         final Criteria criteria = sesion.createCriteria(Usuario.class);
         criteria.add(Restrictions.eq("usuario", usuario));
         final List<Usuario> user = criteria.list();
         if (user != null && !(user.isEmpty())) {
            res = user.get(0);
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
    * @see com.psiject.dao.interfaces.IUsuarioDAO#findPsicologos()
    */
   @Override
   public List<Usuario> findPsicologos() throws Exception {

      List<Usuario> res = new ArrayList<Usuario>();

      try {
         final Session sesion = this.getSession();
         final Criteria criteria = sesion.createCriteria(Usuario.class);
         criteria.add(Restrictions.eq("permiso", true));
         res = criteria.list();
      } catch (final Exception ex) {
         throw new Exception(ex.getMessage(), ex);
      }

      return res;
   }

   @Override
   public Usuario findByCorreo(final String correo) throws Exception {
      Usuario res = null;

      try {
         final Session sesion = this.getSession();

         final Criteria criteria = sesion.createCriteria(Usuario.class);
         criteria.add(Restrictions.eq("correo", correo));
         final List<Usuario> user = criteria.list();
         if (user != null && !(user.isEmpty())) {
            res = user.get(0);
         }
      } catch (final Exception ex) {
         throw new Exception(ex.getMessage(), ex);
      }
      return res;
   }
}
