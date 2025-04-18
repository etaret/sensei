<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
		<div class="col-11 mx-auto">
			<form action="StudentCreateExecute.action" method="get">
				<%-- 入学年度セレクトボックス --%>
				<div class="col-12 mb-3">
					<label class="form-label" for="ent_Year">入学年度</label>
					<div>
						<select class="form-select" id="ent_Year" name="ent_year">
							<option value=0>--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<%-- 現在のyearと選択されていた場合selectedを追記 --%>
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<%-- エラー出力 --%>
					<c:if test="${not empty ageerror}">
			            <p class="mt-2 fw-bold text-warning">${ageerror}</p>
			        </c:if>
				</div>

				<%-- 学生番号入力ボックス --%>
				<div class="col-12 mb-3">
					<label class="form-label">学生番号</label>
					<div>
						<c:choose>
							<c:when test="${not empty f2}">
								<input class="form-control"
									maxlength="10" name="no" placeholder="学生番号を入力してください"
									type="text" value="${f2}" required />
							</c:when>
							<c:otherwise>
								<input class="form-control"
									maxlength="10" name="no" placeholder="学生番号を入力してください"
									type="text" required />
							</c:otherwise>
						</c:choose>
					</div>
					<%-- エラー出力 --%>
					<c:if test="${not empty cderror}">
			            <p class="mt-2 fw-bold text-warning">${cderror}</p>
			        </c:if>
				</div>
				<%-- 氏名入力ボックス --%>
				<div class="col-12 mb-3">
					<label class="form-label">氏名</label>
					<div>
						<c:choose>
							<c:when test="${not empty f3}">
								<input class="form-control"
									maxlength="30" name="name" placeholder="氏名を入力してください"
									type="text" value="${f3}" required />
							</c:when>
							<c:otherwise>
								<input class="form-control"
									maxlength="30" name="name" placeholder="氏名を入力してください"
									type="text" required />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<%-- クラスセレクトボックス --%>
				<div class="col-12 mb-3">
					<label class="form-label" for="class_Num">クラス</label>
					<div>
						<select class="form-select" id="class_Num" name="class_num">
							<c:forEach var="classnum" items="${class_num_set}">
								<option value="${classnum}" <c:if test="${classnum eq f4}">selected</c:if>>${classnum}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<%--- 登録終了ボタン --%>
				<div class="col-12 mb-3">
					<button class="btn btn-primary" id="filter-button" name="end">登録して終了</button>
				</div>

			</form>
			<div class="col-12 mb-3">
				<a href="StudentList.action">戻る</a>
			</div>
		</div>
	</c:param>
</c:import>