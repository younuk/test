package kr.ac.ut.eHr.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.ac.ut.eHr.common.StringUtil;

public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationFailureHandler.class);

    private String userid;
    private String userpw;
    private String loginredirectname;
    private String exceptionmsgname;
    private String defaultFailurUrl;


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

    public String getLoginredirectname() {
        return loginredirectname;
    }

    public void setLoginredirectname(String loginredirectname) {
        this.loginredirectname = loginredirectname;
    }

    public String getExceptionmsgname() {
        return exceptionmsgname;
    }

    public void setExceptionmsgname(String exceptionmsgname) {
        this.exceptionmsgname = exceptionmsgname;
    }

    public String getDefaultFailurUrl() {
        return defaultFailurUrl;
    }

    public void setDefaultFailurUrl(String defaultFailurUrl) {
        this.defaultFailurUrl = defaultFailurUrl;
    }

    public UserAuthenticationFailureHandler() {
        this.userid = "userid";
        this.userpw = "userpw";
        this.loginredirectname = "lginRedirect";
        this.exceptionmsgname = "securityexceptionmsg";
        this.defaultFailurUrl = "/login.do";
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.error("!!!!!!!!!!!onAuthenticationFailure!!!!!!!!!!!!!! "+exception.getMessage());
        logger.error("!!!!!!!!!!!onAuthenticationFailure!!!!!!!!!!!!!! "+exception.getClass());
        logger.error("!!!!!!!!!!!onAuthenticationFailure!!!!!!!!!!!!!! "+StringUtil.encrypt("1111"));


        if(exception instanceof BadCredentialsException){
            String userid = request.getParameter(this.userid);
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("userId", userid);

        }

        String exceptionMsg = exception.getMessage();
        if(exceptionMsg == null){
            exceptionMsg = "사용자 없음";
        }else if (exceptionMsg.equals("Bad credentials")){
            exceptionMsg = "비밀번호오류";

        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String rtnMsg = "{\"response\":{\"error\" : true, \"message\" : \""+exceptionMsg+"\"}}";

        PrintWriter out = response.getWriter();
        out.print(rtnMsg);
        out.flush();
        out.close();

/*
        request.setAttribute(this.userid, request.getParameter("userid"));
        request.setAttribute(this.loginredirectname, request.getParameter("loginredirect"));
        request.setAttribute(this.exceptionmsgname, exceptionMsg);
        request.getRequestDispatcher(this.defaultFailurUrl).forward(request, response);*/
    }
}