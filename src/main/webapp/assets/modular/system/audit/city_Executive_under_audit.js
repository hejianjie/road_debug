layui.use(['table', 'admin', 'ax', 'ztree','laydate','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form=layui.form;

    var CityUnderAudit={
        tableId:"cityUnderAuditTb",
        condition:{
            role:""
        }
    };

    CityUnderAudit.initColumn=function () {
        return [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'zizeng',  title: '序号',type:'numbers'},
            {field:'highway_name', title: '路线'},
            {field:'road_section_name', title: '路段'},
            {field:'position', title: '具体位置'},
            {field:'applicationId', title: '申请id',hide:true},
            {field:'roadHazardId',title:'病害id',hide:true},
            {field:'simple_name',title:'所属单位'},
            {field: 'constructName',  title: '建设性质'},
            {field:'applyerName',title:'申请人'},
            {field: 'applyTime',  title: '申请时间'},
            {field:'status',title:'审核状态',templet:function (d) {
                    switch(d.status) {
                        case 1:
                            return "被否决"
                        case 2:
                            return "已填报"
                        case 3:
                            return "区县审核"
                        case 4:
                            return "区县审核"
                        case 5:
                            return "市区审核"
                        case 6:
                            return "市区审核"
                        case 7:
                            return "市区审核"
                        case 8:
                            return "施工中"
                        case 9:
                            return "施工结束"
                        case 10:
                            return "验收核量"
                        case 11:
                            return "验收核量"
                        case 12:
                            return "验收核量"
                        case 13:
                            return "已结束"


                    }
                    return d.beginTime+"~"+d.endTime
                }},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]]
    }

    /**
     * 点击编辑部门
     *
     * @param data 点击按钮时候的行数据
     */
    CityUnderAudit.audit = function (data) {
        window.location.href=Feng.ctxPath+'/application/cityAuditPage?audited=0&applicationId='+data.applicationId;
    };

    var options ={
        //elem: '#' + CityUnderAudit.tableId,
        url: Feng.ctxPath + '/application/cityExecutiveUnderAuditList',
        toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
        defaultToolbar:[],
        page: true,
        limit:40,
        height: "full-98",
        cellMinWidth: 100,
        cols: CityUnderAudit.initColumn()
    }

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CityUnderAudit.tableId,
        url: Feng.ctxPath + '/application/cityExecutiveUnderAuditList',
        toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
        defaultToolbar:[],
        page: true,
        limit:40,
        height: "full-98",
        cellMinWidth: 100,
        cols: CityUnderAudit.initColumn()
    });
    // 工具条点击事件
    table.on('tool(' + CityUnderAudit.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'audit') {
            CityUnderAudit.audit(data);
        }
    });
    //头工具栏事件
    table.on('toolbar(cityUnderAuditTb)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        var data = checkStatus.data;
        console.log(data);
        if(obj.event === 'batchProcessing')
        {
            if(data.length === 0)
            {
                layer.msg('尚未选择任何数据', {icon: 5});
            }else {
                var check=layer.confirm('请选择处理方式', {
                    btn: ['批量同意', '批量拒绝'] //按钮
                }, function () {
                    var comment="无意见";
                    // for(var i=0;i<data.length;i++)
                    // {
                    //     alert(i);
                    //     alert(data[i].applicationId);
                    // }
                    layer.prompt({title: '请输入审核意见，并确认', formType: 2}, function(text, index){
                        comment=text;
                        layer.close(index);
                        layer.close(check);
                        var layerload=layer.load(2, {shade: false});

                        for(var i=0;i<data.length;i++)
                        {
                            var ajax = new $ax(Feng.ctxPath + "/application/updateCityAuditResult?auditSuggestion=" + "同意" + "&feedback=" + comment + "&applicationId=" + data[i].applicationId+"&reff=0", function () {
                                Feng.success("审核成功");

                            }, function () {
                                Feng.error("审核失败！" + data.responseJSON.message)
                            });
                            ajax.set();
                            ajax.start();
                        }

                        layer.close(layerload);
                        table.reload(CityUnderAudit.tableId, options);

                    });

                }, function () {
                    var comment="无意见";
                    layer.prompt({title: '请输入审核意见，并确认', formType: 2}, function(text, index){
                        comment=text;
                        layer.close(index);
                        layer.close(check);
                        var layerload=layer.load(2, {shade: false});

                        for(var i=0;i<data.length;i++)
                        {
                            var ajax = new $ax(Feng.ctxPath + "/application/updateCityAuditResult?auditSuggestion=" + "否决" + "&feedback=" + comment + "&applicationId=" + data[i].applicationId+"&reff=0", function () {
                                Feng.success("审核成功");

                            }, function () {
                                Feng.error("审核失败！" + data.responseJSON.message)
                            });
                            ajax.set();
                            ajax.start();
                        }

                        layer.close(layerload);
                        table.reload(CityUnderAudit.tableId, options);

                    });
            })
        }

        // switch(obj.event){
        //     case 'batchProcessing':
        //         var data = checkStatus.data;
        //         console.log(data)
        //
        //         layer.alert(JSON.stringify(data));
        //         break;
        //     case 'getCheckData':
        //         var data = checkStatus.data;
        //         layer.alert(JSON.stringify(data));
        //         break;
        //     case 'getCheckLength':
        //         var data = checkStatus.data;
        //         layer.msg('选中了：'+ data.length + ' 个');
        //         break;
        //     case 'isAll':
        //         layer.msg(checkStatus.isAll ? '全选': '未全选');
        //         break;
        //
        // };
    }});
})
