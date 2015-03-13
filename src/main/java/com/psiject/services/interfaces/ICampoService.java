package com.psiject.services.interfaces;

import java.util.List;

import com.psiject.entidades.Campo;

public interface ICampoService {

   public void anadirCampo(String nombre, String descripcion) throws Exception;

   public Boolean borrarCampo(Campo campo) throws Exception;

   public Campo buscarCampoPorId(Long id) throws Exception;

   public Campo buscarCampoPorNombre(String nombreCampo) throws Exception;

   public List<Campo> obtenerCampos() throws Exception;

   public Campo obtenerCampoPsicologo() throws Exception;
}
