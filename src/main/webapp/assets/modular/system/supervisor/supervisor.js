layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    var table = layui.table;
    var treetable = layui.treetable;
    /**
     * 系统管理--消息管理
     */
    var supervisorTable = {
        tableId: "supervisorTable",
    };

    /**
     * 初始化表格的列
     */
    supervisorTable.initColumn = function () {
        return [[
            {type: 'numbers'},
            {field: 'supervisorId', sort: true,  title: '编号',hide: true},
            {field: 'supervisorName', sort: false, title: '名称'},

            {align: 'left', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };
    /**
     * 初始化表格
     */
    supervisorTable.initTable = function (supervisorId, data) {
        return treetable.render({
            elem: '#' + supervisorId,
            url: Feng.ctxPath + '/supervisor/supervisorList',
            where: data,
            page: false,
            height: "full-98",
            cellMinWidth: 100,
            cols: supervisorTable.initColumn(),
            treeColIndex:2,
            treeSpid: 1,
            treeIdName: 'supervisorId',
            treePidName: 'parentId',
            treeDefaultClose: false,
            treeLinkage: true,
            // done:function () {
            //     $("[data-filed='parentId']").css('display','none');//隐藏某一列
            //     $("[data-filed='supervisorId']").css('display','none');
            // }
        });
    };

    // 渲染表格
    var tableResult = supervisorTable.initTable(supervisorTable.tableId);
    /**
     * 点击巡查单位查询按钮开始查询
     */
    supervisorTable.search = function () {
        var queryData = {};
        queryData['supervisorNameCondition'] = $("#supervisorName").val();
        supervisorTable.initTable( supervisorTable.tableId, queryData);
    };

    /**
     * 弹出添加巡查单位
     */
    supervisorTable.openAddSuperVisor = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加巡查单位',
            content: Feng.ctxPath + '/supervisor/supervisor_add',
            end: function () {
                admin.getTempData('formOk') && supervisorTable.initTable(supervisorTable.tableId);
            }
        });
    };
    /**
     * 弹出添加巡查单位
     */
    supervisorTable.openAddMaintenanceStation = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加养护站',
            content: Feng.ctxPath + '/supervisor/supervisorStationAdd/'+ data.supervisorId,
            end: function () {
                admin.getTempData('formOk') && supervisorTable.initTable(supervisorTable.tableId);
            }
        });
    };
    /**
     * 点击编辑通知
     *
     * @param data 点击按钮时候的行数据
     */
    supervisorTable.onEditSuperVisor = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑巡查单位',
            content: Feng.ctxPath + '/supervisor/supervisor_edit/' + data.supervisorId,
            end: function () {
                admin.getTempData('formOk') && supervisorTable.initTable(supervisorTable.tableId);
            }
        });
    };

    /**
     * 点击删除通知
     *
     * @param data 点击按钮时候的行数据
     */
    supervisorTable.onDeleteSuperVisor = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/supervisor/delete", function (data) {
                Feng.success("删除成功!");
                supervisorTable.initTable(supervisorTable.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("supervisorId", data.supervisorId);
            ajax.start();
        };
        Feng.confirm("是否删除巡查单位 " + data.supervisorName + "?", operation);
    };



    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        supervisorTable.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        supervisorTable.openAddSuperVisor();
    });

    $('#expandAll').click(function () {
        treetable.expandAll('#' + supervisorTable.tableId);
    });
    $('#foldAll').click(function () {
        treetable.foldAll('#' + supervisorTable.tableId);
    });

    //置空按钮
    $('#btnClean').click(function () {
        $('#condition').val("");
    });

    // 工具条点击事件
    table.on('tool(' + supervisorTable.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            supervisorTable.onEditSuperVisor(data);
        } else if (layEvent === 'delete') {
            supervisorTable.onDeleteSuperVisor(data);
        }else if(layEvent === 'add'){
            supervisorTable.openAddMaintenanceStation(data);
        }
    });
});
