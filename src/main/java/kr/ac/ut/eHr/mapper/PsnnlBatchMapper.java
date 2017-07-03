package kr.ac.ut.eHr.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.ac.ut.eHr.domain.Code;
import kr.ac.ut.eHr.domain.PsnnlBatch;

@Repository("PsnnlBatchMapper")
public interface PsnnlBatchMapper {
    List<PsnnlBatch> selectList(PsnnlBatch paramVo);
    int selectListCnt(PsnnlBatch paramVo);

    PsnnlBatch select(String param);

    int insert(PsnnlBatch paramVo);

    int update(PsnnlBatch paramVo);

    //인사배치시 삭제
    int delete(String param);
    int deleteSchedule(String param);
    int deletePsnnlManual(String param);
    int deletePsnnl(String param);
    int deleteApplOrgnz(String param);

    List<Code> selectDegreeComboList(String param);

    PsnnlBatch selectRunPsnnlBatch();

    int countHopeOrgnzCheck(String param);
}
