layui.use(['layer', 'form', 'ztree', 'laydate', 'admin', 'ax', 'table', 'treetable'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var roadHazardId=Feng.getUrlParam("roadHazardId");
    var current;
    var list=[
        {title:"提交问题",state:false,isNow:false},
        {title:"县区填报",state:false,isNow:false},
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

    $.ajax({
        type: "get",
        url: Feng.ctxPath + '/flow/getStatus?roadHazardId='+roadHazardId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data)
            current=data-1;
            if (current>=13) {
                current = current - 1;
            }
            $.each(list, function (index, item) {
                console.log("index",index)
                if(index<12){
                    if(index<current){
                        $("#contentBox").append(
                            `<div style="display: inline-block;float: left;width: auto;margin:10px 0;">
                                <button type="button" class="layui-btn" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
                                <div style="display: inline-block;width: 30px;height: 1px;background-color: #000000;margin:0 5px;"></div>
                             </div>`
                        )
                    }else if(index==current){
                        $("#contentBox").append(
                            `<div style="display: inline-block;float: left;width: auto;margin:10px 0;">
                                <button type="button" class="layui-btn layui-btn-normal" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
                                <div style="display: inline-block;width: 30px;height: 1px;background-color: #000000;margin:0 5px;"></div>
                             </div>`
                        )
                    }else{
                        $("#contentBox").append(
                            `<div style="display: inline-block;float: left;width: auto;margin:10px 0;">
                                <button type="button" class="layui-btn layui-btn-primary" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
                                <div style="display: inline-block;width: 30px;height: 1px;background-color: #000000;margin:0 5px;"></div>
                             </div>`
                        )
                    }
                }else{
                    if(index==current && index != 12){
                        $("#contentBox").append(
                            `<div style="display: inline-block;float: left;width: 120px;margin:10px 0;">
                                <button type="button" class="layui-btn layui-btn-normal" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
                            </div>`
                         )
                    }else if (index==current && index == 12 && data == 14) {
                        $("#contentBox").append(
                            `<div style="display: inline-block;float: left;width: auto;margin:10px 0;">
                                <button type="button" class="layui-btn" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
                                <div style="display: inline-block;width: 30px;height: 1px;background-color: #000000;margin:0 5px;"></div>
                             </div>`
                        )
                    }
                    else{
                        $("#contentBox").append(
                            `<div style="display: inline-block;float: left;width: 120px;margin:10px 0;">
                                <button type="button" class="layui-btn layui-btn-primary" style="height: 35px;width: 160px;border-radius:10px;">${index+1}.${item.title}</button>
                            </div>`
                        )
                    }
                }
            });
        }
    });
});
