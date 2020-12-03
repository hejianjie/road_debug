layui.use(['table', 'admin', 'ax', 'ztree','laydate','form',"cascader"], function () {
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
    var orgs=[];
    var pid=null;
    var hazardStatusId=null;
    var getData = $.ajax({
        type:"get",
        url:Feng.ctxPath + '/supervisor/all',
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
        url:Feng.ctxPath + '/nationalHighWay/highwayCascader',
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

    var getData3 = $.ajax({
        type:"get",
        url:Feng.ctxPath + '/hazard_base_info/selectCascaderAll',
        dataTType:"application/json;charset=UTF-8",
        success:function (res) {
            data = res.data;
            console.log(data);
            if(res.code == 0){
                cascader({
                    elem: "#hazardStatus",
                    data: res.data,
                    showLastLevels: false,
                    changeOnSelect: true,
                    success: function (data) {
                        //roads=data;
                        //console.log(roads);
                        console.log(data);
                        hazardStatusId=data[0];
                    }
                });
            }
        }
    });

    var CityUnderAudit={
        tableId:"cityUnderAuditTb",
        condition:{
            role:""
        }
    };

    //日期
    laydate.render({
        elem:'#inspectionDate',
        type:'datetime',
        range:'~',
        format:'yyyy-MM-dd'
    });

    CityUnderAudit.initColumn=function () {
        return [[
            {field: 'zizeng',  title: '序号',type:'numbers'},
            // {field:'roadSectionName',title:'巡查路段',templet:function (d) {
            //         return d.nationalHighwayName+'-'+d.roadSectionName+"-"+d.beginStake
            //             +"~"+d.endStake
            //     }},
            {field:'nationalHighwayName',title:'路线'},
            {field:'roadSectionName',title:'路段'},
            {field:'position',title:'具体位置'},
            {field:'supervisorName',title:'巡查机构'},
            // {field: 'patrolResultId', hide: true, sort: true, title: 'id',templet:function (d) {
            //         if (d.patrolResultId==1){
            //             return 'ID：'+ d.patrolResultId +'，标题：<span style="color: #c00;">'+ d.title +'</span>'
            //         }
            //     }},
            {field:'road_hazard_id',title:'病害id',hide:true},
            {field:'position',hide:true,title:'位置'},
            {field: 'hazardStatus', title: '病害情况'},
            {field:'detect_time',title:'发现时间'},
            {field:'dateTime',title:'计划完成时间',templet:function (d) {
                    if (d.dateTime==null){
                        return '暂无申请信息'
                    }
                    else{
                        return d.dateTime
                    }
                }},
            // {field:''}
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]]
    }


    $('#btnClean').click(function () {
        roads.length=0;
        orgs.length=0;
        $('#inspectionDate').val(null);
        $('#patrollerName').val(null);
        hazardStatusId=null;
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
        patrolerName = null;
        var queryData = {};
        queryData['highwayId'] = highwayId;
        queryData['roadSectionId'] = roadSectionId;
        queryData['stakeId']=stakeId;
        queryData['beginTime']=beginTime;
        queryData['endTime']=endTime;
        queryData['patrolerName']=patrolerName;
        queryData['supervisorId']=supervisorId;
        queryData['hazardStatusId']=hazardStatusId;
        queryData['pid']=pid;
        table.reload(CityUnderAudit.tableId, {where: queryData});
    });

    CityUnderAudit.audit = function (data) {
        console.log(Feng.ctxPath)
        //window.location.href=Feng.ctxPath+'/roadhazard/singleissuedetail?roadHazardId='+data.road_hazard_id;
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '病害信息',
            area: ['80%', '80%'],
            content: Feng.ctxPath+'/roadhazard/singleissuedetail?roadHazardId='+data.road_hazard_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Dept.tableId);
            }
        });
    };

    CityUnderAudit.onShow=function(data){
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '审核进程',
            area: ['800px', '600px'],
            content: Feng.ctxPath+'/flow?roadHazardId='+data.road_hazard_id,
            end: function () {
                admin.getTempData('formOk')
            }
        });
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CityUnderAudit.tableId,
        url: Feng.ctxPath + '/roadhazard/selectAllRoadHazard',
        page: true,
        limit:40,
        height: "full-200",
        cellMinWidth: 100,
        cols: CityUnderAudit.initColumn()
    });
    // 工具条点击事件
    table.on('tool(' + CityUnderAudit.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'audit') {
            CityUnderAudit.audit(data);
        }
        if(layEvent==='showPro')
        {
            console.log("hhhhhh")
            CityUnderAudit.onShow(data);
        }
    });
})
