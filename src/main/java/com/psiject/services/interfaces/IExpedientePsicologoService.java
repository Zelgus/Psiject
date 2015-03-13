package com.psiject.services.interfaces;

import java.util.List;

import com.psiject.entidades.Expediente;
import com.psiject.entidades.ExpedientePsicologo;
import com.psiject.entidades.Usuario;

public interface IExpedientePsicologoService {

   public void anadirExpedientePsicologo(Expediente expediente, Usuario psicologo) throws Exception;

   public void borrarExpedientePsicologo(ExpedientePsicologo expPsi) throws Exception;

   public void comprobarYAnadirExpedientePsicologo(Expediente expediente, Usuario psicologo) throws Exception;

   public void comprobarYBorrarExpedientePsicologo(Expediente expediente, Usuario psicologo) throws Exception;

   public List<Expediente> obtenerExpedientesAccesiblesPorPsicologo(Usuario u) throws Exception;

   public List<Usuario> obtenerPsicologosConAccesoPorExpediente(Expediente e) throws Exception;
}