package kr.ac.ut.eHr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.ut.eHr.common.StringUtil;
import kr.ac.ut.eHr.domain.Login;
import kr.ac.ut.eHr.domain.Psnnl;
import kr.ac.ut.eHr.domain.User;
import kr.ac.ut.eHr.domain.UserSearch;
import kr.ac.ut.eHr.mapper.PsnnlMapper;
import kr.ac.ut.eHr.mapper.UserMapper;

@Service("UserService")
public class UserServiceImpl implements UserService{

    @Resource(name="UserMapper")
    private UserMapper mapper;

    @Resource(name="PsnnlMapper")
    private PsnnlMapper pMapper;

    @Override
    public List<User> selectList(UserSearch vo) {
        return mapper.selectList(vo);
    }

    @Override
    public int selectListCnt(UserSearch vo) {
        return mapper.selectListCnt(vo);
    }

    @Override
    public User select(User paramVo) {
        return this.select(paramVo, false);
    }

    @Override
    public User select(User paramVo, boolean myInfoYn) {
        User vo = (myInfoYn)? mapper.selectMyInfo(paramVo.getUserId()): mapper.select(paramVo);

        /*vo.setAbsences(mapper.selectAbsenceList(vo));
        vo.setDutyPeriods(mapper.selectDutyPeriodList(vo));*/
        return vo;
    }

    @Override
    public String selectRunPsnnl() {
        return mapper.selectRunPsnnl();
    }

    @Override
    @Transactional
    public int insert(User vo, boolean updPsnnlMnlYn) {
        int rtn = 0;
        //1. 사용자 insert
        mapper.insert(vo);


        //2. 당해계급 휴직일/3. 본부 및 학교근무기간
        //this.setAbsenceDutyPeriod(vo, "U");

        this.setPsnnl(vo, updPsnnlMnlYn);

        if(!vo.getUserId().equals("")) rtn = mapper.insertLogin(vo);

        return rtn;
    }

    @Override
    @Transactional
    public int update(User vo, boolean updPsnnlMnlYn) {
        //1. 사용자 update
        mapper.update(vo);

        //2. 당해계급 휴직일/3. 본부 및 학교근무기간
        //this.setAbsenceDutyPeriod(vo, "U");

        this.setPsnnl(vo, updPsnnlMnlYn);

        return mapper.updateLogin(vo);
    }

    @Transactional
    private void setPsnnl(User vo, boolean updPsnnlMnlYn) {
        if(updPsnnlMnlYn && !vo.getPsnnlBatchId().equals("")){
            if(StringUtil.NVL(vo.getTargetCodeId()).equals("") && StringUtil.NVL(vo.getPrmtCodeId()).equals("")){
                mapper.deletePsnnlManual(vo);
            }else{
                mapper.mergePsnnlManual(vo);
            }
        }

        Psnnl pVo = new Psnnl();
        pVo.setUserId(vo.getUserId());
        pVo.setPsnnlBatchId(vo.getPsnnlBatchId());
        pVo.setTiePriorityYn(vo.getTiePriorityYn());
        pMapper.updateTiePriorityYn(pVo);
    }

    @Override
    @Transactional
    public int delete(String param) {
        mapper.deleteSchedule(param);
        mapper.deleteApplyOrgnz(param);
        mapper.deleteLogin(param);
        mapper.deletePsnnl(param);
        User uVo = new User();
        uVo.setUserId(param);
        mapper.deletePsnnlManual(uVo);

        return mapper.delete(param);
    }


    /*
     * ================================= LOGIN =================================
     */
    @Override
    public int selectLoginCnt(User paramVo){
        return mapper.selectLoginCnt(paramVo);
    }

    @Override
    public Login selectLogin(String param){
        return mapper.selectLogin(param);
    }

    @Override
    public int updateLoginPwd(Login paramVo){
        return mapper.updateLoginPwd(paramVo);
    }
/*
    private void setAbsenceDutyPeriod(User vo, String div){
        //2. 당해계급 휴직일
        if(div.equals("U")) mapper.deleteAbsence(vo);
        if(vo.getAbsences() != null && vo.getAbsences().size() > 0)
            mapper.insertAbsence(vo);

        //3. 본부 및 학교근무기간
        if(div.equals("U")) mapper.deleteDutyPeriod(vo);
        if(vo.getDutyPeriods() != null && vo.getDutyPeriods().size() > 0)
            mapper.insertDutyPeriod(vo);
    }
*/

    @Override
    public int updateStay5(String param, String div){
        return mapper.updateStay5(param, div);
    }


    @Override
    public List<Map<String, Object>> selectPopJang(List<String> paramList){
        List<User> userList =mapper.selectMaxPsnnlBatchId(paramList);

        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        for(User uVo:userList){
            rtnList.add(mapper.selectPopJang(uVo));
        }

        return rtnList;
    }

    @Override
    public String selectDataValidate(User paramVo){
        return mapper.selectDataValidate(paramVo);
    }

    @Override
    public int insertExcelAll(List<User> paramList){
        int rtn = 0;
        for(User uVo: paramList){
            if(mapper.insert(uVo) > 0)  ++rtn;

            if(!uVo.getUserId().equals("")) mapper.insertLogin(uVo);
        }
        return rtn;
    }
}
