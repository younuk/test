package kr.ac.ut.eHr.domain;

public class Code {
	private String codeId;
	private String cateCodeId;
    private String codeName;
    private String sortNo;
    private String userYn;

    public String getCodeId() {
        return codeId;
    }
    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }
    public String getCateCodeId() {
        return cateCodeId;
    }
    public void setCateCodeId(String cateCodeId) {
        this.cateCodeId = cateCodeId;
    }
    public String getCodeName() {
        return codeName;
    }
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
    public String getSortNo() {
        return sortNo;
    }
    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }
    public String getUserYn() {
        return userYn;
    }
    public void setUserYn(String userYn) {
        this.userYn = userYn;
    }
}