/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var ok = true;

function check(ps) {
    var pw = $.trim($("#pw").val());
    var rpw = $.trim($("#rpw").val());
    var ps = ps;
    var rule1 = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,}$/;
    var rule2 = /[^\d]/ig;
    var name = $.trim($("#cute").val());
    if (name.length === 0) {
        alert("昵称不能为空！")
        ok = false;
    } else if (pw.length == 0) {
        alert("密码不能为空！");
        ok = false;
    } else if (!pw.match(rule1) && !pw.match(rule2) && pw !== "" || pw.length < 6) {
        alert("密码需为英文、数字或符号,长度必须大于6位,且不得是纯数字");
        ok = false;
    }
    else if (rpw.length == 0) {
        alert("确认密码不能为空！");
        ok = false;
    }
    else if (pw != rpw) {
        alert("两次密码不同请重新输入");
        ok = false;
    }
    else if (pw == ps) {
        alert("密码未修改");
        ok = false;
    }


}

function previewImage(file)
{
    var MAXWIDTH = 200;
    var MAXHEIGHT = 200;
    var div = document.getElementById('preview');
    if (file.files && file.files[0])
    {
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
            img.style.marginTop = rect.top + 'px';
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
        }
        reader.readAsDataURL(file.files[0]);
    }
    else //兼容IE
    {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
    }
}
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {top: 0, left: 0, width: width, height: height};
    if (width > maxWidth || height > maxHeight)
    {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;

        if (rateWidth > rateHeight)
        {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else
        {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }

    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}



function checkAll(ps) {
    check(ps);
   // var wait = false;
    if (ok == true) {
        alert("资料修改成功！");
        var form = document.getElementById("myForm2");
        //var form1 = document.getElementById("myForm2");
        form.submit();
        
    }
}

