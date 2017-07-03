package kr.ac.ut.eHr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.ut.eHr.common.DateUtil;
import kr.ac.ut.eHr.domain.Code;
import kr.ac.ut.eHr.domain.Orgnz;
import kr.ac.ut.eHr.domain.Psnnl;
import kr.ac.ut.eHr.domain.PsnnlBatch;
import kr.ac.ut.eHr.domain.User;
import kr.ac.ut.eHr.mapper.EHrSchedulerMapper;
import kr.ac.ut.eHr.mapper.OrgnzMapper;
import kr.ac.ut.eHr.mapper.PsnnlBatchMapper;
import kr.ac.ut.eHr.mapper.PsnnlMapper;

@Service("PsnnlBatchService")
public class PsnnlBatchServiceImpl implements PsnnlBatchService {

    @Resource(name = "PsnnlBatchMapper")
    private PsnnlBatchMapper mapper;

    @Resource(name = "PsnnlMapper")
    private PsnnlMapper pMapper;


    @Resource(name="EHrSchedulerMapper")
    private EHrSchedulerMapper eMapper;


    @Resource(name="OrgnzMapper")
    private OrgnzMapper oMapper;

    @Override
    public List<PsnnlBatch> selectList(PsnnlBatch paramVo) {
        return mapper.selectList(paramVo);
    }

    @Override
    public int selectListCnt(PsnnlBatch vo) {
        return mapper.selectListCnt(vo);
    }

    @Override
    public PsnnlBatch select(String param) {
        return mapper.select(param);
    }

    @Override
    @Transactional
    public int insert(PsnnlBatch paramVo) {
        mapper.insert(paramVo);

        if (paramVo.getStatCodeId().equals("PST002")) {
            this.setTarget(paramVo);
        } else if (paramVo.getStatCodeId().equals("PST005")) {
            this.setBatch(paramVo);
        }

        return 1;
    }

    @Override
    @Transactional
    public int update(PsnnlBatch paramVo) {
        mapper.update(paramVo);

        if (paramVo.getStatCodeId().equals("PST002")) {
            this.setTarget(paramVo);
        } else if (paramVo.getStatCodeId().equals("PST005")) {
            this.setBatch(paramVo);
        } else if (paramVo.getStatCodeId().equals("PST006")) {
            this.setPsnnl(paramVo);
        }
        return 1;
    }

    @Override
    @Transactional
    public int delete(String param) {
        mapper.deleteApplOrgnz(param);
        mapper.deletePsnnlManual(param);
        mapper.deletePsnnl(param);

        return mapper.delete(param);
    }

    @Override
    public List<Code> selectDegreeComboList(String param) {
        return mapper.selectDegreeComboList(param);
    }

    @Override
    public PsnnlBatch selectRunPsnnlBatch() {
        return mapper.selectRunPsnnlBatch();
    }

    @Transactional
    private void setTarget(PsnnlBatch paramVo) {
        List<Psnnl> stayLevelList = pMapper.selectExixtStaylevelList(paramVo.getPsnnlBatchId());

        // 1. 자동생성 대상자 삭제
        pMapper.delete(paramVo.getPsnnlBatchId());

        // 2. 자동생성 대상자 조회
        List<Psnnl> tgtList = pMapper.selectTargetList(paramVo);

        for(Psnnl pVo: tgtList){
            for(Psnnl slVo: stayLevelList){
                if(pVo.getUserId().equals(slVo.getUserId())){
                    pVo.setStayLevel(slVo.getStayLevel());
                    continue;
                }
            }
        }

        if (tgtList != null && tgtList.size() > 0) {
            /*
             * List<Psnnl> tgtList2 = new ArrayList<Psnnl>(); for(Psnnl pVo:
             * tgtList){ pVo.setStartDt(paramVo.getDt()); tgtList2.add(pVo); }
             */

            //3. 대상자 insert
            pMapper.insertAll(tgtList);

            //4. insert 된 대상자의 psnnl_manual 정보 update
            pMapper.updateTargetManual(paramVo.getPsnnlBatchId());
        }

        //5. 인사대상자 아닌사람의 희망관서삭제
        pMapper.deleteApplOrgnzNoTarget(paramVo.getPsnnlBatchId());
    }

    @Transactional
    private void setBatch(PsnnlBatch paramVo) {
        List<Psnnl> orgList = new ArrayList<Psnnl>();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 1. 배치실행 전, psnnl orgnzid/orgnzrank null처리 and 불만족지수 계산
        pMapper.updateBatchBefore(paramVo.getPsnnlBatchId());
        // 1-1.  배치이력없는사람은 평균값으로 불문족지수 update
        pMapper.updateBatchBefore2(paramVo.getPsnnlBatchId());

        // 2-1. 툭수직무 우선 배치
        paramMap.put("psnnlBatchId", paramVo.getPsnnlBatchId());



        List<Orgnz> oList = oMapper.selectComboList("Y");
        //for(Orgnz oVo: oList){
        //Orgnz oVo = new Orgnz();
        //oVo.setOrgnzId("79");
        for (int jdx = 1; jdx <= 16; jdx++) {
            int specialDutyCnt = pMapper.selectBatchSpecialDutyCnt("");
            Psnnl pVo = null;
            for (int idx = 1; idx <= specialDutyCnt; idx++) {
                pVo = new Psnnl();
                pVo = pMapper.selectBatchSpecialDuty(Integer.toString(jdx), paramVo.getPsnnlBatchId(), "Y");
                if (pVo != null) {
                    pVo.setPsnnlResultDiv("S");
                    pMapper.updateBatchPsnnl(pVo);
                }
            }
        }
        for (int jdx = 1; jdx <= 16; jdx++) {
            int specialDutyCnt = pMapper.selectBatchSpecialDutyCnt("");
            Psnnl pVo = null;
            for (int idx = 1; idx <= specialDutyCnt; idx++) {
                pVo = new Psnnl();
                pVo = pMapper.selectBatchSpecialDuty(Integer.toString(jdx), paramVo.getPsnnlBatchId(), "");
                if (pVo != null) {
                    pVo.setPsnnlResultDiv("S1");
                    pMapper.updateBatchPsnnl(pVo);
                }
            }
        }

        // 2-2. 1순위에서 16순위까지 불만족지수로 줄세워서 기관 to 갯수만큼 배치
        for (int idx = 1; idx <= 16; idx++) {
            paramMap.put("level", idx);
            paramMap.put("staylevelyn", "Y");
            orgList = pMapper.selectBatchOrgnzRank(paramMap);
            for (Psnnl vo : orgList) {
                pMapper.updateBatchPsnnl(vo);
            }
        }

        for (int idx = 1; idx <= 16; idx++) {
            paramMap.put("level", idx);
            paramMap.put("staylevelyn", "Y");
            orgList = pMapper.selectBatchOrgnzRank(paramMap);
            for (Psnnl vo : orgList) {
                pMapper.updateBatchPsnnl(vo);
            }
        }

        for (int idx = 1; idx <= 16; idx++) {
            paramMap.put("level", idx);
            paramMap.put("staylevelyn", "");
            orgList = pMapper.selectBatchOrgnzRank(paramMap);
            for (Psnnl vo : orgList) {
                pMapper.updateBatchPsnnl(vo);
            }
        }
        for (int idx = 1; idx <= 16; idx++) {
            paramMap.put("level", idx);
            paramMap.put("staylevelyn", "");
            orgList = pMapper.selectBatchOrgnzRank(paramMap);
            for (Psnnl vo : orgList) {
                pMapper.updateBatchPsnnl(vo);
            }
        }

        // 3. 합격선 저장
        //pMapper.updateBatchPassScore(paramVo.getPsnnlBatchId());
    }

    @Transactional
    private void setPsnnl(PsnnlBatch paramVo) {
        // 1. 인사실행완료된 대상자 선택
        List<Map<String, Object>> pList = pMapper.selectPsnnlUserList(paramVo.getPsnnlBatchId());

        boolean todayYn = (paramVo.getDt().equals(DateUtil.getDate("yyyy-MM-dd")));
        User vo = null;
        for (Map<String, Object> map : pList) {
            vo = new User();
            // 2. 현재 인사정보(max(startDt))에 종료일(enddt) 설정 (2,3 순서 바뀌면 안됨)
            vo.setPpmntBatch((String)map.get("ppmntBatch"));
            vo.setUserId(Integer.toString((Integer)map.get("userid")));
            vo.setPsnnlBatchId(paramVo.getPsnnlBatchId());
            pMapper.updateEnddt(vo);

            if(todayYn)
                eMapper.updateUserPsnnl(map);
        }

        // 4. schedule insert
        if(!todayYn)
            pMapper.insertSelectSchedule(paramVo.getPsnnlBatchId());
    }

    @Override
    public int countHopeOrgnzCheck(String param){
        return mapper.countHopeOrgnzCheck(param);
    }

}



