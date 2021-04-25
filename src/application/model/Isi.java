package application.model;

public class Isi {
	private String idNota;
	private String idBarang;
	private String nmBrg;
	private Integer hb;
	private Integer hj;
	private Integer qty;
	
	
	public Isi(String idNota, String idBarang, String nmBrg, Integer hb, Integer hj, Integer qty) {
		super();
		this.idNota = idNota;
		this.idBarang = idBarang;
		this.nmBrg = nmBrg;
		this.hb = hb;
		this.hj = hj;
		this.qty = qty;
	}
	
	public String getNmBrg() {
		return nmBrg;
	}
	public void setNmBrg(String nmBrg) {
		this.nmBrg = nmBrg;
	}
	public String getIdNota() {
		return idNota;
	}
	public void setIdNota(String idNota) {
		this.idNota = idNota;
	}
	public String getIdBarang() {
		return idBarang;
	}
	public void setIdBarang(String idBarang) {
		this.idBarang = idBarang;
	}
	public Integer getHb() {
		return hb;
	}
	public void setHb(Integer hb) {
		this.hb = hb;
	}
	public Integer getHj() {
		return hj;
	}
	public void setHj(Integer hj) {
		this.hj = hj;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}
