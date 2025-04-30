<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">クラス一括更新結果</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="m-4">
            <div class="text-container" style="position: relative; display: inline-block; width: 100%;">
                <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
                    クラス一括更新 結果
                </h2>
                <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
            </div>

            <div class="mb-3">処理結果一覧</div>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>旧クラス番号</th>
						<th>新クラス番号</th>
						<th>在籍数</th>
						<th>結果</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${results}">
						<tr>
							<td>${result.old_class_num}</td>
							<td>${result.class_num}</td>
							<td>${result.c_count}</td>
							<td><span
								class="${result.success ? 'text-success' : 'text-danger'}">
									${result.message} </span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


			<!-- 戻るボタン -->
			<div class="d-flex justify-content-between mt-5 px-4">
				<a href="ClassList.action" class="btn btn-outline-secondary">クラス一覧へ</a>
			</div>
		</section>
    </c:param>
</c:import>
