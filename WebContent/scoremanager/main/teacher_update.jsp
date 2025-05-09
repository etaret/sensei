<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員情報変更</h2>
        <div class="col-11 mx-auto">


            <form action="TeacherUpdateExecute.action" method="get">
                <%-- 科目コード入力 --%>
                <div class="col-12 mb-3">
                    <label>教員ID</label>
                    <div class="">
                        <input class="form-control  border-0" name="id" type="text"
                            value="${id}" readonly />
                    </div>
					<!-- エラーメッセージ表示 -->
					<c:if test="${not empty tcherror}">
						<div class="mt-2 fw-bold text-warning" role="alert">${tcherror}</div>
					</c:if>

					<label>教員名</label>
                    <div>
                        <input class="form-control" value="${name}" maxlength="20"
                            name="name" placeholder="選択された教員の教員名"
                            type="text" required />
                    </div>
                    <label>パスワード</label>
                    <div>
                        <input class="form-control" value="${password}" maxlength="30"
                            name="password" placeholder="新しいパスワード"
                            type="text" />
                    </div>
                    <label>学校</label>
                    <div>
                        <select class="form-select" name="school_cd" required>
                            <c:forEach var="school_item" items="${school_list}">
                                <option value="${school_item.cd}" <c:if test="${school_item.cd == current_school_cd}">selected</c:if>>
                                    ${school_item.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <label>管理者権限</label>
                    <div>
                        <input class="form-check-input" name="is_admin" type="checkbox" <c:if test="${isAdmin}">checked</c:if> />
                    </div>
                </div>

                <%-- 登録ボタン --%>
                <div class="col-12 mb-3">
                    <button type="submit" class="btn btn-primary">変更</button>
                </div>
            </form>

            <div class="col-12 mb-3">
                <a href="TeacherList.action">戻る</a>
            </div>
        </div>
    </c:param>
</c:import>
