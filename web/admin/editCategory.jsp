<%--
  Created by IntelliJ IDEA.
  User: Danger
  Date: 2017/11/18
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<title>编辑分类</title>
<div class="workingArea">
    <ol class="breadcrumb">
        <li>所有分类</li>
        <li>编辑分类</li>
    </ol>
    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑分类</div>
        <div class="panel-body">
            <%--enctype="multipart/form-data"是上传二进制数据--%>
            <form method="post" id="editForm" action="admin_category_update" enctype="multipart/form-data">
                <table class="editTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input id="name" name="name" value="${c.name}" type="text"></td>
                    </tr>
                    <tr>
                        <td>分类图片</td>
                        <td><input id="categoryPic" accept="image/*" type="file" name="filepath"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${c.id}">
                            <button type="submit" class="btn btn-success">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
