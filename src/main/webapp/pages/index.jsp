<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--输出,条件,迭代标签库-->
<%@ page isELIgnored="false" %>
<!--支持EL表达式，不设的话，EL表达式不会解析-->
<html>
<head>
    <title>Title</title>
</head>
<body>
<tbody>

<c:forEach items="${clieteList}" var="product">

    <tr>
        <td>${product.id }</td>
        <td>${product.fz }</td>
        <td>${product.tyre.pattern}</td>
        <td>${product.tyre.cliet.createddate}</td>

    </tr>
</c:forEach>
</tbody>

</body>
</html>
