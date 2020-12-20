layui.use(['layer', 'form', 'table', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    var $ = layui.$;
        //渲染时间选择框
        laydate.render({
            elem: '#yearMonth',
            type: 'month',
            max: Feng.currentDate()
        });
    $("#exportBtn").click(function () {
        var time = $("#yearMonth").val() + "-01";

        if (time.length == 0) {
            layer.open({
                title: "提示",
                content: "请选择时间"
            })
        } else {
            window.location.href = Feng.ctxPath + "/export/exportSummaryOfMinorRepair?yearMonth=" + time;
        }

    });
});
