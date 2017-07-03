package kr.ac.ut.eHr.service;

import java.util.List;

import kr.ac.ut.eHr.domain.ApplyOrgnz;
import kr.ac.ut.eHr.domain.Psnnl;
import kr.ac.ut.eHr.domain.PsnnlSearch;
import kr.ac.ut.eHr.domain.User;

public interface PsnnlService {
    List<Psnnl> selectList(PsnnlSearch paramVo);
    int selectListCnt(PsnnlSearch paramVo);

    Psnnl select(String param);

    int update(Psnnl paramVo);

    int insertDemand(User paramVo);

    /*
     * ================================= apply_orgnz =================================
     */
    List<ApplyOrgnz> selectApplOrgnzDisstsList(Psnnl paramVo);
}
