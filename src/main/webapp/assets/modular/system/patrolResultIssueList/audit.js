layui.use(['table', 'admin', 'ax', 'ztree','layer'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var layer = layui.layer;


    $('#submit').click(function () {
        console.log("hhhhhhhhhh");
        var roadHazardId=$('#roadHazardId').val();
        var status=$('#opinion input[name="t"]:checked ').val()//获取选中的值
        if (status==null||status==""){
            layer.open({
                title:"提示",
                content:"审核意见不能为空"
            })
        }
        else{
            console.log(roadHazardId);
            console.log(status);
            var ajax = new $ax(Feng.ctxPath + "/roadhazard/updateStatus?status=" + status + "&roadHazardId=" + roadHazardId, function () {
                Feng.success("审核成功");
                admin.putTempData('formOk', true);
                admin.closeThisDialog();
            }, function () {
                Feng.error("审核失败！" + data.responseJSON.message)
            });
            ajax.set();
            ajax.start();
        }
    })
});
