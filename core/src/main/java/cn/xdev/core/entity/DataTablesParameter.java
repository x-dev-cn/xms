package cn.xdev.core.entity;

import java.io.Serializable;

/**
 * Created by felix on 2015-12-11-0011.
 */
public class DataTablesParameter implements Serializable {

    private String callback;
    private Integer iColumns;
    private String sColumns;
    private Integer iDisplayStart;
    private Integer iDisplayLength;
    private String sEcho;
    private String sSearch;
    private Integer iSortCol_0;
    private String sSortDir_0;
    private Boolean bRegex;
    private Integer iSortingCols;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Integer getiColumns() {
        return iColumns;
    }

    public void setiColumns(Integer iColumns) {
        this.iColumns = iColumns;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    public Integer getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(Integer iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public Integer getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(Integer iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getsSearch() {
        return sSearch;
    }

    public void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }

    public Integer getiSortCol_0() {
        return iSortCol_0;
    }

    public void setiSortCol_0(Integer iSortCol_0) {
        this.iSortCol_0 = iSortCol_0;
    }

    public String getsSortDir_0() {
        return sSortDir_0;
    }

    public void setsSortDir_0(String sSortDir_0) {
        this.sSortDir_0 = sSortDir_0;
    }

    public Boolean getbRegex() {
        return bRegex;
    }

    public void setbRegex(Boolean bRegex) {
        this.bRegex = bRegex;
    }

    public Integer getiSortingCols() {
        return iSortingCols;
    }

    public void setiSortingCols(Integer iSortingCols) {
        this.iSortingCols = iSortingCols;
    }
}
