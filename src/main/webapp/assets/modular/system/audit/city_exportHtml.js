layui.use(['layer', 'form', 'table', 'ztree', 'laydate', 'admin', 'ax', 'selectM'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    const selectM = layui.selectM;
    var $ = layui.$;
    var resTag;


    function createSelectM(domId, data, inputName, selected=[],isRequired=true, idName, titleName, max) {
        var options = {
            //元素容器【必填】
            elem: domId
            //候选数据【必填】
            , data: data
            //默认选中的值
            , selected: selected
            //最多选中个数，默认5
            , max: max
            //input的name 不设置与选择器相同(去#.)
            , name: inputName,
            tips: '请选择'
            //值的分隔符
            , delimiter: ','
            //候选项数据的键名
            , field: {idName: idName, titleName: titleName}
        };
        if(isRequired==true){
            // options = Object.assign({verify: 'required'}, options);
            options.verify='required';
        }
        return selectM(options);
    }

    $.ajax({
        type:"get",
        url:Feng.ctxPath +'/national_highway/list',
        success:function (data) {
            const resData = data;
            resTag = createSelectM("#nationHighway", resData, "tag3", [],true,"highwayId","highwayName",resData.length);
        }
    });

    //渲染时间选择框
    laydate.render({
        elem: '#yearMonth',
        type: 'month',
        max: Feng.currentDate()
    });

    $("#exportBtn").click(function () {
        var time = $("#yearMonth").val() + "-01";
        const nationHighway = resTag.values;
        if (time.length == 0) {
            layer.open({
                title: "提示",
                content: "请选择时间"
            })
        } else if (nationHighway.length == 0) {
            layer.open({
                title: "提示",
                content: "请选择路线"
            })
        } else {
            window.location.href = Feng.ctxPath + "/export/exportSummaryOfMinorRepair?yearMonth=" + time + "&nationHighway=" + nationHighway;
        }

    });
});
