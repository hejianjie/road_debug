layui.use(['table', 'admin', 'ax', 'ztree','laydate','form','layer','xmSelect'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form = layui.form;
    var layer = layui.layer;
    var xmSelect = layui.xmSelect;

    var third_party = [];
    var applicationId = $('#applicationId').val();


    var appraisal = {
        tableId: "appraisalList",
        // condition: {
        //     PatrolResultId: ""
        // }
    };
    layer.photos({
        photos:'.showImags'
        ,anim:5
    });

    appraisal.initColumn = function () {
        return [[
            {field: 'zizeng',  title: '序号',type:'numbers'},
            {field:'money',title:'评估价格',templet:function (d) {
                    if (d.cost_price){
                        return d.cost_price
                    }else{
                        return "未评估"
                    }
                }},
            {field: 'patrolResultId', hide: true, sort: true, title: 'id',templet:function (d) {
                    if (d.patrolResultId==1){
                        return 'ID：'+ d.patrolResultId +'，标题：<span style="color: #c00;">'+ d.title +'</span>'
                    }
                }},
            {field:'dept',title:'评估单位',templet:function (d) {

                    return d.name
                }},
            {field: 'time', sort: true, title: '评估时间',templet:function (d) {
                    if(d.appraisal_time)
                    {return d.appraisal_time}
                    else{
                        return "未评估"
                    }
                }},
        ]];
    };

    var tableResult = table.render({
        elem: '#' + appraisal.tableId,
        url: Feng.ctxPath + '/appraisal/getByApplicationId?applicationId='+applicationId,
        page:false,
        height: "200",
        cellMinWidth: 100,
        cols: appraisal.initColumn()
    });

})
