<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品详情" data-options="fit:true">
    <form class="itemForm" id="itemAddForm" name="itemAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <%--设计成下拉框--%>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品标题：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="title" name="title"
                           data-options="required:true" style="width:100%">
                </td>
            </tr>
            <tr>
                <td class="label">商品卖点：</td>
                <td>
                    <%--长度在0到150之间，两端包含。multiline为true，允许多行。--%>

                    <input class="easyui-textbox" type="text" id="sellPoint" name="sellPoint"
                           data-options="validType:'length[0,150]',multiline:true" style="width:100%;height:60px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品价格(元)：</td>
                <td>
                    <%--一个隐藏的，用来把转换后的数据保存的。--%>
                    <input class="easyui-numberbox" type="text" id="priceView" name="priceView"
                           data-options="required:true,min:0,precision:2">
                    <input type="hidden" id="price" name="price">
                </td>
            </tr>
            <tr>
                <td class="label">商品库存：</td>
                <td>
                    <%--precision:0。精度是0--%>
                    <input class="easyui-numberbox" type="text" id="num" name="num"
                           data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td class="label">条形码：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="barcode" name="barcode"
                           data-options="validType:'length[0,30]'">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="content" type="text/plain">商品描述</script>
                </td>
            </tr>

            <tr class="paramsShow" style="display:none;">
                <td class="label">商品规格：</td>
                <td>

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button onclick="submitForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button onclick="clearForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
        <input name="paramData" id="paramData" style="display:none;">
    </form>
</div>


<script>
    //提交表单
    function submitForm(){
        $('#itemAddForm').form('submit',{
            //提交表单到item进行处理,这里加/，工程名都没有了
            url: 'item',
            //在表单提交之前触发
            onSubmit:function () {
                <%--${this}表示form表单--%>
                //做表单校验，表单上所有字段全部校验通过才能返回true，才会提交表单，
                //如果有任意一个字段没有校验通过，返回false，不会提交表单
                return $(this).form('validate');
            },
            //后台处理成功之后的回调函数
            success:function(){
                console.log('success');
            }
        });
    }


    //实例化富文本编辑器
    var ue = UE.getEditor('container');

    //加载商品类目的树形下拉框
    $('#cid').combotree({
//        这里可以直接带参数，也可以通过隐藏域来传
//        因为涉及分类这个与商品联系密切的实体，但还是建议分开写，用新的action
        url: 'itemCats?parentId=0',
        required: true,
//        这下面写代码，先YY一个请求。
//        写了下面这两个方法才能够做到点击到叶子节点，不然只能点击到父节点，不显示子节点。
        onBeforeExpand: function (node) {
            //获取当前被点击的tree
            var $currentTree = $('#cid').combotree('tree');
            //调用easyui tree组件的options方法
            var option = $currentTree.tree('options');
            //修改option的url属性
            option.url = 'itemCats?parentId=' + node.id;
        },
//        node是传进来 的参数
        onBeforeSelect: function (node) {
//            这里打印的是选中的那条商品类别信息，tb_item_cat
            console.log(node);
            //判断选中节点是否为叶子节点，如果是，返回true
            var isLeaf = $('#cid').tree('isLeaf', node.target);
            //如果后台管理员选中的不是叶子节点的话，给出警告框
            if (!isLeaf) {
                $.messager.alert('警告', '请选中最终的类别！', 'warning');
                return false;
            }

        }
    });
</script>