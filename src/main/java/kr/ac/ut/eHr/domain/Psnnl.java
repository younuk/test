package kr.ac.ut.eHr.domain;

import java.util.List;

public class Psnnl {
    private String psnnlId;
    private String psnnlBatchId;
    private String userId;
    private String startDt;
    private String endDt;
    private String orgnzId;
    private String rankCodeId;
    private String orgnzRank;
    private String disstsfLevel;
    private String stayLevel;
    private String psnnlResultDiv;

    private String orgnzName;
    private String divCodeName;
    private String statCodeName;
    private String prmtCodeName;
    private String targetCodeName;
    private String tiePriorityYn;
    private String name; // username

    private String stayYn;
    private List<ApplyOrgnz> hopeOrgnz;
    private String statCodeId;
    private String stay5YearStartDt;


    public String getPsnnlResultDiv() {
        return psnnlResultDiv;
    }
    public void setPsnnlResultDiv(String psnnlResultDiv) {
        this.psnnlResultDiv = psnnlResultDiv;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStay5YearStartDt() {
        return stay5YearStartDt;
    }
    public void setStay5YearStartDt(String stay5YearStartDt) {
        this.stay5YearStartDt = stay5YearStartDt;
    }
    public String getRankCodeId() {
        return rankCodeId;
    }
    public void setRankCodeId(String rankCodeId) {
        this.rankCodeId = rankCodeId;
    }
    public String getTiePriorityYn() {
        return tiePriorityYn;
    }
    public void setTiePriorityYn(String tiePriorityYn) {
        this.tiePriorityYn = tiePriorityYn;
    }
    public String getStartDt() {
        return startDt;
    }
    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }
    public String getEndDt() {
        return endDt;
    }
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }
    public String getStatCodeId() {
        return statCodeId;
    }
    public void setStatCodeId(String statCodeId) {
        this.statCodeId = statCodeId;
    }
    public String getStayYn() {
        return stayYn;
    }
    public void setStayYn(String stayYn) {
        this.stayYn = stayYn;
    }

    public List<ApplyOrgnz> getHopeOrgnz() {
        return hopeOrgnz;
    }
    public void setHopeOrgnz(List<ApplyOrgnz> hopeOrgnz) {
        this.hopeOrgnz = hopeOrgnz;
    }
    public String getTargetCodeName() {
        return targetCodeName;
    }
    public void setTargetCodeName(String targetCodeName) {
        this.targetCodeName = targetCodeName;
    }
    public String getPrmtCodeName() {
        return prmtCodeName;
    }
    public void setPrmtCodeName(String prmtCodeName) {
        this.prmtCodeName = prmtCodeName;
    }
    public String getDivCodeName() {
        return divCodeName;
    }
    public void setDivCodeName(String divCodeName) {
        this.divCodeName = divCodeName;
    }
    public String getStatCodeName() {
        return statCodeName;
    }
    public void setStatCodeName(String statCodeName) {
        this.statCodeName = statCodeName;
    }
    public String getOrgnzName() {
        return orgnzName;
    }
    public void setOrgnzName(String orgnzName) {
        this.orgnzName = orgnzName;
    }
    public String getPsnnlBatchId() {
        return psnnlBatchId;
    }
    public String getPsnnlId() {
        return psnnlId;
    }
    public void setPsnnlId(String psnnlId) {
        this.psnnlId = psnnlId;
    }
    public void setPsnnlBatchId(String psnnlBatchId) {
        this.psnnlBatchId = psnnlBatchId;
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
    public String getOrgnzRank() {
        return orgnzRank;
    }
    public void setOrgnzRank(String orgnzRank) {
        this.orgnzRank = orgnzRank;
    }
    public String getDisstsfLevel() {
        return disstsfLevel;
    }
    public void setDisstsfLevel(String disstsfLevel) {
        this.disstsfLevel = disstsfLevel;
    }

    public String getStayLevel() {
        return stayLevel;
    }
    public void setStayLevel(String stayLevel) {
        this.stayLevel = stayLevel;
    }
}


