package com.psiject.services.interfaces;

import java.util.List;

import com.psiject.entidades.Expediente;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.Usuario;

public interface ITareaService {

   public Tarea anadirTarea(String titulo, String descripcion, Boolean importante, Boolean completada, Boolean vista, Expediente expediente, Usuario creador) throws Exception;

   public void actualizarTarea(Tarea tarea) throws Exception;

   public List<Tarea> obtenerTareasPorExpediente(Expediente e) throws Exception;

   public List<Tarea> obtenerTareasPorExpedienteYCreador(Expediente e, Usuario u) throws Exception;
}