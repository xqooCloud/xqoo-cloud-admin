package com.xqoo.authorization.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ResourceMenuVO implements Serializable {
    private static final long serialVersionUID = -3222076626114676411L;


    private Integer key;

    private String title;

    private Integer value;

    @ApiModelProperty("是否默认菜单，0-否，1,-是，默认菜单不可删除")
    private Boolean defaultFlag;

    @ApiModelProperty("菜单路径，若严格按照层级，则不显示菜单将会高亮上级菜单，外部连接直接填写域名")
    private String path;

    @ApiModelProperty("是否打开额外窗口，null或空-不打开，_blank-打开")
    private String target;

    @ApiModelProperty("此项可填写前端国际化key值，如没有则直接显示此值,最多64字")
    private String name;

    @ApiModelProperty("组件指向，点击菜单跳转的页面，前端文件相对路径的字符串")
    private String component;

    @ApiModelProperty("图标，名称是前端固定枚举值")
    private String icon;

    @ApiModelProperty("跳转的路径，选取现有菜单的path值即可")
    private String redirect;

    @ApiModelProperty("是否隐藏菜单，0-不隐藏，1-隐藏")
    private Boolean hideInMenu;

    @ApiModelProperty("高亮父级菜单，此菜单被隐藏时可用，跳转此页面时关联的菜单将高亮，值为jsonArr，选取现有菜单path值即可")
    private List<String> parentKeys;

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

    @ApiModelProperty("子路由")
    private List<ResourceMenuVO> children;

    @Override
    public String toString() {
        return "ResourceMenuVO{" +
                "key=" + key +
                ", title='" + title + '\'' +
                ", value=" + value +
                ", defaultFlag=" + defaultFlag +
                ", path='" + path + '\'' +
                ", target='" + target + '\'' +
                ", name='" + name + '\'' +
                ", component='" + component + '\'' +
                ", icon='" + icon + '\'' +
                ", redirect='" + redirect + '\'' +
                ", hideInMenu=" + hideInMenu +
                ", parentKeys=" + parentKeys +
                ", menuRender=" + menuRender +
                ", menuHeaderRender=" + menuHeaderRender +
                ", fixSiderbar=" + fixSiderbar +
                ", layout='" + layout + '\'' +
                ", navTheme='" + navTheme + '\'' +
                ", headerRender=" + headerRender +
                ", fixedHeader=" + fixedHeader +
                ", headerTheme='" + headerTheme + '\'' +
                ", footerRender=" + footerRender +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceMenuVO that = (ResourceMenuVO) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(title, that.title) &&
                Objects.equals(value, that.value) &&
                Objects.equals(defaultFlag, that.defaultFlag) &&
                Objects.equals(path, that.path) &&
                Objects.equals(target, that.target) &&
                Objects.equals(name, that.name) &&
                Objects.equals(component, that.component) &&
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
                Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, title, value, defaultFlag, path, target, name, component, icon, redirect, hideInMenu, parentKeys, menuRender, menuHeaderRender, fixSiderbar, layout, navTheme, headerRender, fixedHeader, headerTheme, footerRender, children);
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
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

    public List<String> getParentKeys() {
        return parentKeys;
    }

    public void setParentKeys(List<String> parentKeys) {
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

    public List<ResourceMenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceMenuVO> children) {
        this.children = children;
    }
}
