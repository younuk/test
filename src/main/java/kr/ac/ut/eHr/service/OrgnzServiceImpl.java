package kr.ac.ut.eHr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.ut.eHr.domain.Orgnz;
import kr.ac.ut.eHr.domain.OrgnzSearch;
import kr.ac.ut.eHr.mapper.OrgnzMapper;

@Service("OrgnzService")
public class OrgnzServiceImpl implements OrgnzService{

    @Resource(name="OrgnzMapper")
    private OrgnzMapper mapper;

    @Override
    public List<Orgnz> selectList(OrgnzSearch paramVo) {
        return mapper.selectList(paramVo);
    }

    @Override
    public int selectListCnt(OrgnzSearch paramVo) {
        return mapper.selectListCnt(paramVo);
    }

    @Override
    public Orgnz select(String param) {
        return mapper.select(param);
    }

    @Override
    public int insert(Orgnz paramVo) {
        return mapper.insert(paramVo);
    }

    @Override
    public int update(Orgnz paramVo) {
        return mapper.update(paramVo);
    }

    @Override
    @Transactional
    public int delete(String param) {
        mapper.deleteSchedule(param);
        mapper.deleteApplOrgnz(param);
        mapper.deleteLogin(param);
        mapper.deletePsnnl(param);
        mapper.deletePsnnlManual(param);
        mapper.deleteUser(param);

        return mapper.delete(param);
    }

    @Override
    public List<Orgnz> selectComboList(String param) {
        return mapper.selectComboList(param);
    }

}
