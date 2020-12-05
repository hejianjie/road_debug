layui.use(['table', 'admin', 'ax', 'ztree','laydate','form',"jquery","cascader"], function () {

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
    var role = "";

    var auditor_id = parseInt($("#userId").val());
    var applicationId = parseInt($("#applicationId").val());
    console.log(auditor_id);
    console.log(applicationId);


    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/application/selectOne',
        data: {applicationId: applicationId},
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            console.log(data)
            console.log("11111"+data[0].status+"")
            if((data[0].status+"") === '2'){
                console.log("111111")
                role = "区县审核人1";
            }else if((data[0].status+"") === '3'){
                role = "区县审核人2";
            }

            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/sys/road_user/selectOne',
                data: {id: auditor_id},
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data1) {
                    if(data1.length>0){
                        if(data1[0].role_id+'' === '13' && data[0].status+'' === '2'){
                            $("#button").show();
                        }else if(data1[0].role_id+'' === '14' && data[0].status+'' === '3'){
                            $("#button").show();
                        }
                    }
                }
            })

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
                            if (item.construct_project_detail_id === data[0].project_name) {
                                form.val("applicationForm", {
                                    "construct_project": item.construct_project_detail_name,
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

            if(data[0].status+'' === '3'){
                    $.ajax({
                        type: "get",
                        url: Feng.ctxPath + '/sys/road_user/selectOne',
                        data:{id:data[0].auditor_one},
                        dataType: 'json',
                        contentType: "application/json;charset=utf-8",
                        success:function(userData){
                            console.log(userData)
                            form.val("applicationForm",{
                                "auditor_one":userData[0].name+'--'+data[0].auditor_one_result,
                                "auditor_one_time":data[0].auditor_one_time,
                            })
                            $("#auditor_one").show();
                            form.render();
                        }
                    })
            }else if(data[0].status+'' !== '2'){
                $.ajax({
                    type: "get",
                    url: Feng.ctxPath + '/sys/road_user/selectOne',
                    data:{id:data[0].auditor_one},
                    dataType: 'json',
                    contentType: "application/json;charset=utf-8",
                    success:function(userData){
                        console.log(userData)
                        form.val("applicationForm",{
                            "auditor_one":userData[0].name+'--'+data[0].auditor_one_result,
                            "auditor_one_time":data[0].auditor_one_time,
                        })
                        $("#auditor_one").show();
                        form.render();
                    }
                })
                //审核人2
                if(data[0].auditor_two_result === "待定"){
                    $.ajax({
                        type: "get",
                        url: Feng.ctxPath + '/sys/road_user/selectOne',
                        data:{id:data[0].auditor_two},
                        dataType: 'json',
                        contentType: "application/json;charset=utf-8",
                        success:function(userData){
                            console.log(userData)
                            form.val("applicationForm",{
                                "auditor_two":userData[0].name+'--'+data[0].auditor_two_result,
                                "auditor_two_time":data[0].auditor_two_time,
                            })
                            $("#auditor_two").show();
                            form.render();
                        }
                    })
                }

                if(data[0].city_dept_head_status+'' !== ''){
                    $.ajax({
                        type: "get",
                        url: Feng.ctxPath + '/sys/road_user/selectOne',
                        data:{id:data[0].city_dept_head},
                        dataType: 'json',
                        contentType: "application/json;charset=utf-8",
                        success:function(userData){
                            console.log(userData)
                            form.val("applicationForm",{
                                "city_auditor_one":userData[0].name+'--'+data[0].city_dept_head_status,
                            })
                            $("#city_auditor_one").show();
                            form.render();
                        }
                    })
                }
                if(data[0].city_executive_status+'' !== ''){
                    $.ajax({
                        type: "get",
                        url: Feng.ctxPath + '/sys/road_user/selectOne',
                        data:{id:data[0].city_executive},
                        dataType: 'json',
                        contentType: "application/json;charset=utf-8",
                        success:function(userData){
                            console.log(userData)
                            form.val("applicationForm",{
                                "city_auditor_two":userData[0].name+'--'+data[0].city_executive_status,
                            })
                            $("#city_auditor_two").show();
                            form.render();
                        }
                    })
                }
                if(data[0].status > 4){

                    form.val("applicationForm",{
                        "fb_1":data[0].city_dept_head_fb,
                    })
                    $("#fb_one").show();
                    form.render();
                }
                if(data[0].status > 5){
                    console.log(data[0].city_executive_fb+'')
                    form.val("applicationForm",{
                        "fb_2":data[0].city_executive_fb,
                    })
                    $("#fb_two").show();
                    form.render();
                }

            }

        }
    });


    $("#demo1").on("click",function () {
        console.log(auditor_id)
        console.log(applicationId)
        var ajax = new $ax(Feng.ctxPath + "/application/countyAuditor?id="+auditor_id +"&result='同意'&applicationId="+applicationId+"&role='"+role+"'", function (data) {
            Feng.success("操作成功！");
        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });
        ajax.start();
        form.render();
        admin.closeThisDialog();
    })
    $("#demo2").on("click",function () {
        var ajax = new $ax(Feng.ctxPath + "/application/countyAuditor?id="+auditor_id+"&result='待定'&applicationId="+applicationId+"&role='"+role+"'", function (data) {
            Feng.success("操作成功！");
        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });
        ajax.start();
        form.render();
        admin.closeThisDialog();
    })
    $("#demo3").on("click",function () {
        var ajax = new $ax(Feng.ctxPath + "/application/countyAuditor?id="+auditor_id+"&result='否决'&applicationId="+applicationId+"&role='"+role+"'", function (data) {
            Feng.success("操作成功！");
        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });


        layer.confirm('请选择处理方式', {
            btn: ['重新填报 ','直接否决'] //按钮
        }, function(){
            //重新填报
            layer.msg('重新填报', {icon: 1});
            //ajax.start();
            form.render();
            admin.closeThisDialog();

        }, function(){
            直接否决
             ajax.start();
            form.render();
            admin.closeThisDialog();

        });

    })
    form.render();



















    // var roadHazardId=Feng.getUrlParam("roadHazardId");
    // var current;
    // var list=[
    //     {title:"提交问题",state:false,isNow:false},
    //     {title:"县区核算",state:false,isNow:false},
    //     {title:"县区部门审核",state:false,isNow:false},
    //     {title:"县区主管审核",state:false,isNow:false},
    //     {title:"市区初审",state:false,isNow:false},
    //     {title:"市主管审核",state:false,isNow:false},
    //     {title:"市处审核",state:false,isNow:false},
    //     {title:"养护施工开始",state:false,isNow:false},
    //     {title:"养护施工结束",state:false,isNow:false},
    //     {title:"县区初步验收核量",state:false,isNow:false},
    //     {title:"第三方巡查核量",state:false,isNow:false},
    //     {title:"市级部门初审",state:false,isNow:false},
    //     {title:"主管领导审核",state:false,isNow:false}
    // ]

    // $.ajax({
    //     type: "get",
    //     url: Feng.ctxPath + '/flow/getStatus?roadHazardId='+$("#roadHarzardId").val(),
    //     dataType: 'json',
    //     contentType: "application/json; charset=utf-8",
    //     success: function (data) {
    //
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
        // var hid=$("#roadHarzardId").val();
        var hid=$(this).data("hid");
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
