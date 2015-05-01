package com.psiject.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.psiject.entidades.Comentario;
import com.psiject.entidades.Expediente;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.Usuario;
import com.psiject.services.interfaces.IComentarioService;
import com.psiject.services.interfaces.IExpedientePsicologoService;
import com.psiject.services.interfaces.ITareaCampoService;
import com.psiject.services.interfaces.ITareaService;
import com.psiject.utilidades.Mensajes;

@ManagedBean(name = "renderingMB")
@SessionScoped
public class RenderingManagedBean implements Serializable {

   private static final long serialVersionUID = 32110930003837399L;

   @ManagedProperty("#{sessionMB}")
   private SessionManagedBean sessionMB;

   @ManagedProperty(value = "#{TareaService}")
   private ITareaService tareaService;

   @ManagedProperty(value = "#{TareaCampoService}")
   private ITareaCampoService tareaCampoService;

   @ManagedProperty(value = "#{ExpedientePsicologoService}")
   private IExpedientePsicologoService expedientePsicologoService;

   @ManagedProperty(value = "#{ComentarioService}")
   private IComentarioService comentarioService;

   public RenderingManagedBean() {
      super();
   }

   public Boolean getBotonEditarCuenta() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      if (usuarioEnSesion != null) {
         res = true;
      }
      return res;
   }

   public Boolean getBotonCerrarSesion() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      if (usuarioEnSesion != null) {
         res = true;
      }
      return res;
   }

   public Boolean getBotonGestionCampos() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      if (usuarioEnSesion != null && usuarioEnSesion.getPermiso()) {
         res = true;
      }
      return res;
   }

   public Boolean botonComentarPaciente(final Tarea tarea) {
      Boolean res = false;
      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && !usuarioEnSesion.getPermiso() && !expedienteSeleccionado.getCerrado()
               && !tarea.getCompletada() && !tareaCampoService.obtenerCamposPorTarea(tarea).isEmpty() && tarea.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonComentarPaciente() {
      Boolean res = false;
      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && !usuarioEnSesion.getPermiso() && !expedienteSeleccionado.getCerrado()
               && !tareaSeleccionada.getCompletada() && !tareaCampoService.obtenerCamposPorTarea(tareaSeleccionada).isEmpty()
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBarraNavegacionWizard() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && !usuarioEnSesion.getPermiso() && !expedienteSeleccionado.getCerrado()
               && !tareaSeleccionada.getCompletada() && tareaCampoService.obtenerCamposPorTarea(tareaSeleccionada).size() > 1
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean botonComentarPsicologo(final Tarea tarea) {
      Boolean res = false;
      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && usuarioEnSesion.getPermiso() && !expedienteSeleccionado.getCerrado()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !tarea.getCompletada()
               && tarea.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonComentarPsicologo() {
      Boolean res = false;
      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso() && !expedienteSeleccionado.getCerrado()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !tareaSeleccionada.getCompletada()
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }
   
   public Boolean botonConsultarComentario(Comentario comentario) {
	      Boolean res = false;
	      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
	      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
	      Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
	      if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && comentario != null
	            && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado) && !comentario.getComentario().equals("-")) {
	         res = true;
	      }
	      return res;
	   }

   public Boolean getBotonEditarComentario() {
      Boolean res = false;
      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      Comentario comentarioSeleccionado = this.sessionMB.getComentarioSeleccionado();
      if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && comentarioSeleccionado != null && !expedienteSeleccionado.getCerrado()
            && !tareaSeleccionada.getCompletada() && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)
            && comentarioSeleccionado.getUsuarioCreador().equals(usuarioEnSesion)) {
         res = true;
      }
      return res;
   }

   public Boolean botonEditarComentario(Comentario comentario) {
      Boolean res = false;
      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && comentario != null && !expedienteSeleccionado.getCerrado()
            && !tareaSeleccionada.getCompletada() && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado) && comentario.getUsuarioCreador().equals(usuarioEnSesion)) {
         res = true;
      }
      return res;
   }

   public Boolean getBotonBorrarComentario() {
      Boolean res = false;
      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      Comentario comentario = this.sessionMB.getComentarioSeleccionado();
      if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && comentario != null && !expedienteSeleccionado.getCerrado()
            && !tareaSeleccionada.getCompletada() && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado) && comentario.getUsuarioCreador().equals(usuarioEnSesion)) {
         res = true;
      }
      return res;
   }

   public Boolean botonBorrarComentario(Comentario comentario) {
      Boolean res = false;
      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && comentario != null && !expedienteSeleccionado.getCerrado()
            && !tareaSeleccionada.getCompletada() && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado) && comentario.getUsuarioCreador().equals(usuarioEnSesion)) {
         res = true;
      }
      return res;
   }

   public Boolean getBotonEditarExpediente() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && usuarioEnSesion.getPermiso() && expedienteSeleccionado.getUsuarioCreador().equals(usuarioEnSesion)
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean botonEditarExpediente(final Expediente expediente) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      try {
         if (usuarioEnSesion != null && expediente != null && usuarioEnSesion.getPermiso() && expediente.getUsuarioCreador().equals(usuarioEnSesion)
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expediente).contains(usuarioEnSesion) && !expediente.getCerrado()) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonModificarPermisos() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null & expedienteSeleccionado != null && usuarioEnSesion.getPermiso() && expedienteSeleccionado.getUsuarioCreador().equals(usuarioEnSesion)
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonCerrarExpediente() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && usuarioEnSesion.getPermiso() && expedienteSeleccionado.getUsuarioCreador().equals(usuarioEnSesion)
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonReabrirExpediente() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && usuarioEnSesion.getPermiso() && expedienteSeleccionado.getUsuarioCreador().equals(usuarioEnSesion)
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && expedienteSeleccionado.getCerrado()) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonAbandonar() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && usuarioEnSesion.getPermiso() && !expedienteSeleccionado.getUsuarioCreador().equals(usuarioEnSesion)
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonAltaExpediente() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      if (usuarioEnSesion != null && usuarioEnSesion.getPermiso()) {
         res = true;
      }
      return res;
   }

   public Boolean botonConsultarExpediente(final Expediente expediente) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      try {
         if (usuarioEnSesion != null && expediente != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expediente).contains(usuarioEnSesion)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonCrearNuevaTarea() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean botonEditarTarea(final Tarea tarea) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()
               && tarea.getUsuarioCreador().equals(usuarioEnSesion) && !tarea.getCompletada() && tarea.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean botonCompletarTarea(final Tarea tarea) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()
               && tarea.getUsuarioCreador().equals(usuarioEnSesion) && !tarea.getCompletada() && tarea.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean botonReabrirTarea(final Tarea tarea) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()
               && tarea.getUsuarioCreador().equals(usuarioEnSesion) && tarea.getCompletada() && tarea.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonTransferir() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && usuarioEnSesion.getPermiso() && expedienteSeleccionado.getUsuarioCreador().equals(usuarioEnSesion)
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonEditarTarea() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()
               && tareaSeleccionada.getUsuarioCreador().equals(usuarioEnSesion) && !tareaSeleccionada.getCompletada()
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonModificarCampos() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()
               && tareaSeleccionada.getUsuarioCreador().equals(usuarioEnSesion) && !tareaSeleccionada.getCompletada()
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonCompletarTarea() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()
               && tareaSeleccionada.getUsuarioCreador().equals(usuarioEnSesion) && !tareaSeleccionada.getCompletada()
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonReabrirTarea() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()
               && tareaSeleccionada.getUsuarioCreador().equals(usuarioEnSesion) && tareaSeleccionada.getCompletada()
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean botonConsultarTarea(final Tarea tarea) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion)
               && tarea.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean botonConsultarTareaPaciente(final Tarea tarea) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && !usuarioEnSesion.getPermiso() && tarea.getExpediente().equals(expedienteSeleccionado)) {
         res = true;
      }
      return res;
   }

   public Boolean getBotonMisComentariosPsicologo() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion)
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonConclusionesPsicologo() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion)
               && tareaSeleccionada.getUsuarioCreador().equals(usuarioEnSesion) && tareaSeleccionada.getCompletada()
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonEditarConclusiones() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso()
               && this.expedientePsicologoService.obtenerPsicologosConAccesoPorExpediente(expedienteSeleccionado).contains(usuarioEnSesion) && !expedienteSeleccionado.getCerrado()
               && tareaSeleccionada.getUsuarioCreador().equals(usuarioEnSesion) && tareaSeleccionada.getCompletada()
               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonMisComentariosPaciente() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && !usuarioEnSesion.getPermiso()
               && !tareaCampoService.obtenerCamposPorTarea(tareaSeleccionada).isEmpty() && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getBotonConclusionesPaciente() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      final Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
      if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && !usuarioEnSesion.getPermiso() && tareaSeleccionada.getCompletada()
            && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
         res = true;
      }
      return res;
   }

   public Boolean iconoNuevoComentarioDePacienteEnExpediente(final Expediente expediente) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      try {
         if (usuarioEnSesion != null && expediente != null && usuarioEnSesion.getPermiso()) {
            final List<Tarea> tareas = this.tareaService.obtenerTareasPorExpediente(expediente);
            for (Tarea tarea : tareas) {
               if (tarea != null && !tarea.getCompletada() && tarea.getExpediente().equals(expediente) && !this.tareaCampoService.obtenerCamposPorTarea(tarea).isEmpty()
                     && this.comentarioService.tareaConNuevoComentarioDePaciente(tarea)) {
                  res = true;
                  break;
               }
            }
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean iconoNuevoComentarioDePaciente(final Tarea tarea) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && usuarioEnSesion.getPermiso() && !tarea.getCompletada()
               && tarea.getExpediente().equals(expedienteSeleccionado) && !this.tareaCampoService.obtenerCamposPorTarea(tarea).isEmpty()
               && this.comentarioService.tareaConNuevoComentarioDePaciente(tarea)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean iconoNuevoComentarioDePsicologo(final Tarea tarea) {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      final Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
      try {
         if (usuarioEnSesion != null && expedienteSeleccionado != null && tarea != null && !usuarioEnSesion.getPermiso() && !tarea.getCompletada()
               && tarea.getExpediente().equals(expedienteSeleccionado) && !this.tareaCampoService.obtenerCamposPorTarea(tarea).isEmpty()
               && this.comentarioService.tareaConNuevoComentarioDePsicologo(tarea)) {
            res = true;
         }
      } catch (Exception e) {
         Mensajes.mostrarLog(e);
      }
      return res;
   }

   public Boolean getNombreYApellidosPanel() {
      Boolean res = false;
      final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
      if (usuarioEnSesion != null && !usuarioEnSesion.getPermiso()) {
         res = true;
      }
      return res;
   }
   
   public Boolean getTablaFechasPaciente(){
	   return this.getBotonComentarPaciente();
   }
   
   public Boolean getTablaFechas(){
	   Boolean res = false;
	      Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
	      Expediente expedienteSeleccionado = this.sessionMB.getExpedienteSeleccionado();
	      Tarea tareaSeleccionada = this.sessionMB.getTareaSeleccionada();
	      try {
	         if (usuarioEnSesion != null && expedienteSeleccionado != null && tareaSeleccionada != null && usuarioEnSesion.getPermiso() && !expedienteSeleccionado.getCerrado()
	               && !tareaSeleccionada.getCompletada() && !tareaCampoService.obtenerCamposPorTarea(tareaSeleccionada).isEmpty()
	               && tareaSeleccionada.getExpediente().equals(expedienteSeleccionado)) {
	            res = true;
	         }
	      } catch (Exception e) {
	         Mensajes.mostrarLog(e);
	      }
	      return res;
   }

   /*
    * GETTERS AND SETTERS
    */

   public SessionManagedBean getSessionMB() {
      return this.sessionMB;
   }

   public void setSessionMB(final SessionManagedBean sessionMB) {
      this.sessionMB = sessionMB;
   }

   public ITareaService getTareaService() {
      return this.tareaService;
   }

   public void setTareaService(final ITareaService tareaService) {
      this.tareaService = tareaService;
   }

   public ITareaCampoService getTareaCampoService() {
      return this.tareaCampoService;
   }

   public void setTareaCampoService(final ITareaCampoService tareaCampoService) {
      this.tareaCampoService = tareaCampoService;
   }

   public IExpedientePsicologoService getExpedientePsicologoService() {
      return this.expedientePsicologoService;
   }

   public void setExpedientePsicologoService(final IExpedientePsicologoService expedientePsicologoService) {
      this.expedientePsicologoService = expedientePsicologoService;
   }

   public IComentarioService getComentarioService() {
      return this.comentarioService;
   }

   public void setComentarioService(final IComentarioService comentarioService) {
      this.comentarioService = comentarioService;
   }
}
