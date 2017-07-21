package kr.ac.ut.eHr.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("resultList", service.selectList(paramVo));
        model.addAttribute("orgnzSearchVo", paramVo);
        model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(service.selectComboList(null)));
    }

    @RequestMapping(value = "/view.do")
    public void view(@RequestParam(value="orgnzId", required=false) String orgnzId, OrgnzSearch paramVo, ModelMap model) {
        Orgnz vo = (!StringUtil.NVL(orgnzId).equals(""))? service.select(orgnzId): new Orgnz();

        model.addAttribute("orgnzVo", vo);
        model.addAttribute("orgnzSearchVo", paramVo);
    }

    @RequestMapping(value="/add.do", method=RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute("orgnzVo") Orgnz paramVo) throws IOException {
        int rtn = (paramVo.getOrgnzId().equals(""))? service.insert(paramVo): service.update(paramVo);

        return CommonUtil.makeRtnJson(rtn);
    }

    @RequestMapping(value="/delete.do", method=RequestMethod.POST)
    @ResponseBody
    public String delete(@ModelAttribute("orgnzVo") Orgnz paramVo) throws IOException {
        String orgnzId = paramVo.getOrgnzId();
        int rtn = service.delete(orgnzId);

        return CommonUtil.makeRtnJson(rtn);
    }
}
