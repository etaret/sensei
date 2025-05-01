<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

	<c:param name="content">
<div id="wrap-box">
<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績情報登録</h2>
<div id="wrap-box">
<p class="text-center" style="backround-color:#8cc3a9">登録が完了しました</p>

<br>
<br>
<br>

<a href="TestRegist.action">戻る</a>
<a href="TestList.action">成績参照</a>
</div>
</div>
</c:param>
</c:import>