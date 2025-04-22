<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>科目更新</title>
</head>
<body>
    <h2>科目更新フォーム</h2>

    <!-- エラーメッセージの表示 -->
    <c:if test="${not empty sderror}">
        <p style="color:red;">${sderror}</p>
    </c:if>

    <!-- 科目情報の入力フォーム -->
    <form action="SubjectUpdateExecuteAction" method="post">
        <label for="subject_cd">科目コード:</label>
        <input type="text" id="subject_cd" name="subject_cd" value="${f1}" required><br>

        <label for="subject_name">科目名:</label>
        <input type="text" id="subject_name" name="subject_name" value="${f2}" required><br>

        <!-- 送信ボタン -->
        <input type="submit" value="更新">
    </form>

    <a href="subject_list.jsp">戻る</a>
</body>
</html> 