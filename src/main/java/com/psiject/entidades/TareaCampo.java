package com.psiject.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class TareaCampo.
 */

@Entity
@Table(name = "tareas_campos")
public class TareaCampo implements Serializable{

   // PrimaryKey
   /** The id tarea campo. */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_Tarea_Campo", unique = true, nullable = false)
   private Long idTareaCampo;

   // ForeingKey
   /** The tarea. */
   @ManyToOne
   @JoinColumn(name = "ID_Tarea", referencedColumnName = "ID_Tarea")
   private Tarea tarea;

   /** The campo. */
   @ManyToOne
   @JoinColumn(name = "ID_Campo", referencedColumnName = "ID_Campo")
   private Campo campo;

   // Atribtos Referenciados
   /** The comentarios. */
   @OneToMany(mappedBy = "tareaCampo")
   private Set<Comentario> comentarios = new HashSet<Comentario>();

   /**
    * Instantiates a new tarea campo.
    */
   public TareaCampo() {
      super();
   }

   /**
    * Instantiates a new tarea campo.
    * 
    * @param idTareaCampo
    *           the id tarea campo
    * @param tarea
    *           the tarea
    * @param campo
    *           the campo
    * @param comentarios
    *           the comentarios
    */
   public TareaCampo(Long idTareaCampo, Tarea tarea, Campo campo, Set<Comentario> comentarios) {
      super();
      this.idTareaCampo = idTareaCampo;
      this.tarea = tarea;
      this.campo = campo;
      this.comentarios = comentarios;
   }

   /**
    * Gets the id tarea campo.
    * 
    * @return the id tarea campo
    */
   public Long getIdTareaCampo() {
      return idTareaCampo;
   }

   /**
    * Sets the id tarea campo.
    * 
    * @param idTareaCampo
    *           the new id tarea campo
    */
   public void setIdTareaCampo(Long idTareaCampo) {
      this.idTareaCampo = idTareaCampo;
   }

   /**
    * Gets the tarea.
    * 
    * @return the tarea
    */
   public Tarea getTarea() {
      return tarea;
   }

   /**
    * Sets the tarea.
    * 
    * @param tarea
    *           the new tarea
    */
   public void setTarea(Tarea tarea) {
      this.tarea = tarea;
   }

   /**
    * Gets the campo.
    * 
    * @return the campo
    */
   public Campo getCampo() {
      return campo;
   }

   /**
    * Sets the campo.
    * 
    * @param campo
    *           the new campo
    */
   public void setCampo(Campo campo) {
      this.campo = campo;
   }

   /**
    * Gets the comentarios.
    * 
    * @return the comentarios
    */
   public Set<Comentario> getComentarios() {
      return comentarios;
   }

   /**
    * Sets the comentarios.
    * 
    * @param comentarios
    *           the new comentarios
    */
   public void setComentarios(Set<Comentario> comentarios) {
      this.comentarios = comentarios;
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
      result = prime * result + ((idTareaCampo == null) ? 0 : idTareaCampo.hashCode());
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
      TareaCampo other = (TareaCampo) obj;
      if (idTareaCampo == null) {
         if (other.idTareaCampo != null) {
            return false;
         }
      } else if (!idTareaCampo.equals(other.idTareaCampo)) {
         return false;
      }
      return true;
   }

}
