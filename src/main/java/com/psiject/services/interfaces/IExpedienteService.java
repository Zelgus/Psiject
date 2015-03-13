package com.psiject.services.interfaces;

import java.util.List;

import com.psiject.entidades.Expediente;
import com.psiject.entidades.Usuario;

public interface IExpedienteService {

   public Expediente anadirExpediente(String nombre, String apellidos, Boolean cerrado, Usuario usuarioAnadido, Usuario creador) throws Exception;

   public void actualizarExpediente(Expediente expediente) throws Exception;

   public List<Expediente> obtenerExpedientesCreadosPorUsuario(Usuario u) throws Exception;

   public List<Expediente> buscarExpedientesPorUsuario(Usuario u) throws Exception;
}
