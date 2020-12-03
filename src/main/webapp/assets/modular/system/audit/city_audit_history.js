layui.use(['table', 'admin', 'ax', 'ztree','laydate','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form=layui.form;

    var CityAuditHistory={
        tableId:"cityAuditHistoryTb",
        condition:{
            role:""
        }
    };

    CityAuditHistory.initColumn=function () {
        return [[
            {field: 'zizeng',  title: '序号',type:'numbers'},
            {field:'highway_name', title: '路线'},
            {field:'road_section_name', title: '路段'},
            {field:'position', title: '具体位置'},
            {field:'applicationId', title: '申请id',hide:true},
            {field:'roadHazardId',title:'病害id',hide:true},
            {field:'simple_name',title:'所属单位'},
            {field:'applyerName',title:'申请人'},
            {field: 'applyTime',  title: '申请时间'},
            {field: 'city_dept_head_time',  title: '审核时间'},

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


            // {field: 'zizeng',  title: '序号',type:'numbers'},
            // {field:'name',title:'审核人'},
            // {field:'status', title: '意见'},
            // {field:'feedback',title:'原因'},
            // {field: 'auditDate',  title: '时间'},
        ]]
    }


    /**
     * 点击编辑部门
     *
     * @param data 点击按钮时候的行数据
     */
    CityAuditHistory.audit = function (data) {
        window.location.href=Feng.ctxPath+'/application/cityAuditPage?audited=1&applicationId='+data.applicationId;
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CityAuditHistory.tableId,
        url: Feng.ctxPath + '/application/cityAuditHistory?role='+"市区审核人1",
        page: true,
        limit:40,
        height: "full-98",
        cellMinWidth: 100,
        cols: CityAuditHistory.initColumn()
    });

    // 工具条点击事件
    table.on('tool(' + CityAuditHistory.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'audit') {
            CityAuditHistory.audit(data);
        }
    });
})
