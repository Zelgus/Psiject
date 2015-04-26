package com.psiject.services;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.transaction.annotation.Transactional;

import com.psiject.dao.interfaces.ICampoDAO;
import com.psiject.entidades.Campo;
import com.psiject.entidades.Tarea;
import com.psiject.services.interfaces.ICampoService;
import com.psiject.services.interfaces.ITareaCampoService;
import com.psiject.utilidades.Constantes;

@Transactional(readOnly = true)
public class CampoService implements ICampoService, Serializable {

   ICampoDAO campoDAO;

   @ManagedProperty(value = "#{TareaCampoService}")
   ITareaCampoService tareaCampoService;

   public CampoService() {
      super();
   }

   public ICampoDAO getCampoDAO() {
      return this.campoDAO;
   }

   public void setCampoDAO(final ICampoDAO campoDAO) {
      this.campoDAO = campoDAO;
   }

   public ITareaCampoService getTareaCampoService() {
      return this.tareaCampoService;
   }

   public void setTareaCampoService(final ITareaCampoService tareaCampoService) {
      this.tareaCampoService = tareaCampoService;
   }

   @Transactional(readOnly = false)
   @Override
   public void anadirCampo(final String nombre, final String descripcion) throws Exception {
      if (nombre != null && !nombre.isEmpty()) {
         final Campo campo = new Campo();
         campo.setNombre(nombre);
         campo.setDescripcion(descripcion);
         this.getCampoDAO().insert(campo);
      }
   }

   @Transactional(readOnly = false)
   @Override
   public Boolean borrarCampo(final Campo campoSeleccionado) throws Exception {
      Boolean res = true;
      final List<Tarea> tareas = this.tareaCampoService.obtenerTareaPorCampo(campoSeleccionado);

      if (tareas.isEmpty()) {
         this.getCampoDAO().delete(campoSeleccionado);
      } else {
         res = false;
      }

      return res;
   }

   @Override
   public Campo buscarCampoPorId(final Long id) throws Exception {
      return this.getCampoDAO().findById(id);
   }

   @Transactional(readOnly = false)
   @Override
   public List<Campo> obtenerCampos() throws Exception {
      List<Campo> res = this.getCampoDAO().findAll();
      Campo campoPsicologo = this.obtenerCampoPsicologo();

      res.remove(campoPsicologo);

      return res;
   }

   @Transactional(readOnly = false)
   @Override
   public Campo obtenerCampoPsicologo() throws Exception {
      Campo campo = this.getCampoDAO().findByNombre(Constantes.CAMPO_COMENTARIOS_PSICOLOGO);

      if (campo == null) {
         campo = new Campo();
         campo.setNombre(Constantes.CAMPO_COMENTARIOS_PSICOLOGO);
         campo.setDescripcion("");
         this.getCampoDAO().insert(campo);

         campo = this.getCampoDAO().findByNombre(Constantes.CAMPO_COMENTARIOS_PSICOLOGO);
      }

      return campo;
   }

   @Override
   public Campo buscarCampoPorNombre(final String nombreCampo) throws Exception {
      return this.campoDAO.findByNombre(nombreCampo);
   }
}