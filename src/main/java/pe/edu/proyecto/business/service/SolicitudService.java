package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbSolicitud;

public interface SolicitudService {
	
	// M�todos empleados para el Caso de Uso Gestionar Solicitud de Servicio
	public List<TbSolicitud> listarSolicitudes() throws Exception;
	public TbSolicitud obtenerSolicitud(TbSolicitud solicitud) throws Exception;
	public void	registrarSolicitud(TbSolicitud solicitud) throws Exception;
	public void	actualizarSolicitud(TbSolicitud solicitud) throws Exception;
	public int eliminarSolicitud(TbSolicitud solicitud) throws Exception;
	public int generarIdSolicitud() throws Exception;
	
	// M�todo de apoyo invocados de la interface EquipoService
	// obtenerEquipo
	// listarEquipos
	// buscarEquipo
	// M�todos �tiles invocados de la interfaces UtilService
	// listarClasesEquipos
	
	// M�todos que sirven de apoyo a la interfaz OrdenDeTrabajoService
	public TbSolicitud obtenerSolicitud(int nroSolicitud) throws Exception;
	// listarSolicitudes()
	public List<TbSolicitud> buscarSolicitud(String representante, String estado);
	
}
