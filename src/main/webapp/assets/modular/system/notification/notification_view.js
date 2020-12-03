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
            {field: 'name', sort: true, title: '发布人'},
            {field: 'publish_time', sort: true, title: '时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Notice.search = function () {
        title=$("#notification").val();
        beginTime = $("#beginTime").val();
        endTime = $("#endTime").val();
        table.render({
            elem: '#' + Notice.tableId,
            url: Feng.ctxPath + '/notification/getMyNotification?title='+title+"&beginTime="+beginTime+"&endTime="+endTime,
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
    Notice.onViewNotice = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '通知详情',
            area:['800px', '560px'],
            content: Feng.ctxPath + '/notification/notificationContent?id='+data.notification_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Notice.tableId);
            }
        });
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Notice.tableId,
        url: Feng.ctxPath + '/notification/getMyNotification?title='+title,
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



    // 工具条点击事件
    table.on('tool(' + Notice.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'view') {
            Notice.onViewNotice(data);
        }
    });
});
