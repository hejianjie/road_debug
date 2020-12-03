layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    // admin.iframeAuto();

    $.ajax({
        type: "get",
        url: Feng.ctxPath + "/appraisal/getCostEvaluationDetail?id="+Feng.getUrlParam("id"),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            $("#name").val(data.name);
            $("#appraisal_time").val(data.appraisal_time);
            $("#cost_price").val(data.cost_price+'元');

        }
    });


});