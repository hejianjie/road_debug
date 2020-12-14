layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var userId = 1;
    var user_name = '';
    var roadHazardId = 1;
    var dateTime1 = 0;
    // var laydate = layui.laydate;
    //
    // //日期
    // laydate.render({
    //     elem: '#date'
    // });
    var thirteenFive = 0;


    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/roadhazard/selectOne',
        dataType: 'json',
        data:{roadHazardId:roadHazardId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var sizeArr=data[0].specific_size.split(",");
            //病害状况
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/hazard_base_info/getOne',
                data:{id:data[0].hazard_status},
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success:function(hazardInfo){
                    form.val("applicationForm",{
                        "road_hazard_status":hazardInfo[0].name,//病害情况
                        "work_frequency":1,
                    })
                    form.render();
                }
            })
            //单位
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/hazard_unit/getAll',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data1) {
                    $("#hazard_unit").empty();
                    if(data1.length!=0){
                        // $('#hazard_unit').append(new Option('请选择单位', ''));
                        // $.each(data1, function(index, item) {
                        //     option = new Option(item.hazard_unit_name, item.hazard_unit_id);
                        //     $('#hazard_unit').append(option);
                        // });
                        $.each(data1, function(index, item) {
                            option = new Option(item.hazard_unit_name, item.hazard_unit_id);
                            if(item.hazard_unit_id === data[0].size_type){
                                option.setAttribute("selected",'true');
                                switch (data[0].size_type) {
                                    case 1:
                                        $("#unit0").hide();
                                        $("#unit1").show();
                                        $("#unit2").hide();
                                        $("#unit3").hide();
                                        $("#unit4").hide();
                                        form.val("applicationForm", {
                                            "specific_size1": sizeArr[0],
                                            "specific_size2": sizeArr[1],
                                            "specific_size3": sizeArr[2],
                                        })
                                        var unit1 = document.getElementById('specific_size1').value
                                        var unit2 = document.getElementById('specific_size2').value
                                        var unit3 = document.getElementById('specific_size3').value
                                        var work_amount = (unit1*unit2*unit3).toFixed(2)
                                        form.val("applicationForm",{
                                            "work_amount":work_amount,//工程量
                                        })
                                        var unit_price = document.getElementById('unit_price').value
                                        var appraisal_cost = (unit_price*work_amount).toFixed(4)
                                        form.val("applicationForm",{
                                            "appraisal_cost":appraisal_cost,
                                        })
                                        break;
                                    case 2:
                                        $("#unit0").hide();
                                        $("#unit1").hide();
                                        $("#unit2").show();
                                        $("#unit3").hide();
                                        $("#unit4").hide();
                                        form.val("applicationForm", {
                                            "specific_size4": sizeArr[0],
                                            "specific_size5": sizeArr[1],
                                        })
                                        var unit4 = document.getElementById('specific_size4').value
                                        var unit5 = document.getElementById('specific_size5').value
                                        var work_amount = (unit5*unit4).toFixed(2)
                                        form.val("applicationForm",{
                                            "work_amount":work_amount,//工程量
                                        })
                                        var unit_price = document.getElementById('unit_price').value
                                        var appraisal_cost = (unit_price*work_amount).toFixed(4)
                                        form.val("applicationForm",{
                                            "appraisal_cost":appraisal_cost,
                                        })
                                        break;
                                    case 3:
                                        $("#unit0").hide();
                                        $("#unit1").hide();
                                        $("#unit2").hide();
                                        $("#unit3").show();
                                        $("#unit4").hide();
                                        form.val("applicationForm", {
                                            "specific_size6": sizeArr[0],
                                        })
                                        var unit6 = document.getElementById('specific_size6').value
                                        var work_amount = (unit6*1).toFixed(2)
                                        form.val("applicationForm",{
                                            "work_amount":work_amount,//工程量

                                        })
                                        var unit_price = document.getElementById('unit_price').value
                                        var appraisal_cost = (unit_price*work_amount).toFixed(4)
                                        form.val("applicationForm",{
                                            "appraisal_cost":appraisal_cost,
                                        })
                                        break;
                                    case 4:
                                        $("#unit0").hide();
                                        $("#unit1").hide();
                                        $("#unit2").hide();
                                        $("#unit3").hide();
                                        $("#unit4").show();
                                        form.val("applicationForm", {
                                            "specific_size7": sizeArr[0],
                                        })
                                        var unit7 = document.getElementById('specific_size7').value
                                        var work_amount = (unit7*1).toFixed(2)
                                        form.val("applicationForm",{
                                            "work_amount":work_amount,//工程量
                                        })
                                        var unit_price = document.getElementById('unit_price').value
                                        var appraisal_cost = (unit_price*work_amount).toFixed(4)
                                        form.val("applicationForm",{
                                            "appraisal_cost":appraisal_cost,
                                        })
                                        break;
                                    default:
                                        $("#unit0").show();
                                        $("#unit1").hide();
                                        $("#unit2").hide();
                                        $("#unit3").hide();
                                        $("#unit4").hide();
                                }
                            }
                            $('#hazard_unit').append(option);
                        });
                    }else{
                        $("#hazard_unit").append(new Option("暂无数据", ""));
                    }

                    layui.form.render("select");
                }
            })


        }
    })



    //建设性质
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/construct_type/getAll',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data1) {
            $("#construct_type").empty();
            if(data1.length!=0){
                $('#construct_type').append(new Option('请选择建设性质', ''));
                $.each(data1, function(index, item) {
                    option = new Option(item.construct_type_name, item.construct_type_id)
                    $('#construct_type').append(option);
                });
            }else{
                $("#construct_type").append(new Option("暂无数据", ""));
            }
            layui.form.render("select");
        }
    })

    //项目
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/construct_project/getAll',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data1) {
            $("#construct_project").empty();
            if(data1.length!=0){
                $('#construct_project').append(new Option('请选择项目', ''));
                $.each(data1, function(index, item) {
                    option = new Option(item.construct_project_name, item.construct_project_id);
                    $('#construct_project').append(option);
                });
            }else{
                $("#construct_project").append(new Option("暂无数据", ""));
            }
            layui.form.render("select");
        }
    })

    //细目变化
    form.on('select(project_filter)',function(data1){
        var value=data1.value;  //select选中的值
        if(value){
            $.ajax({
                url:Feng.ctxPath + '/construct_project_detail/selectByProjectId',
                data:{constructProjectId:value},
                dataType:"json",
                success:function(data1){
                    if(data1.length != 0){
                        //清空赋值
                        $("#construct_project_detail").empty();
                        $('#construct_project_detail').append(new Option('请选择细目', ''));
                        $.each(data1, function(index, item) {
                            option = new Option(item.construct_project_detail_name, item.construct_project_detail_id);
                            $('#construct_project_detail').append(option);
                        });
                    }else{
                        $("#construct_project_detail").empty();
                        $("#construct_project_detail").append(new Option("暂无数据", ""));
                    }
                    layui.form.render("select");
                }
            })
        }
        else{
            $("#construct_project_detail").empty();
            $("#construct_project_detail").append(new Option("请先选择项目", ""));
            layui.form.render("select");
        }
    })

    // //单位
    // $.ajax({
    //     type: "get",
    //     url: Feng.ctxPath + '/hazard_unit/getAll',
    //     dataType: 'json',
    //     contentType: "application/json; charset=utf-8",
    //     success: function (data1) {
    //         console.log(data1)
    //         $("#hazard_unit").empty();
    //         if(data1.length!=0){
    //             $('#hazard_unit').append(new Option('请选择单位', ''));
    //             $.each(data1, function(index, item) {
    //                 option = new Option(item.hazard_unit_name, item.hazard_unit_id);
    //                 $('#hazard_unit').append(option);
    //             });
    //         }else{
    //             $("#hazard_unit").append(new Option("暂无数据", ""));
    //         }
    //
    //         layui.form.render("select");
    //     }
    // })

    //具体尺寸变化
    form.on('select(unit_filter)',function(data1) {
        var value = data1.value;  //select选中的值
        switch(value){
            case '1':
                $("#unit0").hide();
                $("#unit1").show();
                $("#unit2").hide();
                $("#unit3").hide();
                $("#unit4").hide();
                break;
            case '2':
                $("#unit0").hide();
                $("#unit1").hide();
                $("#unit2").show();
                $("#unit3").hide();
                $("#unit4").hide();
                break;
            case '3':
                $("#unit0").hide();
                $("#unit1").hide();
                $("#unit2").hide();
                $("#unit3").show();
                $("#unit4").hide();
                break;
            case '4':
                $("#unit0").hide();
                $("#unit1").hide();
                $("#unit2").hide();
                $("#unit3").hide();
                $("#unit4").show();
                break;
            default:
                $("#unit0").show();
                $("#unit1").hide();
                $("#unit2").hide();
                $("#unit3").hide();
                $("#unit4").hide();
        }
    })

    //计算工程量
    $("#unit1").on("input",function(e){
        var unit1 = document.getElementById('specific_size1').value
        var unit2 = document.getElementById('specific_size2').value
        var unit3 = document.getElementById('specific_size3').value
        var work_amount = (unit1*unit2*unit3).toFixed(2)
        form.val("applicationForm",{
            "work_amount":work_amount,//工程量
        })
        var unit_price = document.getElementById('unit_price').value
        var appraisal_cost = (unit_price*work_amount).toFixed(4)
        form.val("applicationForm",{
            "appraisal_cost":appraisal_cost,
        })
    });
    $("#unit2").on("input",function(e){
        var unit4 = document.getElementById('specific_size4').value
        var unit5 = document.getElementById('specific_size5').value
        var work_amount = (unit5*unit4).toFixed(2)
        form.val("applicationForm",{
            "work_amount":work_amount,//工程量
        })
        var unit_price = document.getElementById('unit_price').value
        var appraisal_cost = (unit_price*work_amount).toFixed(4)
        form.val("applicationForm",{
            "appraisal_cost":appraisal_cost,
        })
    });
    $("#unit3").on("input",function(e){
        //获取input输入的值
        var unit6 = document.getElementById('specific_size6').value
        var work_amount = (unit6*1).toFixed(2)
        form.val("applicationForm",{
            "work_amount":work_amount,//工程量

        })
        var unit_price = document.getElementById('unit_price').value
        var appraisal_cost = (unit_price*work_amount).toFixed(4)
        form.val("applicationForm",{
            "appraisal_cost":appraisal_cost,
        })
    });
    $("#unit4").on("input",function(e){
        //获取input输入的值
        var unit7 = document.getElementById('specific_size7').value
        var work_amount = (unit7*1).toFixed(2)
        form.val("applicationForm",{
            "work_amount":work_amount,//工程量
        })
        var unit_price = document.getElementById('unit_price').value
        var appraisal_cost = (unit_price*work_amount).toFixed(4)
        form.val("applicationForm",{
            "appraisal_cost":appraisal_cost,
        })
    });

    //单价种类
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/unit_price_type/getAll',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data1) {
            $("#unit_price_type").empty();
            if(data1.length!=0){
                $('#unit_price_type').append(new Option('请选择单价种类', ''));
                $.each(data1, function(index, item) {
                    option = new Option(item.unit_price_type_name, item.unit_price_type_id)
                    if(item.unit_price_type_id === 1){
                        thirteenFive = item.unit_price;
                    }
                    $('#unit_price_type').append(option);
                });
            }else{
                $("#unit_price_type").append(new Option("暂无数据", ""));
            }

            layui.form.render("select");
        }
    })

    //单价种类变化
    form.on('select(unit_price_filter)',function(data1){
        var value=data1.value;  //select选中的值
        if(value === '1'){
            form.val("applicationForm", {
                "unit_price":thirteenFive,//单价
            })
            $("#unit_price").prop('readonly',true);
            layui.form.render();
            var unit_price = document.getElementById('unit_price').value
            var work_amount = document.getElementById('work_amount').value
            var appraisal_cost = (unit_price*work_amount).toFixed(4)
            form.val("applicationForm",{
                "appraisal_cost":appraisal_cost,
            })

        }
        else{
            form.val("applicationForm", {
                "unit_price":0,//单价
            })
            $("#unit_price").prop('readonly',false);
            layui.form.render();
            var unit_price = document.getElementById('unit_price').value
            var work_amount = document.getElementById('work_amount').value
            var appraisal_cost = (unit_price*work_amount).toFixed(4)
            form.val("applicationForm",{
                "appraisal_cost":appraisal_cost,
            })
        }
    })

    //计算预估费用
    $("#unit_price").on("input",function(e){
        var unit_price = document.getElementById('unit_price').value
        var work_amount = document.getElementById('work_amount').value
        var appraisal_cost = (unit_price*work_amount).toFixed(4)
        form.val("applicationForm",{
            "appraisal_cost":appraisal_cost,//工程量
        })
    });
    $("#work_amount").on("input",function(e){
        var unit_price = document.getElementById('unit_price').value
        var work_amount = document.getElementById('work_amount').value
        var appraisal_cost = (unit_price*work_amount).toFixed(4)
        form.val("applicationForm",{
            "appraisal_cost":appraisal_cost,//工程量
        })
    });

    //完成时限和完成日期
    $("#estimated_finish_time").on("input",function(e){
        var estimated_finish_time = document.getElementById('estimated_finish_time').value
        var now = new Date();
        var dateTime = now.getTime()+(1000*60*60*24*estimated_finish_time+1000*60*60*24);
        dateTime1 = dateTime
        var date = new Date(dateTime);
        form.val("applicationForm",{
            "date":date.toLocaleDateString().replace('/','-').replace('/','-'),
        })
    })

    // //填报人
    // $.ajax({
    //     type: "get",
    //     url: Feng.ctxPath + '/sys/sys_user/selectOne',
    //     data:{id:userId},
    //     dataType: 'json',
    //     contentType: "application/json;charset=utf-8",
    //     success:function(userData){
    //         console.log(userData)
    //         form.val("applicationForm",{
    //             "county_applicant":userData[0].user_name,
    //             "work_frequency":1,
    //         })
    //         form.render();
    //         user_name = userData[0].user_name;
    //     }
    // })

    //重置
    $("#apk_reset_btn").on("click",function () {
        form.val("applicationForm",{
            "construct_type":'',
            "construct_project":'',
            "construct_project_detail":'',
            "road_hazard_status":'',
            "hazard_unit":'',
            "specific_size1":'',
            "specific_size2":'',
            "specific_size3":'',
            "specific_size4":'',
            "specific_size5":'',
            "specific_size6":'',
            "specific_size7":'',
            "work_amount":'',
            "unit_price_type":'',
            "unit_price":'',
            "appraisal_cost":'',
            "estimated_finish_time":'',
            "date":'',
        })
        form.render();
    });

    //保存
    form.on('submit(demo1)', function (data) {
        //layer.msg(JSON.stringify(data.field));

        //var articleFrom = data.field.articleFrom;
        var specificSize = 0;
        switch(data.field.hazard_unit){
            case '1':
                specificSize = data.field.specific_size1+','+data.field.specific_size2+','+data.field.specific_size3
                break;
            case '2':
                specificSize = data.field.specific_size4+','+data.field.specific_size5
                break;
            case '3':
                specificSize = data.field.specific_size6
                break;
            case '4':
                specificSize = data.field.specific_size7
                break;
        }
        var addOne ={
            "roadHazardId":roadHazardId,
            "userId":userId,
            "construct_type":data.field.construct_type,
            "construct_project":data.field.construct_project,
            "construct_project_detail":data.field.construct_project_detail,
            "hazard_unit":data.field.hazard_unit,
            "specificSize":specificSize,
            "work_amount":data.field.work_amount,
            "unit_price":data.field.unit_price,
            "unit_price_type":data.field.unit_price_type,
            "work_frequency":data.field.work_frequency,
            "appraisal_cost":data.field.appraisal_cost,
            "date":data.field.date,
            "estimated_finish_time":data.field.estimated_finish_time,
        }
        var addTime = new Date(data.field.estimated_finish_time).toLocaleDateString().replace('/','-').replace('/','-')
        var ajax = new $ax(Feng.ctxPath + "/application/add?roadHazardId="+roadHazardId+"&userId="+userId+"&construct_type="+data.field.construct_type+
            "&construct_project="+data.field.construct_project+
            "&construct_project_detail="+data.field.construct_project_detail+
            "&hazard_unit="+data.field.hazard_unit+
            "&specificSize='"+specificSize+
            "'&work_amount="+data.field.work_amount+
            "&unit_price="+data.field.unit_price+
            "&unit_price_type="+data.field.unit_price_type+
            "&work_frequency='"+data.field.work_frequency+
            "'&appraisal_cost="+data.field.appraisal_cost+
            "&date='"+data.field.date+
            "'&estimated_finish_time="+data.field.estimated_finish_time+
            "&dateTime="+dateTime1, function (data) {
            Feng.success("添加成功！");
            }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        // ajax.set(data.field);
        ajax.start();
        //var articleSummary = data.field.articleSummary;
    })
});
