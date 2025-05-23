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

			<form action="CsvUpload.action" method="post" enctype="multipart/form-data" class="mt-4">
			    <div class="mb-3">
			        <label for="csvFile" class="form-label">CSVファイルをアップロードしてください</label>

			        <!-- ドロップエリア -->
			        <div id="dropZone" class="border border-secondary rounded p-5 text-center bg-light">
			            <p class="mb-2">ここにファイルをドラッグ＆ドロップするか、下のボタンで選択してください</p>
			            <input type="file" name="csvFile" id="fileInput" class="form-control" accept=".csv" hidden required />
			            <button type="button" class="btn btn-outline-secondary" onclick="document.getElementById('fileInput').click();">
			                ファイルを選択
			            </button>
			            <p id="fileName" class="mt-2 text-muted"></p>
			        </div>
			    </div>

			    <!-- 横並びのセレクト＋ボタン -->
			    <div class="row align-items-center">
			        <div class="col-auto">
			            <label for="type" class="col-form-label">項目を選んでください：</label>
			        </div>
			        <div class="col-auto">
			            <select class="form-select" name="type" id="type">
			                <option value="学生">学生情報</option>
			                <option value="科目">科目情報</option>
			                <option value="テスト">テスト情報</option>
			                <option value="クラス">クラス情報</option>
			                <c:if test="${user.getIsAdmin()}">
			                    <option value="先生">先生情報</option>
			                </c:if>
			            </select>
			        </div>
			        <div class="col text-end">
			            <input type="submit" value="アップロード" class="btn btn-primary" />
			        </div>
			    </div>
			</form>

			<script>
			    const dropZone = document.getElementById("dropZone");
			    const fileInput = document.getElementById("fileInput");
			    const fileNameDisplay = document.getElementById("fileName");

			    // ハイライト表示
			    dropZone.addEventListener("dragover", function (e) {
			        e.preventDefault();
			        dropZone.classList.add("border-primary", "bg-white");
			    });

			    dropZone.addEventListener("dragleave", function () {
			        dropZone.classList.remove("border-primary", "bg-white");
			    });

			    dropZone.addEventListener("drop", function (e) {
			        e.preventDefault();
			        dropZone.classList.remove("border-primary", "bg-white");

			        const files = e.dataTransfer.files;
			        if (files.length > 0 && files[0].type === "text/csv") {
			            fileInput.files = files;
			            fileNameDisplay.textContent = "選択されたファイル: " + files[0].name;
			        } else {
			            alert("CSVファイルをアップロードしてください。");
			        }
			    });

			    fileInput.addEventListener("change", function () {
			        if (fileInput.files.length > 0) {
			            fileNameDisplay.textContent = "選択されたファイル: " + fileInput.files[0].name;
			        }
			    });
			</script>


		</section>
	</c:param>
</c:import>