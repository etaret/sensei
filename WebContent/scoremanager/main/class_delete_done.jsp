<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス削除</h2>

		<!-- エラーメッセージ -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger text-center fw-bold" role="alert">
				${error}
			</div>
		</c:if>

		<!-- 成功メッセージ -->
		<c:if test="${not empty suc}">
			<div class="alert alert-success text-center fw-bold" role="alert">
				${suc}
			</div>
		</c:if>

		<!-- リンク配置 -->
		<div class="d-flex justify-content-center mt-5 px-4">
			<a href="ClassList.action" class="btn btn-outline-secondary">クラス一覧へ</a>
		</div>

	</c:param>
</c:import>
