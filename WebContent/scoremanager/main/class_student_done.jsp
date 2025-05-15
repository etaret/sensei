<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<div class="text-container position-relative w-100">
		    <h2 class="h3 mb-3 fw-normal py-2 px-4 bg-white position-relative d-inline-block ms-3">
				${title}
		    </h2>
		    <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
		</div>

		<!-- エラー文表示 -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger text-center fw-bold" role="alert">
				${error}
			</div>
		</c:if>

		<!-- 結果表示 -->
		<c:if test="${not empty suc}">
			<div class="alert alert-success text-center fw-bold" role="alert">
				${suc}
			</div>
		</c:if>

		<div class="d-flex justify-content-between mt-5 px-4">
			<a href="ClassList.action" class="btn btn-outline-secondary">クラス一覧</a>
		</div>
	</c:param>
</c:import>
