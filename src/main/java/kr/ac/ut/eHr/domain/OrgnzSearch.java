package kr.ac.ut.eHr.domain;

public class OrgnzSearch extends Page{
    private String srchOrgnzId;
    private String srchNrmCntStart;
    private String srchVccCntStart;
    private String srchNowCntStart;
    private String srchNrmCntEnd;
    private String srchVccCntEnd;
    private String srchNowCntEnd;

    private String orgnzId;

    public String getSrchOrgnzId() {
        return srchOrgnzId;
    }

    public void setSrchOrgnzId(String srchOrgnzId) {
        this.srchOrgnzId = srchOrgnzId;
    }

    public String getSrchNrmCntStart() {
        return srchNrmCntStart;
    }

    public void setSrchNrmCntStart(String srchNrmCntStart) {
        this.srchNrmCntStart = srchNrmCntStart;
    }

    public String getSrchVccCntStart() {
        return srchVccCntStart;
    }

    public void setSrchVccCntStart(String srchVccCntStart) {
        this.srchVccCntStart = srchVccCntStart;
    }

    public String getSrchNowCntStart() {
        return srchNowCntStart;
    }

    public void setSrchNowCntStart(String srchNowCntStart) {
        this.srchNowCntStart = srchNowCntStart;
    }

    public String getSrchNrmCntEnd() {
        return srchNrmCntEnd;
    }

    public void setSrchNrmCntEnd(String srchNrmCntEnd) {
        this.srchNrmCntEnd = srchNrmCntEnd;
    }

    public String getSrchVccCntEnd() {
        return srchVccCntEnd;
    }

    public void setSrchVccCntEnd(String srchVccCntEnd) {
        this.srchVccCntEnd = srchVccCntEnd;
    }

    public String getSrchNowCntEnd() {
        return srchNowCntEnd;
    }

    public void setSrchNowCntEnd(String srchNowCntEnd) {
        this.srchNowCntEnd = srchNowCntEnd;
    }

    public String getOrgnzId() {
        return orgnzId;
    }

    public void setOrgnzId(String orgnzId) {
        this.orgnzId = orgnzId;
    }
}