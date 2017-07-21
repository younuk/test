package kr.ac.ut.eHr.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.ac.ut.eHr.auth.LoginDetail;
import kr.ac.ut.eHr.domain.Code;
import kr.ac.ut.eHr.domain.Orgnz;
import kr.ac.ut.eHr.domain.Page;

public class CommonUtil {

    public static LoginDetail getLoginDetail() {
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }else{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return (principal == null || !(principal instanceof LoginDetail))? null: (LoginDetail) principal;
        }
    }


    /** pageing setting */
	public static PaginationInfo setPageParam(Object paramVo) {


        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(((Page)paramVo).getPageIndex());
        paginationInfo.setRecordCountPerPage(10);
        paginationInfo.setPageSize(10);

        ((Page)paramVo).setFirstIndex(paginationInfo.getFirstRecordIndex());
        ((Page)paramVo).setLastIndex(paginationInfo.getLastRecordIndex());
        ((Page)paramVo).setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        return paginationInfo;
	}

    public static Map<String, String> setCodeCombo(List<?> paramL) {
        return setCodeCombo(paramL, true);
    }

    public static Map<String, String> setCodeCombo(List<?> paramL, boolean defaultYn) {
        Map<String, String> rkMap = new LinkedHashMap<String, String>();
        if(defaultYn)
            rkMap.put("", "선택");

        for(Object obj: paramL){
            if(obj instanceof Code)
                rkMap.put(((Code)obj).getCodeId(), ((Code)obj).getCodeName());
            else if(obj instanceof Orgnz)
                rkMap.put(((Orgnz)obj).getOrgnzId(), ((Orgnz)obj).getName());
        }

        System.out.println(rkMap);

        return rkMap;
    }

    public static void fileDownLoad(File downloadFile, HttpServletResponse response) {
        OutputStream out = null;
        FileInputStream fis = null;

        try {
            response.setContentLength((int)downloadFile.length());
            String fileName;

            fileName = java.net.URLEncoder.encode(downloadFile.getName(), "UTF-8");

            response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            out = response.getOutputStream();

            fis = new FileInputStream(downloadFile);
            FileCopyUtils.copy(fis, out);

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) { try { fis.close(); } catch (Exception e2) {}}
        }
    }

    public static String makeFileName(String paramDir, String param){
        StringBuffer sb = new StringBuffer();
        sb.append(paramDir)
          .append(param)
          .append(DateUtil.getDate("yyyyMMddHHmmss"))
          .append(".xlsx");

        return sb.toString();
    }

    public static String makeRtnJson(int param) throws IOException{
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("result", (param > 0)? "success": "fail");

        JsonUtil jsonUtil = new JsonUtil(info);
        return jsonUtil.toJson();
    }

}
