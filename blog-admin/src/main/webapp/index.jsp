<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="/static/layui/css/layui.css"/>
    <script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">JAVA-0000</div>
        <ul class="layui-nav layui-layout-left"></ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <%-- 				        <img src="<%=path%>/img/fei.png" --%>
                    <!-- 				        class="layui-nav-img"/> -->
                    <img width="40px" height="30px" src="/pic/${admin.image}"/> ${user.name}__${sessionScope.user.name}
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:openUser()">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:openPass()">修改密码</a>
                    </dd>
                    <dd>
                        <a href="javascript:openPic()">修改头像</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:logout()">注销</a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">信息维护</a>
                    <dl class="layui-nav-child">
                        <%--<c:forEach items="${authList}" var="auth">
                            <dd>
                                <a href="javascript:;"
                                   data-url="${auth.path}"
                                   class="site-demo-active">${auth.name}</a>
                            </dd>
                        </c:forEach>--%>
                        <dd>
                            <a href="javascript:;"
                               data-url="/page/blog/list"
                               class="site-demo-active">博客管理</a>
                        </dd>
                        <dd>
                            <a href="javascript:;"
                               data-url="/page/type/list"
                               class="site-demo-active">博客分类管理</a>
                        </dd>
                        <dd>
                            <a href="javascript:;"
                               data-url="/echarts.jsp"
                               class="site-demo-active">统计图表</a>
                        </dd>

                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">基础配置</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;"
                               class="site-demo-active"
                               data-url="/com.situ.web/page/dict/dictlist.jsp">
                                基础数据维护</a>
                        </dd>
                        <dd>
                            <a href="javascript:;" class="site-demo-active"
                               data-url="/com.situ.web/404.jsp">404-1</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body"><!-- 内容主体区域 -->
        <iframe name="rightframe" width="99%" height="97%" src="/echarts.jsp"></iframe>
    </div>
    <div class="layui-footer">© JAVA - 底部固定区域</div>
</div>
<script type="text/javascript">
    var $ = layui.jquery;
    var layer = layui.layer;
    var element = layui.element;
    $('.site-demo-active').click(function () {
        window.open($(this).data('url'), "rightframe");
    });
    element.render();// element.init();
    function openURL(url) {
        window.open(url, "rightframe");
    }

    function logout() {
        layer.confirm('您确认要退出么?', {icon: 3}, function () {
            location.href = "/user?method=logout";
        })
    }

    function openPass() {
        location.href = "/page/password/update";
    }

</script>
</body>
</html>