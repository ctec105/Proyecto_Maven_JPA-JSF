package pe.edu.proyecto.jsf.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import pe.edu.proyecto.jsf.managed.LoginBean;
import pe.edu.proyecto.util.Mensajes;

public class SessionPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("Fase: " + event.getPhaseId());
		
		// Se obtiene el contexto
		FacesContext fc = event.getFacesContext();
		
		// Se obtiene el URL de la página
		String paginaActual = fc.getViewRoot().getViewId();
		System.out.println("Página actual: " + paginaActual); // /paginaActual.xhtml
		
		// Valido que la pagina contenga la palabra login
		boolean esLogin = (paginaActual.lastIndexOf("/login.xhtml") > -1);
		LoginBean controlSesion = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		
		// Si la página actual a la que se quiere entrar no es la del login
		if (!esLogin) {
			// Valido si existe una sesión, de lo contrario impido el acceso a las páginas y redirecciono al login
			if (controlSesion == null || !controlSesion.isLogado()) {
				// Muestra un mensaje
				Mensajes.error("Acceso Denegado", "¡Debe iniciar sesión!");

				// Redirecciona para el login
				NavigationHandler nav = fc.getApplication().getNavigationHandler();
				nav.handleNavigation(fc, null, "login");
				
				// Renderiza la página
				fc.renderResponse();
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}