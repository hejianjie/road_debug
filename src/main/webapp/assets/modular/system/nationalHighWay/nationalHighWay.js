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
    var nationalHighWayTable = {
        tableId: "nationalHighWayTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    nationalHighWayTable.initColumn = function () {
        return [[
            {field: 'highwayId', sort: true,  title: '编号'},
            {field: 'highwayName', sort: false, title: '国道'},
            {field: 'overallLength', sort: false, title: '总长(公里)'},
            {field: 'sectionCount', sort: false, title: '分段数(段)'},
            {field: 'stakeCount', sort: false, title: '综桩数(桩)'},
            {
                field: 'status', sort: false, title: '状态', templet: function (d) {
                    if (d.status == 0) {
                        return "良好";
                    } else if (d.status == 1){
                        return "保养";
                    }else if (d.status == 2){
                        return "维修";
                    }
                }
            },
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200},
            // {align: 'center', toolbar: '#tableInforLook', title: '国道信息', minWidth: 200}
        ]];
    };

    /**
     * 点击国道查询按钮开始查询
     */
    nationalHighWayTable.search = function () {
        var queryData = {};
        queryData['highwayNameCondition'] = $("#condition").val();
        table.reload(nationalHighWayTable.tableId, {where: queryData});
    };

    /**
     * 弹出添加通知
     */
    nationalHighWayTable.openAddNationalHighWay = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加国道',
            content: Feng.ctxPath + '/nationalHighWay/nationalHighWay_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(nationalHighWayTable.tableId);
            }
        });
    };

    /**
     * 点击编辑通知
     *
     * @param data 点击按钮时候的行数据
     */
    nationalHighWayTable.onEditNationalHighWay = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑国道',
            content: Feng.ctxPath + '/nationalHighWay/nationalHighWay_edit/' + data.highwayId,
            end: function () {
                admin.getTempData('formOk') && table.reload(nationalHighWayTable.tableId);
            }
        });
    };

    /**
     * 点击删除通知
     *
     * @param data 点击按钮时候的行数据
     */
    nationalHighWayTable.onDeleteNationalHighWay = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/nationalHighWay/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(nationalHighWayTable.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("highwayId", data.highwayId);
            ajax.start();
        };
        Feng.confirm("是否删除国道 " + data.highwayName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + nationalHighWayTable.tableId,
        url: Feng.ctxPath + '/nationalHighWay/nationalHighWayList',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: nationalHighWayTable.initColumn()
    });

    // 搜索按钮点击事件
  $('#btnSearch').click(function () {
      nationalHighWayTable.search();
   });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        nationalHighWayTable.openAddNationalHighWay();
    });

    // 工具条点击事件
    table.on('tool(' + nationalHighWayTable.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            nationalHighWayTable.onEditNationalHighWay(data);
        } else if (layEvent === 'delete') {
            nationalHighWayTable.onDeleteNationalHighWay(data);
        }
    });
});
