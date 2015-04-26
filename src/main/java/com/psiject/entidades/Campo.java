package com.psiject.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Campo.
 */

@Entity
@Table(name = "campos")
public class Campo implements Serializable{

   // PrimaryKey
   /** The id campo. */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_Campo", unique = true, nullable = false)
   private Long idCampo;

   // Atributos
   /** The nombre. */
   @Column(name = "Nombre", length = 255, unique = true, nullable = false)
   private String nombre;

   /** The descripcion. */
   @Column(name = "Descripcion", length = 255, unique = false, nullable = true)
   private String descripcion;

   // Atributos Referenciados
   /** The tareas. */
   @OneToMany(mappedBy = "campo")
   private Set<TareaCampo> tareas = new HashSet<TareaCampo>();

   /**
    * Instantiates a new campo.
    */
   public Campo() {
      super();
   }

   /**
    * Instantiates a new campo.
    * 
    * @param idCampo
    *           the id campo
    * @param nombre
    *           the nombre
    * @param descripcion
    *           the descripcion
    * @param tareas
    *           the tareas
    */
   public Campo(Long idCampo, String nombre, String descripcion, Set<TareaCampo> tareas) {
      super();
      this.idCampo = idCampo;
      this.nombre = nombre;
      this.descripcion = descripcion;
      this.tareas = tareas;
   }

   /**
    * Gets the id campo.
    * 
    * @return the id campo
    */
   public Long getIdCampo() {
      return idCampo;
   }

   /**
    * Sets the id campo.
    * 
    * @param idCampo
    *           the new id campo
    */
   public void setIdCampo(Long idCampo) {
      this.idCampo = idCampo;
   }

   /**
    * Gets the nombre.
    * 
    * @return the nombre
    */
   public String getNombre() {
      return nombre;
   }

   /**
    * Sets the nombre.
    * 
    * @param nombre
    *           the new nombre
    */
   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   /**
    * Gets the descripcion.
    * 
    * @return the descripcion
    */
   public String getDescripcion() {
      return descripcion;
   }

   /**
    * Sets the descripcion.
    * 
    * @param descripcion
    *           the new descripcion
    */
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   /**
    * Gets the tareas.
    * 
    * @return the tareas
    */
   public Set<TareaCampo> getTareas() {
      return tareas;
   }

   /**
    * Sets the tareas.
    * 
    * @param tareas
    *           the new tareas
    */
   public void setTareas(Set<TareaCampo> tareas) {
      this.tareas = tareas;
   }

   /**
    * {@inheritDoc}
    * 
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((idCampo == null) ? 0 : idCampo.hashCode());
      return result;
   }

   /**
    * {@inheritDoc}
    * 
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      Campo other = (Campo) obj;
      if (idCampo == null) {
         if (other.idCampo != null) {
            return false;
         }
      } else if (!idCampo.equals(other.idCampo)) {
         return false;
      }
      return true;
   }

}
