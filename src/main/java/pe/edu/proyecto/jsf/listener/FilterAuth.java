//package pe.edu.proyecto.jsf.listener;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebFilter(filterName = "FilterAuth", description = "Filtro que impide entrar a las páginas sin antes logear", 
//urlPatterns = ("/faces/inicio.xhtml"))
//public class FilterAuth implements Filter {
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		try {
//			HttpServletRequest reqt = (HttpServletRequest) request;
//			HttpServletResponse resp = (HttpServletResponse) response;
//			HttpSession ses = reqt.getSession(false);
//
//			String reqURL = reqt.getRequestURI();
//			System.out.println("Pagina actual: " + reqURL); /// Proyecto_Maven_JPA-JSF/faces/paginaActual.xhtml
//
//			if (reqURL.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("login") != null)
//					|| reqURL.indexOf("/public/") >= 0 || reqURL.contains("javac.faces.resourse")) {
//				chain.doFilter(request, response);
//			} else {
//				resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
//				/// Proyecto_Maven_JPA-JSF/faces/login.xhtml
//			}
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
//
//}

// Mejor implementarlo con PhaseListener
