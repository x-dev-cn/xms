package cn.xdev.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by felix on 2015-12-11-0011.
 */
public class DataTablesResult implements Serializable {

    private String sEcho;
    private Integer iTotalRecords;
    private Integer iTotalDisplayRecords;
    private List<?> data;

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
