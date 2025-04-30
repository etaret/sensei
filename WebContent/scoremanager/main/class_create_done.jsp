<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<div class="text-container" style="position: relative; display: inline-block; width: 100%;">
		    <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
		        クラス情報登録
		    </h2>
		    <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
		</div>

		<!-- 登録成功メッセージ -->
		<c:if test="${not empty suc}">
			<div class="alert alert-success text-center fw-bold" role="alert">
				${suc}
			</div>
		</c:if>

		<div class="d-flex justify-content-between mt-5 px-4">
			<a href="ClassCreate.action" class="btn btn-outline-primary">追加登録</a>
			<a href="ClassList.action" class="btn btn-outline-secondary">クラス一覧へ</a>
		</div>

	</c:param>
</c:import>
