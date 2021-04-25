package application.model;

public class UserID {
	private static String kd;

	public static String getUserLogin() {
		return kd;
	}

	public static void setUserLogin(String kd) {
		UserID.kd = kd;
	}
	
}
