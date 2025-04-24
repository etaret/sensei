<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
		<div class="col-11 mx-auto">
            <!-- エラーメッセージの表示 -->
            <c:if test="${not empty error}">
                <p class="text-danger">${error}</p>
            </c:if>
			<form action="StudentUpdateExecute.action" method="get">
				<div class="col-12 mb-3">
					<label class="form-label">入学年度</label>
					<div>
						<input class="form-control border-0" id=""
							name="ent_year" type="text"
							value="${students.entYear}" readonly />
					</div>
				</div>

				<%-- 学生番号入力ボックス --%>
				<div class="col-12 mb-3">
					<label>学生番号</label>
					<div class="">
						<input class="form-control  border-0" id=""
							name="no" type="text"
							value="${students.no}" readonly />
					</div>
				</div>
				<%-- 氏名入力ボックス --%>
				<div class="col-12 mb-3">
					<label>氏名</label>
					<div class="">
						<input class="form-control" id=""
							maxlength="30" name="name"
							type="text" value="${students.name}" required pattern="[\p{L} ]+" title="氏名を正しく入力してください" />
					</div>
				</div>
				<%-- クラスセレクトボックス --%>
				<div class="col-12 mb-3">
					<label class="form-label" for="class_Num">クラス</label>
					<div>
						<select class="form-select" id="class_Num" name="class_num">
							<c:forEach var="classnum" items="${class_num_set}">
								<option value="${classnum}"
									<c:if test="${classnum == students.classNum}">selected</c:if>>
									${classnum}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<%-- 在学チェック --%>
				<div class="col-12 mb-3">
					<label>在学中</label>
						<input type="checkbox" class="form-check-input" name="flag" ${students.attend ? "checked" : ""} />
				</div>
				<%--- 登録終了ボタン --%>
				<div class="col-12 mb-3">
				    <button class="btn btn-primary" id="filter-button" name="login">変更</button>
				</div>
			</form>
			<div class="col-12 mb-3">
				<a href="StudentList.action">戻る</a>
			</div>
		</div>



	</c:param>
</c:import>