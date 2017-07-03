package kr.ac.ut.eHr.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import kr.ac.ut.eHr.domain.Login;

@Repository(value="LoginMapper")
public interface LoginMapper {

	Login getLogin(String param) throws DataAccessException;

	int updateLoginPwd(Login paramVo) throws DataAccessException;
}