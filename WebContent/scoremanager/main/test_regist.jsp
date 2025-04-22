<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">成績登録</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="m-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <!-- 選択された科目と回数の表示 -->
            <div class="mb-3">
                <strong>科目：</strong>${subject.name}（${testNo}回）
            </div>

            <!-- 成績入力フォーム -->
            <form action="TestRegistExecute.action" method="post">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${studentList}" varStatus="status">
                            <tr>
                                <td>${student.entYear}</td>
                                <td>${student.classNum}</td>
                                <td>
                                    ${student.no}
                                    <input type="hidden" name="students[${status.index}].no" value="${student.no}" />
                                </td>
                                <td>${student.name}</td>
                                <td>
                                    <input type="number" name="students[${status.index}].point"
                                           class="form-control" value="${student.point}" min="0" max="100" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- 隠しパラメータ -->
                <input type="hidden" name="subjectCd" value="${subject.cd}" />
                <input type="hidden" name="testNo" value="${testNo}" />

                <!-- 登録ボタン -->
                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-primary">登録して終了</button>
                </div>
            </form>
        </section>
    </c:param>

</c:import>
