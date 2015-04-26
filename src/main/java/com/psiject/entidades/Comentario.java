package com.psiject.entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Comentario.
 */

@Entity
@Table(name = "comentarios")
public class Comentario implements Serializable{

   // PrimaryKey
   /** The id comentario. */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_Comentario", unique = true, nullable = false)
   private Long idComentario;

   // ForeingKey
   /** The tarea_ campo. */
   @ManyToOne
   @JoinColumn(name = "ID_Tarea_Campo", referencedColumnName = "ID_Tarea_Campo")
   private TareaCampo tareaCampo;

   /** The usuario_ creador. */
   @ManyToOne
   @JoinColumn(name = "ID_Usuario_Creador", referencedColumnName = "ID_Usuario")
   private Usuario usuarioCreador;

   // Atributos
   /** The comentario. */
   @Column(name = "Comentario", length = 1000, unique = false, nullable = false)
   private String comentario;

   /** The hora. */
   @Column(name = "Hora", unique = false, nullable = false)
   private Date hora;

   /** The visto. */
   @Column(name = "Visto", unique = false, nullable = false)
   private Boolean visto;

   /**
    * Instantiates a new comentario.
    */
   public Comentario() {
      super();
   }

   /**
    * Instantiates a new comentario.
    * 
    * @param idComentario
    *           the id comentario
    * @param tareaCampo
    *           the tarea campo
    * @param usuarioCreador
    *           the usuario creador
    * @param comentario
    *           the comentario
    * @param hora
    *           the hora
    * @param visto
    *           the visto
    */
   public Comentario(Long idComentario, TareaCampo tareaCampo, Usuario usuarioCreador, String comentario, Date hora, Boolean visto) {
      super();
      this.idComentario = idComentario;
      this.tareaCampo = tareaCampo;
      this.usuarioCreador = usuarioCreador;
      this.comentario = comentario;
      this.hora = hora;
      this.visto = visto;
   }

   /**
    * Gets the id comentario.
    * 
    * @return the id comentario
    */
   public Long getIdComentario() {
      return idComentario;
   }

   /**
    * Sets the id comentario.
    * 
    * @param idComentario
    *           the new id comentario
    */
   public void setIdComentario(Long idComentario) {
      this.idComentario = idComentario;
   }

   /**
    * Gets the tarea campo.
    * 
    * @return the tarea campo
    */
   public TareaCampo getTareaCampo() {
      return tareaCampo;
   }

   /**
    * Sets the tarea campo.
    * 
    * @param tareaCampo
    *           the new tarea campo
    */
   public void setTareaCampo(TareaCampo tareaCampo) {
      this.tareaCampo = tareaCampo;
   }

   /**
    * Gets the usuario creador.
    * 
    * @return the usuario creador
    */
   public Usuario getUsuarioCreador() {
      return usuarioCreador;
   }

   /**
    * Sets the usuario creador.
    * 
    * @param usuarioCreador
    *           the new usuario creador
    */
   public void setUsuarioCreador(Usuario usuarioCreador) {
      this.usuarioCreador = usuarioCreador;
   }

   /**
    * Gets the comentario.
    * 
    * @return the comentario
    */
   public String getComentario() {
      return comentario;
   }

   /**
    * Sets the comentario.
    * 
    * @param comentario
    *           the new comentario
    */
   public void setComentario(String comentario) {
      this.comentario = comentario;
   }

   /**
    * Gets the hora.
    * 
    * @return the hora
    */
   public Date getHora() {
      return hora;
   }

   /**
    * Sets the hora.
    * 
    * @param hora
    *           the new hora
    */
   public void setHora(Date hora) {
      this.hora = hora;
   }

   /**
    * Gets the visto.
    * 
    * @return the visto
    */
   public Boolean getVisto() {
      return visto;
   }

   /**
    * Sets the visto.
    * 
    * @param visto
    *           the new visto
    */
   public void setVisto(Boolean visto) {
      this.visto = visto;
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
      result = prime * result + ((idComentario == null) ? 0 : idComentario.hashCode());
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
      Comentario other = (Comentario) obj;
      if (idComentario == null) {
         if (other.idComentario != null) {
            return false;
         }
      } else if (!idComentario.equals(other.idComentario)) {
         return false;
      }
      return true;
   }
   
   public String getHoraString(){
		SimpleDateFormat sdf = new SimpleDateFormat("'Escrito el 'dd/MM/yyyy 'a las ' HH:mm ' horas'");
		return sdf.format(this.getHora());
	}

}
