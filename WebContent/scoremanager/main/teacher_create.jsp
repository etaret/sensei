<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts">
        <style>
			.form-item {
			    margin-bottom: 2.5rem;
			    overflow: hidden;
			}

			.form-label {
			    font-weight: bold;
			    width: 80px;
			    min-width: 80px;
			    margin-right: 1rem;
			}

			.form-line {
			    display: flex;
			    align-items: center;
			}

			.form-input {
			    border: none;
			    border-bottom: 2px solid #ccc;
			    background: transparent;
			    outline: none;
			    width: 100%;
			    padding: 5px 0;
			    font-size: 1rem;
			}

			.form-input:focus {
			    border-color: #007bff;
			}

			.slide-in {
			    transform: translateX(100%);
			    opacity: 0;
			    transition: transform 0.5s ease, opacity 0.5s ease;
			}

			.slide-in.show {
			    transform: translateX(0);
			    opacity: 1;
			}

			/* 新規登録ボタン */
			.btn-slide {
			    position: relative;
			    overflow: hidden;
			    transition: color 0.3s, background-color 0.3s, border-color 0.3s;
			    padding: 10px 20px;
			    border: 2px solid #007bff;
			    background-color: transparent;
			    color: #007bff;
			    font-weight: bold;
			    border-radius: 5px;
			}

			.btn-slide::before {
			    content: "";
			    position: absolute;
			    width: 100%;
			    height: 100%;
			    top: 0;
			    left: -100%;
			    background: #007bff;
			    transition: left 0.3s;
			    z-index: 0;
			}

			.btn-slide:hover::before {
			    left: 0;
			}

			.btn-slide span {
			    position: relative;
			    z-index: 1;
			}

			.btn-slide:hover {
			    color: white;
			    background-color: #007bff;
			    border-color: #007bff;
			}

			/* 戻るボタンのカスタムスタイル */
			.fancy-back-btn {
			    position: relative;
			    overflow: hidden;
			    color: #6c757d;
			    border: 2px solid #6c757d;
			    background-color: transparent;
			    padding: 0.5rem 1.5rem;
			    border-radius: 5px;
			    transition: color 0.3s ease, background-color 0.3s ease, border-color 0.3s ease;
			    display: inline-flex;
			    align-items: center;
			    gap: 0.5rem;
			    font-weight: bold;
			}

			.fancy-back-btn::before {
			    content: '';
			    position: absolute;
			    width: 100%;
			    height: 100%;
				top: 0;
				left: 100%;
				background-color: #6c757d;
				transition: left 0.3s ease;
				z-index: 0;
			}

			.fancy-back-btn:hover::before {
			    left: 0;
			}

			.fancy-back-btn:hover {
			    color: white;
			    background-color: #6c757d;
			    border-color: #6c757d;
			}

			.fancy-back-btn span {
			    position: relative;
			    z-index: 1;
			}
        </style>

        <script>
            window.addEventListener('DOMContentLoaded', function () {
                const items = document.querySelectorAll('.form-item');
                items.forEach(function (item, index) {
                    item.classList.add('slide-in');
                    setTimeout(function () {
                        item.classList.add('show');
                    }, index * 200);
                });
            });
        </script>
    </c:param>

    <c:param name="content">
        <div class="text-container" style="position: relative; display: inline-block; width: 100%;">
            <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
                先生登録
            </h2>
            <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
        </div>

        <div class="col-11 mx-auto">
            <form action="TeacherCreateExecute.action" method="get">

                <!-- ID入力 -->
                <div class="form-item">
                    <div class="form-line">
                        <label class="form-label">ID</label>
                        <c:choose>
                            <c:when test="${not empty f1}">
                                <input class="form-input" maxlength="10" name="id"
                                       placeholder="IDを入力してください"
                                       type="text" value="${id}" required />
                            </c:when>
                            <c:otherwise>
                                <input class="form-input" maxlength="10" name="id"
                                       placeholder="IDを入力してください"
                                       type="text" required />
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:if test="${not empty id_error}">
                        <p class="mt-2 fw-bold text-warning">${id_error}</p>
                    </c:if>
                </div>

                <!-- 名前入力 -->
                <div class="form-item">
                    <div class="form-line">
                        <label class="form-label">名前</label>
                        <c:choose>
                            <c:when test="${not empty f2}">
                                <input class="form-input" maxlength="50" name="name"
                                       placeholder="名前を入力してください"
                                       type="text" value="${name}" required />
                            </c:when>
                            <c:otherwise>
                                <input class="form-input" maxlength="50" name="name"
                                       placeholder="名前を入力してください"
                                       type="text" required />
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:if test="${not empty name_error}">
                        <p class="mt-2 fw-bold text-warning">${name_error}</p>
                    </c:if>
                </div>

                <!-- パスワード入力 -->
                <div class="form-item">
                    <div class="form-line">
                        <label class="form-label">パスワード</label>
                        <c:choose>
                            <c:when test="${not empty f1}">
                                <input class="form-input" maxlength="20" name="password"
                                       placeholder="パスワードを入力してください"
                                       type="text" value="${cd}" required />
                            </c:when>
                            <c:otherwise>
                                <input class="form-input" maxlength="20" name="password"
                                       placeholder="パスワードを入力してください"
                                       type="text" required />
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:if test="${not empty password_error}">
                        <p class="mt-2 fw-bold text-warning">${password_error}</p>
                    </c:if>
                </div>

                <!-- 管理者チェック -->
                <div class="mb-3">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox"
                               id="isAdmin" name="isAdmin" value="true"
                               <c:if test="${f1 and adminFlag}">checked</c:if> />
                        <label class="form-check-label" for="isAdmin">
                            管理者権限
                        </label>
                    </div>
                    <c:if test="${not empty admin_error}">
                        <p class="mt-2 fw-bold text-warning">${admin_error}</p>
                    </c:if>
                </div>

                <!-- その他のエラー出力 -->
                <c:if test="${not empty sderror}">
                    <p class="mt-2 fw-bold text-warning">${sderror}</p>
                </c:if>

                <!-- ボタン -->
                <div class="d-flex justify-content-between col-12 mb-3">
                    <button type="button" class="btn btn-slide fancy-back-btn" onclick="window.location.href='TeacherList.action'">
                        <span><i class="bi bi-arrow-left"></i> 戻る</span>
                    </button>
                    <button type="submit" class="btn-slide">
                        <span>新規登録</span>
                    </button>
                </div>

            </form>
        </div>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    </c:param>
</c:import>
