layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    $("#nationalStatus").each(function() {
        // this代表的是<option></option>，对option再进行遍历


        var msg = $('#highwayStatus').val();

console.log(msg)
        $(this).children("option").each(function() {
            // 判断需要对那个选项进行回显
            if (this.value == msg) {
                console.log($(this).text());
                // 进行回显
                $(this).attr("selected","selected");
            }
        });

        form.render('select');
    })


    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/nationalHighWay/update", function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});