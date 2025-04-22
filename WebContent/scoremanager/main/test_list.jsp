<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="m-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div class="border m-3 p-3 rounded">
				<%-- ★★★ フォーム 1: 科目情報フィルタ ★★★ --%>
				<form method="get">
					<%-- ★ 見出しを削除 --%>
					<%-- <h4 class="mb-3">科目情報</h4> --%>
					<div class="row g-3 align-items-center mb-3"> <%-- align-items-center に変更 --%>
						<%-- 見出し列 (col-md-2) --%>
						<div class="col-md-2 d-flex align-items-center">
							<p class="mb-0">科目情報</p>
						</div>
						<%-- 入学年度 (col-md-2) --%>
						<div class="col-md-2">
							<label class="form-label" for="filter-f1-select">入学年度</label>
							<select class="form-select" id="filter-f1-select" name="f1">
								<option value="0">--------</option>
								<c:forEach var="year" items="${ent_year_set}">
									<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
								</c:forEach>
							</select>
						</div>
						<%-- クラス (col-md-2) --%>
						<div class="col-md-2">
							<label class="form-label" for="filter-f2-select">クラス</label>
							<select class="form-select" id="filter-f2-select" name="f2">
								<option value="0">--------</option>
								<c:forEach var="num" items="${class_num_set}">
									<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
								</c:forEach>
							</select>
						</div>
						<%-- 科目 (col-md-3) --%>
						<div class="col-md-3">
							<label class="form-label" for="filter-f3-select">科目</label>
							<select class="form-select" id="filter-f3-select" name="f3">
								<option value="0">--------</option>
								<c:forEach var="subject" items="${subject_set}">
									<option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.name}</option>
								</c:forEach>
							</select>
						</div>
						<%-- 検索ボタン (col-md-3) --%>
						<div class="col-md-3 d-flex align-items-end justify-content-center">
							<button class="btn btn-secondary" id="filter-button-subject">検索</button>
						</div>
					</div>
				</form>

				<%-- 区切り線 --%>
				<hr class="my-3">

				<%-- ★★★ フォーム 2: 学生番号検索 ★★★ --%>
				<form method="get">
					<div class="row g-3 align-items-center"> <%-- align-items-center に変更 --%>
						<%-- ラベル列 (col-md-2) --%>
						<div class="col-md-2 d-flex align-items-center">
							<%-- ★ ラベルを入力欄の左に移動 --%>
							<label class="mb-0">学生情報</label> <%-- 太字に --%>
						</div>
						<div class="col-md-6">
							<label class="form-label" for="filter-f4-input">学生番号</label>
							<input type="text" id="filter-f4-input" name="f4" class="form-control" value="${f4}" placeholder="学生番号を入力してください">
						</div>
						<%-- 検索ボタン (col-md-4) --%>
						<div class="col-md-4 d-flex align-items-center justify-content-center">
							<button class="btn btn-secondary" id="filter-button-student">検索</button>
						</div>
					</div>
				</form>

				<%-- エラーメッセージ表示 (フォームの外) --%>
				<div class="mt-3 text-warning">${errors.get("filter")}</div> <%-- エラーキーを汎用的なものに? --%>
			</div>

			<c:choose>
				<c:when test="${tests != null && tests.size()>0}">
					<div>検索結果：<c:out value="${tests.size()}" default="0"/>件</div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>回数</th>
								<th>点数</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="test" items="${tests}">
								<tr>
									<td>${test.student.entYear}</td>
									<td>${test.classNum}</td>
									<td>${test.student.no}</td>
									<td>${test.student.name}</td>
									<td>${test.no}</td>
									<td>${test.point}</td>
									<td>
										<a href="TestRegistExecute.action?f4=${test.student.no}&f5=${test.subject.cd}&f6=${test.no}">変更</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div>成績情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>