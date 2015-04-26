package com.psiject.entidades;

import java.io.Serializable;
import java.util.Date;
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
 * The Class Usuario.
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

   // PrimaryKey
   /** The id usuario. */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_Usuario", unique = true, nullable = false)
   private Long idUsuario;

   // Atributos
   /** The usuario. */
   @Column(name = "Usuario", length = 20, unique = true, nullable = false)
   private String usuario;

   /** The contrasena. */
   @Column(name = "Contrasena", length = 255, unique = false, nullable = false)
   private String contrasena;

   /** The correo. */
   @Column(name = "Correo", length = 255, unique = true, nullable = true)
   private String correo;

   /** The permiso. */
   @Column(name = "Permiso", unique = false, nullable = false)
   private Boolean permiso;

   /** The permiso. */
   @Column(name = "Verificacion", unique = false, nullable = true)
   private String verificacion;

   /** The permiso. */
   @Column(name = "Envio", unique = false, nullable = true)
   private Date envio;

   // Atributos Referenciados
   /** The tareas creadas. */
   @OneToMany(mappedBy = "usuarioCreador")
   private Set<Tarea> tareasCreadas = new HashSet<Tarea>();

   /** The comentarios creados. */
   @OneToMany(mappedBy = "usuarioCreador")
   private Set<Comentario> comentariosCreados = new HashSet<Comentario>();

   /** The expedientes. */
   @OneToMany(mappedBy = "usuario")
   private Set<Expediente> expedientes = new HashSet<Expediente>();

   /** The expedientes creados. */
   @OneToMany(mappedBy = "usuarioCreador")
   private Set<Expediente> expedientesCreados = new HashSet<Expediente>();

   /** The expedientes accesibles. */
   @OneToMany(mappedBy = "usuario")
   private Set<ExpedientePsicologo> expedientesAccesibles = new HashSet<ExpedientePsicologo>();

   /**
    * Instantiates a new usuario.
    */
   public Usuario() {
      super();
   }

   /**
    * Instantiates a new usuario.
    * 
    * @param idUsuario
    *           the id usuario
    * @param usuario
    *           the usuario
    * @param contrasena
    *           the contrasena
    * @param correo
    *           the correo
    * @param permiso
    *           the permiso
    * @param tareasCreadas
    *           the tareas creadas
    * @param comentariosCreados
    *           the comentarios creados
    * @param expedientes
    *           the expedientes
    * @param expedientesCreados
    *           the expedientes creados
    * @param expedientesAccesibles
    *           the expedientes accesibles
    */
   public Usuario(final Long idUsuario, final String usuario, final String contrasena, final String correo, final Boolean permiso, final String verificacion, final Date envio,
         final Set<Tarea> tareasCreadas, final Set<Comentario> comentariosCreados, final Set<Expediente> expedientes, final Set<Expediente> expedientesCreados,
         final Set<ExpedientePsicologo> expedientesAccesibles) {
      super();
      this.idUsuario = idUsuario;
      this.usuario = usuario;
      this.contrasena = contrasena;
      this.correo = correo;
      this.permiso = permiso;
      this.verificacion = verificacion;
      this.envio = envio;
      this.tareasCreadas = tareasCreadas;
      this.comentariosCreados = comentariosCreados;
      this.expedientes = expedientes;
      this.expedientesCreados = expedientesCreados;
      this.expedientesAccesibles = expedientesAccesibles;
   }

   /**
    * Gets the id usuario.
    * 
    * @return the id usuario
    */
   public Long getIdUsuario() {
      return this.idUsuario;
   }

   /**
    * Sets the id usuario.
    * 
    * @param idUsuario
    *           the new id usuario
    */
   public void setIdUsuario(final Long idUsuario) {
      this.idUsuario = idUsuario;
   }

   /**
    * Gets the usuario.
    * 
    * @return the usuario
    */
   public String getUsuario() {
      return this.usuario;
   }

   /**
    * Sets the usuario.
    * 
    * @param usuario
    *           the new usuario
    */
   public void setUsuario(final String usuario) {
      this.usuario = usuario;
   }

   /**
    * Gets the contrasena.
    * 
    * @return the contrasena
    */
   public String getContrasena() {
      return this.contrasena;
   }

   /**
    * Sets the contrasena.
    * 
    * @param contrasena
    *           the new contrasena
    */
   public void setContrasena(final String contrasena) {
      this.contrasena = contrasena;
   }

   /**
    * Gets the correo.
    * 
    * @return the correo
    */
   public String getCorreo() {
      return this.correo;
   }

   /**
    * Sets the correo.
    * 
    * @param correo
    *           the new correo
    */
   public void setCorreo(final String correo) {
      this.correo = correo;
   }

   /**
    * Gets the permiso.
    * 
    * @return the permiso
    */
   public Boolean getPermiso() {
      return this.permiso;
   }

   /**
    * Sets the permiso.
    * 
    * @param permiso
    *           the new permiso
    */
   public void setPermiso(final Boolean permiso) {
      this.permiso = permiso;
   }

   /**
    * Gets the tareas creadas.
    * 
    * @return the tareas creadas
    */
   public Set<Tarea> getTareasCreadas() {
      return this.tareasCreadas;
   }

   /**
    * Sets the tareas creadas.
    * 
    * @param tareasCreadas
    *           the new tareas creadas
    */
   public void setTareasCreadas(final Set<Tarea> tareasCreadas) {
      this.tareasCreadas = tareasCreadas;
   }

   /**
    * Gets the comentarios creados.
    * 
    * @return the comentarios creados
    */
   public Set<Comentario> getComentariosCreados() {
      return this.comentariosCreados;
   }

   /**
    * Sets the comentarios creados.
    * 
    * @param comentariosCreados
    *           the new comentarios creados
    */
   public void setComentariosCreados(final Set<Comentario> comentariosCreados) {
      this.comentariosCreados = comentariosCreados;
   }

   /**
    * Gets the expedientes.
    * 
    * @return the expedientes
    */
   public Set<Expediente> getExpedientes() {
      return this.expedientes;
   }

   /**
    * Sets the expedientes.
    * 
    * @param expedientes
    *           the new expedientes
    */
   public void setExpedientes(final Set<Expediente> expedientes) {
      this.expedientes = expedientes;
   }

   /**
    * Gets the expedientes creados.
    * 
    * @return the expedientes creados
    */
   public Set<Expediente> getExpedientesCreados() {
      return this.expedientesCreados;
   }

   /**
    * Sets the expedientes creados.
    * 
    * @param expedientesCreados
    *           the new expedientes creados
    */
   public void setExpedientesCreados(final Set<Expediente> expedientesCreados) {
      this.expedientesCreados = expedientesCreados;
   }

   /**
    * Gets the expedientes accesibles.
    * 
    * @return the expedientes accesibles
    */
   public Set<ExpedientePsicologo> getExpedientesAccesibles() {
      return this.expedientesAccesibles;
   }

   /**
    * Sets the expedientes accesibles.
    * 
    * @param expedientesAccesibles
    *           the new expedientes accesibles
    */
   public void setExpedientesAccesibles(final Set<ExpedientePsicologo> expedientesAccesibles) {
      this.expedientesAccesibles = expedientesAccesibles;
   }

   public String getVerificacion() {
      return this.verificacion;
   }

   public void setVerificacion(final String verificacion) {
      this.verificacion = verificacion;
   }

   public Date getEnvio() {
      return this.envio;
   }

   public void setEnvio(final Date envio) {
      this.envio = envio;
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
      result = prime * result + ((this.idUsuario == null) ? 0 : this.idUsuario.hashCode());
      return result;
   }

   /**
    * {@inheritDoc}
    * 
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (this.getClass() != obj.getClass()) {
         return false;
      }
      final Usuario other = (Usuario) obj;
      if (this.idUsuario == null) {
         if (other.idUsuario != null) {
            return false;
         }
      } else if (!this.idUsuario.equals(other.idUsuario)) {
         return false;
      }
      return true;
   }

}
