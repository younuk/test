package kr.ac.ut.eHr.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("EHrSchedulerMapper")
public interface EHrSchedulerMapper {
    void updatePsnnlBatchStartCode();
    void updatePsnnlBatchEndCode();

    List<Map<String, Object>> selectPsnnlStandby();
    void updateUserPsnnl(Map<String, Object> paramMap);

    void deleteSchedule();
}
