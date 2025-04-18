<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
	<p class="col d-flex align-items-center justify-content-center mx-2 bg-success bg-opacity-50 text-dark py-1 px-4">
	変更が完了しました</p>
	<div class="link-container">
		<a href="StudentList.action">学生一覧</a>
	</div>
	<style>
	.link-container {
	    display: flex;            /* フレックスボックスに設定 */
	    margin-top: 100px;         /* 上の要素とリンクの間にマージンを追加 */
	}
	</style>

	</c:param>
</c:import>