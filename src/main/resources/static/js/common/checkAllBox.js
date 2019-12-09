function bindCheckAllBtn($checkAll,checkBoxContainerId) {
    //全选
    $checkAll.change(function () {
        var $this = $(this);
        setCheckBoxVal($(""+checkBoxContainerId).find("input:checkbox"),getCheckBoxVal($this));
    });

    //#opt 优化点击行选择
    $(document).on("click",""+checkBoxContainerId+" tr",function () {
        var $obj = $(this).find("input:checkbox");
        setCheckBoxVal($obj,!getCheckBoxVal($obj));

        repaintCheckAllbox($checkAll,checkBoxContainerId);
    });
    $(document).on("click",""+checkBoxContainerId+" tr input:checkbox",function (e) {
        e.stopPropagation();

        repaintCheckAllbox($checkAll,checkBoxContainerId);
    });
}

function repaintCheckAllbox($checkAll,checkBoxContainerId) {
    var totalSize = $(""+checkBoxContainerId+" input:checkbox:visible").size();
    if(totalSize == 0){
        setCheckBoxVal($checkAll,false);
        return ;
    }

    if($("#notBindList input:checkbox:visible:checked").size() == totalSize){
        setCheckBoxVal($checkAll,true);
    } else {
        setCheckBoxVal($checkAll,false);
    }
}

function getCheckBoxVal($obj) {
    var v = $obj.attr('checked');
    if(v) {
        return true;
    }else {
        return false;
    }
}
function setCheckBoxVal($obj,b) {
    if (b) {
        $obj.attr("checked", "checked");
    } else {
        $obj.removeAttr("checked");
    }
}