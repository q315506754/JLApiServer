var api_prefix = "https://aries-app.g2s.cn";
// var api_prefix = "http://120.92.138.210:20020";

function pendRow(key,v) {
    $(".container").append(`
            <div class="row">   
                <div class="title">${key}</div>
                <div class="value">${v}</div>
            </div>
        `);
}

function requestAries(path,dt,cb) {
    dt = dt || {};

    $.ajax({
        type: "POST",
        url:api_prefix+"/getUserInfo",
        beforeSend: function(request) {
            // request.setRequestHeader("Referer","https://jl.g2s.cn");
        },
        data:dt,
        success: function (response) {
            console.log(response);

            var dt = response.rt;

            if (dt) {
                cb(dt);
            }
        }
    });
}

$(function () {
    console.log("loaded..");

    var obj = GetRequest();
    var partnerId = obj['partnerId'];

    // for (var key in obj) {
    //     pendRow(key,obj[key]);
    // }

    pendRow('知室uid',partnerId);

    requestAries("/getUserInfo",{
        uid:partnerId
    },function (dt) {
        var user = dt.user;
        pendRow('用户姓名',user.name);
        pendRow('用户头像',`<img src="${user.avatar}"></img>`);
        pendRow('手机号',user.mobile);
        pendRow('邮箱',user.email);
        pendRow('企业id',user.companyId);
        pendRow('企业',user.describe);
    })
});