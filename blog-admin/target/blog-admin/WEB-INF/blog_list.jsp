<%--
  Created by IntelliJ IDEA.
  User: a1595
  Date: 2025/2/14
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>博客管理</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
</head>
<body>
<script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>

<form class="layui-form">
    博客名：
    <div class="layui-inline">
        <input type="text" name="name" class="layui-input" lay-affix="clear">
    </div>
    分类：
    <div class="layui-inline">
        <select id="typeId" name="typeId">
            <option value=""></option>
        </select>
    </div>
    <div class="layui-inline">
        <button class="layui-btn" lay-submit lay-filter="submitSearch">搜索</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>

<table class="layui-hide" id="tableId" lay-filter="tableId"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="deleteAll">批量删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="exportExcel">导出Excel</button>
        <button class="layui-btn layui-btn-sm" lay-event="importExcel">导入Excel</button>
    </div>
</script>

<script type="text/html" id="toolDemo">
    <div class="layui-clear-space">
        <a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="deleteById">删除</a>
        <a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="content">详情</a>
    </div>
</script>

<script type="text/html" id="imageTemplet">
    <img src="/pic/{{d.image}}"></img>
</script>

<script type="text/html" id="statusTemplet">
    {{#     if (d.status == 1) {            }}
    <span class="layui-badge layui-bg-green">上架</span>
    {{#     } else if (d.status == 0) {     }}
    <span class="layui-badge">下架</span>
    {{#     }                               }}
</script>

<script>
    layui.use(['table', 'layer', 'form'], function () {

        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer

        $.post(
            '/type?method=selectAll',
            function (result) {
                console.log(result.data)
                if (result.code === 0) {
                    var list = result.data;
                    console.log(list)
                    $(list).each(function () {
                        $('#typeId').append('<option value="' + this.id + '">' + this.name + '</option>')
                    })
                    form.render('select');
                }
            },
            'json'
        );

        table.render({
            elem: '#tableId'
            , url: '/blog'
            , toolbar: '#toolbarDemo'
            , cols:
                [[
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'id', title: 'ID', sort: true}
                    , {field: 'name', title: '博客名称'}
                    , {field: 'image', title: '封面', templet: '#imageTemplet'}
                    , {field: 'clickCount', title: '点击量'}
                    , {field: 'content', title: '文本内容', escape: false}
                    , {field: 'typeName', title: '博客分类'}
                    , {field: 'status', title: '状态', templet: '#statusTemplet'}
                    , {field: 'isDeleted', title: '逻辑删除'}
                    , {field: 'createTime', title: '创建时间'}
                    , {field: 'updateTime', title: '更新时间'}
                    , {fixed: 'right', title: '操作', width: 134, minWidth: 125, templet: '#toolDemo'}
                ]]
            , page:
                true
        });

        // 搜索提交
        form.on('submit(submitSearch)', function (data) {
            var field = data.field; // 获得表单字段
            // 执行搜索重载
            table.reload('tableId', {
                page: {
                    curr: 1 // 重新从第 1 页开始
                },
                where: field // 搜索的字段
            });
            return false; // 阻止默认 form 跳转
        });

        // 触发单元格工具事件
        table.on('tool(tableId)', function (obj) { // 双击 toolDouble
            var data = obj.data; // 获得当前行数据
            // {id:12,name:'Java',credit:3}
            console.log(data)
            if (obj.event === 'update') {
                layer.open({
                    type: 2,
                    title: '编辑数据',
                    area: ['90%', '90%'],
                    content: '/page/blog/update?id=' + data.id
                });
            } else if (obj.event === 'deleteById') {
                layer.confirm('您确认要删除么?', function () {
                    $.post(
                        '/page/delete',
                        {'id': data.id},
                        function (result) {
                            console.log(result);
                            if (result.code === 0) {
                                mylayer.okMsg(result.msg);
                                //删除数据之后刷新表格，展示最新数据
                                table.reload('tableId');
                            } else {
                                mylayer.errorMsg(result.msg);
                            }
                        },
                        'json'
                    );
                });
            } else if (obj.event === 'content') {
                layer.open({
                    type: 1,
                    title: data.name,
                    area: ['90%', '90%'],
                    content: data.content
                });
            }
        });

        // 头部工具栏事件
        table.on('toolbar(tableId)', function (obj) {
            var id = obj.config.id;
            var checkStatus = table.checkStatus(id);
            var othis = lay(this);
            console.log(obj);
            console.log(checkStatus.data);
            // [{…}, {…}]
            // [{id: 2, name: 'UI', credit: 4}, {id: 3, name: 'H5', credit: 4}]
            // 根据不同的事件名进行相应的操作
            switch (obj.event) { // 对应模板元素中的 lay-event 属性值
                case 'add':
                    layer.open({
                        type: 2,
                        title: '添加数据',
                        area: ['90%', '90%'],
                        content: '/page/blog/add'
                    });
                    break;
                case 'deleteAll':
                    var data = checkStatus.data;
                    var ids = new Array();
                    $(data).each(function () {
                        ids.push(this.id)
                    });
                    layer.confirm('您确认要删除么?', function () {
                        $.post(
                            '/blog?method=deleteAll',
                            {'ids': ids},
                            function (result) {
                                console.log(result);
                                if (result.code == 0) {
                                    mylayer.okMsg(result.msg);
                                    //删除数据之后刷新表格，展示最新数据
                                    table.reload('tableId');
                                } else {
                                    mylayer.errorMsg(result.msg);
                                }
                            },
                            'json'
                        );
                    });
                    break;
                case 'exportExcel':
                    location.href = '/blog?method=exportExcel';
                    break;
                case 'importExcel':

                    break;
            }
        });
    });
</script>
</body>
</html>
