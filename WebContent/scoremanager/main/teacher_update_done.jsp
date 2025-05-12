<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員情報変更完了</h2>
		<div class="col-md-6 mx-auto">
			<p class="text-center fs-5">
				<c:choose>
					<c:when test="${not empty message}">
						${message}
					</c:when>
					<c:otherwise>
						教員情報が正常に更新されました。
					</c:otherwise>
				</c:choose>
			</p>
			<div class="text-center mt-4">
				<a href="TeacherList.action" class="btn btn-secondary">教員一覧へ戻る</a>
			</div>
		</div>
	</c:param>
</c:import>
