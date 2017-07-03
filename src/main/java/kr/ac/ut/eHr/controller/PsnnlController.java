package kr.ac.ut.eHr.controller;

import java.io.IOException;
import java.util.HashMap;
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
import kr.ac.ut.eHr.auth.LoginDetail;
import kr.ac.ut.eHr.common.CommonUtil;
import kr.ac.ut.eHr.common.JsonUtil;
import kr.ac.ut.eHr.common.StringUtil;
import kr.ac.ut.eHr.domain.Psnnl;
import kr.ac.ut.eHr.domain.PsnnlSearch;
import kr.ac.ut.eHr.domain.User;
import kr.ac.ut.eHr.service.CodeService;
import kr.ac.ut.eHr.service.OrgnzService;
import kr.ac.ut.eHr.service.PsnnlService;

@Controller
@RequestMapping("/psnnl")
public class PsnnlController {

    @Resource(name = "PsnnlService")
    private PsnnlService service;

    @Resource(name = "OrgnzService")
    private OrgnzService orgnzService;

    @Resource(name = "CodeService")
    private CodeService codeService;

    @RequestMapping(value = {"/list.do", "/my/list.do"})
    public void list(PsnnlSearch paramVo, ModelMap model) {
        if(StringUtil.NVL(paramVo.getUserId()).equals("")){
            LoginDetail user = CommonUtil.getLoginDetail();
            paramVo.setUserId(user.getUserid());
        }
        PaginationInfo paginationInfo = CommonUtil.setPageParam(paramVo);
        paginationInfo.setTotalRecordCount(service.selectListCnt(paramVo));
        model.addAttribute("resultList", service.selectList(paramVo));

        model.addAttribute("psnnlSearchVo", paramVo);
        model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(orgnzService.selectComboList(null)));
        model.addAttribute("psnnlDivCombo", CommonUtil.setCodeCombo(codeService.selectList("PDV")));
        model.addAttribute("paginationInfo", paginationInfo);
    }

    @RequestMapping(value = {"/view.do", "/my/view.do"})
    public void view(@RequestParam(value="psnnlId", required=false) String psnnlId, ModelMap model) {
        model.addAttribute("psnnlVo", service.select(psnnlId));
        model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(orgnzService.selectComboList("Y")));
    }

    @RequestMapping(value="/my/add.do", method=RequestMethod.POST)
    public String add(@ModelAttribute("psnnlVo") Psnnl paramVo, ModelMap model) {
        int rtn = service.update(paramVo);
        model.addAttribute("result", (rtn > 0)? "success": "fail");

        return "/psnnl/my/view";
    }

    @RequestMapping(value = "/my/popDetail.do")
    public void popDetail(Psnnl paramVo, ModelMap model) {
        model.addAttribute("resultList", service.selectApplOrgnzDisstsList(paramVo));
    }

    @RequestMapping(value = "/popDemand.do")
    public void popDemand(@RequestParam(value="userId") String userId, ModelMap model) {
        User vo = new User();
        vo.setUserId(userId);
        model.addAttribute("userVo", vo);
        model.addAttribute("targetCombo", CommonUtil.setCodeCombo(codeService.selectList("TGD")));
        model.addAttribute("prmtCombo", CommonUtil.setCodeCombo(codeService.selectList("PMT")));
        model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(orgnzService.selectComboList(null)));
    }

    @RequestMapping(value="/addDemand.do", method=RequestMethod.POST)
    @ResponseBody
    public String addDemand(@ModelAttribute("userVo") User paramVo) throws IOException{
        int rtn = service.insertDemand(paramVo);

        Map<String, Object> info = new HashMap<String, Object>();
        info.put("result", (rtn > 0)? "success": "fail");

        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }
}
