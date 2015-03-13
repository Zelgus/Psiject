package com.psiject.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class ExpedientePsicologo.
 */

@Entity
@Table(name = "expedientes_psicologos")
public class ExpedientePsicologo {

   // PrimaryKey
   /** The id expediente psicologo. */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_Expediente_Psicologo", unique = true, nullable = false)
   private Long idExpedientePsicologo;

   // ForeingKey
   /** The usuario. */
   @ManyToOne
   @JoinColumn(name = "ID_Usuario", referencedColumnName = "ID_Usuario")
   private Usuario usuario;

   /** The expediente. */
   @ManyToOne
   @JoinColumn(name = "ID_Expediente", referencedColumnName = "ID_Expediente")
   private Expediente expediente;

   /**
    * Instantiates a new expediente psicologo.
    */
   public ExpedientePsicologo() {
      super();
   }

   /**
    * Instantiates a new expediente psicologo.
    * 
    * @param idExpedientePsicologo
    *           the id expediente psicologo
    * @param usuario
    *           the usuario
    * @param expediente
    *           the expediente
    */
   public ExpedientePsicologo(Long idExpedientePsicologo, Usuario usuario, Expediente expediente) {
      super();
      this.idExpedientePsicologo = idExpedientePsicologo;
      this.usuario = usuario;
      this.expediente = expediente;
   }

   /**
    * Gets the id expediente psicologo.
    * 
    * @return the id expediente psicologo
    */
   public Long getIdExpedientePsicologo() {
      return idExpedientePsicologo;
   }

   /**
    * Sets the id expediente psicologo.
    * 
    * @param idExpedientePsicologo
    *           the new id expediente psicologo
    */
   public void setIdExpedientePsicologo(Long idExpedientePsicologo) {
      this.idExpedientePsicologo = idExpedientePsicologo;
   }

   /**
    * Gets the usuario.
    * 
    * @return the usuario
    */
   public Usuario getUsuario() {
      return usuario;
   }

   /**
    * Sets the usuario.
    * 
    * @param usuario
    *           the new usuario
    */
   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   /**
    * Gets the expediente.
    * 
    * @return the expediente
    */
   public Expediente getExpediente() {
      return expediente;
   }

   /**
    * Sets the expediente.
    * 
    * @param expediente
    *           the new expediente
    */
   public void setExpediente(Expediente expediente) {
      this.expediente = expediente;
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
      result = prime * result + ((idExpedientePsicologo == null) ? 0 : idExpedientePsicologo.hashCode());
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
      ExpedientePsicologo other = (ExpedientePsicologo) obj;
      if (idExpedientePsicologo == null) {
         if (other.idExpedientePsicologo != null) {
            return false;
         }
      } else if (!idExpedientePsicologo.equals(other.idExpedientePsicologo)) {
         return false;
      }
      return true;
   }

}
