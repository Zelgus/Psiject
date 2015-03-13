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
 * The Class Tarea.
 */

@Entity
@Table(name = "tareas")
public class Tarea {

   // PrimaryKey
   /** The id tarea. */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID_Tarea", unique = true, nullable = false)
   private Long idTarea;

   // ForeingKey
   /** The expediente. */
   @ManyToOne
   @JoinColumn(name = "ID_Expediente", referencedColumnName = "ID_Expediente")
   private Expediente expediente;

   /** The usuario creador. */
   @ManyToOne
   @JoinColumn(name = "ID_Usuario_Creador", referencedColumnName = "ID_Usuario")
   private Usuario usuarioCreador;

   // Atributos
   /** The titulo. */
   @Column(name = "Titulo", length = 255, unique = false, nullable = false)
   private String titulo;

   /** The descripcion. */
   @Column(name = "Descripcion", length = 255, unique = false, nullable = true)
   private String descripcion;

   /** The importante. */
   @Column(name = "Importante", unique = false, nullable = false)
   private Boolean importante;

   /** The conclusiones paciente. */
   @Column(name = "Conclusiones_Paciente", length = 1000, unique = false, nullable = true)
   private String conclusionesPaciente;

   /** The conclusiones psicologo. */
   @Column(name = "Conclusiones_Psicologo", length = 1000, unique = false, nullable = true)
   private String conclusionesPsicologo;

   /** The vista. */
   @Column(name = "Vista", unique = false, nullable = false)
   private Boolean vista;

   /** The completada. */
   @Column(name = "Completada", unique = false, nullable = false)
   private Boolean completada;

   // Atributos Referenciados
   /** The campos disponibles. */
   @OneToMany(mappedBy = "tarea")
   private Set<TareaCampo> camposDisponibles = new HashSet<TareaCampo>();

   /**
    * Instantiates a new tarea.
    */
   public Tarea() {
      super();
   }

   /**
    * Instantiates a new tarea.
    * 
    * @param idTarea
    *           the id tarea
    * @param expediente
    *           the expediente
    * @param usuarioCreador
    *           the usuario creador
    * @param titulo
    *           the titulo
    * @param descripcion
    *           the descripcion
    * @param importante
    *           the importante
    * @param conclusionesPaciente
    *           the conclusiones paciente
    * @param conclusionesPsicologo
    *           the conclusiones psicologo
    * @param vista
    *           the vista
    * @param completada
    *           the completada
    * @param camposDisponibles
    *           the campos disponibles
    */
   public Tarea(Long idTarea, Expediente expediente, Usuario usuarioCreador, String titulo, String descripcion, Boolean importante, String conclusionesPaciente,
         String conclusionesPsicologo, Boolean vista, Boolean completada, Set<TareaCampo> camposDisponibles) {
      super();
      this.idTarea = idTarea;
      this.expediente = expediente;
      this.usuarioCreador = usuarioCreador;
      this.titulo = titulo;
      this.descripcion = descripcion;
      this.importante = importante;
      this.conclusionesPaciente = conclusionesPaciente;
      this.conclusionesPsicologo = conclusionesPsicologo;
      this.vista = vista;
      this.completada = completada;
      this.camposDisponibles = camposDisponibles;
   }

   /**
    * Gets the id tarea.
    * 
    * @return the id tarea
    */
   public Long getIdTarea() {
      return idTarea;
   }

   /**
    * Sets the id tarea.
    * 
    * @param idTarea
    *           the new id tarea
    */
   public void setIdTarea(Long idTarea) {
      this.idTarea = idTarea;
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
    * Gets the titulo.
    * 
    * @return the titulo
    */
   public String getTitulo() {
      return titulo;
   }

   /**
    * Sets the titulo.
    * 
    * @param titulo
    *           the new titulo
    */
   public void setTitulo(String titulo) {
      this.titulo = titulo;
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
    * Gets the importante.
    * 
    * @return the importante
    */
   public Boolean getImportante() {
      return importante;
   }

   /**
    * Sets the importante.
    * 
    * @param importante
    *           the new importante
    */
   public void setImportante(Boolean importante) {
      this.importante = importante;
   }

   /**
    * Gets the conclusiones paciente.
    * 
    * @return the conclusiones paciente
    */
   public String getConclusionesPaciente() {
      return conclusionesPaciente;
   }

   /**
    * Sets the conclusiones paciente.
    * 
    * @param conclusionesPaciente
    *           the new conclusiones paciente
    */
   public void setConclusionesPaciente(String conclusionesPaciente) {
      this.conclusionesPaciente = conclusionesPaciente;
   }

   /**
    * Gets the conclusiones psicologo.
    * 
    * @return the conclusiones psicologo
    */
   public String getConclusionesPsicologo() {
      return conclusionesPsicologo;
   }

   /**
    * Sets the conclusiones psicologo.
    * 
    * @param conclusionesPsicologo
    *           the new conclusiones psicologo
    */
   public void setConclusionesPsicologo(String conclusionesPsicologo) {
      this.conclusionesPsicologo = conclusionesPsicologo;
   }

   /**
    * Gets the vista.
    * 
    * @return the vista
    */
   public Boolean getVista() {
      return vista;
   }

   /**
    * Sets the vista.
    * 
    * @param vista
    *           the new vista
    */
   public void setVista(Boolean vista) {
      this.vista = vista;
   }

   /**
    * Gets the completada.
    * 
    * @return the completada
    */
   public Boolean getCompletada() {
      return completada;
   }

   /**
    * Sets the completada.
    * 
    * @param completada
    *           the new completada
    */
   public void setCompletada(Boolean completada) {
      this.completada = completada;
   }

   /**
    * Gets the campos disponibles.
    * 
    * @return the campos disponibles
    */
   public Set<TareaCampo> getCamposDisponibles() {
      return camposDisponibles;
   }

   /**
    * Sets the campos disponibles.
    * 
    * @param camposDisponibles
    *           the new campos disponibles
    */
   public void setCamposDisponibles(Set<TareaCampo> camposDisponibles) {
      this.camposDisponibles = camposDisponibles;
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
      result = prime * result + ((idTarea == null) ? 0 : idTarea.hashCode());
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
      Tarea other = (Tarea) obj;
      if (idTarea == null) {
         if (other.idTarea != null) {
            return false;
         }
      } else if (!idTarea.equals(other.idTarea)) {
         return false;
      }
      return true;
   }

}
