layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();
    var id=Feng.getUrlParam("id");
    $.ajax({
        type: "get",
        url: Feng.ctxPath + "/hazard_base_info/getOne?id="+id,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
             $("#constructTypeName").val(data[0].name)
        }
    })
    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        let name=data.field.constructTypeName;
        var ajax = new $ax(Feng.ctxPath + "/hazard_base_info/update?id="+id, function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set("name",name);
        ajax.start();
    });
});