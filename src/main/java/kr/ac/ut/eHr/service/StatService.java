package kr.ac.ut.eHr.service;

import java.util.List;
import java.util.Map;

import kr.ac.ut.eHr.domain.StatSearch;

public interface StatService {
    List<Map<String, Object>> selectApply(StatSearch paramVo);
    List<Map<String, Object>> selectIn(StatSearch paramVo);
    List<Map<String, Object>> selectInOut(StatSearch paramVo);
    List<Map<String, Object>> selectAvg(StatSearch paramVo);

}
