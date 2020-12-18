layui.use(['layer', 'form', 'table', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    var $ = layui.$;
    layer.open({
            title: "请选择年月",
            content: "<div class=\"layui-inline\">\n" +
                "                                <input id=\"yearMonth\" class=\"layui-input\" type=\"text\" placeholder=\"选择时间\"/>\n" +
                "                            </div>",
            btn: ["确定", "取消"],
            yes: function (index, layero) {
                var time = $("#yearMonth").val();
                window.location.href = Feng.ctxPath + "/export/exportSummaryOfMinorRepair?yearMonth=" + time;
                layer.close(index);
            },
            btn2: function (index, layero) {
                layer.close(index);
            }
        })
        //渲染时间选择框
        laydate.render({
            elem: '#yearMonth',
            max: Feng.currentDate()
        });
});
