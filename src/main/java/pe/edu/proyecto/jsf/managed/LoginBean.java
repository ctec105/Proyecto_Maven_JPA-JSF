package pe.edu.proyecto.jsf.managed;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

//import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
//import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.LoginService;
import pe.edu.proyecto.business.service.UsuarioService;
import pe.edu.proyecto.business.service.UtilService;
import pe.edu.proyecto.persistence.entity.TbArea;
import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.persistence.entity.TbUsuario;
import pe.edu.proyecto.util.Mensajes;

@ManagedBean(name="login")
@SessionScoped
//@Named("login")
//@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3263734420391743097L;
	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static LoginService loginService;
	private static UsuarioService usuarioService;
	private static UtilService utilService;

	// Instances
	private TbUsuario usuario = new TbUsuario();
	private TbArea area = new TbArea();
	private List<TbAreaHasTbMenu> areamenuList;
	private MenuModel model;
	private boolean isLogado = false;

	// Getters and setters
	public TbUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TbUsuario usuario) {
		this.usuario = usuario;
	}

	public TbArea getArea() {
		return area;
	}

	public void setArea(TbArea area) {
		this.area = area;
	}

	public List<TbAreaHasTbMenu> getAreamenuList() {
		return areamenuList;
	}

	public void setAreamenuList(List<TbAreaHasTbMenu> areamenuList) {
		this.areamenuList = areamenuList;
	}

	public MenuModel getModel() {
		return model;
	}

	public boolean isLogado() {
		return isLogado;
	}

	public void setLogado(boolean isLogado) {
		this.isLogado = isLogado;
	}

	// Methods
	public void crearMenu() {
		System.out.println("crearMenu");
		model = new DefaultMenuModel();
		for (TbAreaHasTbMenu tb : areamenuList) {
			if (tb.getTbMenu().getNivel().length() == 1) { // si el menu es independiente
				System.out.println("* " + tb.getTbMenu().getTitulo());
				DefaultSubMenu firstSubmenu = new DefaultSubMenu(tb.getTbMenu().getTitulo());
				for (TbAreaHasTbMenu tb2 : areamenuList) {
					char submenu = tb2.getTbMenu().getNivel().charAt(0);
					if (tb2.getTbMenu().getNivel().length() != 1) {
						if (String.valueOf(submenu).equals(tb.getTbMenu().getNivel())) {
							System.out.println("- " + tb2.getTbMenu().getTitulo());
							DefaultMenuItem item = new DefaultMenuItem(tb2.getTbMenu().getTitulo());
							item.setIcon(tb2.getTbMenu().getIcono());
							if (tb2.getTbMenu().getAction().equals("menuBlanco"))
								item.setUrl("inicio.xhtml");
							else
								item.setCommand(tb2.getTbMenu().getAction()); // test "#{formCliente.listarClientes}"
							item.setAjax(false); // para no recargar toda la página
							firstSubmenu.addElement(item);
						}
					}
				}
				model.addElement(firstSubmenu);
			}
		}
	}

	// Actions Methods
	public String validar() throws Exception {
		loginService = abd.getLoginService();
		if (loginService.validar(usuario)) {
			System.out.println("Obtemeos el objeto usuario");
			usuarioService = abd.getUsuarioService();
			usuario = usuarioService.obtenerUsuarioXLogin(usuario);
			System.out.println("Evaluando estado del usuario");
			if (usuario.getEstado() != 0) {
				Mensajes.warn("Usuario <b>" + usuario.getLogin() + "</b> bloqueado.", null);
				return null;
			} else {
				utilService = abd.getUtilService();
				area = utilService.obtenerArea(usuario.getTbAreaIdArea());
				areamenuList = usuarioService.permisos(usuario.getTbAreaIdArea());
				crearMenu(); // Crear un menú programáticamente con Primefaces
				isLogado = true;
				return "inicio?faces-redirect=true";
			}
		} else {
			Mensajes.warn("Su usuario y/o contraseña son incorrectos", null);
			return null;
		}
	}

	public String CerrarSession() {
		System.out.println("Estoy dentro del método CerrarSession() - INI");
		Map<?, ?> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.remove("login");
		session.clear();
		System.out.println("Estoy dentro del método CerrarSession() - FIN");
		return "login?faces-redirect=true";
	}

}
