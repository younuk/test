package kr.ac.ut.eHr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.ac.ut.eHr.domain.Code;
import kr.ac.ut.eHr.mapper.CodeMapper;

@Service("CodeService")
public class CodeServiceImpl implements CodeService{

    @Resource(name="CodeMapper")
    private CodeMapper mapper;

    @Override
    public List<Code> selectList(String param) {
        return mapper.selectList(param);
    }

}
