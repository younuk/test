package kr.ac.ut.eHr.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.ac.ut.eHr.domain.StatSearch;
import kr.ac.ut.eHr.mapper.StatMapper;

@Service("StatService")
public class StatServiceImpl implements StatService{

    @Resource(name="StatMapper")
    private StatMapper mapper;

    @Override
    public List<Map<String, Object>> selectApply(StatSearch paramVo) {
        return mapper.selectApply(paramVo);
    }

    @Override
    public List<Map<String, Object>> selectIn(StatSearch paramVo) {
        return mapper.selectIn(paramVo);
    }

    @Override
    public List<Map<String, Object>> selectInOut(StatSearch paramVo) {
        return mapper.selectInOut(paramVo);
    }

    @Override
    public List<Map<String, Object>> selectAvg(StatSearch paramVo) {
        return mapper.selectAvg(paramVo);
    }


}
