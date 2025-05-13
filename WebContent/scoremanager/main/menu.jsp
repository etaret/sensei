<%-- メニューJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">メニュー</h2>
			<div class="row text-center px-4 fs-3 my-5">
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #dbb;">
					<a href="StudentList.action">学生管理</a>
				</div>
				<div class="col d-flex align-items-center justify-content-center mx-2 bg-success bg-opacity-25 text-dark py-3 px-4"
					style="height: 10rem; background-color: #bdb;">
					<div>
						<div class="">成績管理</div>
						<div class="">
							<a href="TestRegist.action">成績登録</a>
						</div>
						<div class="">
							<a href="TestList.action">成績参照</a>
						</div>
					</div>
				</div>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #bbd;">
					<a href="SubjectList.action">科目管理</a>
				</div>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #ddb;">
					<a href="ClassList.action">クラス管理</a>
				</div>
			</div>

			<h2>CSVファイルをアップロードしてください</h2>
		    <form action="upload" method="post" enctype="multipart/form-data">
		        <input type="file" name="csvFile" accept=".csv" required />
		        <label>項目を選んでください：</label>
		        <select name="grade">
		            <option value="1年">学生情報</option>
		            <option value="4年">科目情報</option>
		            <option value="4年">テスト情報</option>
		            <option value="2年">クラス情報</option>
		            <c:if test="${user.getIsAdmin()}">
			            <option value="3年">先生情報</option>
			            <option value="4年">学校情報</option>
			        </c:if>
		        </select>
		        <input type="submit" value="アップロード" />
		    </form>
		</section>
	</c:param>
</c:import>