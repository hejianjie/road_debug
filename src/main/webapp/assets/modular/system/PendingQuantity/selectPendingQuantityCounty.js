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
    var roleId='';

    //获取角色信息
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/mgr/getRole',
        contentType: "application/json;charset=utf-8",
        success:function(data){
            roleId = data;
        }
    })



    /**
     * 系统管理--部门管理
     */
    var roadHazardCountyFirstTrialList = {
        tableId: "roadHazardCountyFirstTrialList",
        condition: {
            PatrolResultId: ""
        }
    };

    /**
     * 初始化表格的列
     */
    roadHazardCountyFirstTrialList.initColumn = function () {
        return [[
            {field: 'zizeng',  title: '序号',type:'numbers'},
            {field: 'acceptance_id', title: '申请编号',hide: true},
            {field: 'road_hazard_id', title: '病害',hide: true},
            {field: 'nationalHighwayName', title: '路线'},
            {field: 'roadSectionName', title: '路段'},
            {field:'position',title:'具体位置',templet:function (d) {
                    return d.beginStake+"~"+d.endStake
                }},
            {field: 'patrolResultId', hide: true, sort: true, title: 'id',templet:function (d) {
                    if (d.patrolResultId==1){
                        return 'ID：'+ d.patrolResultId +'，标题：<span style="color: #c00;">'+ d.title +'</span>'
                    }
                }},
            {field:'supervisorName',title:'所属单位'},
            {field: 'beginTime', sort: true, title: '巡检时间',templet:function (d) {
                    return d.beginTime+"~"+d.endTime
                }},
            {field: 'hlStatus', title: '审批状态',templet:function (d) {
                if(d.hlStatus+''==='0'){
                    return "待审批"
                }else if(d.hlStatus+''==='1'){
                    return "主管领导待审批"
                }else if(d.hlStatus+''==='2'){
                    return "已审批"
                }else {
                    return "请先提交核量信息"
                }
                }},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}

        ]];
    };

    /**
     * 点击编辑部门
     *
     * @param data 点击按钮时候的行数据
     */
    roadHazardCountyFirstTrialList.showInfo = function (data) {
        console.log(data.acceptance_id)
        console.log(data.road_hazard_id)
        //window.location.href=Feng.ctxPath+'/acceptance/FindAudit/'+data.acceptanceId+'/'+data.road_hazard_id;
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '核量审批',
            area: ['1000px', '800px'],
            content: Feng.ctxPath+'/acceptance/FindAudit/'+data.acceptance_id+'/'+data.road_hazard_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(roadHazardCountyFirstTrialList.tableId);
            }
        });
    };

    roadHazardCountyFirstTrialList.addHl = function (data) {
        console.log(data.road_hazard_id)
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加核量',
            area: ['1000px', '800px'],
            content: Feng.ctxPath+'/acceptance/addHl?roadHazardId='+data.road_hazard_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(roadHazardCountyFirstTrialList.tableId);
            }
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + roadHazardCountyFirstTrialList.tableId,
        url: Feng.ctxPath + '/acceptance/selectPendingQuantityCountyList',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: roadHazardCountyFirstTrialList.initColumn()
    });

    // 工具条点击事件
    table.on('tool(' + roadHazardCountyFirstTrialList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'showInfo') {
            roadHazardCountyFirstTrialList.showInfo(data);
        }else if(layEvent === 'addHl'){
            roadHazardCountyFirstTrialList.addHl(data);
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
