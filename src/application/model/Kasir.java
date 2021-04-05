package application.model;

public class Kasir {
	private String idKasir;
	private String nmKasir;
	private String jenisKelamin;
	private String noTelepon;
	private String agama;
	private String alamat;
	private String password;
	
	public Kasir(String idKasir, String nmKasir, String jenisKelamin, String noTelepon, String agama, String alamat,
			String password) {
		super();
		this.idKasir = idKasir;
		this.nmKasir = nmKasir;
		this.jenisKelamin = jenisKelamin;
		this.noTelepon = noTelepon;
		this.agama = agama;
		this.alamat = alamat;
		this.password = password;
	}
	public String getIdKasir() {
		return idKasir;
	}
	public void setIdKasir(String idKasir) {
		this.idKasir = idKasir;
	}
	public String getNmKasir() {
		return nmKasir;
	}
	public void setNmKasir(String nmKasir) {
		this.nmKasir = nmKasir;
	}
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	public String getNoTelepon() {
		return noTelepon;
	}
	public void setNoTelepon(String noTelepon) {
		this.noTelepon = noTelepon;
	}
	public String getAgama() {
		return agama;
	}
	public void setAgama(String agama) {
		this.agama = agama;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
