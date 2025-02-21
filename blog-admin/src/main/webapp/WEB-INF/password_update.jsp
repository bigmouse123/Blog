<%--
  Created by IntelliJ IDEA.
  User: a1595
  Date: 2025/2/21
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
</head>
<body>

<style>
    .demo-update-container {
        width: 320px;
        margin: 21px auto 0;
    }

    .demo-update-other .layui-icon {
        position: relative;
        display: inline-block;
        margin: 0 2px;
        top: 2px;
        font-size: 26px;
    }
</style>

<script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>

<form id="formId" method="post">
    <div class="demo-update-container">
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="text" name="oldPassword" value="" lay-verify="required" placeholder="请输入旧密码"
                       lay-reqtext="请填写旧密码" autocomplete="off" class="layui-input" lay-affix="clear">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="text" name="newPassword" value="" lay-verify="required" placeholder="请输入新密码"
                       lay-reqtext="请填写新密码" autocomplete="off" class="layui-input" lay-affix="clear">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="text" name="againPassword" value="" lay-verify="required" placeholder="再次确认新密码密码"
                       lay-reqtext="请再次填写新密码" autocomplete="off" class="layui-input" lay-affix="clear">
            </div>
        </div>
        <div class="layui-form-item">
            <button type="button" class="layui-btn layui-btn-fluid" onclick="submitForm()" lay-submit lay-filter="demo-update">修改
            </button>
        </div>
    </div>
</form>
<script>

    var queryString = window.location.search;
    var urlParams = new URLSearchParams(queryString);
    var name = urlParams.get("name");
    console.log(name);


    function submitForm() {
        $.post(
            '/user?method=updatePassword&name=' + name,
            $('#formId').serialize(),
            function (result) {
                console.log(result);
                if (result.code == 0) {
                    mylayer.okUrl(result.msg, '/');
                } else {
                    mylayer.errorUrl(result.msg, '/');
                }
            },
            'json'
        );
    }
</script>
</body>
</html>
