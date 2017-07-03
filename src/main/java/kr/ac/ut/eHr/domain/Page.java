package kr.ac.ut.eHr.domain;


public class Page {
    //set pasing parameter
    private int pageIndex = 1;          // 현재페이지
    private int firstIndex = 1;         // firstIndex
    private int lastIndex = 1;          // lastIndex
    private int recordCountPerPage = 10; // recordCountPerPage

   public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    /*
    private int pageUnit = 10;          // 페이지갯수
    private int pageSize = 10;          // 페이지사이즈

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }*/

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }
}
