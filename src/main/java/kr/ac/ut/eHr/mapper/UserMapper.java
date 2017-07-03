package kr.ac.ut.eHr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.ac.ut.eHr.domain.Absence;
import kr.ac.ut.eHr.domain.DutyPeriod;
import kr.ac.ut.eHr.domain.Login;
import kr.ac.ut.eHr.domain.User;
import kr.ac.ut.eHr.domain.UserSearch;

@Repository("UserMapper")
public interface UserMapper {
    List<User> selectList(UserSearch paramVo);
    int selectListCnt(UserSearch paramVo);
    User select(User paramVo);
    User selectMyInfo(String param);        //MY INFO
    int insert(User paramVo);
    int update(User paramVo);

    int delete(String param);
    int deleteSchedule(String param);
    int deleteApplyOrgnz(String param);
    int deleteLogin(String param);
    int deletePsnnl(String param);

    String selectRunPsnnl();

    /*
     * ================================= LOGIN =================================
     */
    int selectLoginCnt(User paramVo);
    Login selectLogin(String param);
    int insertLogin(User paramVo);
    int updateLogin(User paramVo);
    int updateLoginPwd(Login paramVo);

    /*
     * ================================= ABSENCE =================================
     */
    List<Absence> selectAbsenceList(User paramVo);
    int insertAbsence(User paramList);
    int deleteAbsence(User paramVo);

    /*
     * ================================= DUTY_PERIOD =================================
     */
    List<DutyPeriod> selectDutyPeriodList(User paramVo);
    int insertDutyPeriod(User paramList);
    int deleteDutyPeriod(User paramVo);


    /*
     * ================================= PSNNL =================================
     */
    List<Map<String, Object>> selectPsnnlHistory(UserSearch paramVo);
    int selectPsnnlHistoryCnt(UserSearch paramVo);

    /*
     * ================================= PSNNL_MANUAL =================================
     */
    void mergePsnnlManual(User paramVo);
    void deletePsnnlManual(User paramVo);


    /*
     * ================================= APPOINTMENT =================================
     */
    int insertAppointment(User paramVo);
    String selectUpRankCodeId(String param);



    /*
     * ================================= PRINT =================================
     */
    Map<String, Object> selectPopJang(User paramVo) ;
    List<User> selectMaxPsnnlBatchId(List<String> paramList);



    /*
     * ================================= PRINT =================================
     */
    String selectDataValidate(User paramVo);






    int updateStay5(@Param("userId") String userId, @Param("div") String div);


}
