<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="m-4">
            <div class="text-container" style="position: relative; display: inline-block; width: 100%;">
                <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
                    一括更新クラス
                </h2>
                <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
            </div>

            <c:choose>
                <c:when test="${old_class_nums.size() > 0 && c_counts.size() > 0}">
                    <form method="post">
                        <!-- 表示数 -->
                        <div class="mb-3">表示数：${old_class_nums.size()}件</div>

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th class="w-25 pr-2 fs-5">クラス番号</th> <!-- 文字の大きさ調整 -->
                                    <th class="w-25 pr-3 fs-5">在籍数</th> <!-- 文字の大きさ調整 -->
                                    <th class="w-50 pl-3 fs-5">新たなクラス番号</th> <!-- 文字の大きさ調整 -->
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="clazzNum" items="${old_class_nums}" varStatus="status">
                                    <tr>
                                        <!-- クラス番号（入力不可） -->
                                        <td class="px-3 fs-5">${clazzNum }</td>

                                        <!-- 在籍数 -->
                                        <td class="px-3 fs-5">${c_counts[status.index]}</td> <!-- 文字の大きさ調整 -->

                                        <!-- 新しいクラス番号入力 -->
                                        <td class="px-3">
                                            <input class="form-control px-5 fs-5"
                                                   name="new_class_nums[${status.index }]" type="text" maxlength="10" required />
                                            <input type="hidden" name="old_class_nums[${status.index }]" value="${clazzNum }">
                                            <input type="hidden" name="c_counts[${status.index }]" value="${c_counts[status.index] }">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- ボタン群 -->
                        <div class="d-flex justify-content-between">
                            <input type="button" value="戻る" class="btn btn-outline-secondary" onclick="window.location.href='ClassList.action'" />
                            <input type="submit" formaction="ClassBatchUpdateExecute.action" value="一括更新" class="btn btn-outline-warning" />
                        </div>
                    </form>
                </c:when>
            </c:choose>
        </section>
    </c:param>
</c:import>
