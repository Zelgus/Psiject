package com.psiject.services.interfaces;

import java.util.List;

import com.psiject.entidades.Usuario;

public interface IUsuarioService {

   public void anadirUsuario(String usuario, String contrasena, String correo, Boolean permiso) throws Exception;

   public void actualizarUsuario(Usuario usuario) throws Exception;

   public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) throws Exception;

   public List<Usuario> obtenerPsicologos() throws Exception;

   public Usuario buscarUsuarioPorCorreo(String correo) throws Exception;

   public Usuario buscarUsuarioLogin(String usuario) throws Exception;
}
