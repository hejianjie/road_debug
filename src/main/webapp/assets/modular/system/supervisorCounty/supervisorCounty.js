layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--消息管理
     */
    var supervisorCountyTable = {
        tableId: "supervisorCountyTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    supervisorCountyTable.initColumn = function () {
        return [[
            {type: 'numbers'},
            {field: 'supervisorId', sort: true,  title: '编号'},
            {field: 'supervisorName', sort: false, title: '名称'},
            {field: 'parentId', sort: false, title: '上级主管部门编号',hide: true},
            {field: 'personCount', sort: false, title: '巡查员人数(人)'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮开始查询
     */
    supervisorCountyTable.search = function () {
        var queryData = {};
        queryData['supervisorNameCondition'] = $("#condition").val();
        queryData['parentId'] = $("#parentId").val();
        table.reload(supervisorCountyTable.tableId, {where: queryData});
    };

    /**
     * 弹出添加通知
     */
    supervisorCountyTable.openAddSupervisorCounty = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加养护站',
            content: Feng.ctxPath + '/supervisorCounty/supervisorCounty_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(supervisorCountyTable.tableId);
            }
        });
    };

    /**
     * 点击编辑通知
     *
     * @param data 点击按钮时候的行数据
     */
    supervisorCountyTable.onEditSupervisorCounty = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑养护站',
            content: Feng.ctxPath + '/supervisorCounty/supervisorCounty_edit/' + data.supervisorId,
            end: function () {
                admin.getTempData('formOk') && table.reload(supervisorCountyTable.tableId);
            }
        });
    };

    /**
     * 点击删除国道性质
     *
     * @param data 点击按钮时候的行数据
     */
    supervisorCountyTable.onDeleteSupervisorCounty = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/supervisorCounty/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(supervisorCountyTable.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("supervisorId", data.supervisorId);
            ajax.start();
        };
        Feng.confirm("是否删除建设性质: " + data.supervisorName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + supervisorCountyTable.tableId,
        url: Feng.ctxPath + '/supervisorCounty/supervisorCountyList',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: supervisorCountyTable.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        supervisorCountyTable.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        supervisorCountyTable.openAddSupervisorCounty();
    });

    //置空按钮
    $('#btnClean').click(function () {
        $('#condition').val("");
    });

    // 工具条点击事件
    table.on('tool(' + supervisorCountyTable.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            supervisorCountyTable.onEditSupervisorCounty(data);
        } else if (layEvent === 'delete') {
            supervisorCountyTable.onDeleteSupervisorCounty(data);
        }
    });
});
