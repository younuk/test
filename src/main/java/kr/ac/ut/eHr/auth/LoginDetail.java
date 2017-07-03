package kr.ac.ut.eHr.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.ac.ut.eHr.domain.Login;

public class LoginDetail implements UserDetails{

	private static final long serialVersionUID = -21795631197417579L;

    private String password = "";   //비밀번호
	private String userid = "";
    private String username = "";   //사용자 이름
    private String userAuth = "";   //권한목록
    private String pwChgYn;         //비밀번호변경여부

    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	public LoginDetail(Login login) {
        this.setUserid(login.getUserId());
        this.setPassword(login.getPassword());
        this.setUserAuth(login.getAuthCodeId());
        this.setPwChgYn(login.getPwChgYn());

        this.authorities.add(new SimpleGrantedAuthority(login.getAuthCodeId()));
	}

    public String getPwChgYn() {
        return pwChgYn;
    }

    public void setPwChgYn(String pwChgYn) {
        this.pwChgYn = pwChgYn;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(String userAuth) {
        this.userAuth = userAuth;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
