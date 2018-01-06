<%--
  Created by IntelliJ IDEA.
  User: Danger
  Date: 2017/11/30
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<title>产品管理</title>
<script>
    $(function () {
        $("#addForm").submit(function () {
            if (!checkEmpty("name","产品名称"))
                return false;
            if (!checkNumber("orignalPrice","原价格"))
                return false;
            if (!checkNumber("promotePrice","优惠价格"))
                return false;
            if (!checkInt("stock","库存"))
                return false
            return true;
        });
    });
</script>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${c.id}">${c.name}</a></li>
        <li class="active">产品管理</li>
    </ol>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <td>ID</td>
                <td>图片</td>
                <td>产品名称</td>
                <td>产品小标题</td>
                <td width="80px">原价格</td>
                <td width="80px">优惠价格</td>
                <td width="80px">库存数量</td>
                <td width="80px">图片管理</td>
                <td width="80px">设置属性</td>
                <td width="42px">编辑</td>
                <td width="42px">删除</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ps}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>
                        <c:if test="${!empty p.firstProductImage}">
                            <img src="img/productSingle/${p.firstProductImage.id}.jpg" width="40px">
                        </c:if>
                    </td>
                    <td>${p.name}</td>
                    <td>${p.subTitle}</td>
                    <td>${p.orignalPrice}</td>
                    <td>${p.promotePrice}</td>
                    <td>${p.stock}</td>
                    <td><a href="admin_productImage_list?pid=${p.id}"><span class="glyphicon glyphicon-picture"></span></a></td>
                    <td><a href="admin_product_editPropertyValue?id=${p.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a href="admin_product_edit?id=${p.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="true" href="admin_product_delete?id=${p.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp"%>
    </div>
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">增加产品</div>
        <div class="panel-body">
            <form method="post" action="admin_product_add" id="addForm">
                <table class="addTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>产品小标题</td>
                        <td><input id="subTitle" name="subTitle" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="orignalPrice" name="orignalPrice" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input id="promotePrice" name="promotePrice" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="stock" name="stock" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="hidden" name="cid" value="${c.id}">
                            <button type="submit" class="btn btn-success">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp"%>
