package kr.ac.ut.eHr.domain;

public class ApplyOrgnz {
	private String level;
	private String orgnzId;
	private String passScore;
    private String orgnzName;
    private String stayLevl;
    private int months;

	public int getMonths() {
        return months;
    }
    public void setMonths(int months) {
        this.months = months;
    }
    public String getStayLevl() {
        return stayLevl;
    }
    public void setStayLevl(String stayLevl) {
        this.stayLevl = stayLevl;
    }
    public String getPassScore() {
        return passScore;
    }
    public void setPassScore(String passScore) {
        this.passScore = passScore;
    }

    public String getOrgnzName() {
        return orgnzName;
    }
    public void setOrgnzName(String orgnzName) {
        this.orgnzName = orgnzName;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getOrgnzId() {
        return orgnzId;
    }
    public void setOrgnzId(String orgnzId) {
        this.orgnzId = orgnzId;
    }
}