package com.psiject.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.psiject.dao.interfaces.ITareaDAO;
import com.psiject.entidades.Expediente;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.Usuario;
import com.psiject.services.interfaces.ITareaService;

@Transactional(readOnly = true)
public class TareaService implements ITareaService {

   ITareaDAO tareaDAO;

   public TareaService() {
      super();
   }

   public ITareaDAO getTareaDAO() {
      return this.tareaDAO;
   }

   public void setTareaDAO(final ITareaDAO TareaDAO) {
      this.tareaDAO = TareaDAO;
   }

   @Transactional(readOnly = false)
   @Override
   public Tarea anadirTarea(final String titulo, final String descripcion, final Boolean importante, final Boolean completada, final Boolean vista, final Expediente expediente,
         final Usuario creador) throws Exception {
      final Tarea t = new Tarea();
      t.setTitulo(titulo);
      t.setDescripcion(descripcion);
      t.setImportante(importante);
      t.setCompletada(completada);
      t.setVista(vista);
      t.setExpediente(expediente);
      t.setUsuarioCreador(creador);
      this.getTareaDAO().insert(t);
      return t;
   }

   @Transactional(readOnly = false)
   @Override
   public void actualizarTarea(final Tarea tarea) throws Exception {
      this.getTareaDAO().update(tarea);
   }

   @Override
   public List<Tarea> obtenerTareasPorExpediente(final Expediente e) throws Exception {
      return this.tareaDAO.findTareasPorExpediente(e);
   }

   @Override
   public List<Tarea> obtenerTareasPorExpedienteYCreador(final Expediente e, final Usuario u) throws Exception {
      return this.tareaDAO.findTareasPorExpedienteYCreador(e, u);
   }
}
