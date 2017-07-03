package kr.ac.ut.eHr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.ac.ut.eHr.domain.Login;
import kr.ac.ut.eHr.mapper.LoginMapper;

@Service("LoginService")
public class LoginServiceImpl implements LoginService{

    @Resource(name="LoginMapper")
    private LoginMapper mapper;

    @Override
    public int updateLoginPwd(Login paramVo) {
        return mapper.updateLoginPwd(paramVo);
    }

    @Override
    public Login getLogin(String param) {
        return mapper.getLogin(param);
    }
}
