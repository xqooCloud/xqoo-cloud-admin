package com.xqoo.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by LiTinggui on 2019/6/18.
 *
 * @author LiTinggui
 * @date 2019/6/18
 */
@ApiModel
public class PageRequestBean implements Serializable {

    private static final long serialVersionUID = -5460916727522078000L;

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;

    /**
     * 当前页，默认0
     */
    @ApiModelProperty(value="当前页，默认1")
    private Integer page = DEFAULT_PAGE;

    /**
     * 每页多少条，默认10条
     */
    @ApiModelProperty(value="每页多少条，默认10条")
    private Integer pageSize = DEFAULT_SIZE;

    @ApiModelProperty(value="排序方式（desc or asc）",hidden = true)
    private String qdesc;

    @ApiModelProperty(value="不用传",hidden = true)
    private Integer currentSize;

    public Integer getPage() {
        if(page==null){
            return DEFAULT_PAGE;
        }else if(page.equals(0) || page==0){
            return DEFAULT_PAGE;
        }
        return page;
    }

    public void setPage(Integer page) {
        if(page==null){
            page = DEFAULT_PAGE;
        }else if(page.equals(0) || page==0){
            page = DEFAULT_PAGE;
        }
        this.page = page;
    }

    public Integer getPageSize() {
        if(pageSize==null){
            return DEFAULT_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize==null){
            pageSize = DEFAULT_SIZE;
        }
        this.pageSize = pageSize;
    }

    public String getQdesc() {
        return qdesc;
    }

    public void setQdesc(String qdesc) {
        this.qdesc = qdesc;
    }

    public Integer getCurrentSize() {
        return (page-1)*pageSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    public static int getDefaultPage() {
        return DEFAULT_PAGE;
    }

    public static int getDefaultSize() {
        return DEFAULT_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageRequestBean that = (PageRequestBean) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(pageSize, that.pageSize) &&
                Objects.equals(qdesc, that.qdesc) &&
                Objects.equals(currentSize, that.currentSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, qdesc, currentSize);
    }

    @Override
    public String toString() {
        return page + pageSize  + qdesc + currentSize +"";
    }
}
