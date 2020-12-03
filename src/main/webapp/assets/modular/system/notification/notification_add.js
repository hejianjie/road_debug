layui.use(['layer', 'form', 'admin', 'ax','layedit','xmSelect'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var layedit = layui.layedit;
    // 让当前iframe弹层高度适应
    // admin.iframeAuto();
    var xmSelect = layui.xmSelect;

    let forward_to;
    var index=layedit.build('content'); //建立编辑器getEventInfo
    // 添加表单验证方法
    form.verify({
        content: function(value) {
            return layedit.sync(index);

        }
    });


// 通知单位下拉框赋值
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/notification/getDepartmentList',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            const arr = [];
            $.each(data, function (index, item) {
                // arr.append(new Option(item.full_name, item.dept_id));
                const dwoption = {};
                dwoption.name=item.full_name;
                dwoption.value=item.dept_id;
                arr.push(dwoption)
            });
            console.log(arr)
            //渲染多选
            var demo1 = xmSelect.render({
                el: '#forward_to',
                toolbar: {
                    show: true,
                },
                filterable: true,
                data: arr,
                on: function(data){
                    //arr:  当前多选已选中的数据
                    forward_to='';
                    const checkedOptions = data.arr;
                    for(var i=0;i<checkedOptions.length;i++){
                        forward_to+=checkedOptions[i].value
                        forward_to+=','
                    }
                    if(forward_to.length!=0){
                        forward_to=forward_to.substring(0,forward_to.length-1)
                    }
                }
            })
        }
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var obj={}
        obj.title = data.field.title;
        obj.content = data.field.content;
        obj.forward_to = forward_to;
        obj.status='0'
        console.log(obj)
        var ajax = new $ax(Feng.ctxPath + "/notification/insertNotification", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(obj);
        ajax.start();
    });

    form.on('submit(btnPublish)', function (data) {
        console.log(forward_to)
        if(forward_to){
            var obj={}
            obj.title = data.field.title;
            obj.content = data.field.content;
            obj.forward_to = forward_to;
            obj.status='1'
            console.log(obj)
            var ajax = new $ax(Feng.ctxPath + "/notification/insertNotification", function (data) {
                Feng.success("下发成功！");

                //传给上个页面，刷新table用
                admin.putTempData('formOk', true);

                //关掉对话框
                admin.closeThisDialog();
            }, function (data) {
                Feng.error("下发失败！" + data.responseJSON.message)
            });
            ajax.set(obj);
            ajax.start();
        }else{
            Feng.error('没有选择下发单位，下发失败')
            admin.closeThisDialog();
        }

    });
});