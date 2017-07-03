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

import kr.ac.ut.eHr.common.JsonUtil;
import kr.ac.ut.eHr.common.StringUtil;
import kr.ac.ut.eHr.domain.Login;
import kr.ac.ut.eHr.service.LoginService;

@Controller
public class LoginController {

    @Resource(name = "LoginService")
    private LoginService service;


    @RequestMapping(value = "/login.do")
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/auth/changePasswordPage.do")
    public String changePasswordPage(@RequestParam("loginId") String loginId, ModelMap model) {
        model.addAttribute("loginId", loginId);

        return "/auth/popPassword";
    }

    @RequestMapping(value="/auth/checkPwd.do", method=RequestMethod.POST)
    @ResponseBody
    public String checkPwd(@ModelAttribute("login") Login paramVo) throws IOException{
        Map<String, Object> info = new HashMap<String, Object>();

        Login lVo = service.getLogin(paramVo.getLoginId());

        if(lVo != null && lVo.getPassword().equals(StringUtil.encrypt(paramVo.getPassword()))){
            info.put("result", "success");
        }else{
            info.put("result", "fail");
        }
        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }

    @RequestMapping(value="/auth/changePwd.do", method=RequestMethod.POST)
    @ResponseBody
    public String changePassword(@ModelAttribute("login") Login paramVo) throws IOException{
        Map<String, Object> info = new HashMap<String, Object>();

        paramVo.setPassword(StringUtil.encrypt(paramVo.getPassword()));
        info.put("result", (service.updateLoginPwd(paramVo) > 0)? "success": "fail");

        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }
}
