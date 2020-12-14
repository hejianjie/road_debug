layui.use(['table', 'admin', 'ax', 'ztree','laydate','form','layer'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form=layui.form;
    var layer=layui.layer;
    var auditSuggestion="";
    var applicationId=$('#application').val();
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
                    console.log("哈哈哈哈哈")
                    var ajax = new $ax(Feng.ctxPath + "/application/updateSupremoStatus?status=" + auditSuggestion + "&applicationId=" + applicationId, function () {
                        Feng.success("审核成功");
                        setTimeout(function () {
                            window.location.href = Feng.ctxPath + "/application/supremoUnderAuditPage"
                        },1500)
                    }, function () {
                        Feng.error("审核失败！" + data.responseJSON.message)
                    });
                    ajax.set();
                    ajax.start();
                }
            }
            if (auditSuggestion == "同意") {
                console.log(comment)
                var ajax = new $ax(Feng.ctxPath + "/application/updateSupremoStatus?status=" + auditSuggestion + "&applicationId=" + applicationId, function () {
                    Feng.success("审核成功");
                    setTimeout(function () {
                        window.location.href = Feng.ctxPath + "/application/supremoUnderAuditPage"
                    },1500)
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
})
