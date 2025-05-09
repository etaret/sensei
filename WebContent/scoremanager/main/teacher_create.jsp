<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">先生登録</h2>
        <div class="col-11 mx-auto">
            <form action="TeacherCreateExecute.action" method="get">

                <%-- ID入力 --%>
                <div class="col-12 mb-3">
                    <label class="form-label">ID</label>
                    <div>
                        <c:choose>
                            <c:when test="${not empty f1}">
                                <input class="form-control"
                                    maxlength="10" name="id" placeholder="先生のIDを入力してください"
                                    type="text" value="${id}" required />
                            </c:when>
                            <c:otherwise>
                                <input class="form-control"
                                    maxlength="10" name="id" placeholder="先生のIDを入力してください"
                                    type="text" required />
                            </c:otherwise>
                        </c:choose>
                        <%-- 科目コードのエラー表示 --%>
                        <c:if test="${not empty id_error}">
                            <p class="mt-2 fw-bold text-warning">${id_error}</p>
                        </c:if>
                    </div>
                </div>

                <%-- 名前入力 --%>
                <div class="col-12 mb-3">
                    <label class="form-label">名前</label>
                    <div>
                        <c:choose>
                            <c:when test="${not empty f2}">
                                <input class="form-control"
                                    maxlength="50" name="name" placeholder="名前を入力してください"
                                    type="text" value="${name}" required />
                            </c:when>
                            <c:otherwise>
                                <input class="form-control"
                                    maxlength="50" name="name" placeholder="名前を入力してください"
                                    type="text" required />
                            </c:otherwise>
                        </c:choose>
                        <%-- 科目名のエラー表示 --%>
                        <c:if test="${not empty name_error}">
                            <p class="mt-2 fw-bold text-warning">${name_error}</p>
                        </c:if>
                    </div>
                </div>

                <%-- パスワード入力 --%>
                <div class="col-12 mb-3">
                    <label class="form-label">パスワード</label>
                    <div>
                        <c:choose>
                            <c:when test="${not empty f1}">
                                <input class="form-control"
                                    maxlength="20" name="password" placeholder="パスワードを入力してください"
                                    type="text" value="${cd}" required />
                            </c:when>
                            <c:otherwise>
                                <input class="form-control"
                                    maxlength="20" name="password" placeholder="パスワードを入力してください"
                                    type="text" required />
                            </c:otherwise>
                        </c:choose>
                        <%-- 科目コードのエラー表示 --%>
                        <c:if test="${not empty password_error}">
                            <p class="mt-2 fw-bold text-warning">${password_error}</p>
                        </c:if>
                    </div>
                </div>

                <%-- 管理者フラグのチェックボックス --%>
				<div class="col-12 mb-3">
				    <label class="form-label">管理者</label>
				    <div class="form-check">
				        <input class="form-check-input" type="checkbox"
				               id="isAdmin" name="isAdmin" value="true"
				               <c:if test="${f1 and adminFlag}">checked</c:if> />
				        <label class="form-check-label" for="isAdmin">
				            管理者として登録する
				        </label>
				    </div>
				    <%-- 管理者チェックのエラー表示（あれば） --%>
				    <c:if test="${not empty admin_error}">
				        <p class="mt-2 fw-bold text-warning">${admin_error}</p>
				    </c:if>
				</div>




                <%-- その他のエラー出力 --%>
                <c:if test="${not empty sderror}">
                    <p class="mt-2 fw-bold text-warning">${sderror}</p>
                </c:if>

                <%-- 登録ボタン --%>
                <div class="col-12 mb-3">
                    <button type="submit" class="btn btn-primary">登録</button>
                </div>
            </form>

            <%-- 戻るリンク --%>
            <div class="col-12 mb-3">
                <a href="TeacherList.action">戻る</a>
            </div>
        </div>
    </c:param>
</c:import>
