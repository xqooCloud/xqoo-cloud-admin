package com.xqoo.codegen.bo;

import com.xqoo.codegen.entity.TableColumnsEntity;
import com.xqoo.codegen.entity.TableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ApiModel("根据表字段生成service等类文件")
public class TableGeneratorBO implements Serializable {
    private static final long serialVersionUID = -1016910050511754865L;

    @ApiModelProperty("类名，可不填，则默认使用表名")
    private String className;

    @ApiModelProperty("字段列表")
    private List<TableColumnsEntity> columnDataArr;

    @ApiModelProperty("数据源id")
    private Integer dataSourceId;

    @NotBlank(message = "业务处理名称不能为空")
    @ApiModelProperty("处理业务路由名称")
    private String handleName;

    @NotBlank(message = "包名不能为空")
    @ApiModelProperty("包名")
    private String packageName;

    @NotBlank(message = "所选表名不能为空")
    @ApiModelProperty("所选表名")
    private String tableName;

    @ApiModelProperty("所选表实体内容")
    private TableEntity tableItem;

    @ApiModelProperty("所选模板")
    private List<String> templateNameArr;

    @ApiModelProperty("是否需要生成可下载压缩包，默认生成")
    private Boolean needDownload;

    @Override
    public String toString() {
        return "TableGeneratorBO{" +
                "className='" + className + '\'' +
                ", columnDataArr=" + columnDataArr +
                ", dataSourceId=" + dataSourceId +
                ", handleName='" + handleName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableItem=" + tableItem +
                ", templateNameArr=" + templateNameArr +
                ", needDownload=" + needDownload +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableGeneratorBO that = (TableGeneratorBO) o;
        return Objects.equals(className, that.className) &&
                Objects.equals(columnDataArr, that.columnDataArr) &&
                Objects.equals(dataSourceId, that.dataSourceId) &&
                Objects.equals(handleName, that.handleName) &&
                Objects.equals(packageName, that.packageName) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(tableItem, that.tableItem) &&
                Objects.equals(templateNameArr, that.templateNameArr) &&
                Objects.equals(needDownload, that.needDownload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, columnDataArr, dataSourceId, handleName, packageName, tableName, tableItem, templateNameArr, needDownload);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<TableColumnsEntity> getColumnDataArr() {
        return columnDataArr;
    }

    public void setColumnDataArr(List<TableColumnsEntity> columnDataArr) {
        this.columnDataArr = columnDataArr;
    }

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TableEntity getTableItem() {
        return tableItem;
    }

    public void setTableItem(TableEntity tableItem) {
        this.tableItem = tableItem;
    }

    public List<String> getTemplateNameArr() {
        return templateNameArr;
    }

    public void setTemplateNameArr(List<String> templateNameArr) {
        this.templateNameArr = templateNameArr;
    }

    public Boolean getNeedDownload() {
        return needDownload;
    }

    public void setNeedDownload(Boolean needDownload) {
        this.needDownload = needDownload;
    }
}
