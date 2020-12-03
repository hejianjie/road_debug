layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var organizationId;


     // 区县下拉框
        $.ajax({
            type: "get",
            url: Feng.ctxPath + '/road_section/getsupervisor1?userId=1',//暂时固定
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (data) {

                $.each(data, function (index, item) {
                    $('#qx').append(new Option(item.supervisorName, item.supervisorId));// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        });



        // 监听下拉框选择事件获取选中值
        form.on('select(qx)', function (data) {
             countyId= data.value;
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/road_section/getsupervisor2?countyId='+countyId,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    $('#s').empty();
                    $('#s').append(new Option("请选择", -1));
                    $.each(data, function (index, item) {
                        $('#s').append(new Option(item.supervisorName, item.supervisorId));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");
                }
            });
        });
        form.on('select(s)', function (data) {
            console.log(data.value);
            organizationId= data.value;
        });

    /**
     * 系统管理--角色管理
     */
    var roadUser = {
        tableId: "roadUserTable",    //表格id
        condition: {
            roadUserName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    roadUser.initColumn = function () {
        return [[
           // {type: 'checkbox'},
            {field: 'number', sort: true, title: '', type:'numbers'},
            {field: 'user_name', sort: true, title: '姓名'},
            {field: 'gender', sort: true, title: '性别'},
            {field: 'age', sort: true, title: '年龄'},
            {field: 'telephone', sort: true, title: '电话'},
            {field: 'organization_name', sort: true, title: '所属单位'},
            {field: 'account', sort: true, title: '账号'},
            {field: 'status', sort: true, title: '账号状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 300}
        ]];
    };

    /**
     * 点击查询按钮
     */
    roadUser.search = function () {
        table.render({
            elem: '#' + roadUser.tableId,
            url: Feng.ctxPath + '/sys/road_user/selectInspector?organizationId='+organizationId,
            page: true,
            height: "full-98",
            cellMinWidth: 100,
            cols: roadUser.initColumn()
        });
        // table.reload(Stake.tableId, {where: queryData});
    };

    /**
     * 弹出添加桩
     */
    roadUser.openAdd = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加巡查员',
            content: Feng.ctxPath + '/sys/road_user/info/addInspector',
            area: ['600px','600px'],
            end: function () {
                admin.getTempData('formOk') && table.reload(roadUser.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    roadUser.exportExcel = function () {
        var checkRows = roadUser.checkStatus(roadUser.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑桩
     *
     * @param data 点击按钮时候的行数据
     */
    roadUser.onEdit= function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改巡查员',
            area: ['600px','600px'],
            content: Feng.ctxPath + '/sys/road_user/info/editInspector?roadUserId=' + data.user_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(roadUser.tableId);
            }
        });
    };

    /**
     * 点击删除桩
     *
     * @param data 点击按钮时候的行数据
     */
    roadUser.onDelete= function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sys/road_user/deleteInspector?roadUserId="+data.user_id, function () {
                Feng.success("删除成功!");
                table.reload(roadUser.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.start();
        };
        Feng.confirm("是否删除巡查员 " + data.user_name + "?", operation);
    };

    //冻结账号
    roadUser.onFreeze=function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sys/road_user/freezeInspector?roadUserId="+data.user_id, function () {
                Feng.success("冻结成功!");
                table.reload(roadUser.tableId);
            }, function (data) {
                Feng.error("冻结失败!" + data.responseJSON.message + "!");
            });
            ajax.start();
        };
        Feng.confirm("是否冻结用户 " + data.user_name + "?", operation);
    }

    //解冻账号
    roadUser.onThaw=function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sys/road_user/thawInspector?roadUserId="+data.user_id, function () {
                Feng.success("解冻成功!");
                table.reload(roadUser.tableId);
            }, function (data) {
                Feng.error("解冻失败!" + data.responseJSON.message + "!");
            });
            ajax.start();
        };
        Feng.confirm("是否解冻用户 " + data.user_name + "?", operation);
    }

    //重置密码
    roadUser.onReset=function (data) {
        var operation= function(){
            var ajax = new $ax(Feng.ctxPath + "/sys/road_user/resetInspectorPassword?roadUserId="+data.user_id, function (){
                Feng.success("重置成功!");
                table.reload(roadUser.tableId);
            },function(data){
                Feng.error('重置失败!'+data.responseJSON.message + "!")

            });
            ajax.start();
        }
        Feng.confirm("是否重置用户 " + data.user_name + "的密码?", operation);
    }

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + roadUser.tableId,
        url: Feng.ctxPath + '/sys/road_user/selectAllInspector',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: roadUser.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        roadUser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        roadUser.openAdd();
    });

    // 导出excel
    $('#btnExp').click(function () {
        roadUser.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + roadUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            roadUser.onEdit(data);
        } else if (layEvent === 'delete') {
            roadUser.onDelete(data);
        }
        else if(layEvent === 'freeze'){
            roadUser.onFreeze(data)
        }
        else if(layEvent === 'thaw'){
            roadUser.onThaw(data)
        }
        else if(layEvent === 'reset'){
            roadUser.onReset(data)
        }

    });


});