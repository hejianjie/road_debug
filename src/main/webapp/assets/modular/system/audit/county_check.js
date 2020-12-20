layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable','xmSelect'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;
    var laydate = layui.laydate;
    var form=layui.form;
    var layer=layui.layer;

    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var xmSelect = layui.xmSelect;
    var user_name = '';
    var third_party =[];
    var status = 0;
    var roleId = 0;
    var pid = 0;

    var userId =parseInt($("#userId").val())
    var roadHazardId = parseInt($("#roadHazardId").val());
    var acceptanceId =parseInt($("#acceptanceId").val())


    var applicationId =0;
    // var laydate = layui.laydate;
    //
    // //日期
    // laydate.render({
    //     elem: '#date'
    // });
    var thirteenFive = 0;
    var pic1 = ""
    var pic2 = ""
    var pic3 = ""
    var pic4 = ""

    console.log(parseInt($("#patrolResultId").val()))
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/patrolresult/getView',
        dataType: 'json',
        data: {roadHazardId: parseInt($("#patrolResultId").val())},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            form.val("Form",{
                "road":data[0].nationalHighwayName,
                "road_section":data[0].roadSectionName,
            })
            form.render();
        }
    })
    $.ajax({  //核算
        type: "get",
        url: Feng.ctxPath + '/audit_img/select',
        dataType: 'json',
        data: {acceptanceId: acceptanceId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $("#pic4").hide();
            $("#pic1").hide();
            $("#pic2").hide();
            $("#pic3").hide();
            switch (data.length) {
                case 4:
                    pic4 = data[3].audit_img_name
                    document.getElementById("pic4").setAttribute("src",pic4)
                    $("#pic4").show();
                case 3:
                    pic3 = data[2].audit_img_name
                    document.getElementById("pic3").setAttribute("src",pic3)
                    $("#pic3").show();
                case 2:
                    pic2 = data[1].audit_img_name
                    document.getElementById("pic2").setAttribute("src",pic2)
                    $("#pic2").show();
                case 1:
                    pic1 = data[0].audit_img_name
                    document.getElementById("pic1").setAttribute("src",pic1)
                    $("#pic1").show();
                    $("#pic0").hide();
                    break;
                case 0:
                    $("#pic0").show();
                    break;
            }
        }
    })

    $("#pic1").on("click",function () {
        var img = new Image();
        img.src = pic1;
        var height = img.height + 50; //获取图片高度
        var width = img.width; //获取图片宽度
        var imgHtml = "<img src='"+pic1+"'/>";
        //弹出层
        layer.open({
            type: 1,
            shade: 0.8,
            offset: 'auto',
            area: [width + 'px',height+'px'],
            shadeClose:true,//点击外围关闭弹窗
            scrollbar: false,//不现实滚动条
            title: "图片预览", //不显示标题
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    })
    $("#pic2").on("click",function () {
        var img = new Image();
        img.src = pic2;
        var height = img.height + 50; //获取图片高度
        var width = img.width; //获取图片宽度
        var imgHtml = "<img src='"+pic2+"'/>";
        //弹出层
        layer.open({
            type: 1,
            shade: 0.8,
            offset: 'auto',
            area: [width + 'px',height+'px'],
            shadeClose:true,//点击外围关闭弹窗
            scrollbar: false,//不现实滚动条
            title: "图片预览", //不显示标题
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    })
    $("#pic3").on("click",function () {
        var img = new Image();
        img.src = pic3;
        var height = img.height + 50; //获取图片高度
        var width = img.width; //获取图片宽度
        var imgHtml = "<img src='"+pic3+"'/>";
        //弹出层
        layer.open({
            type: 1,
            shade: 0.8,
            offset: 'auto',
            area: [width + 'px',height+'px'],
            shadeClose:true,//点击外围关闭弹窗
            scrollbar: true,//不现实滚动条
            title: "图片预览", //不显示标题
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    })
    $("#pic4").on("click",function () {
        var img = new Image();
        img.src = pic4;
        var height = img.height + 50; //获取图片高度
        var width = img.width; //获取图片宽度
        var imgHtml = "<img src='"+pic4+"'/>";
        //弹出层
        layer.open({
            type: 1,
            shade: 0.8,
            offset: 'auto',
            area: [width + 'px',height+'px'],
            shadeClose:true,//点击外围关闭弹窗
            scrollbar: false,//不现实滚动条
            title: "图片预览", //不显示标题
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    })

    $.ajax({  //核算
        type: "get",
        url: Feng.ctxPath + '/acceptance/selectOne',
        dataType: 'json',
        data: {acceptanceId: acceptanceId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            applicationId = data[0].application_id

            $.ajax({  //核算
                type: "get",
                url: Feng.ctxPath + '/application/getView',
                dataType: 'json',
                data: {applicationId: data[0].application_id},
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    form.val("fillForm", {
                        "construct_type": data[0].construct_type,
                        "road_hazard_status":data[0].road_hazard_status,
                        "construct_project":data[0].construct_project,
                        "construct_project_detail":data[0].construct_project_detail,
                        "hazard_unit":data[0].hazard_unit,
                        "work_amount":data[0].work_amount,
                        "work_frequency":data[0].work_frequency,
                        "unit_price_type":data[0].unit_price_type,
                        "unit_price":data[0].unit_price+" 元",
                        "appraisal_cost":data[0].appraisal_cost+" 元",
                        "estimated_finish_time":data[0].estimated_finish_duration+" 天",
                        "date":data[0].estimated_finish_time,

                        "county_applicant":data[0].applicant,
                        "county_applicant_date":data[0].apply_time,
                        "auditor_one":data[0].auditor_one+"--同意",
                        "auditor_one_time":data[0].auditor_one_time,
                        "auditor_two":data[0].auditor_two+"--同意",
                        "auditor_two_time":data[0].auditor_two_time,
                        "auditor_one_city":data[0].city_dept_head+"--同意",
                        "auditor_one_time_city":data[0].city_dept_head_time,
                        "fb_1":data[0].city_dept_head_fb,
                        "auditor_two_city":data[0].city_executive+"--同意",
                        "auditor_two_time_city":data[0].city_audito_date,
                        "fb_2":data[0].city_executive_fb,



                    })
                    form.render()
                    var sizeArr=data[0].specific_size.replace(",","*").replace(",","*").replace(",","*")
                    console.log(sizeArr)
                    switch (data[0].hazard_unit) {
                        case '体积':
                            form.val("fillForm", {
                                "specific_size": sizeArr + " 立方米",
                            })
                            form.render()
                            break;
                        case '面积':
                            form.val("fillForm", {
                                "specific_size": sizeArr + " 平方米",
                            })
                            form.render()
                            break;
                        case '长度':
                            form.val("fillForm", {
                                "specific_size": sizeArr + " 米",
                            })
                            form.render()
                            break;
                        case '个数':
                            form.val("fillForm", {
                                "specific_size": sizeArr + " 个",
                            })
                            form.render()
                            break;
                    }

                }
            })


            if(data[0].role_id+'' === '1200000929026998274'){
                console.log("sdfdsfgdsfd")
                $("#hide11").hide();
                $("#hide12").hide();
                $("#hide13").hide();
                $("#button").hide();
                $("#newHide").show();


                form.val("applicationForm", {
                    "deduction": data[0].deduction,
                    "second_audit_price": data[0].second_audit_price,
                })
            }
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/sys/road_user/selectOne',
                data:{id:userId},
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success:function(userData){
                    if(userData[0].role_id+'' !== '6' && userData[0].role_id+'' !== '1202130817007374338'){

                        $("#hide12").hide();

                        $("#button").hide();
                        //$("#acceptanceAudit").show();


                    }
                    if(userData[0].role_id+'' !== '6' && userData[0].role_id+'' !== '1202130817007374338' && userData[0].role_id+'' !== '1203672010841522177'){
                        $("#hide11").hide();

                        $("#hide13").hide();


                    }
                    $.ajax({
                        type: "get",
                        url: Feng.ctxPath + '/sys_dept/getPid',
                        dataType: 'json',
                        contentType: "application/json;charset=utf-8",
                        success:function(d){
                            console.log(d)
                            pid = d
                        }
                    })
                }
            })


            var tableResult = table.render({
                elem: '#' + appraisal.tableId,
                url: Feng.ctxPath + '/acceptance/getByApplicationId?applicationId='+applicationId,
                page: false,
                height: "200",
                cellMinWidth: 100,
                cols: appraisal.initColumn()
            });
            console.log(data)
            form.val("applicationForm", {
                "work_amount": data[0].work_amount,//工程量
                "unit_price": data[0].unit_price,//单价
                "cost": data[0].cost_price,//金额
                "date":data[0].audit_time.slice(0,10)//完成时间
            })
            console.log(data[0].first_audit_result)
            if(data[0].status+''==='1'){
                form.val("applicationForm", {
                    "audit_date": data[0].auditor_one_time,//初核日期
                })
                $.ajax({
                    type: "get",
                    url: Feng.ctxPath + '/sys/road_user/selectOne',
                    data:{id:data[0].auditor_one},
                    dataType: 'json',
                    contentType: "application/json;charset=utf-8",
                    success:function(userData){
                        if(data[0].auditor_one_result+'' === '0'){
                            form.val("applicationForm", {
                                "result": userData[0].name+"---未完成",//初核结果
                            })
                        }else{
                            form.val("applicationForm", {
                                "result": userData[0].name+"---完成",//初核结果
                            })
                        }
                        form.render();
                    }
                })
                $("#hide1").show();
            }else if(data[0].status+''==='2'){
                form.val("applicationForm", {
                    "audit_date": data[0].auditor_one_time,//初核日期
                })
                $.ajax({
                    type: "get",
                    url: Feng.ctxPath + '/sys/road_user/selectOne',
                    data:{id:data[0].auditor_one},
                    dataType: 'json',
                    contentType: "application/json;charset=utf-8",
                    success:function(userData){
                        if(data[0].auditor_one_result+'' === '0'){
                            form.val("applicationForm", {
                                "result": userData[0].name+"---未完成",//初核结果
                            })
                        }else{
                            form.val("applicationForm", {
                                "result": userData[0].name+"---完成",//初核结果
                            })
                        }
                        form.render();
                    }
                })
                $("#hide1").show();
                form.val("applicationForm", {
                    "audit_date2": data[0].auditor_two_time,//初核日期
                })
                $.ajax({
                    type: "get",
                    url: Feng.ctxPath + '/sys/road_user/selectOne',
                    data:{id:data[0].auditor_two},
                    dataType: 'json',
                    contentType: "application/json;charset=utf-8",
                    success:function(userData){
                        if(data[0].auditor_two_result+'' === '0'){
                            form.val("applicationForm", {
                                "result2": userData[0].name+"---未完成",//初核结果
                            })
                        }else{
                            form.val("applicationForm", {
                                "result2": userData[0].name+"---完成",//初核结果
                            })
                        }
                        form.render();
                    }
                })
                $("#hide2").show();
            }


            $.ajax({ //申请
                type: "get",
                url: Feng.ctxPath + '/application/selectOne?',
                dataType: 'json',
                data: {applicationId: data[0].application_id},
                contentType: "application/json;charset=utf-8",
                success: function (applicationInfo) {
                    $.ajax({
                        type: "get",
                        url: Feng.ctxPath + '/sys/road_user/selectOne',
                        data:{id:userId},
                        dataType: 'json',
                        contentType: "application/json;charset=utf-8",
                        success:function(userData){

                            console.log(userData[0].role_id)
                            console.log(data[0])
                            if(data[0].status+'' === '0' && (userData[0].role_id+'' === '13' ||userData[0].role_id+'' === '6') && data[0].role_id + '' !== '1200000929026998274'){
                                status = applicationInfo[0].status
                                roleId = userData[0].role_id
                                $("#button").show();
                            }
                            if(data[0].status+'' === '1' && (userData[0].role_id+'' === '14'||userData[0].role_id+'' === '1202130817007374338')&& data[0].auditor_one_result+'' === '1' && data[0].role_id + '' !== '1200000929026998274'){
                                status = applicationInfo[0].status + 1
                                roleId = userData[0].role_id
                                $("#button").show();
                            }
                            form.render();
                        }
                    })

                    $.ajax({ //病害
                        type: "get",
                        url: Feng.ctxPath + '/roadhazard/selectOne',
                        dataType: 'json',
                        data: {roadHazardId: applicationInfo[0].road_hazard},
                        contentType: "application/json; charset=utf-8",
                        success: function (data) {
                            $.ajax({
                                type: "get",
                                url: Feng.ctxPath + '/hazard_base_info/getOne',
                                data: {id: data[0].hazard_status},
                                dataType: 'json',
                                contentType: "application/json;charset=utf-8",
                                success: function (hazardInfo) {
                                    console.log(hazardInfo)
                                    form.val("applicationForm", {
                                        "road_hazard_status": hazardInfo[0].name,//病害情况
                                    })
                                    form.render();
                                }
                            })
                        }
                    })
                }
            })

            var sizeArr = data[0].specific_size.split(",");
            //单位
            $.ajax({
                type: "get",
                url: Feng.ctxPath + '/hazard_unit/getAll',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data1) {
                    if (data1.length != 0) {
                        $.each(data1, function (index, item) {
                            if (item.hazard_unit_id === data[0].unit) {
                                form.val("applicationForm", {
                                    "hazard_unit": item.hazard_unit_name,
                                })
                                form.render();
                                switch (data[0].unit) {
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
                                            "specific_size6": sizeArr[0]+" 米",
                                        })
                                        break;
                                    case 4:
                                        $("#unit0").hide();
                                        $("#unit1").hide();
                                        $("#unit2").hide();
                                        $("#unit3").hide();
                                        $("#unit4").show();
                                        form.val("applicationForm", {
                                            "specific_size7": sizeArr[0]+" 个",
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

        }
    })

    var appraisal = {
        tableId: "appraisalList",
        // condition: {
        //     PatrolResultId: ""
        // }
    };
    /**
     * 初始化表格的列
     */
    appraisal.initColumn = function () {
        return [[
            {field: 'zizeng',  title: '序号',type:'numbers'},
            {field:'money',title:'核量单位',templet:function (d) {
                return d.name
            }},
            {field: 'patrolResultId', hide: true, sort: true, title: 'id',templet:function (d) {
                    if (d.patrolResultId==1){
                        return 'ID：'+ d.patrolResultId +'，标题：<span style="color: #c00;">'+ d.title +'</span>'
                    }
                }},
            {field:'audit_time',title:'核量日期',templet:function (d) {
                    if (d.audit_time){
                        return d.audit_time
                    }else{
                        return "未核量"
                    }
                }},
            {align: 'center', toolbar: '#tableBar', title: '查看', minWidth: 200}
        ]];
    };



    // 第三方造价评估单位下拉框赋值
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/inspectionCompany/getInspectionCompany',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            const arr = [];
            $.each(data, function (index, item) {
                // arr.append(new Option(item.full_name, item.dept_id));
                const dwoption = {};
                dwoption.name=item.name
                dwoption.value=item.user_id
                arr.push(dwoption)
            });
            console.log(arr)
            //渲染多选
            var demo1 = xmSelect.render({
                el: '#forward_to',
                toolbar: {
                    show: true,
                },
                filterable: true,
                data: arr,
                on: function(data){
                    //arr:  当前多选已选中的数据
                    forward_to='';
                    const checkedOptions = data.arr;
                    third_party.splice(0,third_party.length);
                    console.log(checkedOptions)
                    for(var i=0;i<checkedOptions.length;i++){
                        third_party.push(checkedOptions[i].value);
                        forward_to+=checkedOptions[i].value
                        forward_to+=','
                    }
                    if(forward_to.length!=0){
                        forward_to=forward_to.substring(0,forward_to.length-1)
                    }
                }
            })
        }
    });
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/inspectionCompany/getByApplicationId',
        dataType: 'json',
        data:{applicationId:applicationId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            if(data.length === 0){
                $("#appraisalList").hide();
            }else{
//                 $.ajax({
//                     type: "get",
//                     url: Feng.ctxPath + '/appraisal/getByApplicationId',
//                     dataType: 'json',
//                     data:{applicationId:applicationId},
//                     contentType: "application/json; charset=utf-8",
//                     success: function (data1) {
//                         console.log(data1)
//                         if(data1.length ===0){
// /////////未评估
//                         }else{
//                             for(let i=0;i<data1.length;i++){
//                                 console.log(data1);
//                             }
//                         }
//                     }
//                 })
            }
        }
    })


    var auditSuggestion="";
    form.on('select(auditSuggestion)',function (data) {
        console.log(data.value);
        if (data.value!=""){
            $("#commit").removeClass("layui-btn-disabled")
        }
        else {
            $("#commit").addClass("layui-btn-disabled")
        }
        auditSuggestion=data.value;
    });

    $('#third_party').click(function () {
        for(var i=0;i<third_party.length;i++){
            var ajax = new $ax(Feng.ctxPath + "/inspectionCompany/addInspectionCompany?third_party_id="+third_party[i]+"&application_id="+applicationId, function (data) {
                if(data+''==='0'){
                    Feng.error("指派失败！不能重复指派")
                }
                else{
                    Feng.success("指派成功！");
                    location.reload();
                }
            }, function (data) {
                Feng.error("指派失败！")
            });
            // ajax.set(data.field);
            ajax.start();
        }
    })

    appraisal.showInfo = function (data) {
        console.log(data)
        if(data.audit_time){
            //window.location.href=Feng.ctxPath+'/acceptance/FindAudit/'+data.acceptanceId+'/'+data.road_hazard_id;
            admin.putTempData('formOk', false);
            top.layui.admin.open({
                type: 2,
                title: '第三方核量',
                area: ['1000px', '800px'],
                content: Feng.ctxPath+'/acceptance/FindAudit/'+data.acceptance_id+'/'+data.road_hazard_id+'?check=1',
                end: function () {
                    admin.getTempData('formOk');
                    table.reload(roadHazardCountyFirstTrialList.tableId);
                }
            });
        }else{
            Feng.error("无法查看！")
        }
    };
    //填报人
    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/sys/road_user/selectOne',
        data:{id:userId},
        dataType: 'json',
        contentType: "application/json;charset=utf-8",
        success:function(userData){
            console.log(userData)
            form.val("applicationForm",{
                "county_applicant":userData[0].user_name,
                "work_frequency":1,
            })
            form.render();
            user_name = userData[0].user_name;
        }
    })

    $("#demo1").on("click",function () {
        if(roleId +'' ==='6'||roleId +'' === '13'){
            var ajax = new $ax(Feng.ctxPath + "/acceptance/firstAuditor?id="+userId+"&result=1&acceptanceId="+acceptanceId+"&applicationId="+applicationId+"&status="+status, function (data) {
                Feng.success("操作成功！");
            }, function (data) {
                Feng.error("操作失败！" + data.responseJSON.message)
            });
            ajax.start();
            window.location.reload()
            form.render();
            admin.closeThisDialog();
        }
        else if(roleId +'' ==='14'||roleId +'' === '1202130817007374338'){
            var ajax = new $ax(Feng.ctxPath + "/acceptance/secondAuditor?id="+userId+"&result=1&acceptanceId="+acceptanceId+"&applicationId="+applicationId+"&status="+"11", function (data) {
                Feng.success("操作成功！");
            }, function (data) {
                Feng.error("操作失败！" + data.responseJSON.message)
            });
            ajax.start();
            window.location.reload()
            form.render();
            admin.closeThisDialog();
        }

    })
    $("#demo3").on("click",function () {
        if(roleId +'' ==='6'||roleId +'' === '13'){
            var ajax = new $ax(Feng.ctxPath + "/acceptance/firstAuditor?id="+userId+"&result=0&acceptanceId="+acceptanceId+"&applicationId="+applicationId+"&status="+status, function (data) {
                Feng.success("操作成功！");
            }, function (data) {
                Feng.error("操作失败！" + data.responseJSON.message)
            });
            ajax.start();
            window.location.reload()
            form.render();
            admin.closeThisDialog();
        }
        else if(roleId +'' ==='14'||roleId +'' === '1202130817007374338'){
            var ajax = new $ax(Feng.ctxPath + "/acceptance/secondAuditor?id="+userId+"&result=0&acceptanceId="+acceptanceId+"&applicationId="+applicationId+"&status="+"11", function (data) {
                Feng.success("操作成功！");
            }, function (data) {
                Feng.error("操作失败！" + data.responseJSON.message)
            });
            ajax.start();
            window.location.reload()
            form.render();
            admin.closeThisDialog();
            // var ajax2 = new $ax(Feng.ctxPath + "/inspectionCompany/addInspectionCompany?third_party_id="+pid+"&application_id="+applicationId, function (data) {
            //     //Feng.success("指派成功！");
            //     location.reload();
            // }, function (data) {
            //     //Feng.error("指派失败！" + data.responseJSON.message)
            // });
            // // ajax.set(data.field);
            // ajax2.start();
        }
    })

    //核量审核通过
    $("#passBtn").click(function () {
        var ajax = new $ax(Feng.ctxPath + "/application/updateStatus?applicationId="+applicationId + "&check=1", function (data) {
            Feng.success("操作成功！");
        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });
        ajax.start();
        window.location.reload()
        form.render();
        //admin.closeThisDialog();
    })
    // 工具条点击事件
    table.on('tool(' + appraisal.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'showInfo') {
            console.log("部门部门部门")
            appraisal.showInfo(data);

        }
    });
});
