// var app =  new Vue({
//     el: '#app',
//     data: {
//
//         visible: false,
//         stakeName: '',
//         location1: '',
//         nationalHighwayList: [],
//         data4: [
//             {
//                 value: 'beijing',
//                 label: '北京',
//                 children: [],
//                 loading: false
//             },
//             {
//                 value: 'hangzhou',
//                 label: '杭州',
//                 children: [],
//                 loading:false
//             }
//         ]
//     },
//
//     mounted: function () {
//         this.getNationalHighway();
//     },
//     methods: {
//         getNationalHighway: function (){
//             console.log(this.data4)
//             axios.get('/stake/selectAllNationalHighway')
//                 .then( ((response)=> {
//                     this.nationalHighwayList=response.data;
//             let arr = [];
//             for (let i = 0; i <this.nationalHighwayList.length; i++) {
//                 arr.push({'label':this.nationalHighwayList[i].nationalHighwayName,'value':this.nationalHighwayList[i].nationalHighwayId,'children':[],'loading':false})
//             }
//             this.nationalHighwayList=arr;
//         }))
//         .catch(function (error) {
//                 console.log(error);
//             });
//         },
//         ok: function(){
//             axios.get('/flow/selectAllNationalHighway')
//                 .then(function (response) {
//                     console.log(response.data);
//                 })
//                 .catch(function (error) {
//                     console.log(error);
//                 });
//         },
//         add: function () {
//             this.visible = true;
//             this.getNationalHighway();
//         },
//         loadData: function(item) {
//             item.loading = true;
//             axios.get('/stake/selectAllRoadSection?nationalHighwayId='+item.value)
//                 .then(((response)=> {
//
//                     for (let i = 0; i <this.nationalHighwayList.length; i++) {
//                 if(this.nationalHighwayList[i].value ==item.value){
//                     for (var k = 0; k < response.data.length ; k++) {
//                         console.log(response.data   )
//                         console.log(response.data.length)
//                         console.log(response.data[k])
//                         this.nationalHighwayList[i].children.push({'label':response.data[k].roadSectionName,'value':response.data[k].roadSectionId})
//                     }
//                 }
//
//             }
//         }))
//         .catch(function (error) {
//                 console.log(error);
//             });
//             console.log(this.nationalHighwayList)
//             item.loading = false;
//         }
//     }
// })


layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var nationalHighwayId=0;
    var roadSectionId=0;
    var stakeName,roadSectionId,stakeLocation;



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
        }
    });
    form.on('select(ld)', function (data) {
        roadSectionId= data.value;
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
                });
                layui.form.render("select");
            }
        });
    });


    /**
     * 系统管理--角色管理
     */
    var Stake = {
        tableId: "stakeTable",    //表格id
        condition: {
            stakeName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Stake.initColumn = function () {
        return [[
            {field: 'stake_id' ,sort: true, title: '桩编号'},
            {field: 'road_section_id',hide: true, sort: true, title: '路段id'},
            {field: 'name', sort: true, title: '桩名称'},
            {field: 'stake_location', sort: true, title: '位置(公里)'},
            {field: 'roadSectionName', sort: true, title: '路段名称'},
            {field: 'nationalHighwayName', sort: true, title: '国道名称'},
            {field: 'supervisorName', sort: true, title: '管理单位名称'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Stake.search = function () {
        // var begin = $("#begin").val();
        // var end = $("#end").val();
        table.render({
            elem: '#' + Stake.tableId,
            url: Feng.ctxPath + '/stake/selectAllStake?nationalHighwayId='+nationalHighwayId+"&roadSectionId="+roadSectionId,
            page: true,
            height: "full-98",
            cellMinWidth: 100,
            cols: Stake.initColumn()
        });
        // table.reload(Stake.tableId, {where: queryData});
    };

    /**
     * 弹出添加桩
     */
    Stake.openAddStake = function () {
        // admin.putTempData('formOk', false);

        layer.open({
            type: 2,
            area: ['700px','800px'],
            title: '添加桩',
            content: Feng.ctxPath + '/information/stake_add',
            maxmin: true,
            end: function () {
                table.reload(Stake.tableId);
            }
        });

        // top.layui.admin.open({
        //     type: 2,
        //     title: '添加桩',
        //     content: Feng.ctxPath + '/information/stake_add',
        //     area: ['400px','400px'],
        //     end: function () {
        //         admin.getTempData('formOk') && table.reload(Stake.tableId);
        //     }
        // });
    };

    /**
     * 导出excel按钮
     */
    Stake.exportExcel = function () {
        var checkRows = table.checkStatus(Stake.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑桩
     *
     * @param data 点击按钮时候的行数据
     */
    Stake.onEditStake= function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改桩',
            content: Feng.ctxPath + '/information/stake_edit?stakeId=' + data.stake_id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Stake.tableId);
            }
        });
    };

    /**
     * 点击删除桩
     *
     * @param data 点击按钮时候的行数据
     */
    Stake.onDeleteStake= function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/stake/deleteStake?stakeId="+data.stake_id, function () {
                Feng.success("删除成功!");
                table.reload(Stake.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.start();
        };
        Feng.confirm("是否删除桩 " + data.name + "?", operation);
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Stake.tableId,
        url: Feng.ctxPath + '/stake/selectAllStake',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Stake.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Stake.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Stake.openAddStake();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Stake.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Stake.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Stake.onEditStake(data);
        } else if (layEvent === 'delete') {
            Stake.onDeleteStake(data);
        }
    });


});
