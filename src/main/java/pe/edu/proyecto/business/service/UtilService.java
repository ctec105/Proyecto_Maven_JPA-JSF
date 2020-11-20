package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbArea;
import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.persistence.entity.TbClaseequipo;
import pe.edu.proyecto.persistence.entity.TbEspecialidade;
import pe.edu.proyecto.persistence.entity.TbEstado;
import pe.edu.proyecto.persistence.entity.TbMarca;
import pe.edu.proyecto.persistence.entity.TbModelo;

public interface UtilService {
	
	// M�todos utiles utilizados en la interface SolicitudService
	public List<TbClaseequipo> listarClasesEquipos() throws Exception;

	// M�todos utiles utilizados en la interface OrdenDeTrabajoService
	public List<TbEstado> listarEstados()throws Exception;
	
	public List<TbMarca> listarMarcas() throws Exception;// M�todos utiles utilizados en la interface PaqueteService
	public List<TbModelo> listarModelos() throws Exception;// M�todos utiles utilizados en la interface PaqueteService
	public List<TbModelo> listarModelosxMarca(String marca) throws Exception;
	
	public List<TbEspecialidade> listarEspecialidades() throws Exception;
	
	// M�todo util utilizado en la interfaces LiquidacionService
	public TbEstado getEstado(int id) throws Exception;
	
	// M�todo util utilizado en la interfaces LoginService
	public TbArea obtenerArea(int codArea) throws Exception;
	
	// M�todo util utilizado en la interfaces UsuarioService
	public List<TbArea> listarAreas ()throws Exception; // M�todo util utilizado en la interfaces PermisoService
	
	// M�todo util utilizado en la interfaces PermisoService
	public  List<TbAreaHasTbMenu> listarPermisosXArea(int area)throws Exception;

	public void modificarPermisos(TbAreaHasTbMenu areamenu);

}