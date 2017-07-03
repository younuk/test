package kr.ac.ut.eHr.domain;

public class Login{
	private String loginId;
	private String password;
	private String userId;
    private String authCodeId;
    private String pwChgYn;

    public String getPwChgYn() {
        return pwChgYn;
    }
    public void setPwChgYn(String pwChgYn) {
        this.pwChgYn = pwChgYn;
    }
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAuthCodeId() {
        return authCodeId;
    }
    public void setAuthCodeId(String authCodeId) {
        this.authCodeId = authCodeId;
    }
}
