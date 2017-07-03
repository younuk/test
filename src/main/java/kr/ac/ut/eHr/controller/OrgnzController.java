package kr.ac.ut.eHr.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.ut.eHr.common.CommonUtil;
import kr.ac.ut.eHr.common.StringUtil;
import kr.ac.ut.eHr.domain.Orgnz;
import kr.ac.ut.eHr.domain.OrgnzSearch;
import kr.ac.ut.eHr.service.OrgnzService;

@Controller
@RequestMapping("/orgnz")
public class OrgnzController {

    @Resource(name = "OrgnzService")
    private OrgnzService service;

    @RequestMapping(value = "/list.do")
    public void list(OrgnzSearch paramVo, ModelMap model) {

        /*PaginationInfo paginationInfo = CommonUtil.setPageParam(paramVo);
        paginationInfo.setTotalRecordCount(service.selectListCnt(paramVo));
        model.addAttribute("paginationInfo", paginationInfo);*/

        model.addAttribute("resultList", service.selectList(paramVo));
        model.addAttribute("orgnzSearchVo", paramVo);
        model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(service.selectComboList(null)));
    }


    @RequestMapping(value = "/view.do")
    public void view(@RequestParam(value="orgnzId", required=false) String orgnzId, ModelMap model) {

        Orgnz vo = new Orgnz();

        if(!StringUtil.NVL(orgnzId).equals("")){
            vo = service.select(orgnzId);
        }

        model.addAttribute("orgnzVo", vo);
    }

    @RequestMapping(value="/add.do", method=RequestMethod.POST)
    public String add(@ModelAttribute("orgnzVo") Orgnz paramVo, ModelMap model) {
        int rtn = 0;

        if(paramVo.getOrgnzId().equals("")){
            rtn = service.insert(paramVo);
        }else{
            rtn = service.update(paramVo);
        }

        model.addAttribute("result", (rtn > 0)? "success": "fail");

        return "orgnz/view";
    }

    @RequestMapping(value="/delete.do", method=RequestMethod.POST)
    public String delete(@ModelAttribute("orgnzVo") Orgnz paramVo, ModelMap model) {
        String orgnzId = paramVo.getOrgnzId();
        model.addAttribute("result", (service.delete(orgnzId) > 0)? "success": "fail");

        return "orgnz/view";
    }
}
