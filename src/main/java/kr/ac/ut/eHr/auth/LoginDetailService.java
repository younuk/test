package kr.ac.ut.eHr.auth;

import javax.annotation.Resource;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.ac.ut.eHr.domain.Login;
import kr.ac.ut.eHr.mapper.LoginMapper;

@Service("loginDetailService")
public class LoginDetailService implements UserDetailsService  {

    @Resource(name="LoginMapper")
    private LoginMapper loginMapper;


    @Override
    public LoginDetail loadUserByUsername(String userId) throws UsernameNotFoundException {

        Login loginInfo = loginMapper.getLogin(userId);

        // 사용자 계정이 없을 때
        if(loginInfo==null) {
            throw new BadCredentialsException("user not found");
        }

        LoginDetail loginDetail = new LoginDetail(loginInfo);

        return loginDetail;
    }
}
