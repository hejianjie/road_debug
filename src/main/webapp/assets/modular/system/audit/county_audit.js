layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;


    // var laydate = layui.laydate;
    //
    // //日期
    // laydate.render({
    //     elem: '#date'
    // });
    var applicationId=1;//申请id
    var auditor_id = 6;//审核人id
    var role = "";

    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/application/selectOne',
        data: {applicationId: applicationId},
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            if(!data[0].auditor_one||data[0].auditor_one == auditor_id){
                role = "区县审核人1";
            }else{
                role = "区县审核人2";
            }


            var sizeArr=data[0].specific_size.split(",");//具体尺寸String转化成数组
            form.val("applicationForm",{
                "work_amount":data[0].work_amount,//工程量
                "unit_price":data[0].unit_price,//单价
                "work_frequency":data[0].work_frequency,//作业频率
                "appraisal_cost":data[0].appraisal_cost,//预估费用
                "date":data[0].estimated_finish_time.slice(0,10),//完成日期
                "estimated_finish_time":data[0].estimated_finish_duration,//完成时限
                //"road_hazard_status":data[0].road_hazard,//病害情况
            })
            form.render();


            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/roadhazard/selectOne',
                data:{roadHazardId:data[0].road_hazard},
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success:function(hazardData){
                    console.log(hazardData)
                    $.ajax({
                        type: "get",
                        url: Feng.ctxPath + '/hazard_base_info/getOne',
                        data:{id:hazardData[0].hazard_status},
                        dataType: 'json',
                        contentType: "application/json;charset=utf-8",
                        success:function(hazardInfo){
                            console.log(hazardInfo)
                            form.val("applicationForm",{
                                "road_hazard_status":hazardInfo[0].name,//病害情况
                            })
                            form.render();

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
                    if(data1.length!=0){
                        $.each(data1, function(index, item) {
                            if(item.construct_type_id === data[0].type_selection) {
                                form.val("applicationForm", {
                                    "construct_type": item.construct_type_name,
                                })
                                form.render();
                            }
                        });
                    }
                }
            })

            //项目
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/construct_project/getAll',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data1) {
                    if(data1.length!=0) {
                        $.each(data1, function (index, item) {
                            if (item.construct_project_id === data[0].project_name) {
                                form.val("applicationForm", {
                                    "construct_project": item.construct_project_name,
                                })
                                form.render();
                            }
                        });
                    }
                }
            })

            //细目
            $.ajax({
                url:Feng.ctxPath + '/construct_project_detail/selectByProjectId',
                data:{constructProjectId:data[0].project_name},
                dataType:"json",
                success:function(data1){
                    if(data1.length != 0){
                        $.each(data1, function(index, item) {
                            if(item.construct_project_detail_id === data[0].detail_name){
                                form.val("applicationForm", {
                                    "construct_project_detail": item.construct_project_detail_name,
                                })
                                form.render();
                            }
                        });
                    }
                }
            })

            //单位
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/hazard_unit/getAll',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data1) {
                    if(data1.length!=0){
                        $.each(data1, function(index, item) {
                            if(item.hazard_unit_id === data[0].organization_id){
                                form.val("applicationForm", {
                                    "hazard_unit": item.hazard_unit_name,
                                })
                                form.render();
                                switch (data[0].organization_id) {
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
                                        break;
                                    default:
                                        $("#unit0").show();
                                        $("#unit1").hide();
                                        $("#unit2").hide();
                                        $("#unit3").hide();
                                        $("#unit4").hide();
                                }
                            }
                        });
                    }
                }
            })

            //单价种类
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/unit_price_type/getAll',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data1) {
                    if(data1.length!=0) {
                        $.each(data1, function (index, item) {
                            if (item.unit_price_type_id === data[0].unit_price_type) {
                                form.val("applicationForm", {
                                    "unit_price_type": item.unit_price_type_name,
                                })
                                form.render();
                            }
                        });
                    }
                }
            })

            //填报人
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/sys/road_user/selectOne',
                data:{id:data[0].applicant},
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success:function(userData){
                    console.log(userData)
                    form.val("applicationForm",{
                        "county_applicant":userData[0].user_name,
                    })
                    form.render();
                }
            })

            //初审
            // if(data[0].first_auditor){
            //     $.ajax({
            //         type: "get",
            //         url: Feng.ctxPath + '/sys/road_user/selectOne',
            //         data:{id:data[0].first_auditor},
            //         dataType: 'json',
            //         contentType: "application/json;charset=utf-8",
            //         success:function(userData){
            //             console.log(userData)
            //             form.val("applicationForm",{
            //                 "first_auditor":userData[0].user_name+'--'+data[0].first_auditor_result,
            //             })
            //             $("#first_auditor").show();
            //             form.render();
            //         }
            //     })
            // }

            //审核人1
            if(data[0].auditor_one){
                $.ajax({
                    type: "get",
                    url: Feng.ctxPath + '/sys/road_user/selectOne',
                    data:{id:data[0].auditor_one},
                    dataType: 'json',
                    contentType: "application/json;charset=utf-8",
                    success:function(userData){
                        console.log(userData)
                        form.val("applicationForm",{
                            "auditor_one":userData[0].user_name+'--'+data[0].auditor_one_result,
                        })
                        $("#auditor_one").show();
                        form.render();
                    }
                })
            }

            //审核人2
            if(data[0].auditor_two){
                $.ajax({
                    type: "get",
                    url: Feng.ctxPath + '/sys/road_user/selectOne',
                    data:{id:data[0].auditor_two},
                    dataType: 'json',
                    contentType: "application/json;charset=utf-8",
                    success:function(userData){
                        console.log(userData)
                        form.val("applicationForm",{
                            "auditor_two":userData[0].user_name+'--'+data[0].auditor_two_result,
                        })
                        $("#auditor_two").show();
                        form.render();
                    }
                })
            }
        }
    });


    $("#demo1").on("click",function () {
        console.log(auditor_id)
        console.log(applicationId)
        var ajax = new $ax(Feng.ctxPath + "/application/countyAuditor?id="+auditor_id+"&result='同意'&applicationId="+applicationId+"&role='"+role+"'", function (data) {
            Feng.success("操作成功！");
        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });
        ajax.start();
        form.render();
    })
    $("#demo2").on("click",function () {
        var ajax = new $ax(Feng.ctxPath + "/application/countyAuditor?id="+auditor_id+"&result='待定'&applicationId="+applicationId+"&role='"+role+"'", function (data) {
            Feng.success("操作成功！");
        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });
        ajax.start();
        form.render();
    })
    $("#demo3").on("click",function () {
        var ajax = new $ax(Feng.ctxPath + "/application/countyAuditor?id="+auditor_id+"&result='否决'&applicationId="+applicationId+"&role='"+role+"'", function (data) {
            Feng.success("操作成功！");
        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });
        ajax.start();
        form.render();
    })
    form.render();
});
