package com.psiject.services.interfaces;

import java.util.List;

import com.psiject.entidades.Campo;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.TareaCampo;

public interface ITareaCampoService {

   public void anadirTareaCampo(TareaCampo tarCam) throws Exception;

   public void borrarTareaCampo(TareaCampo tarCam) throws Exception;

   public TareaCampo buscarTareaCampoPorTareaYCampo(Tarea tarea, Campo campo) throws Exception;

   public void comprobarYAnadirTareaCampo(Tarea tarea, Campo campo) throws Exception;

   public void comprobarYBorrarTareaCampo(Tarea tarea, Campo campo) throws Exception;

   public List<Campo> obtenerCamposPorTarea(Tarea t) throws Exception;

   public List<Tarea> obtenerTareaPorCampo(final Campo c) throws Exception;
}
