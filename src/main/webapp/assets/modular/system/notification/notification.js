layui.use(['layer', 'form', 'table', 'admin', 'ax','formSelects', 'laydate'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var title='';
    var beginTime,endTime;
    /**
     * 系统管理--消息管理
     */
    var Notice = {
        tableId: "noticeTable"    //表格id
    };
    var formSelects = layui.formSelects;
    /**
     * 初始化表格的列
     */
    Notice.initColumn = function () {
        return [[
            // {type: 'checkbox'},
            {field: 'notification_id', hide: true, sort: true, title: 'id'},
            {field: 'status', hide: true,  title: 'status'},
            {title: '编号',align: 'center',type:'numbers'},
            {field: 'title', sort: true, title: '标题'},
            // {field: 'content', sort: true, title: '内容'},
            {field: 'forward_to', sort: true, title: '通知单位'},
            {field: 'name', sort: true, title: '发布人'},
            {field: 'publish_time', sort: true, title: '时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Notice.search = function () {

        console.log("cookie "+document.cookie)
        document.cookie = "username=taro";
        title=$("#notification").val();
        beginTime = $("#beginTime").val();
        endTime = $("#endTime").val();
        table.render({
            elem: '#' + Notice.tableId,
            url: Feng.ctxPath + '/notification/getAllNotification?title='+title+"&beginTime="+beginTime+"&endTime="+endTime,
            page: true,
            limit:40,
            height: "full-98",
            cellMinWidth: 100,
            cols: Notice.initColumn()
        });
    };
    //渲染时间选择框
    laydate.render({
        elem: '#beginTime',
        type:'datetime'
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endTime',
        type:'datetime'
    });

    /**
     * 弹出添加通知
     */
    Notice.openAddNotice = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加通知',
            area:['800px', '800px'],
            content: Feng.ctxPath + '/notification/notificationAdd',
            end: function () {
                admin.getTempData('formOk') && table.reload(Notice.tableId);
            }
        });
    };

    /**
     * 点击编辑通知
     *
     * @param data 点击按钮时候的行数据
     */
    Notice.onEditNotice = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改通知',
            area:['800px', '800px'],
            content: Feng.ctxPath + '/notification/notificationEdit?id='+data.notification_id ,
            end: function () {
                admin.getTempData('formOk') && table.reload(Notice.tableId);
            }
        });
    };

    Notice.onViewNotice = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '通知详情',
            area:['600px', '600px'],
            content: Feng.ctxPath + '/notification/notificationContent?id='+data.notification_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Notice.tableId);
            }
        });
    };

    /**
     * 点击删除通知
     *
     * @param data 点击按钮时候的行数据
     */
    Notice.onDeleteNotice = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/notification/deleteNotification", function (data) {
                Feng.success("删除成功!");
                table.reload(Notice.tableId);
            }, function (data) {
                // Feng.error("删除失败!" + data.responseJSON.message + "!");
                Feng.error(data.responseJSON.message + "!");
            });
            ajax.set("id", data.notification_id);
            ajax.start();
        };
        Feng.confirm("是否删除通知 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Notice.tableId,
        url: Feng.ctxPath + '/notification/getAllNotification?title='+title,
        page: true,
        limit:40,
        height: "full-98",
        cellMinWidth: 100,
        cols: Notice.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Notice.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Notice.openAddNotice();
    });

    // 工具条点击事件
    table.on('tool(' + Notice.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Notice.onEditNotice(data);
        } else if (layEvent === 'delete') {
            Notice.onDeleteNotice(data);
        }else if (layEvent === 'view') {
            Notice.onViewNotice(data);
        }
    });
});
