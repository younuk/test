package kr.ac.ut.eHr.service;

import java.util.List;

import kr.ac.ut.eHr.domain.Orgnz;
import kr.ac.ut.eHr.domain.OrgnzSearch;

public interface OrgnzService {
    List<Orgnz> selectList(OrgnzSearch paramVo);

    int selectListCnt(OrgnzSearch paramVo);

    Orgnz select(String param);

    int insert(Orgnz paramVo);

    int update(Orgnz paramVo);

    int delete(String param);


    List<Orgnz> selectComboList(String param);
}
