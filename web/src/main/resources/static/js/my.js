function request(strParame) {
    var args = new Object();
    var query = location.search.substring(1);

    var pairs = query.split("&"); // Break at ampersand
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('=');
        if (pos == -1) continue;
        var argname = pairs[i].substring(0, pos);
        var value = pairs[i].substring(pos + 1);
        value = decodeURIComponent(value);
        args[argname] = value;
    }
    return args[strParame];
}

//路径
var message_zh_min_url = '/jquery-validation-1.19.0/dist/localization/messages_zh.min.js';

//多语言跳转
function setLang(lang) {
    var url = location.href;
    //包含lang ，替换
    if (url.indexOf('lang=') != -1) {
        if ('en_US' == lang) {
            location.href = url.replace(/lang=[^\&]*/, "lang=en_US");
        } else {
            location.href = url.replace(/lang=[^\&]*/, "lang=zh_CN");
        }
    }
    //不包含lang(indexOf()>-1)，附加
    else {
        if ('en_US' == lang) {
            location.href = url + "?lang=en_US";
        } else {
            location.href = url + "?lang=zh_CN";
        }
    }
}

//显示当前账号
var curAccount = $('#curAccount').text();
$("#currentUser").text(curAccount);
//编辑当前用户
function editCurAccount() {
    if ("root" == curAccount.toLowerCase()) {
        swal('提示', 'root账号，不能编辑！', 'warning');
        return;
    }
    else{
        var params = {
            account: curAccount
        };
        $.ajax({
            url: '/users/getUsers',
            data: params,
            dataType: 'json',
            type: "post",
            success: function (data) {
                if (data) {
                    if (data.code) {
                        //清空 文本框
                        $('#editCurAccountModal .m-input').val('');

                        var sysUsers = data.sysUsers;
                        $('#editCurAccountId').val(sysUsers.id);
                        $('#editCurAccountName').val(sysUsers.name);
                        $('#editCurAccountAccount').val(sysUsers.account);
                        $('#editCurAccountTel').val(sysUsers.tel);
                        $('#editCurAccountEmail').val(sysUsers.email);

                        $('#editCurAccountModal').modal('show');
                    } else {
                        swal('提示', data.msg, 'warning');
                    }
                }
            }
        });
    }
}
$(function () {
    //修改账号信息表单
    $("#editCurAccountForm").validate({
            highlight: function (e) {
                $(e).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (e) {
                $(e).closest('.form-group').removeClass('has-error');
            }
            ,
            submitHandler: function () {
                //保存当前帐号信息
                saveCurSysUsers();
            }
        }
    );
    //修改密码表单
    $("#updatePwdForm").validate({
            highlight: function (e) {
                $(e).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (e) {
                $(e).closest('.form-group').removeClass('has-error');
            }
            ,
            submitHandler: function () {
                updatePassword();
            }
        }
    );
});
//保存当前帐号信息
function saveCurSysUsers() {
    var params = {
        id: $.trim($('#editCurAccountId').val()),
        name: $.trim($('#editCurAccountName').val()),
        account: $.trim($('#editCurAccountAccount').val()),
        tel: $.trim($('#editCurAccountTel').val()),
        email: $.trim($('#editCurAccountEmail').val())
    }
    $.ajax({
        type: "post",
        data: params,
        url: "/users/addSysUsers",
        dataType: "json",
        success: function (data) {
            if (data.code) {
                swal('提示', data.msg, 'success');
            } else {
                swal('提示', data.msg, 'warning');
            }
        }
    })
}
//修改密码
$('#updatePwdBtn').click(function () {
    //清空 文本框
    $('#updatePwdModal .m-input').val('');
    $('#editCurAccountModal').modal('hide');
    $('#updatePwdModal').modal('show');
})
function updatePassword() {
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var confirmPassword = $("#confirmPassword").val();
    if(newPassword != confirmPassword){
        swal('提示', '两次输入密码不一样！', 'warning');
        return;
    }
    var params = {
        account: curAccount,
        oldPassword: oldPassword,
        newPassword: newPassword
    }
    $.ajax({
        type: "post",
        data: params,
        url: "/users/updatePassword",
        dataType: "json",
        success: function (data) {
            if (data.code) {
                swal('提示', data.msg, 'success');
                // setTimeout(function () {
                //     $('#updatePwdModalModal').modal('hide');
                // }, 1000);
            } else {
                swal('提示', data.msg, 'warning');
            }
        }
    })
}
