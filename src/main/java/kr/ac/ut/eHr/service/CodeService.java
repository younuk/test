package kr.ac.ut.eHr.service;

import java.util.List;

import kr.ac.ut.eHr.domain.Code;

public interface CodeService {
    List<Code> selectList(String param);
}
