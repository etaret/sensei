<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>科目更新完了</title>
</head>
<body>
    <h2>科目更新完了</h2>

    <!-- 成功メッセージの表示 -->
    <c:if test="${not empty suc}">
        <p style="color:green;">${suc}</p>
    </c:if>

    <a href="subject_list.jsp">科目一覧に戻る</a>
</body>
</html> 