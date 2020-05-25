package project.jeongha.member.vo;

public class AuthInfo {

	private String clientId;
	private String clientSecret;

	public AuthInfo(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

}
