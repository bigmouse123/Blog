<%--
  Created by IntelliJ IDEA.
  User: a1595
  Date: 2025/2/17
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">博客名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" autocomplete="off" placeholder="请输入" lay-verify="required"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">博客分类</label>
        <div class="layui-input-block">
            <select id="typeId" name="typeId" lay-filter="aihao">
                <option value=""></option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">封面</label>
        <div class="layui-input-block">
            <div class="layui-upload-list">
                <input type="hidden" name="image" id="imageId">
                <img class="layui-upload-img" id="ID-upload-demo-img" width="150px" height="150px">
                <div id="ID-upload-demo-text"></div>
            </div>
            <button type="button" class="layui-btn" id="uploadId">
                <i class="layui-icon layui-icon-upload"></i> 单图片上传
            </button>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">博客内容</label>
        <div class="layui-input-block">
            <textarea id="contentId" name="content" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit lay-filter="submitForm">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script>

    var kindEditorParams = {
        afterBlur: function () {
            this.sync();
        }
    };
    var editor = KindEditor.create('#contentId', kindEditorParams);

    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var upload = layui.upload;
        var layer = layui.layer;

        $.post(
            '/type?method=selectAll',
            function (result) {
                if (result.code === 0) {
                    var list = result.data;
                    $(list).each(function () {
                        $('#typeId').append('<option value="' + this.id + '">' + this.name + '</option>')
                    })
                }
                form.render('select');
            },
            'json'
        );

        // 单图片上传
        var uploadInst = upload.render({
            elem: '#uploadId',
            url: '/upload', // 实际使用时改成您自己的上传接口即可。
            before: function (obj) {
                // 预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#ID-upload-demo-img').attr('src', result); // 图片链接（base64）
                });
            },
            done: function (result) {
                if (result.code == 0) {
                    console.log(result);
                    $('#imageId').val(result.data);
                } else {
                    mylayer.errorMsg('上传失败')
                }
                $('#ID-upload-demo-text').html('');
            },
            error: function () {
                // 演示失败状态，并实现重传
                var demoText = $('#ID-upload-demo-text');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            },
        });

        // 提交事件
        form.on('submit(submitForm)', function (data) {
            var field = data.field; // 获取表单字段值
            console.log(field);
            console.log(data.field);
            //{"name":"UI","credit":"12"}
            // 此处可执行 Ajax 等操作
            $.post(
                '/blog?method=add',
                data.field,
                function (result) {
                    console.log(result);
                    if (result.code == 0) {
                        mylayer.okMsg(result.msg);
                        //table.reload('tableId');
                        setInterval(function () {
                            //关闭弹出层
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            //刷新父页面
                            window.parent.location.reload();
                        }, 2000);
                    }
                },
                'json'
            );
            return false; // 阻止默认 form 跳转
        });
    });
</script>
</body>
</html>
