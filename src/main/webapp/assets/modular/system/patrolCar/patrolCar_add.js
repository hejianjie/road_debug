layui.use(['layer', 'form', 'admin', 'ax','layedit','xmSelect'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var layedit = layui.layedit;
    var title;
    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        title = data.field.title;

        var ajax = new $ax(Feng.ctxPath + "/patrolcar/insertCar?number="+title, function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error(data.responseJSON.message)
        });
        ajax.start();
    });


});