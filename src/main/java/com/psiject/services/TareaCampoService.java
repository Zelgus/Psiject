package com.psiject.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.transaction.annotation.Transactional;

import com.psiject.dao.interfaces.ITareaCampoDAO;
import com.psiject.entidades.Campo;
import com.psiject.entidades.Comentario;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.TareaCampo;
import com.psiject.services.interfaces.ICampoService;
import com.psiject.services.interfaces.IComentarioService;
import com.psiject.services.interfaces.ITareaCampoService;
import com.psiject.utilidades.Constantes;

@Transactional(readOnly = true)
public class TareaCampoService implements ITareaCampoService, Serializable {

   ITareaCampoDAO tareaCampoDAO;

   @ManagedProperty(value = "#{CampoService}")
   ICampoService campoService;

   @ManagedProperty(value = "#{ComentarioService}")
   IComentarioService comentarioService;

   public TareaCampoService() {
      super();
   }

   public ITareaCampoDAO getTareaCampoDAO() {
      return this.tareaCampoDAO;
   }

   public void setTareaCampoDAO(final ITareaCampoDAO tareaCampoDAO) {
      this.tareaCampoDAO = tareaCampoDAO;
   }

   public ICampoService getCampoService() {
      return this.campoService;
   }

   public void setCampoService(final ICampoService campoService) {
      this.campoService = campoService;
   }

   public IComentarioService getComentarioService() {
      return this.comentarioService;
   }

   public void setComentarioService(final IComentarioService comentarioService) {
      this.comentarioService = comentarioService;
   }

   @Transactional(readOnly = false)
   @Override
   public void anadirTareaCampo(final TareaCampo tarCam) throws Exception {
      this.getTareaCampoDAO().insert(tarCam);
   }

   @Transactional(readOnly = false)
   @Override
   public void borrarTareaCampo(final TareaCampo tarCam) throws Exception {
      this.getTareaCampoDAO().delete(tarCam);
   }

   @Transactional(readOnly = false)
   @Override
   public void comprobarYAnadirTareaCampo(final Tarea tarea, final Campo campo) throws Exception {
      Boolean encontrado = false;
      final TareaCampo tc = this.tareaCampoDAO.findTareaCampo(tarea, campo);
      if (tc != null) {
         encontrado = true;
      }
      if (!encontrado) {
         final TareaCampo tarCam = new TareaCampo();
         tarCam.setTarea(tarea);
         tarCam.setCampo(campo);
         final Integer numeroComentarios = this.comentarioService.obtenerNumeroComentariosPacientePorTarea(tarea);
         this.anadirTareaCampo(tarCam);

         /*
          * Si el campo no es el de psicologo y la tarea tenía comentarios, se
          * añade un comentario vacío por cada comentario al campo.
          */
         if (!campo.getNombre().equals(Constantes.CAMPO_COMENTARIOS_PSICOLOGO) && numeroComentarios != 0) {
            for (int i = 0; i < numeroComentarios; i++) {
               this.comentarioService.anadirComentario("-", new Date(), true, tarea, campo, tarea.getExpediente().getUsuario());
            }
         }
      }
   }

   @Transactional(readOnly = false)
   @Override
   public void comprobarYBorrarTareaCampo(final Tarea tarea, final Campo campo) throws Exception {
      Boolean encontrado = false;
      final TareaCampo tareaCampo = this.tareaCampoDAO.findTareaCampo(tarea, campo);
      if (tareaCampo != null) {
         encontrado = true;
      }
      if (encontrado && !campo.getNombre().equals(Constantes.CAMPO_COMENTARIOS_PSICOLOGO)) {
         Boolean conComentarios = false;
         final List<Comentario> comentariosDeLaTarea = this.comentarioService.obtenerComentariosPacientePorTareaYCampo(tarea, campo);
         for (final Comentario c : comentariosDeLaTarea) {
            if (!c.getComentario().equals("-")) {
               conComentarios = true;
               break;
            }
         }
         // Si todos los comentarios son "-", se puede borrar el campo de la
         // tarea
         if (!conComentarios) {
            for (final Comentario c : comentariosDeLaTarea) {
               this.comentarioService.borrarComentario(c);
            }
            this.borrarTareaCampo(tareaCampo);
         }
      }
   }

   @Transactional(readOnly = false)
   @Override
   public List<Campo> obtenerCamposPorTarea(final Tarea t) throws Exception {
      final List<Campo> res = this.tareaCampoDAO.findCamposPorTarea(t);
      final Campo campoPsicologo = this.campoService.obtenerCampoPsicologo();
      res.remove(campoPsicologo);
      return res;
   }

   @Override
   public List<Tarea> obtenerTareaPorCampo(final Campo c) throws Exception {
      return this.tareaCampoDAO.findTareasPorCampo(c);
   }

   @Override
   public TareaCampo buscarTareaCampoPorTareaYCampo(final Tarea tarea, final Campo campo) throws Exception {
      return this.tareaCampoDAO.findTareaCampo(tarea, campo);
   }
}