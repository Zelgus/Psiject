package com.psiject.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

/**
 * The Interface IGenericDAO.
 * 
 * @param <T>
 *           the generic type
 * @param <ID>
 *           the generic type
 */
public interface IGenericDAO<T, ID extends Serializable> {

   /**
    * Find by id.
    * 
    * @param id
    *           the id
    * @return the t
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#findById(java.io.Serializable)
    */
   public T findById(ID id) throws Exception;

   /**
    * Find by id without close.
    * 
    * @param id
    *           the id
    * @return the t
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#findByIdWithoutClose(java.io.Serializable)
    */
   public T findByIdWithoutClose(ID id) throws Exception;

   /**
    * Find all.
    * 
    * @return the list
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#findAll()
    */
   public List<T> findAll() throws Exception;

   /**
    * Delete all.
    * 
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#deleteAll()
    */
   public void deleteAll() throws Exception;

   /**
    * Insert.
    * 
    * @param entity
    *           the entity
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#insert(java.lang.Object)
    */
   public void insert(T entity) throws Exception;

   /**
    * Insertar una lista de elementos.
    * 
    * @param entity
    *           the entity
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#insert(java.lang.Object)
    */
   public void insertList(List<T> entity) throws Exception;

   /**
    * Update.
    * 
    * @param entity1
    *           the entity
    * @return the t
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#update(java.lang.Object)
    */
   public T update(T entity1) throws Exception;

   /**
    * Update lista de elementos.
    * 
    * @param entity1
    *           the entity
    * @return the list
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#update(java.lang.Object)
    */
   public List<T> updateList(List<T> entity1) throws Exception;

   /**
    * Save or update.
    * 
    * @param entity
    *           the entity
    * @throws Exception
    *            the architecture exception
    */
   public void saveOrUpdate(T entity) throws Exception;

   /**
    * Delete.
    * 
    * @param entity
    *           the entity
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#delete(java.lang.Object)
    */
   public void delete(T entity) throws Exception;

   /**
    * Delete una lista de elementos.
    * 
    * @param entity
    *           the entity
    * @throws Exception
    *            the architecture exception
    * @see es.juntadeandalucia.plataforma.modulos.dao.IGenericDAO#delete(java.lang.Object)
    */
   public void deleteList(List<T> entity) throws Exception;

   /**
    * Gets the session.
    * 
    * @return the session
    */
   public Session getSession();

}