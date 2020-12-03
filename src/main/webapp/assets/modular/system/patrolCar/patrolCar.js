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
    var Car = {
        tableId: "carTable"    //表格id
    };
    /**
     * 初始化表格的列
     */
    Car.initColumn = function () {
        return [[
            {title: '编号',align: 'center',type:'numbers'},
            {align: 'center',field: 'car_number', sort: true, title: '车牌号'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Car.search = function () {
        title=$("#carNumber").val();
        table.render({
            elem: '#' + Car.tableId,
            url: Feng.ctxPath + '/patrolcar/getCarList?number='+title,
            page: true,
            limit:40,
            height: "full-98",
            cellMinWidth: 100,
            cols: Car.initColumn()
        });
    };


    /**
     * 弹出添加通知
     */
    Car.openAddCar = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加车辆',
            content: Feng.ctxPath + '/patrolcar/patrolCar_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Car.tableId);
            }
        });
    };


    /**
     * 点击删除通知
     *
     * @param data 点击按钮时候的行数据
     */
    Car.onDeleteCar= function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/patrolcar/deleteCar", function (data) {
                Feng.success("删除成功!");
                table.reload(Car.tableId);
            }, function (data) {
                // Feng.error("删除失败!" + data.responseJSON.message + "!");
                Feng.error(data.responseJSON.message + "!");
            });
            ajax.set("number", data.car_number);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Car.tableId,
        url: Feng.ctxPath + '/patrolcar/getCarList',
        page: true,
        limit:40,
        height: "full-98",
        cellMinWidth: 100,
        cols: Car.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Car.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Car.openAddCar();
    });

    // 工具条点击事件
    table.on('tool(' + Car.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delete') {
            Car.onDeleteCar(data);
        }
    });
});
