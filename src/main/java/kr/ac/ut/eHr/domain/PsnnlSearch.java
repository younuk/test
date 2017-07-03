package kr.ac.ut.eHr.domain;

public class PsnnlSearch extends Page{
    private String userId;
    private String psnnlId;
    private String name;
    private String divCodeId;
    private String srchStartDt;
    private String srchEndDt;
    private String srchOrgnzId;
    private String loginId;

    public String getPsnnlId() {
        return psnnlId;
    }
    public void setPsnnlId(String psnnlId) {
        this.psnnlId = psnnlId;
    }
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDivCodeId() {
        return divCodeId;
    }
    public void setDivCodeId(String divCodeId) {
        this.divCodeId = divCodeId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getSrchStartDt() {
        return srchStartDt;
    }
    public void setSrchStartDt(String srchStartDt) {
        this.srchStartDt = srchStartDt;
    }
    public String getSrchEndDt() {
        return srchEndDt;
    }
    public void setSrchEndDt(String srchEndDt) {
        this.srchEndDt = srchEndDt;
    }
    public String getSrchOrgnzId() {
        return srchOrgnzId;
    }
    public void setSrchOrgnzId(String srchOrgnzId) {
        this.srchOrgnzId = srchOrgnzId;
    }
}