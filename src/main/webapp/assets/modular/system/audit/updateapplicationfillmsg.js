layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var dateTime1 = 0;
    var roadHazardId = parseInt($("#roadHazardId").val());
    var patrolResultId = parseInt($("#patrolResultId").val());
//    console.log(roadHazardId)
    var applicationId = 0;

    // var laydate = layui.laydate;
    //
    // //日期
    // laydate.render({
    //     elem: '#date'
    // });
    var thirteenFive = 0;


    var structType = -1;
    var subject = -1;
    var subject_detail = -1;
    var unitType = -1;
    var unitStyle = -1;

    var applicationId=-1;


    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/patrolresult/getView',
        dataType: 'json',
        data: {
            roadHazardId: patrolResultId
        },
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            form.val("Form",{
                "road":data[0].nationalHighwayName,
                "road_section":data[0].roadSectionName,
            })
            form.render();
        }
    })

    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/roadhazard/selectOne',
        dataType: 'json',
        data:{roadHazardId:roadHazardId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var roadHazardId = parseInt($("#roadHazardId").val());
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/application/selectByRoadHazardId?roadHazardId='+roadHazardId,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data1) {
                    if(data1.length === 0){
                        console.log("xxxx")
                      console.log(data1)
                        $("#fillForm").show();
                        $("#submitButton").show();

                        var sizeArr=data[0].specific_size.split(",");
                        //病害状况
                        $.ajax({
                            type: "get",
                            url: Feng.ctxPath + '/hazard_base_info/getOne',
                            data:{id:data[0].hazard_status},
                            dataType: 'json',
                            contentType: "application/json;charset=utf-8",
                            success:function(hazardInfo){
                         //       console.log(hazardInfo)
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
                          //      console.log(data1)
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
                    }else
                    {
                    //    console.log(data1)
                        applicationId = data1[0].application_id
                        if(data1[0].status+'' === '1'){
                            $("#editButton").show();
                        }
                        $("#fillForm").show();
                        $("#apply").show();

                        var sizeArr=data1[0].specific_size.split(",");//具体尺寸String转化成数组
                        form.val("applicationForm",{
                            "work_amount":data1[0].work_amount,//工程量
                            "unit_price":data1[0].unit_price,//单价
                            "work_frequency":data1[0].work_frequency,//作业频率
                            "appraisal_cost":data1[0].appraisal_cost,//预估费用
                            "date":data1[0].estimated_finish_time,//完成日期
                            "estimated_finish_time":data1[0].estimated_finish_duration,//完成时限
                            "county_applicant_date":data1[0].apply_time,//完成时限
                            //"road_hazard_status":data[0].road_hazard,//病害情况
                        })
                        form.render();


                        $.ajax({
                            type: "get",
                            url: Feng.ctxPath + '/roadhazard/selectOne',
                            data:{roadHazardId:data1[0].road_hazard},
                            dataType: 'json',
                            contentType: "application/json;charset=utf-8",
                            success:function(hazardData){
                           //     console.log(hazardData)
                                $.ajax({
                                    type: "get",
                                    url: Feng.ctxPath + '/hazard_base_info/getOne',
                                    data:{id:hazardData[0].hazard_status},
                                    dataType: 'json',
                                    contentType: "application/json;charset=utf-8",
                                    success:function(hazardInfo){
                                  //      console.log(hazardInfo)
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
                            success: function (data11) {
                                if(data1.length!=0){
                                    $.each(data11, function(index, item) {
                                    //    console.log(item.construct_type_id)
                                     //   console.log(data1[0].type_selection)
                                        if(item.construct_type_id+'' === data1[0].type_selection+'') {

                                            structType = item.construct_type_id;
                                            for(var i=0;i< $("#construct_type")[0].length;i++){
                                                if($("#construct_type")[0][i].value+''===structType+''){
                                                    $("#construct_type")[0][i].selected='true'
                                                }
                                            }

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
                            success: function (data11) {
                                if(data11.length!=0) {
                                    $.each(data11, function (index, item) {
                                        if (item.construct_project_detail_id+'' === data1[0].project_name+'') {
                                            subject = item.construct_project_detail_id
                                            for(var i=0;i< $("#construct_project")[0].length;i++){
                                                if($("#construct_project")[0][i].value+''===subject+''){
                                                    $("#construct_project")[0][i].selected='true'


                                                    $.ajax({
                                                        url:Feng.ctxPath + '/construct_project_detail/selectByProjectId',
                                                        data:{constructProjectId:data1[0].project_name},
                                                        dataType:"json",
                                                        success:function(data111){
                                                        //    console.log(data111)
                                                            if(data111.length != 0){
                                                                $("#construct_project_detail").empty();
                                                                $('#construct_project_detail').append(new Option('请选择细目', ''));
                                                                $.each(data111, function(index, item) {
                                                               //     console.log(item.construct_project_detail_id)
                                                                    option = new Option(item.construct_project_detail_name, item.construct_project_detail_id);
                                                                    if(item.construct_project_detail_id+'' === item.construct_project_detail_id+'') {
                                                                        option.setAttribute("selected", 'true')
                                                                    }
                                                                    $('#construct_project_detail').append(option);
                                                                    layui.form.render("select");
                                                                });

                                                            }
                                                        }
                                                    })
                                                }
                                            }
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
                            success: function (data11) {
                            //    console.log(data11)
                                $("#hazard_unit").empty();
                                if(data11.length!=0){
                                    // $('#hazard_unit').append(new Option('请选择单位', ''));
                                    // $.each(data1, function(index, item) {
                                    //     option = new Option(item.hazard_unit_name, item.hazard_unit_id);
                                    //     $('#hazard_unit').append(option);
                                    // });
                                    $.each(data11, function(index, item) {
                                        option = new Option(item.hazard_unit_name, item.hazard_unit_id);
                                        if(item.hazard_unit_id === data1[0].organization_id){
                                            option.setAttribute("selected",'true');
                                            switch (data1[0].organization_id) {
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

                        //单价种类
                        $.ajax({
                            type: "get",
                            url: Feng.ctxPath + '/unit_price_type/getAll',
                            dataType: 'json',
                            contentType: "application/json; charset=utf-8",
                            success: function (data11) {
                                if(data11.length!=0) {
                                    $.each(data11, function (index, item) {
                                        if (item.unit_price_type_id+'' === data1[0].unit_price_type+'') {
                                            unitType = item.unit_price_type_id;
                                            for(var i=0;i< $("#unit_price_type")[0].length;i++){
                                                if($("#unit_price_type")[0][i].value+''===unitType+''){
                                                    $("#unit_price_type")[0][i].selected='true'
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        })

                        //填报人
                        $.ajax({
                            type: "get",
                            url: Feng.ctxPath + '/sys/road_user/selectOne',
                            data:{id:data1[0].applicant},
                            dataType: 'json',
                            contentType: "application/json;charset=utf-8",
                            success:function(userData){
                           //     console.log(userData)
                                form.val("applicationForm",{
                                    "county_applicant":userData[0].name,
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

                        if(data1[0].auditor_one_result+'' !== ''){
                            $.ajax({
                                type: "get",
                                url: Feng.ctxPath + '/sys/road_user/selectOne',
                                data:{id:data1[0].auditor_one},
                                dataType: 'json',
                                contentType: "application/json;charset=utf-8",
                                success:function(userData){
                              //      console.log(userData)
                                    form.val("applicationForm",{
                                        "auditor_one":userData[0].name+'--'+data1[0].auditor_one_result,
                                        "auditor_one_time":data1[0].auditor_one_time,
                                    })
                                    $("#auditor_one").show();
                                    form.render();
                                }
                            })
                        }
                        if(data1[0].auditor_two_result+'' !== ''){
                            $.ajax({
                                type: "get",
                                url: Feng.ctxPath + '/sys/road_user/selectOne',
                                data:{id:data1[0].auditor_two},
                                dataType: 'json',
                                contentType: "application/json;charset=utf-8",
                                success:function(userData){
                              //      console.log(userData)
                                    form.val("applicationForm",{
                                        "auditor_two":userData[0].name+'--'+data1[0].auditor_two_result,
                                        "auditor_two_time":data1[0].auditor_two_time,
                                    })
                                    $("#auditor_two").show();
                                    form.render();
                                }
                            })
                        }
                        if(data1[0].city_dept_head_status+'' !== ''){
                            $.ajax({
                                type: "get",
                                url: Feng.ctxPath + '/sys/road_user/selectOne',
                                data:{id:data1[0].city_dept_head},
                                dataType: 'json',
                                contentType: "application/json;charset=utf-8",
                                success:function(userData){
                              //      console.log(userData)
                                    form.val("applicationForm",{
                                        "auditor_one_city":userData[0].name+'--'+data1[0].city_dept_head_status,
                                        "fb_1":data1[0].city_dept_head_fb,
                                        "auditor_one_time_city":data1[0].city_dept_head_time,
                                    })
                                    $("#city_auditor_one").show();
                                    $("#fb_one").show();
                                    form.render();
                                }
                            })
                        }
                        if(data1[0].city_executive_status+'' !== ''){
                            $.ajax({
                                type: "get",
                                url: Feng.ctxPath + '/sys/road_user/selectOne',
                                data:{id:data1[0].city_executive},
                                dataType: 'json',
                                contentType: "application/json;charset=utf-8",
                                success:function(userData){
                             //       console.log(userData)
                                    form.val("applicationForm",{
                                        "auditor_two_city":userData[0].name+'--'+data1[0].city_executive_status,
                                        "fb_2":data1[0].city_executive_fb,
                                        "auditor_two_time_city":data1[0].city_audito_date,
                                    })
                                    $("#city_auditor_two").show();
                                    $("#fb_two").show();
                                    form.render();
                                }
                            })
                        }
                    }
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
                 //   console.log(item.construct_type_id)
                 //   console.log(structType)
                    if(item.construct_type_id+'' === structType+''){
                        option.setAttribute("selected",'true')
                    }
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
         //   console.log(data1)
            $("#construct_project").empty();
            if(data1.length!=0){
                $('#construct_project').append(new Option('请选择项目', ''));
                $.each(data1, function(index, item) {
                    option = new Option(item.construct_project_detail_name, item.construct_project_detail_id);
                    if(item.construct_project_detail_id+'' === subject+''){
                        option.setAttribute("selected",'true')
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
                                        console.log(item.construct_project_detail_id)
                                        option = new Option(item.construct_project_detail_name, item.construct_project_detail_id);
                                        if(item.construct_project_detail_id+'' === subject_detail+'') {
                                            option.setAttribute("selected", 'true')
                                        }
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
                            console.log(item.construct_project_detail_id)
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
                    if(item.unit_price_type_id+'' === unitType+'') {
                        option.setAttribute("selected", 'true')
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
            //$("#unit_price").prop('readonly',true);
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
        dateTime1 = dateTime;
        var date = new Date(dateTime);
        form.val("applicationForm",{
            "date":date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()
            //"date":date.toLocaleDateString().replace('/','-').replace('年','-').replace('月','-').replace('日','-').replace('/','-'),
        })
    })



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
    //重置
    $("#reset_btn").on("click",function () {
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
    //修改
    form.on('submit(demo2)', function (data) {

        console.log(data.field.road_hazard_status)//病害状况
        console.log("data.field.construct_type")
        console.log(data.field.construct_type)//建设性质
        console.log("data.field.construct_project")
        console.log(data.field.construct_project)//项目名称
        console.log("data.field.construct_project_detail")
        console.log(data.field.construct_project_detail)//项目细则名称
        console.log("data.field.hazard_unit")
        console.log(data.field.hazard_unit)//单位
        console.log("data.field.work_amount")
        console.log(data.field.work_amount)//工程量
        console.log("data.field.unit_price")
        console.log(data.field.unit_price)//单价
        console.log("data.field.unit_price_type")
        console.log(data.field.unit_price_type)//单价种类
        console.log("data.field.work_frequency")
        console.log(data.field.work_frequency)//作业频率
        console.log("data.field.appraisal_cost")
        console.log(data.field.appraisal_cost)//预估费用
        console.log("data.field.date")
        console.log(data.field.date)//时限日期
        console.log(" data.field.estimated_finish_time")
        console.log( data.field.estimated_finish_time)//完成时限

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
        }//具体尺寸


        // var addTime = new Date(data.field.estimated_finish_time).toLocaleDateString().replace('/','-').replace('/','-')
        // console.log(addTime)
        var ajax = new $ax(Feng.ctxPath + "/application/updateOne?roadHazardId="+
            parseInt($("#roadHazardId").val())
            // +"&userId="+parseInt($("#userId").val())
            +"&construct_type="+data.field.construct_type+
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
            admin.closeThisDialog();

            Feng.success("修改成功！");
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.start();
        console.log("zzccccccccccccccccccccccccccccccccccccccc")
    })
    //保存
    form.on('submit(demo1)', function (data) {
        //layer.msg(JSON.stringify(data.field));

    //    console.log(JSON.stringify(data.field))
   //     console.log(data.field.road_hazard_status)
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
     //   console.log(data.field.date)
      //  console.log(addOne)
        var addTime = new Date(data.field.estimated_finish_time).toLocaleDateString().replace('/','-').replace('/','-')
     //   console.log(addTime)
        var ajax = new $ax(Feng.ctxPath + "/application/add?roadHazardId="+parseInt($("#roadHazardId").val())+"&userId="+parseInt($("#userId").val())+"&construct_type="+data.field.construct_type+
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
            admin.closeThisDialog();
            window.parent.location.reload();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        // ajax.set(data.field);
        ajax.start();
        //var articleSummary = data.field.articleSummary;
    })
    var roadHazardId=$("#roadHazardId").val();
    var current;
    var list=[
        {title:"提交问题",state:false,isNow:false},
        {title:"县区核算",state:false,isNow:false},
        {title:"县区部门审核",state:false,isNow:false},
        {title:"县区主管审核",state:false,isNow:false},
        {title:"市区初审",state:false,isNow:false},
        {title:"市主管审核",state:false,isNow:false},
        {title:"市处审核",state:false,isNow:false},
        {title:"养护施工开始",state:false,isNow:false},
        {title:"养护施工结束",state:false,isNow:false},
        {title:"县区初步验收核量",state:false,isNow:false},
        {title:"第三方巡查核量",state:false,isNow:false},
        {title:"市级部门初审",state:false,isNow:false},
        {title:"主管领导审核",state:false,isNow:false}
    ]

    // $.ajax({
    //     type: "get",
    //     url: Feng.ctxPath + '/flow/getStatus?roadHazardId='+roadHazardId,
    //     dataType: 'json',
    //     contentType: "application/json; charset=utf-8",
    //     success: function (data) {
    //         console.log(data)
    //         current=data
    //         $.each(list, function (index, item) {
    //             if(index<12){
    //                 if(index<current){
    //                     $("#contentBox").append(
    //                         `<div style="display: inline-block;float: left;width: auto;margin:10px 0;">
    //                             <button type="button" class="layui-btn" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
    //                             <div style="display: inline-block;width: 30px;height: 1px;background-color: #000000;margin:0 5px;"></div>
    //                          </div>`
    //                     )
    //                 }else if(index==current){
    //                     $("#contentBox").append(
    //                         `<div style="display: inline-block;float: left;width: auto;margin:10px 0;">
    //                             <button type="button" class="layui-btn layui-btn-normal" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
    //                             <div style="display: inline-block;width: 30px;height: 1px;background-color: #000000;margin:0 5px;"></div>
    //                          </div>`
    //                     )
    //                 }else{
    //                     $("#contentBox").append(
    //                         `<div style="display: inline-block;float: left;width: auto;margin:10px 0;">
    //                             <button type="button" class="layui-btn layui-btn-primary" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
    //                             <div style="display: inline-block;width: 30px;height: 1px;background-color: #000000;margin:0 5px;"></div>
    //                          </div>`
    //                     )
    //                 }
    //             }else{
    //                 if(index==current){
    //                     $("#contentBox").append(
    //                         `<div style="display: inline-block;float: left;width: 120px;margin:10px 0;">
    //                             <button type="button" class="layui-btn layui-btn-normal" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
    //                         </div>`
    //                     )
    //                 }else{
    //                     $("#contentBox").append(
    //                         `<div style="display: inline-block;float: left;width: 120px;margin:10px 0;">
    //                             <button type="button" class="layui-btn layui-btn-primary" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
    //                         </div>`
    //                     )
    //                 }
    //             }
    //         });
    //     }
    // });
    $(".showPro").click(function () {
        var hid=roadHazardId;
        onShow(hid);
    });
    var onShow=function(hid){

        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '审核进程',
            area: ['800px', '600px'],
            content: Feng.ctxPath+'/flow?roadHazardId='+hid,
            end: function () {
                admin.getTempData('formOk')
            }
        });
    };
});