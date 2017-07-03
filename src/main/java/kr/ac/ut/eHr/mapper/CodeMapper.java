package kr.ac.ut.eHr.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.ac.ut.eHr.domain.Code;

@Repository("CodeMapper")
public interface CodeMapper {
    List<Code> selectList(String param);
}
