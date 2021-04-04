package application.model;

public class Pelanggan {
	
	private int idPelanggan;
	private String nama;
	private String jenisKelamin;
	private String telepon;
	private String Alamat;
	
	public Pelanggan() {
		
	}
	
	public Pelanggan(int idPelanggan, String nama, String jenisKelamin, String telepon, String alamat) {
		super();
		this.idPelanggan = idPelanggan;
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.telepon = telepon;
		Alamat = alamat;
	}
	public int getIdPelanggan() {
		return idPelanggan;
	}
	public void setIdPelanggan(int idPelanggan) {
		this.idPelanggan = idPelanggan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	public String getTelepon() {
		return telepon;
	}
	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}
	public String getAlamat() {
		return Alamat;
	}
	public void setAlamat(String alamat) {
		Alamat = alamat;
	}
	
	
}
