package kr.ac.ut.eHr.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.ut.eHr.common.CommonUtil;
import kr.ac.ut.eHr.common.StringUtil;
import kr.ac.ut.eHr.domain.Code;
import kr.ac.ut.eHr.domain.StatSearch;
import kr.ac.ut.eHr.service.CodeService;
import kr.ac.ut.eHr.service.OrgnzService;
import kr.ac.ut.eHr.service.PsnnlBatchService;
import kr.ac.ut.eHr.service.StatService;

@Controller
@RequestMapping("/stat")
public class StatController {

    @Resource(name = "StatService")
    private StatService service;

    @Resource(name = "PsnnlBatchService")
    private PsnnlBatchService pbService;

    @Resource(name = "OrgnzService")
    private OrgnzService orgnzService;

    @Resource(name = "CodeService")
    private CodeService codeService;

    @Value("#{config['file_dir']}") private String _FILE_DIR;


    @RequestMapping(value = "/selectApply.do")
    public void selectApply(@ModelAttribute StatSearch paramVo, ModelMap model) {
        List<Code> degreeList = pbService.selectDegreeComboList("PDV001");
        if(paramVo == null ||(StringUtil.NVL(paramVo.getSrchDegree()).equals("") && (degreeList != null && degreeList.size() > 0))){
            paramVo.setSrchDegree((degreeList.get(0)).getCateCodeId());
        }
        model.addAttribute("resultList", service.selectApply(paramVo));
        model.addAttribute("degreeCombo", degreeList);
        model.addAttribute("orgnzCombo", orgnzService.selectComboList("Y"));
        model.addAttribute("rankCombo", codeService.selectList("RNK"));
        model.addAttribute("spcDutyCombo", codeService.selectList("SPD"));
        model.addAttribute("srchInfo", paramVo);
    }

    @RequestMapping(value = "/selectIn.do")
    public void selectIn(@ModelAttribute StatSearch paramVo, ModelMap model) {
        List<Code> degreeList = pbService.selectDegreeComboList("PDV001");
        if(paramVo == null ||(StringUtil.NVL(paramVo.getSrchDegree()).equals("") && (degreeList != null && degreeList.size() > 0))){
            paramVo.setSrchDegree((degreeList.get(0)).getCateCodeId());
        }
        model.addAttribute("resultList", service.selectIn(paramVo));
        model.addAttribute("degreeCombo", degreeList);
        model.addAttribute("orgnzCombo", orgnzService.selectComboList("Y"));
        model.addAttribute("rankCombo", codeService.selectList("RNK"));
        model.addAttribute("spcDutyCombo", codeService.selectList("SPD"));
        model.addAttribute("srchInfo", paramVo);
    }

    @RequestMapping(value = "/selectInOut.do")
    public void selectInOut(@ModelAttribute StatSearch paramVo, ModelMap model) {
        List<Code> degreeList = pbService.selectDegreeComboList("PDV001");
        if(paramVo == null ||(StringUtil.NVL(paramVo.getSrchDegree()).equals("") && (degreeList != null && degreeList.size() > 0))){
            paramVo.setSrchDegree((degreeList.get(0)).getCateCodeId());
        }
        model.addAttribute("resultList", service.selectInOut(paramVo));
        model.addAttribute("degreeCombo", degreeList);
        model.addAttribute("orgnzCombo", orgnzService.selectComboList("Y"));
        model.addAttribute("rankCombo", codeService.selectList("RNK"));
        model.addAttribute("spcDutyCombo", codeService.selectList("SPD"));
        model.addAttribute("srchInfo", paramVo);
    }

    @RequestMapping(value = "/selectAvg.do")
    public void selectAvg(@ModelAttribute StatSearch paramVo, ModelMap model) {
        List<Code> degreeList = pbService.selectDegreeComboList("PDV001");
        if(paramVo == null ||(StringUtil.NVL(paramVo.getSrchDegree()).equals("") && (degreeList != null && degreeList.size() > 0))){
            paramVo.setSrchDegree((degreeList.get(0)).getCateCodeId());
        }
        model.addAttribute("resultList", service.selectAvg(paramVo));
        model.addAttribute("degreeCombo", degreeList);
        model.addAttribute("orgnzCombo", orgnzService.selectComboList("Y"));
        model.addAttribute("spcDutyCombo", codeService.selectList("SPD"));
        model.addAttribute("srchInfo", paramVo);
    }

    @RequestMapping(value = "/applyFileDown.do")
    public void  applyFileDown(@ModelAttribute StatSearch paramVo, HttpServletResponse response) {
        FileOutputStream fileoutputstream = null;
        File downloadFile = null;
        String fullPath = CommonUtil.makeFileName(_FILE_DIR, "rank_");

        XSSFWorkbook workbook=new XSSFWorkbook();       //1차로 workbook을 생성
        XSSFSheet sheet=workbook.createSheet("sheet");    //2차는 sheet생성
        XSSFRow row=null;                               //엑셀의 행
        int i=0,j=0;

        row=sheet.createRow(i++);
        row.createCell(j++).setCellValue("관서명");
        row.createCell(j++).setCellValue("1희망지");
        row.createCell(j++).setCellValue("2희망지");
        row.createCell(j++).setCellValue("3희망지");
        row.createCell(j++).setCellValue("4희망지");
        row.createCell(j++).setCellValue("5희망지");
        row.createCell(j++).setCellValue("6희망지");
        row.createCell(j++).setCellValue("7희망지");
        row.createCell(j++).setCellValue("8희망지");
        row.createCell(j++).setCellValue("9희망지");
        row.createCell(j++).setCellValue("10희망지");
        row.createCell(j++).setCellValue("11희망지");
        row.createCell(j++).setCellValue("12희망지");
        row.createCell(j++).setCellValue("13희망지");
        row.createCell(j++).setCellValue("14희망지");
        row.createCell(j++).setCellValue("15희망지");
        row.createCell(j++).setCellValue("16희망지");

        List<Map<String, Object>> rtnList = service.selectApply(paramVo);
        if(rtnList !=null &&rtnList.size() >0){
            for(Map<String, Object> oneMap : rtnList){
                j = 0;
                row=sheet.createRow(i++);
                row.createCell(j++).setCellValue((String)oneMap.get("name"));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o1")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o2")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o3")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o4")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o5")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o6")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o7")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o8")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o9")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o10")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o11")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o12")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o13")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o14")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o15")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("o16")));
            }
        }

        try {
            fileoutputstream = new FileOutputStream(fullPath);
            workbook.write(fileoutputstream);               //파일을 쓴다
            downloadFile = new File(fullPath);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileoutputstream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        CommonUtil.fileDownLoad(downloadFile, response);
    }


    @RequestMapping(value = "/inFileDown.do")
    public void  inFileDown(@ModelAttribute StatSearch paramVo, HttpServletResponse response) {
        FileOutputStream fileoutputstream = null;
        File downloadFile = null;
        String fullPath = CommonUtil.makeFileName(_FILE_DIR, "transfer_score_");

        XSSFWorkbook workbook=new XSSFWorkbook();       //1차로 workbook을 생성
        XSSFSheet sheet=workbook.createSheet("sheet");    //2차는 sheet생성
        XSSFRow row=null;                               //엑셀의 행
        int i=0,j=0;

        row=sheet.createRow(i++);
        row.createCell(j++).setCellValue("관서명");
        row.createCell(j++).setCellValue("1희망지");
        row.createCell(j++).setCellValue("2희망지");
        row.createCell(j++).setCellValue("3희망지");
        row.createCell(j++).setCellValue("4희망지");
        row.createCell(j++).setCellValue("5희망지");
        row.createCell(j++).setCellValue("6희망지");
        row.createCell(j++).setCellValue("7희망지");
        row.createCell(j++).setCellValue("8희망지");
        row.createCell(j++).setCellValue("9희망지");
        row.createCell(j++).setCellValue("10희망지");
        row.createCell(j++).setCellValue("11희망지");
        row.createCell(j++).setCellValue("12희망지");
        row.createCell(j++).setCellValue("13희망지");
        row.createCell(j++).setCellValue("14희망지");
        row.createCell(j++).setCellValue("15희망지");
        row.createCell(j++).setCellValue("16희망지");

        List<Map<String, Object>> rtnList = service.selectIn(paramVo);
        if(rtnList !=null &&rtnList.size() >0){
            for(Map<String, Object> oneMap : rtnList){
                j = 0;
                row=sheet.createRow(i++);
                row.createCell(j++).setCellValue((String)oneMap.get("name"));
                row.createCell(j++).setCellValue((String)oneMap.get("o1"));
                row.createCell(j++).setCellValue((String)oneMap.get("o2"));
                row.createCell(j++).setCellValue((String)oneMap.get("o3"));
                row.createCell(j++).setCellValue((String)oneMap.get("o4"));
                row.createCell(j++).setCellValue((String)oneMap.get("o5"));
                row.createCell(j++).setCellValue((String)oneMap.get("o6"));
                row.createCell(j++).setCellValue((String)oneMap.get("o7"));
                row.createCell(j++).setCellValue((String)oneMap.get("o8"));
                row.createCell(j++).setCellValue((String)oneMap.get("o9"));
                row.createCell(j++).setCellValue((String)oneMap.get("o10"));
                row.createCell(j++).setCellValue((String)oneMap.get("o11"));
                row.createCell(j++).setCellValue((String)oneMap.get("o12"));
                row.createCell(j++).setCellValue((String)oneMap.get("o13"));
                row.createCell(j++).setCellValue((String)oneMap.get("o14"));
                row.createCell(j++).setCellValue((String)oneMap.get("o15"));
                row.createCell(j++).setCellValue((String)oneMap.get("o16"));
            }
        }

        try {
            fileoutputstream = new FileOutputStream(fullPath);
            workbook.write(fileoutputstream);               //파일을 쓴다
            downloadFile = new File(fullPath);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileoutputstream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        CommonUtil.fileDownLoad(downloadFile, response);
    }

    @RequestMapping(value = "/inOutFileDown.do")
    public void  inOutFileDown(@ModelAttribute StatSearch paramVo, HttpServletResponse response) {
        FileOutputStream fileoutputstream = null;
        File downloadFile = null;
        String fullPath = CommonUtil.makeFileName(_FILE_DIR, "office_transfer_");

        XSSFWorkbook workbook=new XSSFWorkbook();       //1차로 workbook을 생성
        XSSFSheet sheet=workbook.createSheet("sheet");    //2차는 sheet생성
        XSSFRow row=null;                               //엑셀의 행
        int i=0,j=0;

        row=sheet.createRow(i++);
        row.createCell(j++).setCellValue("관서명");
        row.createCell(j++).setCellValue("정원");
        row.createCell(j++).setCellValue("현원");
        row.createCell(j++).setCellValue("전입-소계");
        row.createCell(j++).setCellValue("전입-소방정");
        row.createCell(j++).setCellValue("전입-소방령");
        row.createCell(j++).setCellValue("전입-소방경");
        row.createCell(j++).setCellValue("전입-소방위");
        row.createCell(j++).setCellValue("전입-소방장");
        row.createCell(j++).setCellValue("전입-소방교");
        row.createCell(j++).setCellValue("전입-소방사");
        row.createCell(j++).setCellValue("전출-소계");
        row.createCell(j++).setCellValue("전출-소방정");
        row.createCell(j++).setCellValue("전출-소방령");
        row.createCell(j++).setCellValue("전출-소방경");
        row.createCell(j++).setCellValue("전출-소방위");
        row.createCell(j++).setCellValue("전출-소방장");
        row.createCell(j++).setCellValue("전출-소방교");
        row.createCell(j++).setCellValue("전출-소방사");

        List<Map<String, Object>> rtnList = service.selectInOut(paramVo);
        if(rtnList !=null &&rtnList.size() >0){
            for(Map<String, Object> oneMap : rtnList){
                j = 0;
                row=sheet.createRow(i++);
                row.createCell(j++).setCellValue((String)oneMap.get("name"));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("nrmTotal")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("nowTotal")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("sumrnk")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("rnk001")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("rnk002")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("rnk003")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("rnk004")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("rnk005")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("rnk006")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("rnk007")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("sumoutrnk")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("outrnk001")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("outrnk002")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("outrnk003")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("outrnk004")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("outrnk005")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("outrnk006")));
                row.createCell(j++).setCellValue(Integer.toString((Integer)oneMap.get("outrnk007")));
            }
        }

        try {
            fileoutputstream = new FileOutputStream(fullPath);
            workbook.write(fileoutputstream);               //파일을 쓴다
            downloadFile = new File(fullPath);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileoutputstream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        CommonUtil.fileDownLoad(downloadFile, response);
    }

    @RequestMapping(value = "/avgFileDown.do")
    public void  avgFileDown(@ModelAttribute StatSearch paramVo, HttpServletResponse response) {
        FileOutputStream fileoutputstream = null;
        File downloadFile = null;
        String fullPath = CommonUtil.makeFileName(_FILE_DIR, "rank_score_");

        XSSFWorkbook workbook=new XSSFWorkbook();       //1차로 workbook을 생성
        XSSFSheet sheet=workbook.createSheet("sheet");    //2차는 sheet생성
        XSSFRow row=null;                               //엑셀의 행
        int i=0,j=0;

        row=sheet.createRow(i++);
        row.createCell(j++).setCellValue("계급명");
        row.createCell(j++).setCellValue("인사차수");
        row.createCell(j++).setCellValue("평균지수점수");

        List<Map<String, Object>> rtnList = service.selectAvg(paramVo);
        if(rtnList !=null &&rtnList.size() >0){
            for(Map<String, Object> oneMap : rtnList){
                j = 0;
                row=sheet.createRow(i++);
                row.createCell(j++).setCellValue((String)oneMap.get("codename"));
                row.createCell(j++).setCellValue((String)oneMap.get("degree"));
                row.createCell(j++).setCellValue((String)oneMap.get("disstsflevel"));
            }
        }

        try {
            fileoutputstream = new FileOutputStream(fullPath);
            workbook.write(fileoutputstream);               //파일을 쓴다
            downloadFile = new File(fullPath);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileoutputstream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        CommonUtil.fileDownLoad(downloadFile, response);
    }
}

