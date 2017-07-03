package kr.ac.ut.eHr.service;

import java.util.List;
import java.util.Map;

import kr.ac.ut.eHr.domain.Login;
import kr.ac.ut.eHr.domain.User;
import kr.ac.ut.eHr.domain.UserSearch;

public interface UserService {
    List<User> selectList(UserSearch paramVo);
    int selectListCnt(UserSearch paramVo);
    User select(User paramVo);
    User select(User paramVo, boolean myInfoYn);
    String selectRunPsnnl();
    int insert(User paramVo, boolean updPsnnlYn);
    int update(User paramVo, boolean updPsnnlYn);
    int delete(String param);

    /*
     * ================================= LOGIN =================================
     */
    int selectLoginCnt(User paramVo);
    Login selectLogin(String param);
    int updateLoginPwd(Login paramVo);

    int updateStay5(String param, String div);

    /*
     * ================================= PRINT =================================
     */
    List<Map<String, Object>> selectPopJang(List<String> paramList);


    /*
     * ================================= EXCEL =================================
     */
    String selectDataValidate(User paramVo);
    int insertExcelAll(List<User> paramList);
}
