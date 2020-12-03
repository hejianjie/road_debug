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
    var html_decode= function (str) {
        var s = "";
        if (str.length == 0) return "";
        s = str.replace(/&amp;/g, "&");
        s = s.replace(/&lt;/g, "<");
        s = s.replace(/&gt;/g, ">");
        s = s.replace(/& lt;/g, "<");
        s = s.replace(/& gt;/g, ">");
        s = s.replace(/&nbsp;/g, " ");
        s = s.replace(/&#39;/g, "\'");
        s = s.replace(/&quot;/g, "\"");
        s = s.replace(/<br\/>/g, "\n");
        return s;
    };

    // 添加表单验证方法
    form.verify({
        content: function(value) {
            return layedit.sync(index);

        }
    });
    var content;
    var id=Feng.getUrlParam("id");
    $.ajax({
        type: "get",
        url: Feng.ctxPath + "/notification/getNotificationById?id="+Feng.getUrlParam("id"),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            content=data.content;
            if(data.forward_to){
                forward_to=data.forward_to.split(',')
            }
            $("#title").val(data.title)
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/notification/getDepartmentList',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    const arr = [];
                    $.each(data, function (index, item) {
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
                        initValue: forward_to,
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
            var s= html_decode(content);
            layedit.setContent(index,s,false);
        }
    });




    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var obj={};
        obj.id=id;
        obj.title = data.field.title;
        obj.content = data.field.content;
        obj.forward_to = forward_to;
        obj.status='0'
        var ajax = new $ax(Feng.ctxPath + "/notification/updateNotification", function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set(obj);
        ajax.start();
    });

    form.on('submit(btnPublish)', function (data) {
        if(forward_to){
            var obj={};
            obj.id=id;
            obj.title = data.field.title;
            obj.content = data.field.content;
            obj.forward_to =forward_to;
            obj.status='1'
            var ajax = new $ax(Feng.ctxPath + "/notification/updateNotification", function (data) {
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