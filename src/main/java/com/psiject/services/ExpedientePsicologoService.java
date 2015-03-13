package com.psiject.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.psiject.dao.interfaces.IExpedientePsicologoDAO;
import com.psiject.entidades.Expediente;
import com.psiject.entidades.ExpedientePsicologo;
import com.psiject.entidades.Usuario;
import com.psiject.services.interfaces.IExpedientePsicologoService;

@Transactional(readOnly = true)
public class ExpedientePsicologoService implements IExpedientePsicologoService {

   IExpedientePsicologoDAO expedientePsicologoDAO;

   public ExpedientePsicologoService() {
      super();
   }

   public IExpedientePsicologoDAO getExpedientePsicologoDAO() {
      return this.expedientePsicologoDAO;
   }

   public void setExpedientePsicologoDAO(final IExpedientePsicologoDAO expedientePsicologoDAO) {
      this.expedientePsicologoDAO = expedientePsicologoDAO;
   }

   @Transactional(readOnly = false)
   @Override
   public void anadirExpedientePsicologo(final Expediente expediente, final Usuario psicologo) throws Exception {
      final ExpedientePsicologo expPsi = new ExpedientePsicologo();
      expPsi.setExpediente(expediente);
      expPsi.setUsuario(psicologo);
      this.getExpedientePsicologoDAO().insert(expPsi);
   }

   @Transactional(readOnly = false)
   @Override
   public void borrarExpedientePsicologo(final ExpedientePsicologo expPsi) throws Exception {
      this.getExpedientePsicologoDAO().delete(expPsi);
   }

   @Transactional(readOnly = false)
   @Override
   public void comprobarYAnadirExpedientePsicologo(final Expediente expediente, final Usuario psicologo) throws Exception {
      Boolean encontrado = false;
      final ExpedientePsicologo ep = this.expedientePsicologoDAO.findExpedientePsicologo(expediente, psicologo);
      if (ep != null) {
         encontrado = true;
      }
      if (!encontrado) {
         this.anadirExpedientePsicologo(expediente, psicologo);
      }
   }

   @Transactional(readOnly = false)
   @Override
   public void comprobarYBorrarExpedientePsicologo(final Expediente expediente, final Usuario psicologo) throws Exception {
      Boolean encontrado = false;
      final ExpedientePsicologo ep = this.expedientePsicologoDAO.findExpedientePsicologo(expediente, psicologo);
      if (ep != null) {
         encontrado = true;
      }
      if (encontrado) {
         this.borrarExpedientePsicologo(ep);
      }
   }

   @Override
   public List<Expediente> obtenerExpedientesAccesiblesPorPsicologo(final Usuario u) throws Exception {
      return this.expedientePsicologoDAO.findExpedientesAccesiblesPorPsicologo(u);
   }

   @Override
   public List<Usuario> obtenerPsicologosConAccesoPorExpediente(final Expediente e) throws Exception {
      return this.expedientePsicologoDAO.findPsicologosConAccesoPorExpediente(e);
   }
}
