package kr.ac.ut.eHr.domain;

public class Absence {
	private String absenceId;
	private String userId;
    private String rankCodeId;
    private String startDt;
    private String endDt;

    public String getAbsenceId() {
        return absenceId;
    }
    public void setAbsenceId(String absenceId) {
        this.absenceId = absenceId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRankCodeId() {
        return rankCodeId;
    }
    public void setRankCodeId(String rankCodeId) {
        this.rankCodeId = rankCodeId;
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