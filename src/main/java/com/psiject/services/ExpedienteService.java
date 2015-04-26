package com.psiject.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.psiject.dao.interfaces.IExpedienteDAO;
import com.psiject.entidades.Expediente;
import com.psiject.entidades.Usuario;
import com.psiject.services.interfaces.IExpedienteService;

@Transactional(readOnly = true)
public class ExpedienteService implements IExpedienteService, Serializable {

   IExpedienteDAO expedienteDAO;

   public ExpedienteService() {
      super();
   }

   public IExpedienteDAO getExpedienteDAO() {
      return this.expedienteDAO;
   }

   public void setExpedienteDAO(final IExpedienteDAO expedienteDAO) {
      this.expedienteDAO = expedienteDAO;
   }

   @Transactional(readOnly = false)
   @Override
   public Expediente anadirExpediente(final String nombre, final String apellidos, final Boolean cerrado, final Usuario usuarioAnadido, final Usuario creador)
         throws Exception {
      final Expediente e = new Expediente();
      e.setNombre(nombre);
      e.setApellidos(apellidos);
      e.setCerrado(false);
      e.setUsuario(usuarioAnadido);
      e.setUsuarioCreador(creador);
      this.getExpedienteDAO().insert(e);
      return e;
   }

   @Transactional(readOnly = false)
   @Override
   public void actualizarExpediente(final Expediente expediente) throws Exception {
      this.getExpedienteDAO().update(expediente);
   }

   @Override
   public List<Expediente> obtenerExpedientesCreadosPorUsuario(final Usuario u) throws Exception {
      return this.expedienteDAO.findExpedientesCreadosPorUsuario(u);
   }

   @Override
   public List<Expediente> buscarExpedientesPorUsuario(final Usuario u) throws Exception {
      return this.expedienteDAO.findExpedientesPorUsuario(u);
   }
}
