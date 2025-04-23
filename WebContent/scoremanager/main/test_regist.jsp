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
				<form method="get" action="TestRegistExecute.action">
					<div class="row g-3 align-items-center mb-3"> <%-- align-items-center に変更 --%>
						<div class="col-md-2">
							<label class="form-label" for="filter-f1-select">入学年度</label>
							<select class="form-select" id="filter-f1-select" name="f1">
								<option value="0">--------</option>
								<c:forEach var="year" items="${ent_year_set}">
									<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<label class="form-label" for="filter-f2-select">クラス</label>
							<select class="form-select" id="filter-f2-select" name="f2">
								<option value="0">--------</option>
								<c:forEach var="num" items="${class_num_set}">
									<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-3">
							<label class="form-label" for="filter-f3-select">科目</label>
							<select class="form-select" id="filter-f3-select" name="f3">
								<option value="0">--------</option>
								<c:forEach var="subject" items="${subject_set}">
									<option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.name}</option>
								</c:forEach>
							</select>
						</div>
                        <div class="col-md-2">
							<label class="form-label" for="filter-f3-select">回数</label>
							<select class="form-select" id="filter-f3-select" name="f4">
								<option value="0">--------</option>
								<c:forEach var="testNo" items="${test_no_set}">
									<option value="${testNo}" <c:if test="${testNo==f3}">selected</c:if>>${testNo}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2 d-flex align-items-end justify-content-center">
							<button class="btn btn-secondary" id="filter-button-subject">検索</button>
						</div>
					</div>
				</form>

				<div class="mt-3 text-warning">${errors.get("filter")}</div>
                
                <%-- === 科目検索結果の表示 === --%>
				<c:when test="${resultType == 'subject'}">
					<c:choose>
						<c:when test="${subjectTests != null && subjectTests.size()>0}">
							<div>
								<c:if test="${selectedSubject != null}">
									検索科目： <c:out value="${selectedSubject.name}"/>
								</c:if>
							</div>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>入学年度</th>
										<th>クラス</th>
										<th>学生番号</th>
										<th>氏名</th>
										<th>１回</th>
										<th>２回</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="subjectTest" items="${subjectTests}">
										<tr>
											<td>${subjectTest.entYear}</td>
											<td>${subjectTest.classNum}</td>
											<td>${subjectTest.studentNo}</td>
											<td>${subjectTest.studentName}</td>
											<%-- スクリプトレットによる点数表示 --%>
											<%
											  bean.TestListSubject currentTest = (bean.TestListSubject) pageContext.getAttribute("subjectTest");
											  if (currentTest != null && currentTest.getPoints() != null) {
											    Integer point1 = currentTest.getPoints().get(1);
											    Integer point2 = currentTest.getPoints().get(2);
											    out.print("<td>" + (point1 != null && point1 != 0 ? point1 : "-") + "</td>");
											    out.print("<td>" + (point2 != null && point2 != 0 ? point2 : "-") + "</td>");
											  } else {
											    out.print("<td>Error</td><td>Error</td>");
											  }
											%>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<div>成績情報が存在しませんでした。</div>
						</c:otherwise>
					</c:choose>
				</c:when>

			</div>
		</section>
	</c:param>
</c:import>
