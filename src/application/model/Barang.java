package application.model;

public class Barang {
	private String kdBarang;
	private String nmBarang;
	private String jenis;
	private Integer hargabeli;
	private Integer hargajual;
	
	
	public Barang(String kdBarang, String nmBarang, String jenis, Integer hargabeli, Integer hargajual) {
		super();
		this.kdBarang = kdBarang;
		this.nmBarang = nmBarang;
		this.jenis = jenis;
		this.hargabeli = hargabeli;
		this.hargajual = hargajual;
	}


	public String getKdBarang() {
		return kdBarang;
	}


	public void setKdBarang(String kdBarang) {
		this.kdBarang = kdBarang;
	}


	public String getNmBarang() {
		return nmBarang;
	}


	public void setNmBarang(String nmBarang) {
		this.nmBarang = nmBarang;
	}


	public String getJenis() {
		return jenis;
	}


	public void setJenis(String jenis) {
		this.jenis = jenis;
	}


	public Integer getHargabeli() {
		return hargabeli;
	}


	public void setHargabeli(Integer hargabeli) {
		this.hargabeli = hargabeli;
	}


	public Integer getHargajual() {
		return hargajual;
	}


	public void setHargajual(Integer hargajual) {
		this.hargajual = hargajual;
	}
	
	
	
}
