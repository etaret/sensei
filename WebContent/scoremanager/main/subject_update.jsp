<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報更新</h2>
        <div class="col-11 mx-auto">
            <form action="SubjectUpdate.action" method="post">

                <!-- 科目コード（変更不可） -->
                <div class="col-12 mb-3">
                    <label class="form-label">科目コード</label>
                    <div>
                        <input class="form-control"
                            maxlength="10" name="subject_cd" type="text"
                            value="${param.subject_cd != null ? param.subject_cd : f1}" readonly />
                    </div>
                </div>

                <!-- 科目名 -->
                <div class="col-12 mb-3">
                    <label class="form-label">科目名</label>
                    <div>
                        <input class="form-control"
                            maxlength="100" name="subject_name" type="text"
                            value="${param.subject_name != null ? param.subject_name : f2}" required />
                    </div>
                </div>

                <!-- エラー表示 -->
                <c:if test="${not empty sderror}">
                    <p class="mt-2 fw-bold text-warning">${sderror}</p>
                </c:if>

                <!-- ボタン -->
                <div class="mt-3">
                    <button type="submit" class="btn btn-primary">確認</button>
                    <a href="SubjectList.action" class="btn btn-secondary ms-2">戻る</a>
                </div>

            </form>
        </div>
    </c:param>

</c:import>
