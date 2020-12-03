
var roadSectionInfoDlg = {
    data: {
        rid: "",
        rName: ""
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
    var roadSectionName,start,end,overallLength,nationalHighwayId,supervisorId=-1;
    var roadSectionId=Feng.getUrlParam("roadSectionId");
    console.log(roadSectionId);
    var departId,countyId;


     $.ajax({
            type: "get",
            url: Feng.ctxPath + '/road_section/getOneSection?roadSectionId='+roadSectionId,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (data){
                 $("#name").val(data[0].name);
                 $("#start").val(data[0].begin_at);
                 $("#end").val(data[0].end_at);
                 form.render();
            }
        })

    // 区县下拉框
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/road_section/getsupervisor1',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            $.each(data, function (index, item) {
                $('#qx').append(new Option(item.supervisorName, item.supervisorId));// 下拉菜单里添加元素
            });
            layui.form.render("select");
        }
    });



    // 监听下拉框选择事件获取选中值
    form.on('select(qx)', function (data) {
         countyId= data.value;
        $.ajax({
            type: "get",
            url: Feng.ctxPath + '/road_section/getsupervisor2?countyId='+countyId,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                $('#s').empty();
                $('#s').append(new Option("请选择", -1));
                $.each(data, function (index, item) {
                    $('#s').append(new Option(item.supervisorName, item.supervisorId));// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        });
    });
    form.on('select(s)', function (data) {
        console.log(data.value);
        supervisorId= data.value;
    });


    //国道下拉框
     $.ajax({
            type: "get",
            url: Feng.ctxPath + '/stake/selectAllNationalHighway',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                //$('#s').reset();
                $.each(data, function (index, item) {
                    $('#gd').append(new Option(item.nationalHighwayName, item.nationalHighwayId));// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        });

     form.on('select(gd)', function (data) {
            nationalHighwayId= data.value;
            });
    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
         start=data.field.start;
         end=data.field.end;
         roadSectionName=start+'~'+end+'公里';
         overallLength=end-start;
         if(overallLength<=0||supervisorId<0){
                   Feng.error("数据有误！");
          }
          else{
          var ajax = new $ax(Feng.ctxPath + "/road_section/update?name="+roadSectionName+"&overallLength="+overallLength+"&beginAt="+start+"&endAt="+end+"&supervisorId="+supervisorId+"&nationalHighwayId="+nationalHighwayId+"&roadSectionId="+roadSectionId, function (data) {
                      Feng.success("修改成功！");

                      //传给上个页面，刷新table用
                      admin.putTempData('formOk', true);

                      //关掉对话框
                      admin.closeThisDialog();
                  }, function (data) {
                      Feng.error("修改失败！" + data.responseJSON.message)
                  });
                  // ajax.set(data.field);
                  ajax.start();
          }


    });
});