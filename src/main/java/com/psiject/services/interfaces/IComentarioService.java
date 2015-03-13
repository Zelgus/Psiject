package com.psiject.services.interfaces;

import java.util.Date;
import java.util.List;

import com.psiject.entidades.Campo;
import com.psiject.entidades.Comentario;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.Usuario;

public interface IComentarioService {

   public Boolean anadirComentario(final String comentario, Date hora, Boolean visto, final Tarea tareaSeleccionada, final Campo campoSeleccionado, final Usuario usuarioEnSesion)
         throws Exception;

   public void actualizarComentario(Comentario comentario) throws Exception;
   
   public void borrarComentario(Comentario comentario) throws Exception;

   public Integer obtenerNumeroComentariosPacientePorTarea(final Tarea tareaSeleccionada) throws Exception;

   public List<Comentario> obtenerComentariosPacientePorTareaYCampo(Tarea tareaSeleccionada, Campo campoSeleccionado) throws Exception;

   public List<Comentario> obtenerComentariosPsicologoPorTarea(final Tarea tareaSeleccionada) throws Exception;

   public Boolean tareaConNuevoComentarioDePsicologo(final Tarea tareaSeleccionada) throws Exception;

   public Boolean tareaConNuevoComentarioDePaciente(final Tarea tareaSeleccionada) throws Exception;
}
