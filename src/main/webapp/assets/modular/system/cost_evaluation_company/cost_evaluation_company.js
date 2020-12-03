layui.use(['layer', 'form', 'table', 'admin', 'ax','formSelects', 'laydate'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var title='';
    /**
     * 系统管理--消息管理
     */
    var ThirdParty = {
        tableId: "thirdPartyTable"    //表格id
    };
    /**
     * 初始化表格的列
     */
    ThirdParty.initColumn = function () {
        return [[
            {title: '编号',align: 'center',type:'numbers'},
            {field: 'name', sort: true, title: '第三方造价评估公司名称'},
            {field: 'account', sort: true, title: '第三方造价评估公司账号'},
            {field: 'email', sort: true, title: '邮箱'},
            {field: 'phone', sort: true, title: '电话'},
            {field: 'user_id', hide:true,sort: true, title: '第三方造价评估公司id'},

        ]];
    };

    /**
     * 点击查询按钮
     */
    ThirdParty.search = function () {
        title=$("#thirdPartyName").val();
        table.render({
            elem: '#' + ThirdParty.tableId,
            url: Feng.ctxPath + '/CostEvaluationCompany/getCompanyList?companyName='+title,
            page: true,
            height: "full-98",
            cellMinWidth: 100,
            cols: ThirdParty.initColumn()
        });
    };

  

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ThirdParty.tableId,
        url: Feng.ctxPath + '/CostEvaluationCompany/getCompanyList',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: ThirdParty.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ThirdParty.search();
    });

 
});
