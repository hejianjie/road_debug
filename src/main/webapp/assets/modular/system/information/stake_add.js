
var StakeInfoDlg = {
    data: {
        sid: "",
        sName: ""
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

    var nationalHighwayId=0;
    var roadSectionId=0;
    var stakeName,stakeLocation;
    // 下拉框
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/stake/selectAllNationalHighway',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $("#gd").append(new Option('请选择国道'))
            $.each(data, function (index, item) {
                $('#gd').append(new Option(item.nationalHighwayName, item.nationalHighwayId));// 下拉菜单里添加元素
            });
            console.log($('#ld'));
            layui.form.render("select");
        }
    });

    // 监听下拉框选择事件获取选中值
    form.on('select(gd)', function (data) {
        $("#ld").empty();
        $("#ld").append(new Option('请选择路段'))
         nationalHighwayId= data.value;
        $.ajax({
            type: "get",
            url: Feng.ctxPath + '/stake/selectAllRoadSection?nationalHighwayId='+nationalHighwayId,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                $.each(data, function (index, item) {
                    $('#ld').append(new Option(item.roadSectionName, item.roadSectionId));// 下拉菜单里添加元素
                    // $('#ld').val(roadSectionId)
                });
                console.log($('#ld'))
                layui.form.render("select");
            }
        });
    });
    form.on('select(ld)', function (data) {
        console.log(data.value);
        roadSectionId= data.value;
    });
    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
         stakeName=data.field.name;
        stakeLocation=data.field.stakeLocation;
        var ajax = new $ax(Feng.ctxPath + "/stake/addStake?stakeName="+stakeName+"&roadSectionId="+roadSectionId+"&location="+stakeLocation, function (data) {
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