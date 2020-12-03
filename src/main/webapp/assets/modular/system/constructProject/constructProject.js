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
            url: Feng.ctxPath + '/construct_project/getConstructProjectList',
            where: data,
            page: false,
            height: "full-98",
            cellMinWidth: 100,
            cols: supervisorTable.initColumn(),
            treeColIndex:2,
            treeSpid: 0  ,
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
     * 弹出添加巡查单位
     */
    supervisorTable.openAddSuperVisor = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加项目名称',
            content: Feng.ctxPath + '/construct_project/constructProjectAdd',
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
            content: Feng.ctxPath + '/construct_project/constructProjectAddChild?pid='+ data.supervisorId,
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
            var ajax = new $ax(Feng.ctxPath + "/construct_project/delete?id="+data.supervisorId, function (data) {
                Feng.success("删除成功!");
                supervisorTable.initTable(supervisorTable.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            // ajax.set("supervisorId", data.supervisorId);
            ajax.start();
        };
        Feng.confirm("是否删除 " + data.supervisorName + "?", operation);
    };




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
