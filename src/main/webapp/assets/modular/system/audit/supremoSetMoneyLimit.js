layui.use(['table', 'admin', 'ax', 'ztree','laydate','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form=layui.form;

    $('#autoAudit').click(function () {
        var limitMoney=$('#maneyLimit').val();
        var ajax = new $ax(Feng.ctxPath + "/supremoLimitMoney/updateOrInsert?money=" + limitMoney , function () {
            Feng.success("设置成功");
            admin.putTempData('formOk', true);
            admin.closeThisDialog();
        }, function () {
            Feng.error("审核失败！" + data.responseJSON.message)
        });
        ajax.set();
        ajax.start();
    })
})
