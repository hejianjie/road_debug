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
            {field:'roadSectionName',title:'巡查路段',templet:function (d) {
                    return d.nationalHighwayName+'-'+d.roadSectionName+"-"+d.beginStake
                        +"~"+d.endStake
                }},
            {field: 'patrolResultId', hide: true, sort: true, title: 'id',templet:function (d) {
                    if (d.patrolResultId==1){
                        return 'ID：'+ d.patrolResultId +'，标题：<span style="color: #c00;">'+ d.title +'</span>'
                    }
                }},
            {field:'supervisorName',title:'巡查机构'},
            {field: 'beginTime', sort: true, title: '巡检时间',templet:function (d) {
                    return d.beginTime+"~"+d.endTime
                }},
            {field:'position',title:'病害发现位置'},
            {field:'description',title:'病害描述'},
            {field:'supervisorName',title:'巡查机构'},
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
            content: Feng.ctxPath+'/application/findByRoadHazardId/'+data.road_hazard_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(roadHazard.tableId);
            }
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + roadHazard.tableId,
        url: Feng.ctxPath + '/roadhazard/selectByStatus',
        page: true,
        height: "full-98",
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
