<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス情報更新</h2>

		<div class="col-11 mx-auto">
			<form action="ClassUpdateExecute.action" method="get">

				<!-- クラス番号入力ボックス -->
				<div class="col-12 mb-3">
					<label for="class_num" class="form-label">クラス番号</label>
					<div>
						<c:choose>
							<c:when test="${not empty f1}">
								<input class="form-control"
									maxlength="10" name="class_num" placeholder="クラス番号を入力してください"
									type="text" value="${f1}" required />
								<input type="hidden" name="old_class_num" value="${old_class_num}" />
							</c:when>
							<c:otherwise>
								<input class="form-control"
									maxlength="10" name="class_num" placeholder="クラス番号を入力してください"
									type="text" required />
								<input type="hidden" name="old_class_num" value="${old_class_num}" />
							</c:otherwise>
						</c:choose>

						<input type="hidden" name="c_count" value="${c_count}" />
					</div>
					<!-- エラーメッセージ -->
					<div class="col-12 mb-4">
						<c:if test="${not empty cderror}">
							<p class="mt-2 fw-bold text-warning">${cderror}</p>
						</c:if>
					</div>
				</div>

				<!-- ボタン配置（戻るは左、更新は右） -->
				<div class="d-flex justify-content-between col-12 mb-3">
					<!-- 戻るボタン -->
					<input type="button" value="戻る" class="btn btn-outline-secondary"
					       onclick="window.location.href='ClassList.action'" />

					<!-- 更新ボタン -->
					<input type="submit" value="更新" class="btn btn-outline-primary" />
				</div>

			</form>
		</div>

	</c:param>
</c:import>
