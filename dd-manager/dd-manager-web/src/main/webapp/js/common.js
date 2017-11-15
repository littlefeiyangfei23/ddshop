
var ddshop = {

    registerMenuEvent:function(){
             var _this=this;
            //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
            var $tree = $('#menu .easyui-tree');
            $tree.tree({
                onClick:function(node){
                    var href = node.attributes.href;//得到item-add
                    var text = node.text;
                    // console.log(this);  //打印的是ul.这是一个DOM对象
                    // console.log(_this);  //打印了ddshop这个变量
                    _this.addTabs(text,href);//调用方法
//                产生一个tab,
//               href: href,  从URL加载远程数据内容填充到选项卡面板。

                }
            });

    },



        addTabs:function(text,href){
            // exists是判断，如果存在，选择已经开着的选项卡。没有的话，再新建一个。
            if($('#tab').tabs('exists',text)){
                $('#tab').tabs('select',text)
            }else{
                $('#tab').tabs('add',{
                    title: text,
                    href: href,
                    closable:true
                });
            }


        },

    // 下面的方法还可以优化
       /* closeTab:function (){
            if($("#tab").tabs("exists","查询商品")){
                $("#tab").tabs("close","查询商品");
            }

            if($("#tab").tabs("exists","新增商品")){
                $("#tab").tabs("close","新增商品");
            }

            this.addTabs("查询商品","item-list");

        }*/
       closeTabs:function (title) {
           $('#tab').tabs('close',title);

       }

};



