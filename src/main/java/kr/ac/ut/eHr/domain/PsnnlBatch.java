package kr.ac.ut.eHr.domain;

public class PsnnlBatch extends Page{
    private String psnnlBatchId;
    private String degree;
    private String divCodeId;
    private String inStartDt;
    private String inEndDt;
    private String dt;
    private String statCodeId;
    private String runYn;

    private String divCodeName;
    private String statCodeName;
    private String targetNum;
    private String psnnlNum;

    private String srchStartDt;
    private String srchEndDt;
    private String srchState;

    public String getPsnnlNum() {
        return psnnlNum;
    }
    public void setPsnnlNum(String psnnlNum) {
        this.psnnlNum = psnnlNum;
    }
    public String getRunYn() {
        return runYn;
    }
    public void setRunYn(String runYn) {
        this.runYn = runYn;
    }
    public String getSrchState() {
        return srchState;
    }
    public void setSrchState(String srchState) {
        this.srchState = srchState;
    }
    public String getStatCodeName() {
        return statCodeName;
    }
    public void setStatCodeName(String statCodeName) {
        this.statCodeName = statCodeName;
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
    public String getDivCodeName() {
        return divCodeName;
    }
    public void setDivCodeName(String divCodeName) {
        this.divCodeName = divCodeName;
    }
    public String getPsnnlBatchId() {
        return psnnlBatchId;
    }
    public void setPsnnlBatchId(String psnnlBatchId) {
        this.psnnlBatchId = psnnlBatchId;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getDivCodeId() {
        return divCodeId;
    }
    public void setDivCodeId(String divCodeId) {
        this.divCodeId = divCodeId;
    }
    public String getInStartDt() {
        return inStartDt;
    }
    public void setInStartDt(String inStartDt) {
        this.inStartDt = inStartDt;
    }
    public String getInEndDt() {
        return inEndDt;
    }
    public void setInEndDt(String inEndDt) {
        this.inEndDt = inEndDt;
    }
    public String getDt() {
        return dt;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }
    public String getTargetNum() {
        return targetNum;
    }
    public void setTargetNum(String targetNum) {
        this.targetNum = targetNum;
    }
    public String getStatCodeId() {
        return statCodeId;
    }
    public void setStatCodeId(String statCodeId) {
        this.statCodeId = statCodeId;
    }
}


