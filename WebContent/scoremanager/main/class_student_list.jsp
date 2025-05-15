<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="m-4">
			<div class="text-container" style="position: relative; display: inline-block; width: 100%;">
			    <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
			        所属している学生
			    </h2>
			    <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
			</div>
			<c:choose>
				<c:when test="${activeStudents.size()>0}">
					<div class="row">
					    <div class="col-auto">表示数：${activeStudents.size()}件</div>
					    <div class="col-auto ms-auto">在学中</div>
					</div>
					<table class="table table-hover">
						<tr>
							<th>学生番号</th>
							<th>学生氏名</th>
							<th></th>
						</tr>
						<c:forEach var="astuzz" items="${activeStudents}">
						    <tr>
						        <td>${astuzz.no }</td>
						        <td>${astuzz.name }</td>
						        <td>
						            <!-- 退学確認付きのリンク -->
						            <a href="ClassStudentExpel.action?student_no=${astuzz.no}&class_num=${astuzz.classNum}"
						               onclick="return confirmStudentWithdrawal('${astuzz.no}', '${astuzz.name}');"
						               class="btn btn-outline-danger btn-sm">退学</a>
						        </td>
						    </tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${graduatedStudents.size()>0}">
					<div class="row">
					    <div class="col-auto">表示数：${graduatedStudents.size()}件</div>
					    <div class="col-auto ms-auto text-center">
						    <!-- 虹色のアニメーションを適用 -->
						    <p class="rainbow-text">退学済み</p>
					    </div>
					</div>
					<table class="table table-hover">
						<tr>
							<th>学生番号</th>
							<th>学生氏名</th>
							<th></th>
						</tr>
						<c:forEach var="gstuzz" items="${graduatedStudents}">
						    <tr>
						        <td>${gstuzz.no }</td>
						        <td>${gstuzz.name }</td>
						        <td>
						            <!-- 削除確認付きのリンクに変更 -->
						            <a href="ClassStudentDelete.action?student_no=${gstuzz.no}&class_num=${gstuzz.classNum}"
						               onclick="return confirmSingleDeletion('${gstuzz.no}', '${gstuzz.name}');"
						               class="btn btn-outline-danger btn-sm">削除</a>
						        </td>
						    </tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>
			<div class="row mt-3">
			    <!-- 戻るボタン（左側） -->
			    <div class="col-auto">
			        <a href="ClassList.action" class="btn btn-outline-secondary">戻る</a>
			    </div>

			    <!-- 入力フォーム（右側） -->
			    <div class="col-auto ms-auto">
			        <form action="AllDelete.action" method="get" class="d-flex">
			            <input type="text" name="delete" class="form-control border-0 text-danger fw-bold" />
			        </form>
			    </div>
			</div>
		</section>
		<script>
			// 削除確認用の関数
		    function confirmSingleDeletion(studentNo, studentName) {
		        var message = "学生番号: " + studentNo + " 名前: " + studentName + "\n   をこのクラスから削除します。\n続行するには「削除します」と入力してください。";
		        var confirmation = prompt(message);

		        if (confirmation === null) {
		            return false; // キャンセルされた
		        }

		        if (confirmation.trim() !== "削除します") {
		            alert("入力が正しくありません。削除を中止しました。");
		            return false;
		        }

		        return true;
		    }

		 	// 退学確認用の関数
		    function confirmStudentWithdrawal(studentNo, studentName) {
		        var message = "学生番号: " + studentNo + " 名前: " + studentName + "\n   をこのクラスから退学させます。\n続行するには「退学します」と入力してください。";
		        var confirmation = prompt(message);

		        if (confirmation === null) {
		            return false; // キャンセルされた
		        }

		        if (confirmation.trim() !== "退学します") {
		            alert("入力が正しくありません。退学処理を中止しました。");
		            return false;
		        }

		        return true;
		    }
		</script>
	</c:param>
</c:import>

<!-- 虹色の文字色アニメーション用CSS -->
<style>
    /* 虹色の文字色アニメーション */
    @keyframes rainbow {
        0% { color: red; }
        14% { color: orange; }
        28% { color: yellow; }
        42% { color: green; }
        57% { color: blue; }
        71% { color: indigo; }
        85% { color: violet; }
        100% { color: red; }
    }

    /* 虹色アニメーションを適用 */
    .rainbow-text {
        font-weight: bold;
        font-size: 24px;  /* サイズ調整 */
        text-align: center;
        animation: rainbow 0.1s linear infinite;  /* 5秒で1周、無限ループ */
    }
</style>
