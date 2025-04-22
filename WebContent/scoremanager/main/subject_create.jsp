<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
        <div class="col-11 mx-auto">
            <form action="SubjectCreateExecute.action" method="get">

                <%-- 科目コード入力 --%>
                <div class="col-12 mb-3">
                    <label class="form-label">科目コード</label>
                    <div>
                        <c:choose>
                            <c:when test="${not empty f1}">
                                <input class="form-control"
                                    maxlength="10" name="subject_cd" placeholder="科目コードを入力してください"
                                    type="text" value="${f1}" required />
                            </c:when>
                            <c:otherwise>
                                <input class="form-control"
                                    maxlength="10" name="subject_cd" placeholder="科目コードを入力してください"
                                    type="text" required />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <%-- 科目名入力 --%>
                <div class="col-12 mb-3">
                    <label class="form-label">科目名</label>
                    <div>
                        <c:choose>
                            <c:when test="${not empty f2}">
                                <input class="form-control"
                                    maxlength="50" name="subject_name" placeholder="科目名を入力してください"
                                    type="text" value="${f2}" required />
                            </c:when>
                            <c:otherwise>
                                <input class="form-control"
                                    maxlength="50" name="subject_name" placeholder="科目名を入力してください"
                                    type="text" required />
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <%-- エラー出力 --%>
                    <c:if test="${not empty sderror}">
                        <p class="mt-2 fw-bold text-warning">${sderror}</p>
                    </c:if>
                </div>

                <%-- 登録ボタン --%>
                <div class="col-12 mb-3">
                    <button type="submit" class="btn btn-primary">登録</button>
                </div>
            </form>

            <%-- 戻るリンク --%>
            <div class="col-12 mb-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </div>
    </c:param>
</c:import>
