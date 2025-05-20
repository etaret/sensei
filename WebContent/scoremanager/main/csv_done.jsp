<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">${type}</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="m-4">
            <div class="text-container" style="position: relative; display: inline-block; width: 100%;">
                <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
                    ${type}
                </h2>
                <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
            </div>

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <!-- ヘッダーの動的表示 -->
                        <c:forEach var="h" items="${head}">
                            <th><c:out value="${h}" /></th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <!-- データの動的表示 -->
                    <c:forEach var="row" items="${data}">
                        <tr>
                            <!-- row は List<String> の各行なので、その中のセルを表示 -->
                            <c:forEach var="cell" items="${row}">
                                <td><c:out value="${cell}" /></td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- 戻るボタン -->
            <div class="d-flex justify-content-between mt-5 px-4">
                <a href="Menu.action" class="btn btn-outline-secondary">メニューへ</a>
            </div>
        </section>
    </c:param>
</c:import>
