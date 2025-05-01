<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">成績登録</c:param>

    <c:param name="scripts"></c:param>

    	<c:param name="content">
		<section class="m-5">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div class="border m-3 p-3 rounded">
				<form method="get" action="TestRegist.action">
					<div class="row g-3 align-items-center mb-3"> <%-- align-items-center に変更 --%>
						<div class="col-md-2">
							<label class="form-label" for="filter-f1-select">入学年度</label>
							<select class="form-select" id="filter-f1-select" name="ent_year">
								<option value="0">--------</option>
								<c:forEach var="year" items="${ent_year_set}">
									<option value="${year}" <c:if test="${year == param.ent_year}">selected</c:if>>${year}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<label class="form-label" for="filter-f2-select">クラス</label>
							<select class="form-select" id="filter-f2-select" name="class_num">
								<option value="0">--------</option>
								<c:forEach var="num" items="${class_num_set}">
									<option value="${num}" <c:if test="${num == param.class_num}">selected</c:if>>${num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-3">
							<label class="form-label" for="filter-f3-select">科目</label>
							<select class="form-select" id="filter-f3-select" name="subject">
								<option value="0">--------</option>
								<c:forEach var="subject" items="${subject_set}">
									<option value="${subject.cd}" <c:if test="${subject.cd == param.subject}">selected</c:if>>${subject.name}</option>
								</c:forEach>
							</select>
						</div>
                        <div class="col-md-2">
							<label class="form-label" for="filter-f4-select">回数</label>
							<select class="form-select" id="filter-f4-select" name="num">
								<option value="0">--------</option>
								<c:forEach var="testNo" items="${test_no_set}">
									<option value="${testNo}" <c:if test="${testNo == param.num}">selected</c:if>>${testNo}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2 d-flex align-items-end justify-content-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
					</div>
				</form>

				<div class="mt-3 text-warning">${errors.get("filter")}</div>
				<div class="mt-1 text-warning">${errors.get("ent_year")}</div> <%-- 入学年度エラー表示 --%>
				<div class="mt-1 text-warning">${errors.get("class_num")}</div> <%-- クラスエラー表示 --%>
				<div class="mt-1 text-warning">${errors.get("subject")}</div> <%-- 科目エラー表示 --%>
				<div class="mt-1 text-warning">${errors.get("num")}</div> <%-- テスト回数エラー表示を追加 --%>

				<c:if test="${not empty tests}">
					<form method="post" action="TestRegistExecute.action">
						<table class="table table-hover mt-3">
							<thead>
								<tr>
									<th>入学年度</th>
									<th>クラス</th>
									<th>学生番号</th>
									<th>氏名</th>
									<th>得点</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="test" items="${tests}" varStatus="status">
									<tr>
										<td>${test.student.entYear}</td>
										<td>${test.classNum}</td>
										<td>${test.student.no}</td>
										<td>${test.student.name}</td>
										<td>
											<input type="number" name="points[${status.index}]" value="${test.point}" min="0" max="100" class="form-control form-control-sm" style="width: 80px;">
											<input type="hidden" name="studentNos[${status.index}]" value="${test.student.no}">
											<input type="hidden" name="subjectCds[${status.index}]" value="${param.subject}">
											<input type="hidden" name="testNos[${status.index}]" value="${param.num}">
											<input type="hidden" name="classNums[${status.index}]" value="${test.classNum}">
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="d-flex justify-content-center mt-3">
							 <button type="submit" class="btn btn-primary">登録</button>
						</div>
					</form>
				</c:if>
				 <c:if test="${empty tests and not empty param.ent_year and empty errors}"> <%-- errorsが空の場合のみ表示 --%>
					<div class="mt-3 alert alert-warning">成績情報が存在しませんでした。</div>
				 </c:if>

			</div>
		</section>
	</c:param>
</c:import>
