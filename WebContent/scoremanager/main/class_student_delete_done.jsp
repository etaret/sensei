<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
	<div class="text-container" style="position: relative; display: inline-block; width: 100%;">
	    <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
	        学生削除
	    </h2>
	    <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
	</div>
	<%-- エラー文表示 --%>
	<c:if test="${not empty error}">
        <p style="color: red; font-weight: bold;">${error}</p>
    </c:if>
    <%-- 結果表示 --%>
	<p class="col d-flex align-items-center justify-content-center mx-2 bg-success bg-opacity-50 text-dark py-1 px-4">
	${suc}</p>
	<div class="link-container">
		<a href="ClassList.action">クラス一覧</a>
	</div>
	<style>
	.link-container {
	    display: flex;            /* フレックスボックスに設定 */
	    gap: 100px;                /* リンク間に隙間を追加 (20px) */
	    margin-top: 100px;         /* 上の要素とリンクの間にマージンを追加 */
	}
	</style>

	</c:param>
</c:import>