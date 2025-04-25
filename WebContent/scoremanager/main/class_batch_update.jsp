<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="m-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">一括更新クラス一覧</h2>
			<c:choose>
				<c:when test="${old_class_nums.size() > 0 && c_counts.size() > 0}">
					<form method="post">

						<!-- 表示数 -->
						<div class="mb-3">表示数：${old_class_nums.size()}件</div>

						<table class="table table-hover">
							<thead>
								<tr>
									<th class="w-25 pr-2">クラス番号</th> <!-- 右側のパディングを減らす -->
									<th class="w-25 pr-3">在籍数</th> <!-- 右側のパディングを減らす -->
									<th class="w-50 pl-3">新たなクラス番号</th> <!-- 左側のパディングを増やす -->
								</tr>
							</thead>
							<tbody>
								<c:forEach var="clazzNum" items="${old_class_nums}" varStatus="status">
									<tr>
										<!-- クラス番号（入力不可） -->
										<td>
											<input type="text" name="old_class_num"
											       value="${clazzNum}" class="form-control-plaintext px-3 fs-5"
											       readonly />
										</td>

										<!-- 在籍数 -->
										<td class="px-3">${c_counts[status.index]}</td>

										<!-- 新しいクラス番号入力 -->
										<td class="px-3">
											<input class="form-control px-5 fs-5"
											       name="new_class_nums" type="text" maxlength="10" required />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<!-- ボタン群 -->
						<div class="d-flex justify-content-between">
						    <input type="button" value="戻る" class="btn btn-outline-secondary" onclick="window.location.href='ClassList.action'" />

						    <input type="submit" formaction="ClassBatchUpdate.action" value="一括更新" class="btn btn-outline-warning" />
						</div>
					</form>
				</c:when>
			</c:choose>
		</section>
	</c:param>
</c:import>
