<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報更新</h2>
        <div class="col-11 mx-auto">

            <!-- 成功・失敗メッセージ -->
            <c:if test="${not empty suc}">
                <p class="fs-5 fw-bold text-success">${suc}</p>
            </c:if>

            <!-- 戻るボタン -->
            <div class="mt-3">
                <a href="SubjectList.action" class="btn btn-secondary">科目一覧</a>
            </div>
        </div>
    </c:param>

</c:import>
