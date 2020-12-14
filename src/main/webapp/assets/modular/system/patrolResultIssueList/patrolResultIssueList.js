
layui.use(['table', 'admin', 'ax', 'ztree','layer'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var layer=layui.layer;
    var patrolResultId=$("#patrolResultId").val();
    var issueInfosVal=$("#issueInfosVal").val();
    /**
     * 系统管理--部门管理
     */
    var PatrolResultIssueList = {
        tableId: "patrolResultIssueListTable",
        condition: {
            PatrolResultIssueList: ""
        }
    };

    layer.photos({
        photos:'.showImags'
        ,anim:5
    });

    var onSubmitPlan=function(pid){
        // console.log("pid");
        // console.log(pid);
        // var selectId=pid+'select';
        // var inputhazardId=pid+'inroadHazardId';
        // console.log($("#"+selectId).val());
        // console.log($("#"+inputhazardId).val());
        // var roadHazardId=$("#"+inputhazardId).val();
        // var status=$("#"+selectId).val();
        // if (status==null||status==""){
        //     layer.open({
        //         title:"提示",
        //         content:"审核意见不能为空"
        //     })
        // }
        // else {
        //
        //     var ajax = new $ax(Feng.ctxPath + "/roadhazard/updateStatus?status=" + status + "&roadHazardId=" + roadHazardId, function () {
        //         Feng.success("审核成功");
        //     }, function () {
        //         Feng.error("审核失败！" + data.responseJSON.message)
        //     });
        //     ajax.set();
        //     ajax.start();
        // }
        var inputhazardId=pid+'roadHazardId';
        var roadHazardId=$("#"+inputhazardId).val();
        console.log(roadHazardId);
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '审核',
            area: ['30%', '30%'],
            content: Feng.ctxPath+'/roadhazard/auditPage?roadHazardId='+roadHazardId,
            end: function () {
                location.reload();
            }
        });
    };

    var onShow=function(hid){
        var inputhazardId=hid+'roadHazardId';
        console.log(hid)

        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '审核进度',
            area: ['800px', '600px'],
            content: Feng.ctxPath+'/flow?roadHazardId='+hid,
            end: function () {
                admin.getTempData('formOk')
            }
        });
    };



    $(".btnSubmit").click(function () {
        var pid=$(this).data("pid");
        console.log(pid);
        onSubmitPlan(pid);
    });

    $(".showPro").click(function () {
        var hid=$(this).data("hid");
        onShow(hid);
    });

    // /**
    //  * 初始化表格的列
    //  */
    // PatrolResultIssueList.initColumn = function () {
    //     return [[
    //         {field: 'zizeng',  title: '序号',type:'numbers'},
    //         {field: 'patrolResultId',hide:true, sort: true, title: 'pid'},
    //         {field: 'roadHazardId', sort: true, title: '病害编号',templet:'<div><a href="/roadhazard/issuedetail?roadHazardId={{d.roadHazardId}}" class="layui-table-link">{{d.roadHazardId}}</a></div>'},
    //         {field: 'position', sort: true, title: '病害位置'},
    //         {field: 'hazardStatus', sort: true, title: '病害情况'},
    //         {field: 'sizeType', sort: true, title: '尺寸类型'},
    //         {field: 'specificSize', sort: true, title: '具体尺寸'},
    //         {field: 'potentialHazard', sort: true, title: '安全隐患'},
    //         {field: 'detectTime', sort: true, title: '提交时间'},
    //         {field: 'status', sort: true, title: '状态',templet:function (d) {
    //                 if (d.status==0){
    //                     return '<span style="color: #ff171c">'+'未审核'+'</span>'
    //                 }
    //                 else
    //                     return '<span style="color: #00A65A">'+'已审核'+'</span>'
    //             }},
    //     ]];
    // };
    //
    //
    // // 渲染表格
    // var tableResult = table.render({
    //     elem: '#' + PatrolResultIssueList.tableId,
    //     url: Feng.ctxPath + '/roadhazard/list?patrolResultId='+patrolResultId,
    //     page: true,
    //     height: "full-98",
    //     cellMinWidth: 100,
    //     cols: PatrolResultIssueList.initColumn()
    // });

});
