
var roadUserInfoDlg = {
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

    var roadUserName,gender,age,telephone,account,password,password1,organizationId=-1;
    var departId,countyId;

    // 区县下拉框
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/road_section/getsupervisor1?userId=1',//暂时固定
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
        organizationId= data.value;
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
         roadUserName=data.field.name;
         gender=data.field.gender;
         age=data.field.age;
         telephone=data.field.telephone;
         account=data.field.account;
         flagAccount=1;

         var ajax1 = new $ax(Feng.ctxPath + '/sys/road_user/judgeAccount1?account='+account, function (data) {
          if(data>0)
          {
              flagAccount=0;
              Feng.error("账户已存在");
          }
            });

         ajax1.start();



          if(flagAccount==0)return;
          if(organizationId==-1){
               Feng.error("请选择部门");
          }
          else{
          var ajax = new $ax(Feng.ctxPath + "/sys/road_user/addInspector?roadUserName="+roadUserName+"&gender="+gender+"&age="+age+"&telephone="+telephone+"&organizationId="+organizationId+"&account="+account, function (data) {
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
          }


    });
});