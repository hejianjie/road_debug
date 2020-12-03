
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
    admin.iframeAuto();
    var nationalHighwayId=0;
    var stakeName,roadSectionId,stakeId,stakeLocation;
    stakeId=Feng.getUrlParam("stakeId");



    //初始化角色的详情数据
    $.ajax({
        type: "get",
        url: Feng.ctxPath + "/stake/slectStakeOne?stakeId="+stakeId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            stakeName=data[0].name;
            stakeLocation=data[0].stake_location;
            roadSectionId=data[0].road_section_id;
            nationalHighwayId=data[0].highway_id;
            $("#name").val(stakeName);
            $("#stakeLocation").val(stakeLocation);
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/stake/selectAllNationalHighway',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    $.each(data, function (index, item) {
                        $('#gd').append(new Option(item.nationalHighwayName, item.nationalHighwayId));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");
                    var select = 'dd[lay-value=' + nationalHighwayId + ']';
                    $('#gd').siblings("div.layui-form-select").find('dl').find(select).click();
                    layui.form.render("select");
                }
            });
        }
    });
    // 监听下拉框选择事件获取选中值
    form.on('select(gd)', function (data) {
        $("#ld").empty();
        nationalHighwayId= data.value;
        $.ajax({
            type: "get",
            url: Feng.ctxPath + '/stake/selectAllRoadSection?nationalHighwayId='+nationalHighwayId,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                $.each(data, function (index, item) {
                    $('#ld').append(new Option(item.roadSectionName, item.roadSectionId));// 下拉菜单里添加元素
                    $('#ld').val(roadSectionId)
                });
                layui.form.render("select");
            }
        });
    });
    form.on('select(ld)', function (data) {
        roadSectionId= data.value;
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        stakeName=data.field.name;
        stakeLocation=data.field.stakeLocation;
        var ajax = new $ax(Feng.ctxPath + "/stake/updateStake?stakeName="+stakeName+"&roadSectionId="+roadSectionId+"&stakeId="+stakeId+"&location="+stakeLocation, function (data) {
            Feng.success("修改成功!");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    });
});