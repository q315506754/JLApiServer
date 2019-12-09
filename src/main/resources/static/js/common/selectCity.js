//$objArr 多级jquery对象
//选择层级
//选择层级对应的id,反向选择前面几级
function bindSelectCity($objArr,id) {
    var layerSelectFilter = "citySelect";
    var MAX_LAYER = $objArr.length;
    var relaModulePath = '/tblCity';

    console.log(arguments);

    function Plugin() {
        this._initProcess();
    }

    Plugin.prototype = {
        _initProcess: function () {
            var tthis = this;
            tthis._bindEvents();

            tthis._refreshOptions($objArr[0],0,function () {
                tthis._setInitVal(id);
            });
        }
        ,_reset: function () {
            var tthis = this;

            // tthis._refreshOptions($objArr[0],0);
            tthis._triggerChanged($objArr[0],"");
        }
        ,_setInitVal: function (idid) {
            console.log(idid);
            var tthis = this;

            //ajax sync
            if (idid) {
                $.ajax({
                    url: basePath + relaModulePath + "/queryAncestors",
                    type: "POST",
                    data: {id:idid},
                    async:true,
                    success: function (arrs) {
                        // var arrs = [12,22];

                        for(var i=0;i<arrs.length;i++){
                            var one = arrs[i];
                            tthis._triggerChanged($objArr[i],one.id);
                        }
                    }
                });
            }

            // var arrs = [12,22];
            //
            // for(var i=0;i<arrs.length;i++){
            //     var one = arrs[i];
            //     this._triggerChanged($objArr[i],one);
            // }
        }
        ,_bindEvents: function () {
            var layer = 1;
            var tthis = this;

            //重新渲染弹窗 并且监听下拉事件
            layui.use(['form', 'layer'], function () {
                var form = layui.form;
                form.on(`select(${layerSelectFilter})`, function (data) {
                    var _val = data.value;
                    // console.log('');
                    // console.log($(this));

                    tthis._triggerChanged($(this).parents(".layui-form-select").prev("select"),_val)
                });
            });

            for(var i=0;i<$objArr.length;i++){
                var $one = $objArr[i];
                $one.data("_layer",layer++);
                $one.change(function () {
                    // console.log($one.data("_layer"));
                    tthis._triggerChanged($one,$one.val())
                });
            }
        }
        ,_triggerChanged: function ($obj,v) {
            // console.log($obj);
            // console.log($obj.val());
            var tthis = this;
            var layer = $obj.data("_layer");

            tthis._setVal($obj,v);

            if (layer<MAX_LAYER) {
                tthis._refreshOptions($objArr[layer],v);
            }
        }
        ,_refreshOptions: function ($obj,pid,cb) {
            // console.log($obj);
            // console.log($obj.val());
            var tthis = this;
            var layer = $obj.data("_layer");

            $obj.empty();

            if (layer == 1) {
                $obj.append(`<option value="">请选择</option>`);
            }

            //ajax sync
            $.ajax({
                url: basePath + relaModulePath + "/doQueryList",
                type: "POST",
                data: {pageSize:999,pid:pid===''?-1:pid},
                async:false,
                success: function (pg) {
                    // var arrs = [12,22];

                    // console.log(pg);

                    for(var i=0;i<pg.records.length;i++){
                        var one = pg.records[i];
                        $obj.append(`<option value="${one.id}">${one.name}</option>`);
                    }
                }
            });


            tthis._setVal($obj,0);

            if (cb) {
                cb();
            }
        }
        ,_setVal: function ($obj,v) {
            // console.log($obj);
            // console.log($obj.val());
            $obj.val(v);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        }
    }

    return new Plugin();
}