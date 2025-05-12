<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">先生情報削除</h2>
	<%-- 結果表示 --%>
	<p class="col d-flex align-items-center justify-content-center mx-2 bg-success bg-opacity-50 text-dark py-1 px-4">
	削除が完了しました</p>
	<br>
	<br>
	<br>
	<div class="link-container">
		<a href="TeacherList.action">先生一覧</a>
	</div>

	</c:param>
</c:import>