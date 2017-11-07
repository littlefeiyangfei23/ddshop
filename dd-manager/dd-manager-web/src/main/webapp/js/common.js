
var ddshop = {

    registerMenuEvent:function(){

            //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
            var $tree = $('#menu .easyui-tree');
            $tree.tree({
                onClick:function(node){
                    var href = node.attributes.href;//得到item-add
                    var text = node.text;
//                产生一个tab,
//               href: href,  从URL加载远程数据内容填充到选项卡面板。
                    $('#tab').tabs('add',{
                        title: text,
                        href: href,
                        closable:true
                    });
                }
            });

    }

};



