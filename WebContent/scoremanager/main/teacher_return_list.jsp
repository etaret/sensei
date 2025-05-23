<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="m-4">
            <div class="text-container" style="position: relative; display: inline-block; width: 100%;">
                <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
                    先生復活画面
                </h2>
                <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
            </div>
            <c:choose>
                <c:when test="${teachers.size()>0}">
                    <form method="post" onsubmit="return validateSelection();">
                        <div class="my-2 text-end px-4">

                        	<!-- 復活ボタン -->

                        </div>

                        <div>表示数：${teachers.size()}件</div>
                        <table class="table table-hover">
                            <tr>
                                <th>ID</th>
                                <th>名前</th>
                                <th>管理者</th>
                                <th></th>
                            </tr>
                            <c:forEach var="teacher" items="${teachers}">
                                <tr>
                                    <td>${teacher.id }</td>
                                    <td>${teacher.name}</td>
                                    <td class="text-start">
										<%-- 管理者フラグがたっている場合「○」それ以外は「×」を表示 --%>
										<c:choose>
											<c:when test="${teacher.isAdmin}">
											         ○
											</c:when>
											<c:otherwise>
											         ×
											</c:otherwise>
										</c:choose>
									</td>
                                    <td>
                                        <a href="TeacherReviveExecute.action?teachers_id=${teacher.id}"
                                           class="btn btn-outline-warning btn-sm">復活</a>
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

                <c:otherwise>
                	<!-- データがない場合 -->
                	<div style="margin: 40px;">データがありません。</div>
					<div class="d-flex justify-content-start">
			            <input type="button" value="メニューへ戻る" class="btn btn-outline-secondary" onclick="window.location.href='Menu.action'" />
			        </div>
                </c:otherwise>
            </c:choose>
        </section>

    </c:param>
</c:import>
