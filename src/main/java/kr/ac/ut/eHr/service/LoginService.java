package kr.ac.ut.eHr.service;

import kr.ac.ut.eHr.domain.Login;

public interface LoginService {
    Login getLogin(String param);
    int updateLoginPwd(Login paramVo);
}
