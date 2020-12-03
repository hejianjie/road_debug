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
        url: Feng.ctxPath + "/notification/getNotificationById?id="+Feng.getUrlParam("id"),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $("#title").val(data.title);
            $("#content").html(data.content);
        }
    });


});