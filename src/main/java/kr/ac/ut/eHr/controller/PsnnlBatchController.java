package kr.ac.ut.eHr.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.ac.ut.eHr.common.CommonUtil;
import kr.ac.ut.eHr.common.JsonUtil;
import kr.ac.ut.eHr.common.StringUtil;
import kr.ac.ut.eHr.domain.Code;
import kr.ac.ut.eHr.domain.PsnnlBatch;
import kr.ac.ut.eHr.service.CodeService;
import kr.ac.ut.eHr.service.PsnnlBatchService;

@Controller
@RequestMapping("/batch")
public class PsnnlBatchController {

    @Resource(name = "PsnnlBatchService")
    private PsnnlBatchService service;

    @Resource(name = "CodeService")
    private CodeService codeService;

    @RequestMapping(value = "/list.do")
    public void list(PsnnlBatch paramVo, ModelMap model) {
        PaginationInfo paginationInfo = CommonUtil.setPageParam(paramVo);
        paginationInfo.setTotalRecordCount(service.selectListCnt(paramVo));
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("resultList", service.selectList(paramVo));
        model.addAttribute("psnnlSearchVo", paramVo);

        //model.addAttribute("degreeCombo", CommonUtil.setCodeCombo(service.selectDegreeComboList("")));
        model.addAttribute("srchStateCombo", CommonUtil.setCodeCombo(codeService.selectList("PST")));
        System.out.println(CommonUtil.setCodeCombo(codeService.selectList("PST")));;
    }

    @RequestMapping(value = "/view.do")
    public void view(@RequestParam(value="psnnlBatchId", required=false) String psnnlBatchId, PsnnlBatch searchVo, ModelMap model) {
        PsnnlBatch vo = new PsnnlBatch();

        if(!StringUtil.NVL(psnnlBatchId).equals("")){
            vo = service.select(psnnlBatchId);
        }else{
            vo.setDivCodeId("PDV001");
        }

        List<Code> cList = codeService.selectList("PST");
        for(Code cVo : cList){
            if(cVo.getCodeName().equals("인사대기자선정완료") || cVo.getCodeName().equals("배치실행완료")){
                cVo.setCodeName(cVo.getCodeName().replaceAll("완료", ""));
            }
        }
        //model.addAttribute("divCodeCombo", CommonUtil.setCodeCombo(codeService.selectList("PDV")));
        model.addAttribute("stateCombo", CommonUtil.setCodeCombo(cList, false));
        model.addAttribute("psnnlBatchVo", vo);
        model.addAttribute("psnnlSearchVo", searchVo);
    }

    @RequestMapping(value="/add.do", method=RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute("psnnlBatchVo") PsnnlBatch paramVo) throws IOException {
        //인사차수 재설정
        paramVo.setDegree(paramVo.getDt().substring(0, 4)+"-"+ paramVo.getDegree());
        paramVo.setStatCodeId(StringUtil.NVL(paramVo.getStatCodeId(), "PST001"));

        int rtn = (paramVo.getPsnnlBatchId().equals(""))? service.insert(paramVo): service.update(paramVo);

        return CommonUtil.makeRtnJson(rtn);
    }

    @RequestMapping(value="/delete.do", method=RequestMethod.POST)
    @ResponseBody
    public String delete(@ModelAttribute("psnnlBatchVo") PsnnlBatch paramVo, ModelMap model) throws IOException {
        int rtn = service.delete(paramVo.getPsnnlBatchId());

        return CommonUtil.makeRtnJson(rtn);
    }

    @RequestMapping(value="/checkInProgress.do", method=RequestMethod.POST)
    @ResponseBody
    public String checkInProgress() throws IOException{
        Map<String, Object> info = new HashMap<String, Object>();

        info.put("result", service.selectRunPsnnlBatch());
        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }

    @RequestMapping(value="/checkHopeIn.do", method=RequestMethod.POST)
    @ResponseBody
    public String checkHopeIn(@RequestParam("psnnlBatchId") String psnnlBatchId) throws IOException{
        Map<String, Object> info = new HashMap<String, Object>();

        info.put("result", (service.countHopeOrgnzCheck(psnnlBatchId) > 0)? "fail": "success");
        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }
}
