package kr.ac.ut.eHr.domain;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = 1829945895357022972L;
    private String name;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.name;
    }

}
