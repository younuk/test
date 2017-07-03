package kr.ac.ut.eHr.domain;

public class DutyPeriod {
	private String dutyPeriodId;
	private String userId;
    private String startDt;
    private String endDt;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDutyPeriodId() {
        return dutyPeriodId;
    }
    public void setDutyPeriodId(String dutyPeriodId) {
        this.dutyPeriodId = dutyPeriodId;
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
}