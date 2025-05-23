<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
        <div class="col-11 mx-auto">


            <form action="SubjectUpdateExecute.action" method="get">
                <%-- 科目コード入力 --%>
                <div class="col-12 mb-3">
                    <label>科目コード</label>
                    <div class="">
                        <input class="form-control  border-0" name="cd" type="text"
                            value="${cd}" readonly />
                    </div>
					<!-- エラーメッセージ表示 -->
					<c:if test="${not empty sderror}">
						<div class="mt-2 fw-bold text-warning" role="alert">${sderror}</div>
					</c:if>

					<label>科目名</label>
                    <div>
                        <input class="form-control" value="${name}" maxlength="20"
                            name="name" placeholder="選択された科目の科目名"
                            type="text" required />
                        <input type="hidden" name="old_class_num" value="${old_class_num}" />
                    </div>
                </div>

                <%-- 登録ボタン --%>
                <div class="col-12 mb-3">
                    <button type="submit" class="btn btn-primary">変更</button>
                </div>
            </form>

            <div class="col-12 mb-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </div>
    </c:param>
</c:import>
