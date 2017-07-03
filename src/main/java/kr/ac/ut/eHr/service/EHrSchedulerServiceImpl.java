package kr.ac.ut.eHr.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.ut.eHr.mapper.EHrSchedulerMapper;

@Service("EHrSchedulerService")
public class EHrSchedulerServiceImpl implements EHrSchedulerService{

    @Resource(name="EHrSchedulerMapper")
    private EHrSchedulerMapper mapper;

    @Override
    @Transactional
    public void updatePsnnlBatchStat(){
        mapper.updatePsnnlBatchStartCode();


        mapper.updatePsnnlBatchEndCode();
    }

    @Override
    @Transactional
    public void updatePsnnl() {
        List<Map<String, Object>> tgtList = mapper.selectPsnnlStandby();

        for(Map<String, Object> map: tgtList){
            mapper.updateUserPsnnl(map);
        }

        if(tgtList != null && tgtList.size() > 0)
            mapper.deleteSchedule();
    }
}
