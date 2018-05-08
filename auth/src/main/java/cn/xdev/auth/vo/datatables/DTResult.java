package cn.xdev.auth.vo.datatables;


import cn.xdev.core.entity.BaseModel;

import java.util.List;

/**
 * Created by felix on 2016-07-08-0008.
 */
public class DTResult extends BaseModel {
    private int draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<?> data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
