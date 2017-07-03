package kr.ac.ut.eHr.service;

import java.util.List;

import kr.ac.ut.eHr.domain.Code;
import kr.ac.ut.eHr.domain.PsnnlBatch;

public interface PsnnlBatchService {
    List<PsnnlBatch> selectList(PsnnlBatch paramVo);
    int selectListCnt(PsnnlBatch paramVo);

    PsnnlBatch select(String param);

    int insert(PsnnlBatch paramVo);

    int update(PsnnlBatch paramVo);

    int delete(String param);

    List<Code> selectDegreeComboList(String param);

    PsnnlBatch selectRunPsnnlBatch();

    int countHopeOrgnzCheck(String param);
}
