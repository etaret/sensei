<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="m-4">
            <div class="text-container" style="position: relative; display: inline-block; width: 100%;">
                <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
                    先生管理
                </h2>
                <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
            </div>
            <c:choose>
                <c:when test="${teachers.size()>0}">
                    <form method="post" onsubmit="return validateSelection();">
                        <div class="my-2 text-end px-4">

                        	<!-- 復活ボタン -->
                           <input type="submit"
						       formaction="teacher_iyadabotan.jsp"
						       value="先生復活ボタン"
						       class="btn btn-outline-dark" />

                            <!-- 新規登録ボタン -->
                            <input type="button"
                                   value="新規登録"
                                   class="btn btn-outline-primary"
                                   onclick="window.location.href='TeacherCreate.action'" />
                        </div>

                        <div>表示数：${teachers.size()}件</div>
                        <table class="table table-hover">
                            <tr>
                                <th>ID</th>
                                <th>名前</th>
                                <th>管理者</th>
                                <th></th>
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
                                        <a href="TeachersUpdate.action?teachers_id=${teacher.id}"
                                           class="btn btn-outline-warning btn-sm">更新</a>
                                    </td>
                                    <td>
                                        <a href="TeachersDelete.action?teachers_id=${teacher.id}"
                                           onclick="return confirm('本当に削除してもよろしいですか？');"
                                           class="btn btn-outline-danger btn-sm">削除</a>
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
