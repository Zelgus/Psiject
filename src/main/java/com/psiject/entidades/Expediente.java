package com.psiject.entidades;

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
 * The Class Expediente.
 */

@Entity
@Table(name = "expedientes")
public class Expediente {

   // PrimaryKey
   /** The id expediente. */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_Expediente", unique = true, nullable = false)
   private Long idExpediente;

   // ForeingKey
   /** The usuario. */
   @ManyToOne
   @JoinColumn(name = "ID_Usuario", referencedColumnName = "ID_Usuario")
   private Usuario usuario;

   /** The usuario_ creador. */
   @ManyToOne
   @JoinColumn(name = "ID_Usuario_Creador", referencedColumnName = "ID_Usuario")
   private Usuario usuarioCreador;

   // Atributos
   /** The nombre. */
   @Column(name = "Nombre", length = 255, unique = false, nullable = false)
   private String nombre;

   /** The apellidos. */
   @Column(name = "Apellidos", length = 255, unique = false, nullable = true)
   private String apellidos;

   /** The cerrado. */
   @Column(name = "Cerrado", unique = false, nullable = false)
   private Boolean cerrado;

   // Atributos Referenciados
   /** The tareas. */
   @OneToMany(mappedBy = "expediente")
   private Set<Tarea> tareas = new HashSet<Tarea>();

   /** The psicologos accesibles. */
   @OneToMany(mappedBy = "expediente")
   private Set<ExpedientePsicologo> psicologosAccesibles = new HashSet<ExpedientePsicologo>();

   /**
    * Instantiates a new expediente.
    */
   public Expediente() {
      super();
   }

   /**
    * Instantiates a new expediente.
    * 
    * @param idExpediente
    *           the id expediente
    * @param usuario
    *           the usuario
    * @param usuarioCreador
    *           the usuario_ creador
    * @param nombre
    *           the nombre
    * @param apellidos
    *           the apellidos
    * @param dni
    *           the dni
    * @param cerrado
    *           the cerrado
    * @param tareas
    *           the tareas
    * @param psicologosAccesibles
    *           the psicologos accesibles
    */
   public Expediente(Long idExpediente, Usuario usuario, Usuario usuarioCreador, String nombre, String apellidos, Boolean cerrado, Set<Tarea> tareas,
         Set<ExpedientePsicologo> psicologosAccesibles) {
      super();
      this.idExpediente = idExpediente;
      this.usuario = usuario;
      this.usuarioCreador = usuarioCreador;
      this.nombre = nombre;
      this.apellidos = apellidos;
      this.cerrado = cerrado;
      this.tareas = tareas;
      this.psicologosAccesibles = psicologosAccesibles;
   }

   /**
    * Gets the id expediente.
    * 
    * @return the id expediente
    */
   public Long getIdExpediente() {
      return idExpediente;
   }

   /**
    * Sets the id expediente.
    * 
    * @param idExpediente
    *           the new id expediente
    */
   public void setIdExpediente(Long idExpediente) {
      this.idExpediente = idExpediente;
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
    * Gets the apellidos.
    * 
    * @return the apellidos
    */
   public String getApellidos() {
      return apellidos;
   }

   /**
    * Sets the apellidos.
    * 
    * @param apellidos
    *           the new apellidos
    */
   public void setApellidos(String apellidos) {
      this.apellidos = apellidos;
   }

   /**
    * Gets the cerrado.
    * 
    * @return the cerrado
    */
   public Boolean getCerrado() {
      return cerrado;
   }

   /**
    * Sets the cerrado.
    * 
    * @param cerrado
    *           the new cerrado
    */
   public void setCerrado(Boolean cerrado) {
      this.cerrado = cerrado;
   }

   /**
    * Gets the tareas.
    * 
    * @return the tareas
    */
   public Set<Tarea> getTareas() {
      return tareas;
   }

   /**
    * Sets the tareas.
    * 
    * @param tareas
    *           the new tareas
    */
   public void setTareas(Set<Tarea> tareas) {
      this.tareas = tareas;
   }

   /**
    * Gets the psicologos accesibles.
    * 
    * @return the psicologos accesibles
    */
   public Set<ExpedientePsicologo> getPsicologosAccesibles() {
      return psicologosAccesibles;
   }

   /**
    * Sets the psicologos accesibles.
    * 
    * @param psicologosAccesibles
    *           the new psicologos accesibles
    */
   public void setPsicologosAccesibles(Set<ExpedientePsicologo> psicologosAccesibles) {
      this.psicologosAccesibles = psicologosAccesibles;
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
      result = prime * result + ((idExpediente == null) ? 0 : idExpediente.hashCode());
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
      Expediente other = (Expediente) obj;
      if (idExpediente == null) {
         if (other.idExpediente != null) {
            return false;
         }
      } else if (!idExpediente.equals(other.idExpediente)) {
         return false;
      }
      return true;
   }

}
