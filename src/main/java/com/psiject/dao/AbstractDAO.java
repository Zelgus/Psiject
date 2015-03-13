package com.psiject.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.psiject.dao.interfaces.IGenericDAO;

/**
 * Clase para control de datos de manera generica.
 * 
 * @param <T>
 *           the generic type
 * @param <ID>
 *           the generic type
 */
public abstract class AbstractDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

   /** The Constant FROM. */
   private static final String FROM = "from ";

   /** The persistent class. */
   protected Class persistentClass;


   /** The session. */
   protected Session session;

   /** The session factory. */
   protected SessionFactory sessionFactory;

   /**
    * Instantiates a new abstract dao.
    */
   @SuppressWarnings("unchecked")
   public AbstractDAO() {
      this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

   }

   /**
    * Gets the persistent class.
    * 
    * @return the persistent class
    */
   public Class getPersistentClass() {
      return this.persistentClass;
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#findById(ID)
    */
   @Override
   @SuppressWarnings("unchecked")
   public T findById(final ID id) throws Exception {
      T entity = null;

      final Session sesion = this.getSession();
      entity = (T) sesion.get(this.getPersistentClass(), id);
      return entity;
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#findByIdWithoutClose(ID)
    */
   @Override
   @SuppressWarnings("unchecked")
   public T findByIdWithoutClose(final ID id) throws Exception {
      T entity = null;

      final Session sesion = this.getSession();
      entity = (T) sesion.get(this.getPersistentClass(), id);
      return entity;
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#findAll()
    */
   @Override
   @SuppressWarnings("unchecked")
   public List<T> findAll() throws Exception {
      List<T> result = null;

      final Session sesion = this.getSession();
      final Query query = sesion.createQuery(FROM + this.persistentClass.getName());
      result = query.list();
      return result;
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#deleteAll()
    */
   @Override
   public void deleteAll() throws Exception {

      final Session sesion = this.getSession();
      final List<T> result = (List<T>) sesion.get(this.getPersistentClass(), null);
      if (result != null) {
         for (final T obj : result) {
            this.delete(obj);
         }
      }

   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#insert(T)
    */
   @Override
   public void insert(final T entity) throws Exception {

      final Session sesion = this.getSession();
      sesion.saveOrUpdate(entity);

   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#insertList(java.util.List)
    */
   @Override
   public void insertList(final List<T> entity) throws Exception {

      final Session sesion = this.getSession();
      sesion.saveOrUpdate(entity);
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#update(T)
    */
   @Override
   public T update(final T entity1) throws Exception {
      T entity = entity1;

      final Session sesion = this.getSession();
      entity = (T) sesion.merge(entity);
      this.getSession().flush();
      return entity;
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#updateList(java.util.List)
    */
   @Override
   public List<T> updateList(final List<T> entity1) throws Exception {
      List<T> entity = entity1;

      final Session sesion = this.getSession();
      entity = (List<T>) sesion.merge(entity);
      this.getSession().flush();
      return entity;
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#saveOrUpdate(T)
    */
   @Override
   public void saveOrUpdate(final T entity) throws Exception {

      final Session sesion = this.getSession();
      sesion.saveOrUpdate(entity);
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#delete(T)
    */
   @Override
   public void delete(final T entity) throws Exception {

      final Session sesion = this.getSession();
      sesion.delete(entity);
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#deleteList(java.util.List)
    */
   @Override
   public void deleteList(final List<T> entity) throws Exception {

      final Session sesion = this.getSession();
      sesion.delete(entity);
   }

   /**
    * {@inheritDoc}
    * 
    * @see com.psiject.dao.interfaces.IGenericDAO#getSession()
    */
   @Override
   public Session getSession() {
      return this.sessionFactory.getCurrentSession();
   }

   /**
    * Set the SessionFactory that this instance should manage transactions for.
    * 
    * @param sessionFactory
    *           the new session factory
    */
   public void setSessionFactory(final SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   /**
    * Return the SessionFactory that this instance should manage transactions
    * for.
    * 
    * @return the session factory
    */
   public SessionFactory getSessionFactory() {
      return this.sessionFactory;
   }

}
