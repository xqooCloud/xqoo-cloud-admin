package com.xqoo.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by LiTinggui on 2019/6/18.
 *
 * @author LiTinggui
 * @date 2019/6/18
 */
@ApiModel
public class PageResponseBean<T> implements Serializable {

    private static final long serialVersionUID = 6887389993060457824L;
    /**
     * 返回数据内容
     */
    @ApiModelProperty(value = "返回数据内容")
    private List<T> content;
    /**
     * 总条目数
     */
    @ApiModelProperty(value = "总条目数")
    private long totalElements;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private int totalPages;
    /**
     * 是否是最后一页
     */
    @ApiModelProperty(value = "是否是最后一页")
    private boolean last;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int number;
    /**
     * 当前页的条目数
     */
    @ApiModelProperty(value = "实际返回条目数")
    private int size;
    @ApiModelProperty(value = "预计返回条数")
    private int numberOfElements;
    /**
     * 是否是第一页
     */
    @ApiModelProperty(value = "是否是第一页")
    private boolean first;

    public PageResponseBean() {
    }

    public PageResponseBean(PageRequestBean bean, Integer count) {
        this.totalElements = count;
        this.totalPages = (int) Math.ceil(count.doubleValue() / (bean.getPageSize()==0?count.doubleValue():bean.getPageSize()));
        this.last = totalPages == bean.getPage();
        this.first = bean.getPage() == 1;
        this.number = bean.getPage();
        this.numberOfElements = bean.getPageSize();
    }

    public PageResponseBean(Integer page, Integer pageSize, Integer count) {
        if(count == null){
            count = 0;
        }
        this.totalElements = count;
        this.totalPages = (int) Math.ceil(count.doubleValue() / (pageSize==0?count.doubleValue():pageSize));
        this.last = totalPages == page;
        this.first = page == 1;
        this.number = page;
        this.numberOfElements = pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResponseBean<?> that = (PageResponseBean<?>) o;
        return totalElements == that.totalElements &&
                totalPages == that.totalPages &&
                last == that.last &&
                number == that.number &&
                size == that.size &&
                numberOfElements == that.numberOfElements &&
                first == that.first &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, totalElements, totalPages, last, number, size, numberOfElements, first);
    }
}
