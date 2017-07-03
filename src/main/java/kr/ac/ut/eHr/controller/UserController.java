package kr.ac.ut.eHr.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.ac.ut.eHr.auth.LoginDetail;
import kr.ac.ut.eHr.common.CommonUtil;
import kr.ac.ut.eHr.common.DateUtil;
import kr.ac.ut.eHr.common.JsonUtil;
import kr.ac.ut.eHr.common.StringUtil;
import kr.ac.ut.eHr.common.excel.ExcelRead;
import kr.ac.ut.eHr.common.excel.ExcelReadOption;
import kr.ac.ut.eHr.domain.Login;
import kr.ac.ut.eHr.domain.Orgnz;
import kr.ac.ut.eHr.domain.PsnnlBatch;
import kr.ac.ut.eHr.domain.User;
import kr.ac.ut.eHr.domain.UserSearch;
import kr.ac.ut.eHr.service.CodeService;
import kr.ac.ut.eHr.service.OrgnzService;
import kr.ac.ut.eHr.service.PsnnlBatchService;
import kr.ac.ut.eHr.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "UserService")
    private UserService service;

    @Resource(name = "OrgnzService")
    private OrgnzService orgnzService;

    @Resource(name = "CodeService")
    private CodeService codeService;


    @Resource(name = "PsnnlBatchService")
    private PsnnlBatchService pbService;

    @Value("#{config['user_sample_file']}") private String _USER_SAMPLE_FILE;
    @Value("#{config['file_dir']}") private String _FILE_DIR;


    @RequestMapping(value = "/list.do")
    public void list(UserSearch paramVo, ModelMap model) {
        LoginDetail lVo = CommonUtil.getLoginDetail();

        //관서 관리자일 경우, 본인 관서만 조회
        if(StringUtil.NVL(lVo.getUserAuth()).equals("ROL002")){
            User uVo = new User();
            uVo.setUserId(lVo.getUserid());
            uVo = service.select(uVo);

            List<Orgnz> orgList = new ArrayList<Orgnz>();
            orgList.add(orgnzService.select(uVo.getOrgnzId()));

            model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(orgList, false));
            paramVo.setOrgnzId(uVo.getOrgnzId());
        }else{
            model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(orgnzService.selectComboList(null)));
        }

        PaginationInfo paginationInfo = CommonUtil.setPageParam(paramVo);
        paginationInfo.setTotalRecordCount(service.selectListCnt(paramVo));
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("resultList", service.selectList(paramVo));
        model.addAttribute("userSearchVo", paramVo);

        model.addAttribute("rankCombo", CommonUtil.setCodeCombo(codeService.selectList("RNK")));
        model.addAttribute("spcDutyCombo", CommonUtil.setCodeCombo(codeService.selectList("SPD")));
    }


    @RequestMapping(value = "/view.do")
    public void view(@RequestParam(value="userId", required=false) String userId, ModelMap model) {
        User vo = new User();
        PsnnlBatch pbVo = pbService.selectRunPsnnlBatch();

        vo.setUserId(userId);
        if(!StringUtil.NVL(userId).equals("")){
            if(pbVo != null)
                vo.setPsnnlBatchId(pbVo.getPsnnlBatchId());
            vo = service.select(vo);
        }

        //관서 관리자일 경우, 본인 관서만 조회
        LoginDetail lVo = CommonUtil.getLoginDetail();
        if(StringUtil.NVL(lVo.getUserAuth()).equals("ROL002")){
            List<Orgnz> orgList = new ArrayList<Orgnz>();
            orgList.add(orgnzService.select(vo.getOrgnzId()));

            model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(orgList, false));
        }else{
            model.addAttribute("orgnzCombo", CommonUtil.setCodeCombo(orgnzService.selectComboList(null)));
        }


        if(pbVo != null && pbVo.getRunYn().equals("N")){ // 인사배치 정보가 있고, 인사배치실행중 아닐 때
            model.addAttribute("targetCombo", CommonUtil.setCodeCombo(codeService.selectList("TGD")));
            model.addAttribute("prmtCombo", CommonUtil.setCodeCombo(codeService.selectList("PMT")));
        }
        model.addAttribute("rankCombo", CommonUtil.setCodeCombo(codeService.selectList("RNK")));
        model.addAttribute("spcDutyCombo", CommonUtil.setCodeCombo(codeService.selectList("SPD")));
        model.addAttribute("authCodeId", CommonUtil.setCodeCombo(codeService.selectList("ROL")));
        model.addAttribute("runBatchInfo", pbVo);
        model.addAttribute("userVo", vo);
    }

    @RequestMapping(value = "/my/view.do")
    public void view(ModelMap model) {
        LoginDetail user = CommonUtil.getLoginDetail();
        User vo = new User();
        vo.setUserId(user.getUserid());
        vo = service.select(vo, true);

        model.addAttribute("userVo", vo);
    }

    @RequestMapping(value="/add.do", method=RequestMethod.POST)
    public String add(@ModelAttribute("userVo") User paramVo,  ModelMap model) {
        LoginDetail lvo= CommonUtil.getLoginDetail();
        boolean updPsnnlYn = (lvo.getUserAuth().equals("ROL001"))?true: false;

        int rtn = (paramVo.getUserId().equals(""))? service.insert(paramVo, updPsnnlYn): service.update(paramVo, updPsnnlYn);

        model.addAttribute("result", (rtn > 0)? "success": "fail");

        return "user/view";
    }


    @RequestMapping(value="/delete.do", method=RequestMethod.POST)
    public String delete(@ModelAttribute("userVo") User paramVo, ModelMap model) {
        String userId = paramVo.getUserId();
        model.addAttribute("result", (service.delete(userId) > 0)? "success": "fail");

        return "user/view";
    }

    @RequestMapping(value="/checkLoginId.do", method=RequestMethod.POST)
    @ResponseBody
    public String checkLoginId(@RequestParam("loginId") String loginId
                             , @RequestParam("userId") String userId) throws IOException{
        Map<String, Object> info = new HashMap<String, Object>();
        User paramVo = new User();

        paramVo.setUserId(userId);
        paramVo.setLoginId(loginId);
        info.put("result", (service.selectLoginCnt(paramVo) > 0)? "fail": "success");

        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }

    @RequestMapping(value="/changePwd.do", method=RequestMethod.POST)
    @ResponseBody
    public String changePwd(@RequestParam(value="userId", required=false) String userId
                          , @RequestParam(value="password", required=false) String password) throws IOException{
        Map<String, Object> info = new HashMap<String, Object>();
        Login paramVo = new Login();


        if(StringUtil.NVL(userId).equals("")){
            LoginDetail lvo= CommonUtil.getLoginDetail();
            userId = lvo.getUserid();
        }

        paramVo.setUserId(userId);
        password = StringUtil.NVL(password);
        if(password.equals("")){
            Login lVo = service.selectLogin(userId);
            paramVo.setPassword(StringUtil.encrypt("0000"));
            paramVo.setPwChgYn("N");
        }else{
            paramVo.setPassword(StringUtil.encrypt(password));
            paramVo.setPwChgYn("Y");
        }

        info.put("result", (service.updateLoginPwd(paramVo) > 0)? "success": "fail");

        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }

    @RequestMapping(value="/changeStay5.do", method=RequestMethod.POST)
    @ResponseBody
    public String changeStay5() throws IOException{
        LoginDetail user = CommonUtil.getLoginDetail();

        Map<String, Object> info = new HashMap<String, Object>();
        info.put("result", (service.updateStay5(user.getUserAuth(), "CLEAR") > 0)? "success": "fail");

        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }


    @RequestMapping(value="/selectPopJang.do")
    public String selectPopJang(@ModelAttribute("userVo") User paramVo, HttpServletRequest request, ModelMap model) {
        String div = StringUtil.NVL(request.getParameter("div"));
        List<String> userArr = new ArrayList<String>();
        if(div.equals("all")){
            String[] strArr = paramVo.getUserId().split(",");
            if(strArr != null && strArr.length > 0)
                userArr = Arrays.asList(strArr);

            model.addAttribute("list", service.selectPopJang(userArr));
        }else{
            userArr.add(paramVo.getUserId());
            model.addAttribute("list", service.selectPopJang(userArr));
        }
        return "user/popPrintJang";
    }

    @RequestMapping(value = "/fileDown.do", method = RequestMethod.GET)
    public void  fileDown(HttpServletResponse response) {
        CommonUtil.fileDownLoad(new File(_USER_SAMPLE_FILE), response);
    }

    @RequestMapping(value = "/excelUploadAjax", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String excelUploadAjax(MultipartHttpServletRequest request){
        MultipartFile excelFile =request.getFile("excelFile");
        if(excelFile==null || excelFile.isEmpty()){
            return "null";
        }

        int rtn = 0;
        File destFile = new File(_FILE_DIR+DateUtil.getDate("yyyyMMddHHmmss")+excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(destFile);
            ExcelReadOption excelReadOption = new ExcelReadOption();
            excelReadOption.setFilePath(destFile.getAbsolutePath());
            excelReadOption.setOutputColumns("A","B","C","D","E","F","G","H","I","J");
            excelReadOption.setStartRow(2);

            List<User> insList = new ArrayList<User>();
            List<Map<String, String>> excelContent =ExcelRead.read(excelReadOption);
            if(excelContent != null && excelContent.size()>0){
                User uVo = null;
                String orgnzId = "";
                for(Map<String, String> article: excelContent){
                    System.out.println(article);
                    if(!StringUtil.NVL(article.get("A")).equals("")){
                        uVo = new User();
                        uVo.setName(article.get("A"));
                        uVo.setOrgnzId(article.get("B"));
                        uVo.setRankCodeId(article.get("C"));
                        uVo.setBirthDt(article.get("D"));
                        uVo.setRankAppmnt(article.get("E"));
                        uVo.setOrgnzAppmnt(article.get("F"));
                        uVo.setPpmntBatch(article.get("G"));
                        uVo.setSpecialDutyCodeId(article.get("H"));
                        uVo.setLoginId(article.get("I"));
                        uVo.setAuthCodeId(article.get("J"));

                        orgnzId = service.selectDataValidate(uVo);
                        if(!StringUtil.NVL(orgnzId).equals("")){
                            uVo.setOrgnzId(orgnzId);
                            insList.add(uVo);
                        }
                    }
                }
            }
            if(insList != null && insList.size() > 0){
                rtn = service.insertExcelAll(insList);
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            destFile.delete();
        }

        Map<String, Object> rtnMap = new HashMap<String, Object>();
        rtnMap.put("result", rtn);

        return (new JSONObject(rtnMap)).toString();
    }
}
