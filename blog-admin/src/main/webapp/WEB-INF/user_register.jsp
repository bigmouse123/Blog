<%--
  Created by IntelliJ IDEA.
  User: a1595
  Date: 2025/2/21
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!-- 确保启用 EL -->
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <style>
        .ok {
            color: green;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>
<style>
    .demo-reg-container {
        width: 320px;
        margin: 21px auto 0;
    }

    .demo-reg-other {
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

<form class="layui-form" id="formId">
    <div class="demo-reg-container">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-xs7">
                    <div class="layui-input-wrap">
                        <div class="layui-input-prefix">
                            <i class="layui-icon layui-icon-cellphone"></i>
                        </div>
                        <input type="text" name="cellphone" id="cellphone" onblur="checkPhone()" value=""
                               lay-verify="required|phone" placeholder="手机号"
                               lay-reqtext="请填写手机号" autocomplete="off" class="layui-input" id="reg-cellphone">
                        <span id="phoneMsg"></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="text" name="name" id="name" onblur="checkName()" value="" lay-verify="required"
                       placeholder="用户名"
                       autocomplete="off" class="layui-input" id="reg-user" lay-affix="eye">
                <span id="nameMsg"></span>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="password" name="password" id="password" onblur="checkPassword()" value=""
                       lay-verify="required" placeholder="密码"
                       autocomplete="off" class="layui-input" id="reg-password" lay-affix="eye">
                <span id="passwordMsg"></span>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="password" name="confirmPassword" id="confirmPassword" onblur="checkConfirm()" value=""
                       lay-verify="required|confirmPassword"
                       placeholder="确认密码" autocomplete="off" class="layui-input" lay-affix="eye">
                <span id="confirmMsg"></span>
            </div>
        </div>
        <div class="layui-form-item">
            <button type="submit" class="layui-btn layui-btn-fluid" onclick="submitForm()" lay-submit
                    lay-filter="demo-reg">注册
            </button>
        </div>
        <div class="layui-form-item demo-reg-other">
            <a href="/page/login">登录已有帐号</a>
        </div>
    </div>
</form>
<script>
    function checkPhone() {
        var cellphone = $('#cellphone').val();
        console.log(cellphone);
        var regex = /\d{11}/
        if (cellphone.match(regex)) {
            $('#phoneMsg').html('格式正确');
            $('#phoneMsg').removeClass();
            $('#phoneMsg').addClass('ok');
        } else {
            $('#phoneMsg').html(('请输入11位电话'));
            $('#phoneMsg').removeClass();
            $('#phoneMsg').addClass('error');
        }
    }

    function checkName() {
        var name = $('#name').val();
        console.log(name);
        var regex = /^[a-zA-Z_][a-zA-Z0-9_]{3,7}$/
        if (name.match(regex)) {
            $('#nameMsg').html('格式正确');
            $('#nameMsg').removeClass();
            $('#nameMsg').addClass('ok');
        } else {
            $('#nameMsg').html(('4~8位字母数字或下划线,以字母或下划线开头'));
            $('#nameMsg').removeClass();
            $('#nameMsg').addClass('error');
        }
    }

    function checkPassword() {
        var password = $('#password').val();
        console.log(password);
        var regex = /^[a-zA-Z0-9_]{8,16}$/
        if (password.match(regex)) {
            $('#passwordMsg').html('格式正确');
            $('#passwordMsg').removeClass();
            $('#passwordMsg').addClass('ok');
        } else {
            $('#passwordMsg').html(('密码格式为8~16位数字字母下划线'));
            $('#passwordMsg').removeClass();
            $('#passwordMsg').addClass('error');
        }
    }

    function checkConfirm() {
        var password = $('#password').val();
        var confirmPassword = $('#confirmPassword').val();
        console.log(password);
        console.log(confirmPassword);
        if (password === confirmPassword) {
            $('#confirmMsg').html('两次密码一致');
            $('#confirmMsg').removeClass();
            $('#confirmMsg').addClass('ok');
        } else {
            $('#confirmMsg').html(('两次密码不一致'));
            $('#confirmMsg').removeClass();
            $('#confirmMsg').addClass('error');
        }
    }

    $('#formId').submit(function (e) {
        e.preventDefault(); // 阻止表单默认提交
        const name = $('#name').val();
        const password = $('#password').val();

        $.post(
            '/user?method=register',
            {'name': name, 'password': password},
            function (result) {
                console.log(result);
                if (result.code == 0) {
                    mylayer.okUrl(result.msg, '/page/login');
                } else {
                    mylayer.errorUrl(result.msg, '/page/user/register');
                }
            },
            'json'
        );
    });
    // function submitForm() {
    //     var name = $('#name').val();
    //     var password = $('#password').val();
    //     console.log(name);
    //     console.log(password);
    //     $.post(
    //         '/user?method=register',
    //         {'name': name, 'password': password},
    //         function (result) {
    //             console.log(result);
    //             if (result.code == 0) {
    //                 mylayer.okUrl(result.msg, '/page/login');
    //             } else {
    //                 mylayer.errorUrl(result.msg, '/page/user/register');
    //             }
    //         },
    //         'json'
    //     );
    // }
</script>
</body>
</html>
