layui.use(['table', 'admin', 'ax', 'ztree','laydate','form','layer','xmSelect'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form=layui.form;
    var layer=layui.layer;
    var xmSelect = layui.xmSelect;

    var third_party =[];
    var applicationId=$('#application').val();


    var appraisal = {
        tableId: "appraisalList",
        // condition: {
        //     PatrolResultId: ""
        // }
    };
    /**
     * 初始化表格的列
     */
    // appraisal.initColumn = function () {
    //     return [[
    //         {field: 'zizeng',  title: '序号',type:'numbers'},
    //         {field:'id',hide:true,title:'id'},
    //         {field:'money',title:'评估价格',templet:function (d) {
    //             if (d.cost_price){
    //                 return d.cost_price
    //             }else{
    //                 return "未评估"
    //             }
    //             }},
    //         {field: 'patrolResultId', hide: true, sort: true, title: 'id',templet:function (d) {
    //                 if (d.patrolResultId==1){
    //                     return 'ID：'+ d.patrolResultId +'，标题：<span style="color: #c00;">'+ d.title +'</span>'
    //                 }
    //             }},
    //         {field:'dept',title:'评估单位',templet:function (d) {
    //
    //                 return d.name
    //             }},
    //         {field: 'time', sort: true, title: '评估时间',templet:function (d) {
    //             if(d.appraisal_time)
    //                 {return d.appraisal_time}
    //                 else{
    //                 return "未评估"
    //             }
    //             }},
    //         {align: 'center', toolbar: '#tableBar1', title: '操作', minWidth: 200}
    //
    //     ]];
    // };

    var tableResult = table.render({
        elem: '#' + appraisal.tableId,
        url: Feng.ctxPath + '/appraisal/getByApplicationId?applicationId='+applicationId,
        page:false,
        height: "200",
        cellMinWidth: 100,
        cols: appraisal.initColumn()
    });

    appraisal.onDeleteNotice = function (data) {

        var id = data.id;
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/CostEvaluationCompany/deleteByApplicationId?id="+id, function (data) {
                Feng.success("删除成功!");

                table.reload(appraisal.tableId);
            }, function (data) {
                // Feng.error("删除失败!" + data.responseJSON.message + "!");
                Feng.error(data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除 " +"?", operation);
    };

    // 第三方造价评估单位下拉框赋值
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/CostEvaluationCompany/getCostEvaluationCompany',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            const arr = [];
            $.each(data, function (index, item) {
                // arr.append(new Option(item.full_name, item.dept_id));
                const dwoption = {};
                dwoption.name=item.name
                dwoption.value=item.user_id
                arr.push(dwoption)
            });
            console.log(arr)
            //渲染多选
            var demo1 = xmSelect.render({
                el: '#forward_to',
                toolbar: {
                    show: true,
                },
                filterable: true,
                data: arr,
                on: function(data){
                    //arr:  当前多选已选中的数据
                    forward_to='';
                    const checkedOptions = data.arr;
                    third_party.splice(0,third_party.length);
                    console.log(checkedOptions)
                    for(var i=0;i<checkedOptions.length;i++){
                        third_party.push(checkedOptions[i].value);
                        forward_to+=checkedOptions[i].value
                        forward_to+=','
                    }
                    if(forward_to.length!=0){
                        forward_to=forward_to.substring(0,forward_to.length-1)
                    }
                }
            })
        }
    });
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/CostEvaluationCompany/getByApplicationId',
        dataType: 'json',
        data:{applicationId:applicationId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            if(data.length ===0){
                $("#appraisalList").hide();
            }else{
//                 $.ajax({
//                     type: "get",
//                     url: Feng.ctxPath + '/appraisal/getByApplicationId',
//                     dataType: 'json',
//                     data:{applicationId:applicationId},
//                     contentType: "application/json; charset=utf-8",
//                     success: function (data1) {
//                         console.log(data1)
//                         if(data1.length ===0){
// /////////未评估
//                         }else{
//                             for(let i=0;i<data1.length;i++){
//                                 console.log(data1);
//                             }
//                         }
//                     }
//                 })
            }
        }
    })

    var auditSuggestion="";
    form.on('select(auditSuggestion)',function (data) {
        console.log(data.value);
        if (data.value!=""){
            $("#commit").removeClass("layui-btn-disabled")
        }
        else {
            $("#commit").addClass("layui-btn-disabled")
        }
        auditSuggestion=data.value;
    });

    $('#third_party').click(function () {
        for(var i=0;i<third_party.length;i++){
            var ajax = new $ax(Feng.ctxPath + "/CostEvaluationCompany/addCostEvaluationCompany?third_party_id="+third_party[i]+"&application_id="+applicationId, function (data) {
                location.reload();
                Feng.success("指派成功！");
            }, function (data) {
                Feng.error("指派失败！" + data.responseJSON.message)
            });
            // ajax.set(data.field);
            ajax.start();
        }
    })

    $('#commit').click(function () {
        var comment=$('#feedback').val();
        console.log(comment);
        if (auditSuggestion==""){
            layer.open({
                title:"提示",
                content:"审核意见不能为空"
            })
        }
        else {
            if (auditSuggestion != "同意") {
                if (comment == null || comment == "") {
                    layer.open({
                        title: "提示",
                        content: "请输入原因"
                    })
                } else {
                    var ajax = new $ax(Feng.ctxPath + "/application/updateCityAuditResult?auditSuggestion=" + auditSuggestion + "&feedback=" + comment + "&applicationId=" + applicationId+"&reff=0", function () {
                        Feng.success("审核成功");

                    }, function () {
                        Feng.error("审核失败！" + data.responseJSON.message)
                    });
                    ajax.set();
                    ajax.start();
                }
            }
            if (auditSuggestion == "同意") {
                console.log(comment)
                var ajax = new $ax(Feng.ctxPath + "/application/updateCityAuditResult?auditSuggestion=" + auditSuggestion + "&feedback=" + comment + "&applicationId=" + applicationId+"&reff=0", function () {
                    Feng.success("审核成功");

                }, function () {
                    Feng.error("审核失败！" + data.responseJSON.message)
                });
                ajax.set();
                ajax.start();
                //window.location.href=Feng.ctxPath+'/application/updateCityAuditResult?auditSuggestion='+auditSuggestion+'&feedback='+comment;
            }
        }
    })
    // var CityAudit={
    //     tableId:cityAuditHistoryTb
    // }
    //
    // CityAudit.initColumn=function () {
    //     return [[
    //         {field: 'zizeng',  title: '序号',type:'numbers'},
    //         {field: 'status',  title: '审核意见',templet:function () {
    //                 if (d.status==1){
    //                     return "同意"
    //                 }
    //                 if (d.status==2){
    //                     return "否决"
    //                 }
    //                 if (d.status==3){
    //                     return "待定"
    //                 }
    //             }},
    //         {field: 'auditorOpinion',  title: '原因'},
    //         {field: 'cityAuditoDate',  title: '审核日期'},
    //     ]]
    // }
    appraisal.showInfo = function (data) {
        console.log(data.id)
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '评估详情',
            area:['600px', '600px'],
            content: Feng.ctxPath + '/application/getCostEvaluationDetail?id='+data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Notice.tableId);
            }
        });
    };

    table.on('tool(' + appraisal.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            appraisal.onEditNotice(data);
        } else if (layEvent === 'delete') {
            console.log(obj);
            appraisal.onDeleteNotice(data);
        }else if (layEvent === 'showInfo') {
            appraisal.showInfo(data);
        }
    });
})
