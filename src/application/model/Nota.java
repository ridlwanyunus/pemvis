package application.model;

import java.util.Date;

public class Nota {
	private String idNota;
	private Date tglNota;
	private String idPelanggan;
	private String idKasir;
	
	public Nota(String idNota, Date tglNota, String idPelanggan, String idKasir) {
		super();
		this.idNota = idNota;
		this.tglNota = tglNota;
		this.idPelanggan = idPelanggan;
		this.idKasir = idKasir;
	}
	
	public String getIdNota() {
		return idNota;
	}
	
	public void setIdNota(String idNota) {
		this.idNota = idNota;
	}
	
	public Date getTglNota() {
		return tglNota;
	}
	
	public void setTglNota(Date tglNota) {
		this.tglNota = tglNota;
	}
	
	public String getIdPelanggan() {
		return idPelanggan;
	}
	
	public void setIdPelanggan(String idPelanggan) {
		this.idPelanggan = idPelanggan;
	}
	public String getIdKasir() {
		return idKasir;
	}
	
	public void setIdKasir(String idKasir) {
		this.idKasir = idKasir;
	}
	
}
