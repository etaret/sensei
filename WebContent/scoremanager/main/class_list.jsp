<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="m-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
			<c:choose>
				<c:when test="${classes.size()>0}">
					<form method="post">
						<div class="my-2 text-end px-4">
							<!-- 新規登録ボタン -->
							<input type="button"
							       value="新規登録"
							       class="btn btn-outline-primary"
							       onclick="window.location.href='ClassCreate.action'" />

							<!-- 一括更新ボタン -->
							<input type="submit"
							       formaction="ClassBatchUpdate.action"
							       value="一括更新"
							       class="btn btn-outline-warning" />

							<!-- 一括削除ボタン -->
							<input type="submit"
							       formaction="ClassBatchDelete.action"
							       value="一括削除"
							       onclick="return confirm('本当に削除してもよろしいですか？');"
							       class="btn btn-outline-danger" />
						</div>

						<div>表示数：${classes.size()}件</div>
						<table class="table table-hover">
							<tr>
								<th></th>
								<th>クラス番号</th>
								<th>在籍数</th>
								<th></th>
								<th></th>
							</tr>
							<c:forEach var="clazz" items="${classes}">
								<tr>
									<td>
										<input type="checkbox" name="selectedClasses" value="${clazz.class_num}:${clazz.c_count}" />
									</td>
									<td>
										<a href="ClassStudentList.action?class_num=${clazz.class_num}"
										     class="text-decoration-none text-dark">
										     ${clazz.class_num}
										</a>
									</td>
									<td>${clazz.c_count }</td>
									<td>
									    <a href="ClassUpdate.action?class_num=${clazz.class_num}&c_count=${clazz.c_count}"
									       class="btn btn-outline-warning btn-sm">更新</a>
									</td>
									<td>
									    <a href="ClassDelete.action?class_num=${clazz.class_num}&c_count=${clazz.c_count}"
									       onclick="return confirm('本当に削除してもよろしいですか？');"
									       class="btn btn-outline-danger btn-sm">削除</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</form>
					<div class="d-flex justify-content-start">
					    <!-- メニューへ戻るボタン（左下に配置） -->
					    <input type="button" value="メニューへ戻る" class="btn btn-outline-secondary" onclick="window.location.href='Menu.action'" />
					</div>
				</c:when>
			</c:choose>
		</section>
	</c:param>
</c:import>