package pe.edu.proyecto.jsf.listener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.edu.proyecto.jsf.managed.LoginBean;

@WebFilter(filterName = "FilterResource", description = "Filtra el accesso a la carpeta css", 
urlPatterns = ("/faces/javax.faces.resource/css/*"))
public class FilterResource implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest reqt = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// Obtener la sesión
		HttpSession session = reqt.getSession();
		LoginBean sesion = (LoginBean) session.getAttribute("login");

		// Si el usuario no ha logeado redirecionar al login
		if (sesion == null || !sesion.isLogado()) {
			resp.sendRedirect("/Proyecto_Maven_JPA-JSF/faces/login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

}
