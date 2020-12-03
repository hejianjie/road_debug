layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var nationalHighwayId=0;

    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/stake/selectAllNationalHighway',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $.each(data, function (index, item) {
                $('#gd').append(new Option(item.nationalHighwayName, item.nationalHighwayId));// 下拉菜单里添加元素
            });
            layui.form.render("select");
        }
    });

    form.on('select(gd)', function (data) {
        nationalHighwayId= data.value;
        console.log(nationalHighwayId)
    });
    /**
     * 系统管理--角色管理
     */
    var roadSection = {
        tableId: "roadSectionTable",    //表格id
        condition: {
            roadSectionName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    roadSection.initColumn = function () {
        return [[
           // {type: 'checkbox'},
            {field: 'number', sort: true, title: '序号', type:'numbers'},
            {field: 'name', sort: true, title: '路段名称'},
            {field: 'overall_length', sort: true, title: '总长(公里)'},
            {field: 'begin_at', sort: true, title: '起始位置(公里)'},
            {field: 'end_at', sort: true, title: '结束位置(公里)'},
            {field: 'stakeCount', sort: true, title: '总桩数'},
            {field: 'status', sort: true, title: '状态'},
            {field: 'supervisor_name', sort: true, title: '管理单位名称'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    roadSection.search = function () {
        table.render({
            elem: '#' + roadSection.tableId,
            url: Feng.ctxPath + '/road_section/select?highwayId='+nationalHighwayId,
            page: true,
            height: "full-98",
            cellMinWidth: 100,
            cols: roadSection.initColumn()
        });
        // table.reload(Stake.tableId, {where: queryData});
    };

    /**
     * 弹出添加桩
     */
    roadSection.openAdd = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加路段',
            content: Feng.ctxPath + '/information/addRoadSection',
            area: ['600px','600px'],
            end: function () {
                admin.getTempData('formOk') && table.reload(roadSection.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    roadSection.exportExcel = function () {
        var checkRows = roadSection.checkStatus(roadSection.tableId);
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
    roadSection.onEdit= function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改路段',
            area: ['600px','600px'],
            content: Feng.ctxPath + '/information/editRoadSection?roadSectionId=' + data.road_section_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(roadSection.tableId);
            }
        });
    };

    /**
     * 点击删除桩
     *
     * @param data 点击按钮时候的行数据
     */
    roadSection.onDelete= function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/road_section/delete?roadSectionId="+data.road_section_id, function () {
                Feng.success("删除成功!");
                table.reload(roadSection.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.start();
        };
        Feng.confirm("是否删除桩 " + data.name + "?", operation);
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + roadSection.tableId,
        url: Feng.ctxPath + '/road_section/selectAll',
        page: true,
        height: "full-98",
        limit:40,
        cellMinWidth: 100,
        cols: roadSection.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        roadSection.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        roadSection.openAdd();
    });

    // 导出excel
    $('#btnExp').click(function () {
        roadSection.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + roadSection.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            roadSection.onEdit(data);
        } else if (layEvent === 'delete') {
            roadSection.onDelete(data);
        }
    });


});