<%--
  Created by IntelliJ IDEA.
  User: Danger
  Date: 2018/1/13
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<script src="/js/jquery/2.0.0/jquery.min.js"></script>
<link href="/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<link href="/css/fore/style.css" rel="stylesheet">

<script>
    function formatMoney(num) {
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*100+0.50000000001);
        cents = num%100;
        num = Math.floor(num/100).toString();
        if(cents<10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+','+
                num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + num + '.' + cents);
    }
    function checkEmpty(id,name) {
        var value=$("#"+id).val();
        if (value.length==0){
            $("#"+id)[0].focus();
            return false;
        }
        return true;
    }

</script>
</html>

