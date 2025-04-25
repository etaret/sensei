<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス情報変更</h2>

		<!-- 結果表示 -->
		<c:if test="${not empty suc}">
			<div class="alert alert-success text-center fw-bold" role="alert">
				${suc}
			</div>
		</c:if>

		<!-- リンク配置（再度変更：左、クラス一覧：右） -->
		<div class="d-flex justify-content-between mt-5 px-4">
			<a href="ClassList.action" class="btn btn-outline-secondary">クラス一覧へ</a>
		</div>

	</c:param>
</c:import>
