
var roadSectionInfoDlg = {
    data: {
        rid: "",
        rName: ""
    }
};

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    // admin.iframeAuto();

    var name,account

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        name=data.field.name;
        account=data.field.account;
        var ajax = new $ax(Feng.ctxPath + "/sys_user/add?name="+name+"&account="+account, function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        // ajax.set(data.field);
        ajax.start();

    });
});