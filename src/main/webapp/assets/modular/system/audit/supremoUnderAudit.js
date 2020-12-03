layui.use(['table', 'admin', 'ax', 'ztree','laydate','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form=layui.form;

    var SupremoUnderAudit={
        tableId:"supremoUnderAuditTb",
        condition:{
            role:""
        }
    };

    SupremoUnderAudit.initColumn=function () {
        return [[
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
    // SupremoUnderAudit.agree = function (data) {
    //     var ajax = new $ax(Feng.ctxPath+'/application/updateSupremoStatus?status='+'同意&'+'applicationId='+data.applicationId, function () {
    //         Feng.success("审核成功");
    //     }, function () {
    //         Feng.error("审核失败！" + data.responseJSON.message)
    //     });
    //     ajax.set();
    //     ajax.start();
    //     //window.location.href=Feng.ctxPath+'/application/updateSupremoStatus?status='+'同意&'+'applicationId='+data.applicationId;
    // };
    //
    // SupremoUnderAudit.disagree = function (data) {
    //     var ajax = new $ax(Feng.ctxPath+'/application/updateSupremoStatus?status='+'否决&'+'applicationId='+data.applicationId, function () {
    //         Feng.success("审核成功");
    //     }, function () {
    //         Feng.error("审核失败！" + data.responseJSON.message)
    //     });
    //     ajax.set();
    //     ajax.start();
    //     //window.location.href=Feng.ctxPath+'/application/updateSupremoStatus?status='+'否决&'+'applicationId='+data.applicationId;
    // };

    SupremoUnderAudit.audit = function (data) {
        window.location.href=Feng.ctxPath+'/application/supremoAuditPage?applicationId='+data.applicationId;
    };

    $('#autoAudit').click(function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '设置金额',
            content: Feng.ctxPath+'/supremoLimitMoney/autoAuditPage',
            end: function () {
                admin.getTempData('formOk') && table.reload(SupremoUnderAudit.tableId);
            }
        });
    })

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + SupremoUnderAudit.tableId,
        url: Feng.ctxPath + '/application/supremoUnderAuditList',
        page: true,
        limit:40,
        height: "full-98",
        cellMinWidth: 100,
        cols: SupremoUnderAudit.initColumn()
    });
    // 工具条点击事件
    table.on('tool(' + SupremoUnderAudit.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'audit') {
            SupremoUnderAudit.audit(data);
        }
    });
})
