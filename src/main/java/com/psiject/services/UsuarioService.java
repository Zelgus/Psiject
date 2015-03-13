package com.psiject.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.psiject.dao.interfaces.IUsuarioDAO;
import com.psiject.entidades.Usuario;
import com.psiject.services.interfaces.IUsuarioService;
import com.psiject.utilidades.Utilidades;

@Transactional(readOnly = true)
public class UsuarioService implements IUsuarioService {

   IUsuarioDAO usuarioDAO;

   public UsuarioService() {
      super();
   }

   public IUsuarioDAO getUsuarioDAO() {
      return this.usuarioDAO;
   }

   public void setUsuarioDAO(final IUsuarioDAO usuarioDAO) {
      this.usuarioDAO = usuarioDAO;
   }

   @Override
   public List<Usuario> obtenerPsicologos() throws Exception {
      return this.usuarioDAO.findPsicologos();
   }

   @Override
   public Usuario buscarUsuarioPorNombreUsuario(final String nombreUsuario) throws Exception {
      return this.usuarioDAO.findByUsuario(nombreUsuario);
   }

   @Override
   public Usuario buscarUsuarioPorCorreo(final String correo) throws Exception {
      return this.usuarioDAO.findByCorreo(correo);
   }

   public Usuario buscarUsuarioLogin(final String usuario) throws Exception {
      Usuario res = this.buscarUsuarioPorNombreUsuario(usuario);
      if (res == null) {
         res = this.buscarUsuarioPorCorreo(usuario);
      }
      return res;
   }

   @Transactional(readOnly = false)
   @Override
   public void anadirUsuario(final String usuario, final String contrasena, final String correo, final Boolean permiso) throws Exception {

      // Encriptado de la contraseña
      String contrasenaEncriptada = "";
      contrasenaEncriptada = Utilidades.encriptar(contrasena);

      final Usuario nuevoUsuario = new Usuario();
      nuevoUsuario.setUsuario(usuario);
      nuevoUsuario.setContrasena(contrasenaEncriptada);
      nuevoUsuario.setCorreo(correo);
      nuevoUsuario.setPermiso(permiso);

      this.getUsuarioDAO().insert(nuevoUsuario);
   }

   @Transactional(readOnly = false)
   @Override
   public void actualizarUsuario(final Usuario usuario) throws Exception {
      this.getUsuarioDAO().update(usuario);
   }
}