package kr.ac.ut.eHr.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.ac.ut.eHr.domain.Orgnz;
import kr.ac.ut.eHr.domain.OrgnzSearch;

@Repository("OrgnzMapper")
public interface OrgnzMapper {
    List<Orgnz> selectList(OrgnzSearch paramVo);

    int selectListCnt(OrgnzSearch paramVo);

    Orgnz select(String param);

    int insert(Orgnz paramVo);

    int update(Orgnz paramVo);

    int delete(String param);
    int deleteSchedule(String param);
    int deleteApplOrgnz(String param);
    int deleteLogin(String param);
    int deletePsnnl(String param);
    int deletePsnnlManual(String param);
    int deleteUser(String param);


    List<Orgnz> selectComboList(String param);
}
