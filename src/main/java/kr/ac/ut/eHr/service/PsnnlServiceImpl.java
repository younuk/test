package kr.ac.ut.eHr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.ut.eHr.common.DateUtil;
import kr.ac.ut.eHr.common.StringUtil;
import kr.ac.ut.eHr.domain.ApplyOrgnz;
import kr.ac.ut.eHr.domain.Psnnl;
import kr.ac.ut.eHr.domain.PsnnlBatch;
import kr.ac.ut.eHr.domain.PsnnlSearch;
import kr.ac.ut.eHr.domain.User;
import kr.ac.ut.eHr.mapper.EHrSchedulerMapper;
import kr.ac.ut.eHr.mapper.PsnnlBatchMapper;
import kr.ac.ut.eHr.mapper.PsnnlMapper;
import kr.ac.ut.eHr.mapper.UserMapper;

@Service("PsnnlService")
public class PsnnlServiceImpl implements PsnnlService{

    @Resource(name="PsnnlMapper")
    private PsnnlMapper mapper;

    @Resource(name="UserMapper")
    private UserMapper uMapper;

    @Resource(name="PsnnlBatchMapper")
    private PsnnlBatchMapper pbMapper;

    @Resource(name="EHrSchedulerMapper")
    private EHrSchedulerMapper eMapper;


    @Override
    public  List<Psnnl> selectList(PsnnlSearch paramVo){
        return mapper.selectList(paramVo);
    }

    @Override
    public int selectListCnt(PsnnlSearch vo) {
        return mapper.selectListCnt(vo);
    }

    @Override
    public Psnnl select(String param) {
        Psnnl vo = mapper.select(param);
        vo.setHopeOrgnz(mapper.selectApplOrgnzList(vo));

        return vo;
    }

    @Override
    @Transactional
    public int update(Psnnl paramVo) {
        //1. 희망관서 삭제
        mapper.deleteApplOrgnz(paramVo);

        //2. 희망관서입력
        if(paramVo.getHopeOrgnz() != null && paramVo.getHopeOrgnz().size() > 0){
            List<ApplyOrgnz> newList = new ArrayList<ApplyOrgnz>();
            int idx = 1;
            int stayLevel = Integer.parseInt(StringUtil.NVL(paramVo.getStayLevel(), "0"));
            String userOrgnzId = paramVo.getOrgnzId();
            int userOrgnzIdx = paramVo.getHopeOrgnz().size() +1;
            for(ApplyOrgnz aoVo: paramVo.getHopeOrgnz()){
                if(stayLevel > 0){              //현관서 잔류 여부가 있을 때
                    aoVo.setStayLevl("N");
                    if(aoVo.getOrgnzId().equals(userOrgnzId)){
                        userOrgnzIdx = idx;
                    }

                    if(stayLevel >= idx || userOrgnzIdx <= idx){
                        aoVo.setStayLevl("Y");
                    }
                }else{
                    aoVo.setStayLevl("Y");
                }
                aoVo.setLevel(Integer.toString(idx));
                newList.add(aoVo);
                ++idx;
            }
            paramVo.setHopeOrgnz(newList);

            mapper.insertApplOrgnz(paramVo);
        }

        /*//2. 현관서잔류여부 수정
        User uVo = new User();
        uVo.setUserId(paramVo.getUserId());
        uVo.setStay5YearStartDt(paramVo.getStay5YearStartDt());
        return uMapper.updateStay5Year(uVo);*/

        //3. 인사정보(현관서 잔류여부) 수정
        return mapper.updateStayLevel(paramVo);
    }

    @Override
    @Transactional
    public int insertDemand(User paramVo) {
        int rtn = 0;
        //1.배치정보 insert
        PsnnlBatch pbVo = new PsnnlBatch();
        pbVo.setDivCodeId("PDV002");
        pbVo.setInStartDt("");
        pbVo.setInEndDt("");
        pbVo.setDt(paramVo.getPpmntBatch());
        pbVo.setStatCodeId("PST006");
        pbMapper.insert(pbVo);

        paramVo.setPsnnlBatchId(pbVo.getPsnnlBatchId());

        //2. 현재 인사정보(max(startDt))에 종료일(enddt) 설정 (2,3 순서 바뀌면 안됨)
        mapper.updateEnddt(paramVo);

        User uVo = uMapper.select(paramVo);
        String rankChangYn = "N";
        String orgnzChangeYn = "N";
        if(paramVo.getPrmtCodeId().equals("PMT001")  || paramVo.getPrmtCodeId().equals("PMT002") ){
            paramVo.setRankCodeId(uMapper.selectUpRankCodeId(uVo.getRankCodeId()));
            rankChangYn = "Y";
        }
        if(!uVo.getOrgnzId().equals(paramVo.getOrgnzId())){
            orgnzChangeYn = "Y";
        }

        //3. 개인 인사정보 insert
        rtn = mapper.insertDemand(paramVo);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userid", paramVo.getUserId());
        paramMap.put("rundt", paramVo.getPpmntBatch());
        paramMap.put("orgnzid", paramVo.getOrgnzId());
        paramMap.put("rankcodeid", paramVo.getRankCodeId());
        paramMap.put("orgnzchangeyn", orgnzChangeYn);
        paramMap.put("rankchangyn", rankChangYn);
        System.out.println(paramMap);
        if(paramVo.getPpmntBatch().equals(DateUtil.getDate("YYYY-MM-dd"))){
            //4-1. user update
            eMapper.updateUserPsnnl(paramMap);
        }else{
            //4-2. schedule insert
            mapper.insertSchedule(paramMap);
        }
        return rtn;
    }

    @Override
    public List<ApplyOrgnz> selectApplOrgnzList(Psnnl paramVo) {
        return mapper.selectApplOrgnzList(paramVo);
    }
}
