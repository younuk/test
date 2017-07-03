package kr.ac.ut.eHr.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.ac.ut.eHr.domain.StatSearch;

@Repository("StatMapper")
public interface StatMapper {
    List<Map<String, Object>> selectApply(StatSearch paramVo);
    List<Map<String, Object>> selectIn(StatSearch paramVo);
    List<Map<String, Object>> selectInOut(StatSearch paramVo);
    List<Map<String, Object>> selectAvg(StatSearch paramVo);
}
