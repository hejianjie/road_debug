layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var dateTime1 = 0;
    var roadHazardId = Feng.getUrlParam("roadHazardId")
    var patrolResultId = parseInt($("#patrolResultId").val());
    var applicationId = 0;
    var roleId = 0;

    var laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#date'
    });
    var thirteenFive = 0;

    var structType = -1;
    var subject = -1;
    var subject_detail = -1;
    var unitType = -1;
    var unitStyle = -1;

    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/roadhazard/selectOne',
        dataType: 'json',
        data:{roadHazardId:roadHazardId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/application/selectByRoadHazardId?roadHazardId='+roadHazardId,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data1) {

                        console.log(data1)
                        applicationId = data1[0].application_id
                        if(data1[0].status+'' === '1'){
                            $("#editButton").show();
                        }
                        $("#fillForm").show();
                        $("#apply").show();
                    $.ajax({
                                type: "get",
                                url: Feng.ctxPath + '/hazard_base_info/getOne',
                                data:{id:data[0].hazard_status},
                                dataType: 'json',
                                contentType: "application/json;charset=utf-8",
                                success:function(hazardInfo){
                                    console.log(hazardInfo)

                                    form.val("applicationForm",{
                                        "road_hazard_status":hazardInfo[0].name,//病害情况
                                        "work_frequency":1,
                                    })
                                }
                            })
                        var sizeArr=data1[0].specific_size.split(",");//具体尺寸String转化成数组
                        form.val("applicationForm",{
                            "work_amount":data1[0].work_amount,//工程量
                            "unit_price":data1[0].unit_price,//单价
                            "work_frequency":data1[0].work_frequency,//作业频率
                            "appraisal_cost":data1[0].appraisal_cost,//预估费用
                            "date":data1[0].estimated_finish_time,//完成日期
                            "estimated_finish_time":data1[0].estimated_finish_duration,//完成时限
                            "county_applicant_date":data1[0].apply_time,//完成时限
                            // "road_hazard_status":data[0].road_hazard,//病害情况
                        })
                        form.render();

                        //单位
                        $.ajax({
                            type: "get",
                            url: Feng.ctxPath + '/hazard_unit/getAll',
                            dataType: 'json',
                            contentType: "application/json; charset=utf-8",
                            success: function (data11) {
                                console.log(data11)
                                $("#hazard_unit").empty();
                                if(data11.length!=0){
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

                        if(data1[0].auditor_one_result+'' !== ''){
                            $.ajax({
                                type: "get",
                                url: Feng.ctxPath + '/sys/road_user/selectOne',
                                data:{id:data1[0].auditor_one},
                                dataType: 'json',
                                contentType: "application/json;charset=utf-8",
                                success:function(userData){
                                    console.log(userData)
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
                                    console.log(userData)
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
                                    console.log(userData)
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
                                    console.log(userData)
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
                    // }
                }
            })



        }
    })

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

    //计算费用
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

    //保存
    $("#save").on("click",function () {
            var specificSize = 0;
            switch($("#hazard_unit").val()){
                case '1':
                    specificSize =$("#specific_size1").val() +','+ $("#specific_size2").val() +','+ $("#specific_size3").val()
                    break;
                case '2':
                    specificSize = $("#specific_size4").val() +','+ $("#specific_size5").val()
                    break;
                case '3':
                    specificSize = $("#specific_size6").val()
                    break;
                case '4':
                    specificSize = $("#specific_size7").val()
                    break;
            }

            $.ajax({
                type: "post",
                url: Feng.ctxPath + '/acceptance/add?'+
                    "roadHazardId="+roadHazardId+
                    "&hazard_unit="+$("#hazard_unit").val()+//
                    "&specificSize="+specificSize+//
                    "&work_amount="+$("#work_amount").val()+//
                    "&unit_price="+$("#unit_price").val()+//
                    "&cost="+$("#appraisal_cost").val()+//
                    "&date="+$("#date").val()+//
                    "&applicationId="+applicationId
                ,
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success:function(Data){
                    console.log(Data)
                    parent.location.reload()
                    alert("保存成功！")
                },error:function (Data) {
                    console.log(Data)
                    window.location.reload()
                }
            })
    })


    form.on('submit(demo2)', function (data) {
        var specificSize = 0;
        switch($("#hazard_unit").val()){
            case '1':
                specificSize =$("#specific_size1").val() +','+ $("#specific_size2").val() +','+ $("#specific_size3").val()
                break;
            case '2':
                specificSize = $("#specific_size4").val() +','+ $("#specific_size5").val()
                break;
            case '3':
                specificSize = $("#specific_size6").val()
                break;
            case '4':
                specificSize = $("#specific_size7").val()
                break;
        }

        var ajax = new $ax(Feng.ctxPath +
            '/acceptance/add?'+
            "roadHazardId="+roadHazardId+
            "&hazard_unit="+$("#hazard_unit").val()+
            "&specificSize="+specificSize+
            "&work_amount="+$("#work_amount").val()+
            "&unit_price="+$("#unit_price").val()+
            "&cost="+$("#appraisal_cost").val()+
            "&date="+$("#date").val()+
            "&applicationId="+applicationId, function (data) {

            Feng.success("核量成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("核量失败！" )
        });
        ajax.start();
    });


});