package com.psiject.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.transaction.annotation.Transactional;

import com.psiject.dao.interfaces.IComentarioDAO;
import com.psiject.entidades.Campo;
import com.psiject.entidades.Comentario;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.TareaCampo;
import com.psiject.entidades.Usuario;
import com.psiject.services.interfaces.ICampoService;
import com.psiject.services.interfaces.IComentarioService;
import com.psiject.services.interfaces.ITareaCampoService;
import com.psiject.utilidades.Constantes;

@Transactional(readOnly = true)
public class ComentarioService implements IComentarioService, Serializable {

   IComentarioDAO comentarioDAO;

   @ManagedProperty(value = "#{TareaCampoService}")
   ITareaCampoService tareaCampoService;

   @ManagedProperty(value = "#{CampoService}")
   ICampoService campoService;

   public ComentarioService() {
      super();
   }

   public IComentarioDAO getComentarioDAO() {
      return this.comentarioDAO;
   }

   public void setComentarioDAO(final IComentarioDAO comentarioDAO) {
      this.comentarioDAO = comentarioDAO;
   }

   public ITareaCampoService getTareaCampoService() {
      return this.tareaCampoService;
   }

   public void setTareaCampoService(final ITareaCampoService tareaCampoService) {
      this.tareaCampoService = tareaCampoService;
   }

   public ICampoService getCampoService() {
      return this.campoService;
   }

   public void setCampoService(final ICampoService campoService) {
      this.campoService = campoService;
   }

   @Transactional(readOnly = false)
   @Override
   public Boolean anadirComentario(final String comentario, final Date hora, final Boolean visto, final Tarea tareaSeleccionada, final Campo campoSeleccionado,
         final Usuario usuarioEnSesion) throws Exception {
      Boolean res = false;
      if (tareaSeleccionada != null && campoSeleccionado != null) {
         final Comentario nuevoComentario = new Comentario();
         nuevoComentario.setComentario(comentario);
         nuevoComentario.setHora(hora);
         nuevoComentario.setVisto(visto);
         final TareaCampo tcActual = this.tareaCampoService.buscarTareaCampoPorTareaYCampo(tareaSeleccionada, campoSeleccionado);
         nuevoComentario.setTareaCampo(tcActual);
         nuevoComentario.setUsuarioCreador(usuarioEnSesion);
         this.getComentarioDAO().insert(nuevoComentario);
         res = true;
      } else {
         res = false;
      }
      return res;
   }

   @Transactional(readOnly = false)
   @Override
   public void actualizarComentario(final Comentario comentario) throws Exception {
      this.getComentarioDAO().update(comentario);
   }

   @Transactional(readOnly = false)
   @Override
   public void borrarComentario(final Comentario comentario) throws Exception {
      this.getComentarioDAO().delete(comentario);
   }

   @Override
   public Integer obtenerNumeroComentariosPacientePorTarea(final Tarea tareaSeleccionada) throws Exception {
      Integer res = 0;
      final List<Campo> campos = this.tareaCampoService.obtenerCamposPorTarea(tareaSeleccionada);

      if (campos != null && !campos.isEmpty()) {
         for (final Campo campo : campos) {
            if (!campo.getNombre().equals(Constantes.CAMPO_COMENTARIOS_PSICOLOGO)) {
               final TareaCampo tareaCampo = this.tareaCampoService.buscarTareaCampoPorTareaYCampo(tareaSeleccionada, campo);
               final List<Comentario> comentarios = this.comentarioDAO.findComentariosPorTareaCampo(tareaCampo);
               res = comentarios.size();
               break;
            }
         }
      }

      return res;
   }

   @Override
   public List<Comentario> obtenerComentariosPacientePorTareaYCampo(final Tarea tareaSeleccionada, final Campo campoSeleccionado) throws Exception {
      final List<Comentario> res = new ArrayList<Comentario>();
      final TareaCampo tcActual = this.tareaCampoService.buscarTareaCampoPorTareaYCampo(tareaSeleccionada, campoSeleccionado);

      if (tcActual != null) {
         final List<Comentario> comentariosTareaCampo = this.comentarioDAO.findComentariosPorTareaCampo(tcActual);
         for (final Comentario coment : comentariosTareaCampo) {
            if (!coment.getUsuarioCreador().getPermiso()) {
               res.add(coment);
            }
         }
      }

      return res;
   }

   @Transactional(readOnly = false)
   @Override
   public List<Comentario> obtenerComentariosPsicologoPorTarea(final Tarea tareaSeleccionada) throws Exception {
      final List<Comentario> res = new ArrayList<Comentario>();
      final Campo campoPsicologo = this.campoService.obtenerCampoPsicologo();
      final TareaCampo tcActual = this.tareaCampoService.buscarTareaCampoPorTareaYCampo(tareaSeleccionada, campoPsicologo);

      if (tcActual != null) {
         final List<Comentario> comentariosTareaCampo = this.comentarioDAO.findComentariosPorTareaCampo(tcActual);
         for (final Comentario coment : comentariosTareaCampo) {
            if (coment.getUsuarioCreador().getPermiso()) {
               res.add(coment);
            }
         }
      }

      return res;
   }

   @Override
   public Boolean tareaConNuevoComentarioDePsicologo(final Tarea tareaSeleccionada) throws Exception {
      Boolean res = false;
      final Campo campo = this.campoService.obtenerCampoPsicologo();
      
      if (campo != null) {
         final TareaCampo tcActual = this.tareaCampoService.buscarTareaCampoPorTareaYCampo(tareaSeleccionada, campo);
         final List<Comentario> comentariosTareaCampo = this.comentarioDAO.findComentariosPorTareaCampo(tcActual);
         for (final Comentario coment : comentariosTareaCampo) {
            if (res) {
               break;
            }
            if (coment.getUsuarioCreador().getPermiso() && !coment.getVisto()) {
               res = true;
            }
         }
      }

      return res;
   }

   @Override
   public Boolean tareaConNuevoComentarioDePaciente(final Tarea tareaSeleccionada) throws Exception {
      Boolean res = false;
      final List<Campo> camposDeLaTarea = this.tareaCampoService.obtenerCamposPorTarea(tareaSeleccionada);

      if (camposDeLaTarea != null && !camposDeLaTarea.isEmpty()) {
         for (final Campo campo : camposDeLaTarea) {
            if (res) {
               break;
            }
            final TareaCampo tcActual = this.tareaCampoService.buscarTareaCampoPorTareaYCampo(tareaSeleccionada, campo);
            final List<Comentario> comentariosTareaCampo = this.comentarioDAO.findComentariosPorTareaCampo(tcActual);
            for (final Comentario coment : comentariosTareaCampo) {
               if (res) {
                  break;
               }
               if (!coment.getUsuarioCreador().getPermiso() && !coment.getVisto()) {
                  res = true;
               }
            }
         }
      }

      return res;
   }
}
