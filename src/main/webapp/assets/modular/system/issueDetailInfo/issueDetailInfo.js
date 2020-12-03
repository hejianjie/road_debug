layui.use(['table', 'admin', 'ax', 'ztree','laytpl','layer'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var layer=layui.layer;

    layer.photos({
        photos:'#showImags'
        ,anim:5
    });
});
