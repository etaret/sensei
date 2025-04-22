<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目情報削除</title>



</head>
<body>
    <div class="container">
        <h2>科目情報削除</h2>
        <div class="message">
            「${subjectName} ${subjectcd}」を削除してよろしいですか？
        </div>
        <form action="SubjectDeleteExecute.action" method="post">
            <input type="hidden" name="subjectId" value="${subjectcd}" />
            <button type="submit" class="btn-danger">削除</button>
            <a href="SubjectList.action" class="btn-back">戻る</a>
        </form>
    </div>
</body>
</html>
