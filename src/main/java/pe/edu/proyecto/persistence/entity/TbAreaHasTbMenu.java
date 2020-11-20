package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "tb_area_has_tb_menu")
public class TbAreaHasTbMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tb_area_id_area")
	private int idArea;

	// bi-directional many-to-one association to TbArea
//  @ManyToOne
//	@JoinColumn(name="tb_area_id_area")
//	private TbArea tbArea;

	// bi-directional many-to-one association to TbMenu
	@Id
	@ManyToOne
	@JoinColumn(name = "tb_menu_id_menu")
	private TbMenu tbMenu;

	private String ver;

	public TbAreaHasTbMenu() {
	}

//	public TbArea getTbArea() {
//		return this.tbArea;
//	}
//
//	public void setTbArea(TbArea tbArea) {
//		this.tbArea = tbArea;
//	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public TbMenu getTbMenu() {
		return this.tbMenu;
	}

	public void setTbMenu(TbMenu tbMenu) {
		this.tbMenu = tbMenu;
	}
	
	public String getVer() {
		return this.ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

}