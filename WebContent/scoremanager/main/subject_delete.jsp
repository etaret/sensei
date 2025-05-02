<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
        <div class="col-11 mx-auto">

            <form action="SubjectDeleteExecute.action" method="post">

                <!-- 削除確認メッセージ -->
                <div class="mb-4">
                    <p class="fs-5">「${subjectName}（${subjectcd}）」を削除してよろしいですか？</p>
                </div>

                <!-- hidden: 削除対象ID -->
                <input type="hidden" name="subjectId" value="${subjectcd}" />

                <!-- ボタン -->
                <div class="mt-3">
                    <button type="submit" class="btn btn-danger">削除</button>

                </div>

            </form>
            <div class="col-12 mb-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </div>
    </c:param>

</c:import>
