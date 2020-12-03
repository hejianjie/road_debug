layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;



    /**
     * 系统管理--角色管理
     */
    var userList = {
        tableId: "userTable",    //表格id
        condition: {
            userName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    userList.initColumn = function () {
        return [[
            // {type: 'checkbox'},
            {field: 'number', sort: true, title: '序号', type:'numbers'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'account', sort: true, title: '账户'},
            {field: 'full_name', sort: true, title: '部门名称'},
         //   {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 弹出添加用户
     */
    // userList.openAdd = function () {
    //     admin.putTempData('formOk', false);
    //     top.layui.admin.open({
    //         type: 2,
    //         title: '添加路段',
    //         content: Feng.ctxPath + '/sys_user/page/addReferenceUser',
    //         area: ['600px','600px'],
    //         end: function () {
    //             admin.getTempData('formOk') && table.reload(userList.tableId);
    //         }
    //     });
    // };

    /**
     * 点击重置密码
     *
     * @param data 点击按钮时候的行数据
     */
    // userList.onReset= function (data) {
    //     var operation = function () {
    //         var ajax = new $ax(Feng.ctxPath + "/sys_user/reset?userId="+data.user_id, function () {
    //             Feng.success("重置密码成功!");
    //             table.reload(userList.tableId);
    //         }, function (data) {
    //             Feng.error("重置失败!" + data.responseJSON.message + "!");
    //         });
    //         ajax.start();
    //     };
    //     Feng.confirm("是否重置 " + data.name + "的密码?", operation);
    // };

    /**
     * 点击删除桩
     *
     * @param data 点击按钮时候的行数据
     */
    // userList.onDelete= function (data) {
    //     var operation = function () {
    //         var ajax = new $ax(Feng.ctxPath + "/sys_user/delete?userId="+data.user_id, function () {
    //             Feng.success("删除成功!");
    //             table.reload(userList.tableId);
    //         }, function (data) {
    //             Feng.error("删除失败!" + data.responseJSON.message + "!");
    //         });
    //         ajax.start();
    //     };
    //     Feng.confirm("是否删除用户 " + data.name + "?", operation);
    // };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + userList.tableId,
        url: Feng.ctxPath + '/sys_user/getbyrole',
        page: true,
        height: "full-98",
        limit:40,
        cellMinWidth: 100,
        cols: userList.initColumn()
    });


    // // 添加按钮点击事件
    // $('#btnAdd').click(function () {
    //     userList.openAdd();
    // });
    //
    // // 工具条点击事件
    // table.on('tool(' + userList.tableId + ')', function (obj) {
    //     var data = obj.data;
    //     var layEvent = obj.event;
    //
    //     if (layEvent === 'reset') {
    //         userList.onReset(data);
    //     } else if (layEvent === 'delete') {
    //         userList.onDelete(data);
    //     }
    // });


});