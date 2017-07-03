package kr.ac.ut.eHr.domain;

public class User{
    private String userId;
    private String name;
    private String orgnzId;
    private String rankCodeId;
    private String birthDt;
    private String initialAppmnt;
    private String rankAppmnt;
    private String orgnzAppmnt;
    private String ppmntBatch;
    private String specialDutyCodeId;
    private String nextPsnnlDt;
    private String stay5YearStartDt;
    private String stay5YearDone;

    private String loginId;
    private String authCodeId;
    private String orgnzName;
    private String rankCodeName;
    private String specialDutyCodeName;
    private String targetCodeId;
    private String prmtCodeId;
    private String psnnlBatchId;
    private String tiePriorityYn;
/*
    private List<Absence> absences;
    private List<DutyPeriod> dutyPeriods;

    public List<DutyPeriod> getDutyPeriods() {
        return dutyPeriods;
    }
    public void setDutyPeriods(List<DutyPeriod> dutyPeriods) {
        this.dutyPeriods = dutyPeriods;
    }
    public List<Absence> getAbsences() {
        return absences;
    }
    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }
    */


    public String getNextPsnnlDt() {
        return nextPsnnlDt;
    }

    public String getStay5YearDone() {
        return stay5YearDone;
    }

    public void setStay5YearDone(String stay5YearDone) {
        this.stay5YearDone = stay5YearDone;
    }

    public void setNextPsnnlDt(String nextPsnnlDt) {
        this.nextPsnnlDt = nextPsnnlDt;
    }

    public String getTargetCodeId() {
        return targetCodeId;
    }

    public String getTiePriorityYn() {
        return tiePriorityYn;
    }
    public void setTiePriorityYn(String tiePriorityYn) {
        this.tiePriorityYn = tiePriorityYn;
    }
    public String getPsnnlBatchId() {
        return psnnlBatchId;
    }
    public void setPsnnlBatchId(String psnnlBatchId) {
        this.psnnlBatchId = psnnlBatchId;
    }
    public String getStay5YearStartDt() {
        return stay5YearStartDt;
    }
    public void setStay5YearStartDt(String stay5YearStartDt) {
        this.stay5YearStartDt = stay5YearStartDt;
    }
    public void setTargetCodeId(String targetCodeId) {
        this.targetCodeId = targetCodeId;
    }
    public String getPrmtCodeId() {
        return prmtCodeId;
    }
    public void setPrmtCodeId(String prmtCodeId) {
        this.prmtCodeId = prmtCodeId;
    }
    public String getPpmntBatch() {
        return ppmntBatch;
    }
    public void setPpmntBatch(String ppmntBatch) {
        this.ppmntBatch = ppmntBatch;
    }
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getRankCodeName() {
        return rankCodeName;
    }
    public void setRankCodeName(String rankCodeName) {
        this.rankCodeName = rankCodeName;
    }
    public String getSpecialDutyCodeName() {
        return specialDutyCodeName;
    }
    public void setSpecialDutyCodeName(String specialDutyCodeName) {
        this.specialDutyCodeName = specialDutyCodeName;
    }
    public String getOrgnzName() {
        return orgnzName;
    }
    public void setOrgnzName(String orgnzName) {
        this.orgnzName = orgnzName;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOrgnzId() {
        return orgnzId;
    }
    public void setOrgnzId(String orgnzId) {
        this.orgnzId = orgnzId;
    }
    public String getRankCodeId() {
        return rankCodeId;
    }
    public void setRankCodeId(String rankCodeId) {
        this.rankCodeId = rankCodeId;
    }
    public String getBirthDt() {
        return birthDt;
    }
    public void setBirthDt(String birthDt) {
        this.birthDt = birthDt;
    }
    public String getInitialAppmnt() {
        return initialAppmnt;
    }
    public void setInitialAppmnt(String initialAppmnt) {
        this.initialAppmnt = initialAppmnt;
    }
    public String getRankAppmnt() {
        return rankAppmnt;
    }
    public void setRankAppmnt(String rankAppmnt) {
        this.rankAppmnt = rankAppmnt;
    }
    public String getOrgnzAppmnt() {
        return orgnzAppmnt;
    }
    public void setOrgnzAppmnt(String orgnzAppmnt) {
        this.orgnzAppmnt = orgnzAppmnt;
    }
    public String getSpecialDutyCodeId() {
        return specialDutyCodeId;
    }
    public void setSpecialDutyCodeId(String specialDutyCodeId) {
        this.specialDutyCodeId = specialDutyCodeId;
    }
    public String getAuthCodeId() {
        return authCodeId;
    }
    public void setAuthCodeId(String authCodeId) {
        this.authCodeId = authCodeId;
    }
}


