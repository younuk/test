package kr.ac.ut.eHr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.ac.ut.eHr.domain.ApplyOrgnz;
import kr.ac.ut.eHr.domain.Psnnl;
import kr.ac.ut.eHr.domain.PsnnlBatch;
import kr.ac.ut.eHr.domain.PsnnlSearch;
import kr.ac.ut.eHr.domain.User;

@Repository("PsnnlMapper")
public interface PsnnlMapper {
    List<Psnnl> selectList(PsnnlSearch paramVo);
    int selectListCnt(PsnnlSearch paramVo);

    Psnnl select(String param);

    int updateStayLevel(Psnnl paramVo);
    int updateTiePriorityYn(Psnnl paramVo);
    int updateEnddt(User paramVo);


    //인사대상자생성
    List<Psnnl> selectExixtStaylevelList(String param);
    int delete(String param);
    List<Psnnl> selectTargetList(PsnnlBatch paramVo);
    int insertAll(List<Psnnl> paramList);
    int updateTargetManual(String param);

    //배치실행전 불만족지수
    int updateBatchPointMonths(@Param("dt") String dt, @Param("psnnlBatchId") String psnnlBatchId);
    int updateBatchBefore(String param);
    int updateBatchBefore2(String param);
    int updateBatchBefore3(String param);

    int selectBatchSpecialDutyCnt(String param);
    // List<Psnnl> selectBatchSpecialDuty(Map<String, Object> paramMap);
    Psnnl selectBatchSpecialDuty(@Param("level") String level, @Param("psnnlBatchId") String psnnlBatchId, @Param("staylevelyn") String staylevelyn);
    List<Psnnl> selectBatchOrgnzRank(Map<String, Object> paramMap);
    int updateBatchPsnnl(Psnnl paramVo);
    int updateBatchPassScore(String param);
    List<Map<String, Object>> selectPsnnlUserList(String param);

    //수시인사등록
    int insertDemand(User paramVo);

    /*
     * ================================= apply_orgnz =================================
     */
    List<ApplyOrgnz> selectApplOrgnzList(Psnnl paramVo);
    List<ApplyOrgnz> selectApplOrgnzDisstsList(Psnnl paramVo);
    int insertApplOrgnz(Psnnl paramVo);
    int deleteApplOrgnz(Psnnl paramVo);
    int deleteApplOrgnzNoTarget(String param);

    /*

    //사용자관리>사용자관리 상세보기 - 사용자 정보 수정 시, 인사정보 등록 정보 수정
    int updatePsnnl(Psnnl paramVo);
    int deleteAll(List<Psnnl> paramList);
    int updateAutoTargetYn(Psnnl paramVo);
    */


    int insertSchedule(Map<String, Object> paramMap);
    int insertSelectSchedule(String param);

    int deletePsnnlBatch(@Param("psnnlBatchId") String psnnlBatchId);
}
