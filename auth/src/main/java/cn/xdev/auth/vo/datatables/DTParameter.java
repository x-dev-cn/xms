package cn.xdev.auth.vo.datatables;

import cn.xdev.core.entity.BaseModel;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by felix on 2016-07-08-0008.
 */
public class DTParameter extends BaseModel {
    private int draw;
    private int start;
    private int length;
    private String search;
    private String regex;
    private String order_column;
    private String order_dir;

    public DTParameter(HttpServletRequest request) {
        this.draw = ServletRequestUtils.getIntParameter(request, "draw", 0);
        this.start = ServletRequestUtils.getIntParameter(request, "start", 0);
        this.length = ServletRequestUtils.getIntParameter(request, "length", 0);
        this.search = ServletRequestUtils.getStringParameter(request, "search[value]", "");
        String column_idx = ServletRequestUtils.getStringParameter(request, "order[0][column]", "");
        this.order_column = ServletRequestUtils.getStringParameter(request, "columns[" + column_idx + "][data]", "");
        this.order_dir = ServletRequestUtils.getStringParameter(request, "order[0][dir]", "");
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getOrder_column() {
        return order_column;
    }

    public void setOrder_column(String order_column) {
        this.order_column = order_column;
    }

    public String getOrder_dir() {
        return order_dir;
    }

    public void setOrder_dir(String order_dir) {
        this.order_dir = order_dir;
    }
}
