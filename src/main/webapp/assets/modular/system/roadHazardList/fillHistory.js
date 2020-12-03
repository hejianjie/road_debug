layui.use(['table', 'admin', 'ax', 'ztree','laydate','form',"jquery","cascader"], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form=layui.form;
    var parentId=null;
    var supervisorId=null;
    var roadSectionId=null;
    var nationalHighwayId=null;
    var beginStake=null;
    var endStake=null;
    var cascader = layui.cascader;
    var data = [];
    var times=[];
    var road;
    var roads=[];
    var org;


    /**
     * 系统管理--部门管理
     */
    var roadHazard = {
        tableId: "roadHazardList",
        condition: {
            PatrolResultId: ""
        }
    };

    /**
     * 初始化表格的列
     */
    roadHazard.initColumn = function () {
        return [[
            {field: 'zizeng',  title: '序号',type:'numbers'},
            {field:'highway_name',title:'所属国道'},
            {field:'road_section_name',title:'所属路段'},
            {field:'position',title:'具体位置'},
            {field:'simple_name',title:'所属单位'},
            {field:'apply_time',title:'填报时间'},
            {field:'status',title:'审核状态',templet:function (d) {
                switch(d.status) {
                    case 1:
                        return "被否决"
                    case 2:
                        return "已填报"
                    case 3:
                        return "区县审核"
                    case 4:
                        return "区县审核"
                    case 5:
                        return "市区审核"
                    case 6:
                        return "市区审核"
                    case 7:
                        return "市区审核"
                    case 8:
                        return "施工中"
                    case 9:
                        return "施工结束"
                    case 10:
                        return "验收核量"
                    case 11:
                        return "验收核量"
                    case 12:
                        return "验收核量"
                    case 13:
                        return "已结束"


                }
                    return d.beginTime+"~"+d.endTime
                }},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击编辑部门
     *
     * @param data 点击按钮时候的行数据
     */
    roadHazard.showInfo = function (data) {
        // window.location.href=Feng.ctxPath+'/roadhazard?patrolResultId='+data.patrolResultId;
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '病害详情',
            area: ['850px', '800px'],
            content: Feng.ctxPath+'/application/findByRoadHazardId/'+data.road_hazard,
            end: function () {
                admin.getTempData('formOk') && table.reload(roadHazard.tableId);
            }
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + roadHazard.tableId,
        url: Feng.ctxPath + '/application/fillHistory',
        page: true,
        height: "full-98",
        limit:40,
        cellMinWidth: 100,
        cols: roadHazard.initColumn()
    });

    // 工具条点击事件
    table.on('tool(' + roadHazard.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'showInfo') {
            roadHazard.showInfo(data);
        }
    });
    //日期
    laydate.render({
        elem:'#inspectionDate',
        type:'datetime',
        range:'~',
        format:'yyyy-MM-dd'
    });
});
