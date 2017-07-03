package kr.ac.ut.eHr.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.ut.eHr.domain.Code;
import kr.ac.ut.eHr.service.CodeService;

@Controller
public class CodeController {

    @Resource(name = "CodeService")
    private CodeService service;

    @RequestMapping(value = "/test.do")
    public void selectList() {
        System.out.println("!!!!!!!!!!!!!!!!!!!");
        List<Code> rtnArr = service.selectList("");


        for(Code bo : rtnArr){
            System.out.println(bo.getCodeId());
            System.out.println(bo.getCateCodeId());
        }
    }
}
