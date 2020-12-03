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
    var pid;
    var orgs=[];
    var getData = $.ajax({
        type:"get",
        url: '/supervisor/all',
        dataTType:"application/json;charset=UTF-8",
        success:function (res) {
            data = res.data;
            console.log(data);
            if(res.code == 0){
                cascader({
                    elem: "#a",
                    data: res.data,
                    //    url:  Feng.ctxPath + 'supervisor/all',
                    //    type: "get",
                    //    triggerType: "change",
                    showLastLevels: false,
                    //   where: {
                    //       a: "aaa"
                    //   },
                    // value: ["B", "BB2", "BBB4"],
                    changeOnSelect: true,
                    success: function (data) {
                        orgs=data;
                        console.log(data);
                    }
                });
            }
        }
    });

    var getData2 = $.ajax({
        type:"get",
        url:'/nationalHighWay/highwayCascader',
        dataTType:"application/json;charset=UTF-8",
        success:function (res) {
            data = res.data;
            console.log(data);
            if(res.code == 0){
                cascader({
                    elem: "#b",
                    data: res.data,
                    showLastLevels: false,
                    changeOnSelect: true,
                    success: function (data) {
                        roads=data;
                        console.log(roads);
                        console.log(data);
                    }
                });
            }
        }
    });
    /**
     * 系统管理--部门管理
     */
    var PatrolResult = {
        tableId: "patrolResultTable",
        condition: {
            PatrolResultId: ""
        }
    };

    /**
     * 初始化表格的列
     */
    PatrolResult.initColumn = function () {
        return [[
            {field: 'zizeng',  title: '序号',type:'numbers'},
            {field: 'supervisor_Id',  title: '养护站id',type:'numbers',hide:true},
            // {field:'roadSectionName',title:'巡查路段',templet:function (d) {
            //         return d.nationalHighwayName+'-'+d.roadSectionName+"-"+d.beginStake
            //         +"~"+d.endStake
            //     }},
            {field:'nationalHighwayName',title:'路线'},
            {field:'roadSectionName',title:'路段'},
            {field:'beginEndStake',title:'桩',templet:function (d) {
                 return  d.beginStake +"~"+d.endStake}},
            {field: 'patrolResultId', hide: true, sort: true, title: 'id',templet:function (d) {
                    if (d.patrolResultId==1){
                        return 'ID：'+ d.patrolResultId +'，标题：<span style="color: #c00;">'+ d.title +'</span>'
                    }
                }},
            {field:'supervisorName',title:'巡查机构'},
            {field: 'name', title: '巡查员'},
            {field: 'beginTime', sort: true, title: '巡检时间',templet:function (d) {
                    return d.beginTime+"~"+d.endTime
                }},
            {field:'issueCount',title:'问题',templet:function (d) {
                    if(d.issueCount==null){
                        return '无'
                    }
                    else {
                        return d.issueCount+'处'
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
    PatrolResult.showInfo = function (data) {
        // window.location.href=Feng.ctxPath+'/roadhazard?patrolResultId='+data.patrolResultId;
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '病害信息',
            area: ['80%', '80%'],
            content: Feng.ctxPath+'/roadhazard?patrolResultId='+data.patrolResultId+"&supervisor_Id="+data.supervisor_Id+"&flag=1",
            end: function () {
                admin.getTempData('formOk') && table.reload(Dept.tableId);
            }
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + PatrolResult.tableId,
        url: Feng.ctxPath + '/patrolresult/deptList',
        page: true,
        limit:40,
        height: "full-200",
        cellMinWidth: 100,
        cols: PatrolResult.initColumn()
    });

    $('#btnClean').click(function () {
        roads.length=0;
        orgs.length=0;
        $('#inspectionDate').val(null);
        $('#patrollerName').val(null);
        window.location.reload();
    });

    $('#btnSearch').click(function () {
        var highwayId=null;
        var roadSectionId=null;
        var stakeId=null;
        var supervisorId=null;
        var patrolerName=null;
        var beginTime="1970-01-01 00:00:00";
        var endTime="3000-01-01 00:00:00";
        times=$('#inspectionDate').val().split("~");
        if (times.length==2) {
            beginTime = times[0];
            endTime = times[1];
        }
        if (roads.length==1){
            highwayId=roads[0];
        }
        if (roads.length==2){
            highwayId=roads[0];
            roadSectionId=roads[1];
        }
        if (roads.length==3){
            highwayId=roads[0];
            roadSectionId=roads[1];
            stakeId=roads[3];
        }
        if (orgs.length==1){
            pid=orgs[0];
        }
        if (orgs.length==2){
            supervisorId = orgs[1];
        }
        patrolerName = $('#patrollerName').val();
        var queryData = {};
        queryData['highwayId'] = highwayId;
        queryData['roadSectionId'] = roadSectionId;
        queryData['stakeId']=stakeId;
        queryData['beginTime']=beginTime;
        queryData['endTime']=endTime;
        queryData['patrolerName']=patrolerName;
        queryData['supervisorId']=supervisorId;
        queryData['pid']=pid;
        table.reload(PatrolResult.tableId, {where: queryData});
    });

    // 工具条点击事件
    table.on('tool(' + PatrolResult.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'showInfo') {
            PatrolResult.showInfo(data);
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
