package application.model;

public class Pelanggan {
	
	private String id;
	private String nmplgn;
	private String jenis;
	private String telepon;
	private String Alamat;
	
	public Pelanggan() {
		
	}

	
	public Pelanggan(String id, String nmplgn, String jenis, String telepon, String alamat) {
		super();
		this.id = id;
		this.nmplgn = nmplgn;
		this.jenis = jenis;
		this.telepon = telepon;
		Alamat = alamat;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getNmplgn() {
		return nmplgn;
	}


	public void setNmplgn(String nmplgn) {
		this.nmplgn = nmplgn;
	}


	public String getJenis() {
		return jenis;
	}


	public void setJenis(String jenis) {
		this.jenis = jenis;
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
