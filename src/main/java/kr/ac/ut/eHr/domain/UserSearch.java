package kr.ac.ut.eHr.domain;

public class UserSearch extends Page{
    private String orgnzId;
    private String name;
    private String loginId;
    private String targetYn;
    private String rankCodeId;
    private String specialDutyCodeId;
    private String srchStartDt;
    private String srchEndDt;

    private String userId;

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
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getOrgnzId() {
        return orgnzId;
    }
    public void setOrgnzId(String orgnzId) {
        this.orgnzId = orgnzId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getTargetYn() {
        return targetYn;
    }
    public void setTargetYn(String targetYn) {
        this.targetYn = targetYn;
    }
    public String getRankCodeId() {
        return rankCodeId;
    }
    public void setRankCodeId(String rankCodeId) {
        this.rankCodeId = rankCodeId;
    }
    public String getSpecialDutyCodeId() {
        return specialDutyCodeId;
    }
    public void setSpecialDutyCodeId(String specialDutyCodeId) {
        this.specialDutyCodeId = specialDutyCodeId;
    }
}


