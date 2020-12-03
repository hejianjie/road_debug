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
    var constructTypeTable = {
        tableId: "constructTypeTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    constructTypeTable.initColumn = function () {
        return [[
            {field: 'constructTypeId', sort: true,  title: '编号'},
            {field: 'constructTypeName', sort: false, title: '建设性质名称'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮开始查询
     */
    constructTypeTable.search = function () {
        var queryData = {};
        queryData['constructTypeCondition'] = $("#condition").val();
        table.reload(constructTypeTable.tableId, {where: queryData});
    };

    /**
     * 弹出添加通知
     */
    constructTypeTable.openAddConstructType = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加建设性质',
            content: Feng.ctxPath + '/construct_type/constructType_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(constructTypeTable.tableId);
            }
        });
    };

    /**
     * 点击编辑建设性质
     *
     * @param data 点击按钮时候的行数据
     */
    constructTypeTable.onEditConstructType = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑建设性质',
            content: Feng.ctxPath + '/construct_type/constructType_edit/' + data.constructTypeId,
            end: function () {
                admin.getTempData('formOk') && table.reload(constructTypeTable.tableId);
            }
        });
    };

    /**
     * 点击删除国道性质
     *
     * @param data 点击按钮时候的行数据
     */
    constructTypeTable.onDeleteConstructType = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/construct_type/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(constructTypeTable.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("constructTypeId", data.constructTypeId);
            ajax.start();
        };
        Feng.confirm("是否删除建设性质: " + data.constructTypeName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + constructTypeTable.tableId,
        url: Feng.ctxPath + '/construct_type/constructTypeList',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: constructTypeTable.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        constructTypeTable.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        constructTypeTable.openAddConstructType();
    });

    //置空按钮
    $('#btnClean').click(function () {
        $('#condition').val("");
    });

    // 工具条点击事件
    table.on('tool(' + constructTypeTable.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            constructTypeTable.onEditConstructType(data);
        } else if (layEvent === 'delete') {
            constructTypeTable.onDeleteConstructType(data);
        }
    });
});
