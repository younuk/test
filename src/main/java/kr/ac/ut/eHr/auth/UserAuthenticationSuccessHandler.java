package kr.ac.ut.eHr.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import kr.ac.ut.eHr.common.CommonUtil;

public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private String userid;
	private String userpw;
	private String defaultUrl;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public UserAuthenticationSuccessHandler() {
        this.userid = "userid";
        this.userpw = "userpw";
	}

	/**
	 * login 성공처리
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        this.clearAuthenticationAttributes(request);

        LoginDetail loginDetail = CommonUtil.getLoginDetail();
        String auth = loginDetail.getUserAuth();

        this.setDefaultUrl("/");


        String rtnMsg = "{\"response\":{\"error\" : false, \"url\" : \""+this.defaultUrl+"\"";

        if(loginDetail.getPwChgYn().equals("N")){
            rtnMsg += ", \"pwChgYn\" : \""+loginDetail.getPwChgYn()+"\"";

            SecurityContextHolder.clearContext();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }

        rtnMsg += "}}";
        System.out.println(rtnMsg);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(rtnMsg);
        out.flush();
        out.close();
	}

	private void clearAuthenticationAttributes(HttpServletRequest request){
	    HttpSession session = request.getSession();
	    if(session == null) return;
	    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
