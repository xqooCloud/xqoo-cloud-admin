package com.xqoo.authorization.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("管理控制台菜单表")
@TableName(value = "sys_console_menu")
public class SysConsoleMenuEntity extends Model<SysConsoleMenuEntity> {

    private static final long serialVersionUID = -6151494347558571881L;
    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("逻辑删除标记，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("是否默认菜单，0-否，1,-是，默认菜单不可删除")
    private Boolean defaultFlag;

    @ApiModelProperty("父级菜单，为0则是第一级菜单")
    private Integer parentId;

    @ApiModelProperty("排序序号，1-9999之间")
    private Integer sortNo;

    @ApiModelProperty("是否外部跳转，0-不是，1-是，外部跳转时下面除了path,name,icon,target，其余属性均无用")
    private Boolean outSideJump;

    @ApiModelProperty("菜单路径，若严格按照层级，则不显示菜单将会高亮上级菜单，外部连接直接填写域名")
    private String path;

    @ApiModelProperty("是否打开额外窗口，null或空-不打开，_blank-打开")
    @TableField(value = "target", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private String target;

    @ApiModelProperty("此项可填写前端国际化key值，如没有则直接显示此值,最多64字")
    private String name;

    @ApiModelProperty("组件指向，点击菜单跳转的页面，前端文件相对路径的字符串")
    @TableField(value = "component", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private String component;

    @ApiModelProperty("中文名字，仅用来做备注使用，最多24字")
    private String chineseName;

    @ApiModelProperty("图标，名称是前端固定枚举值")
    @TableField(value = "icon", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private String icon;

    @ApiModelProperty("跳转的路径，选取现有菜单的path值即可")
    @TableField(value = "redirect", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private String redirect;

    @ApiModelProperty("是否隐藏菜单，0-不隐藏，1-隐藏")
    private Boolean hideInMenu;

    @ApiModelProperty("高亮父级菜单，此菜单被隐藏时可用，跳转此页面时关联的菜单将高亮，值为jsonArr，选取现有菜单path值即可")
    private String parentKeys;

    @ApiModelProperty("是否展示菜单栏，0-不展示，1-展示，此项为0时打开对应菜单将隐藏整个菜单栏")
    private Boolean menuRender;

    @ApiModelProperty("是否展示菜单顶栏，0-不展示，1-展示，此项为0时打开对应菜单将隐藏菜单顶栏")
    private Boolean menuHeaderRender;

    @ApiModelProperty("固定菜单栏，不随页面上下滑动，0-不固定，1-固定，此项当menuRender为0时无效")
    private Boolean fixSiderbar;

    @ApiModelProperty("打开页面时菜单展示方式，固定值 side-正常，top-顶端，mix-混合，默认side，此项当menuRender为0时无效")
    private String layout;

    @ApiModelProperty("打开页面时菜单主题，固定值 dark-黑，light-亮色，realDark-暗黑，默认dark，此项当menuRender为0时无效")
    private String navTheme;

    @ApiModelProperty("是否展示顶栏，0-不展示，1-展示，此项为0时打开对应菜单将隐藏整个顶部导航栏")
    private Boolean headerRender;

    @ApiModelProperty("固定顶栏，0-不固定，1-固定，此项在headerRender为0时无效")
    private Boolean fixedHeader;

    @ApiModelProperty("顶栏主题，固定值 dark-黑，light-亮色，此项仅当布局为mix时有效")
    private String headerTheme;

    @ApiModelProperty("是否展示页脚，0-不展示，1-展示")
    private Boolean footerRender;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createBy;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty("最近修改人")
    private String updateBy;

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @ApiModelProperty("最近修改时间")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    @Override
    public String toString() {
        return "SysConsoleMenuEntity{" +
                "id=" + id +
                ", delFlag=" + delFlag +
                ", defaultFlag=" + defaultFlag +
                ", parentId=" + parentId +
                ", sortNo=" + sortNo +
                ", outSideJump=" + outSideJump +
                ", path='" + path + '\'' +
                ", target='" + target + '\'' +
                ", name='" + name + '\'' +
                ", component='" + component + '\'' +
                ", chineseName='" + chineseName + '\'' +
                ", icon='" + icon + '\'' +
                ", redirect='" + redirect + '\'' +
                ", hideInMenu=" + hideInMenu +
                ", parentKeys='" + parentKeys + '\'' +
                ", menuRender=" + menuRender +
                ", menuHeaderRender=" + menuHeaderRender +
                ", fixSiderbar=" + fixSiderbar +
                ", layout='" + layout + '\'' +
                ", navTheme='" + navTheme + '\'' +
                ", headerRender=" + headerRender +
                ", fixedHeader=" + fixedHeader +
                ", headerTheme='" + headerTheme + '\'' +
                ", footerRender=" + footerRender +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysConsoleMenuEntity that = (SysConsoleMenuEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(defaultFlag, that.defaultFlag) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(sortNo, that.sortNo) &&
                Objects.equals(outSideJump, that.outSideJump) &&
                Objects.equals(path, that.path) &&
                Objects.equals(target, that.target) &&
                Objects.equals(name, that.name) &&
                Objects.equals(component, that.component) &&
                Objects.equals(chineseName, that.chineseName) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(redirect, that.redirect) &&
                Objects.equals(hideInMenu, that.hideInMenu) &&
                Objects.equals(parentKeys, that.parentKeys) &&
                Objects.equals(menuRender, that.menuRender) &&
                Objects.equals(menuHeaderRender, that.menuHeaderRender) &&
                Objects.equals(fixSiderbar, that.fixSiderbar) &&
                Objects.equals(layout, that.layout) &&
                Objects.equals(navTheme, that.navTheme) &&
                Objects.equals(headerRender, that.headerRender) &&
                Objects.equals(fixedHeader, that.fixedHeader) &&
                Objects.equals(headerTheme, that.headerTheme) &&
                Objects.equals(footerRender, that.footerRender) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, delFlag, defaultFlag, parentId, sortNo, outSideJump, path, target, name, component, chineseName, icon, redirect, hideInMenu, parentKeys, menuRender, menuHeaderRender, fixSiderbar, layout, navTheme, headerRender, fixedHeader, headerTheme, footerRender, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Boolean getOutSideJump() {
        return outSideJump;
    }

    public void setOutSideJump(Boolean outSideJump) {
        this.outSideJump = outSideJump;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Boolean getHideInMenu() {
        return hideInMenu;
    }

    public void setHideInMenu(Boolean hideInMenu) {
        this.hideInMenu = hideInMenu;
    }

    public String getParentKeys() {
        return parentKeys;
    }

    public void setParentKeys(String parentKeys) {
        this.parentKeys = parentKeys;
    }

    public Boolean getMenuRender() {
        return menuRender;
    }

    public void setMenuRender(Boolean menuRender) {
        this.menuRender = menuRender;
    }

    public Boolean getMenuHeaderRender() {
        return menuHeaderRender;
    }

    public void setMenuHeaderRender(Boolean menuHeaderRender) {
        this.menuHeaderRender = menuHeaderRender;
    }

    public Boolean getFixSiderbar() {
        return fixSiderbar;
    }

    public void setFixSiderbar(Boolean fixSiderbar) {
        this.fixSiderbar = fixSiderbar;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getNavTheme() {
        return navTheme;
    }

    public void setNavTheme(String navTheme) {
        this.navTheme = navTheme;
    }

    public Boolean getHeaderRender() {
        return headerRender;
    }

    public void setHeaderRender(Boolean headerRender) {
        this.headerRender = headerRender;
    }

    public Boolean getFixedHeader() {
        return fixedHeader;
    }

    public void setFixedHeader(Boolean fixedHeader) {
        this.fixedHeader = fixedHeader;
    }

    public String getHeaderTheme() {
        return headerTheme;
    }

    public void setHeaderTheme(String headerTheme) {
        this.headerTheme = headerTheme;
    }

    public Boolean getFooterRender() {
        return footerRender;
    }

    public void setFooterRender(Boolean footerRender) {
        this.footerRender = footerRender;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarkTips() {
        return remarkTips;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
