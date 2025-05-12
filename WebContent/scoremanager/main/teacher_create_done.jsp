<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts">
		<style>
			/* 追加登録ボタン (スライド効果) */
			.btn-slide {
			    position: relative;
			    overflow: hidden;
			    transition: color 0.3s, background-color 0.3s, border-color 0.3s;
			    padding: 10px 20px;
			    border: 2px solid #007bff;  /* 追加登録ボタンの枠線は青 */
			    background-color: transparent;
			    color: #007bff;  /* 追加登録ボタンの文字色は青 */
			    font-weight: bold;
			    border-radius: 5px;
			    display: inline-flex;
			    align-items: center;
			    justify-content: center;
			    text-decoration: none;  /* URLの下線を消す */
			}

			/* スライド効果のための設定 */
			.btn-slide::before {
			    content: '';
			    position: absolute;
			    width: 100%;
			    height: 100%;
			    top: 0;
			    left: 100%;  /* 初期位置を左外に設定 */
			    background-color: #007bff;  /* 追加登録ボタンの背景は青 */
			    transition: left 0.3s ease;
			    z-index: 0;
			}

			/* ホバー時にスライド */
			.btn-slide:hover::before {
			    left: 0;  /* ボタンがスライドして表示される位置 */
			}

			/* ホバー時の色変更 */
			.btn-slide:hover {
			    color: white;
			    background-color: #007bff;
			    border-color: #007bff;
			}

			.btn-slide span {
			    position: relative;
			    z-index: 1;
			}

			/* 先生一覧へボタン (スライド効果) */
			.fancy-back-btn {
			    position: relative;
			    overflow: hidden;
			    color: #6c757d;  /* 先生一覧ボタンの文字色はグレー */
			    border: 2px solid #6c757d;  /* 先生一覧ボタンの枠線はグレー */
			    background-color: transparent;
			    padding: 0.5rem 1.5rem;
			    border-radius: 5px;
			    transition: color 0.3s ease, background-color 0.3s ease, border-color 0.3s ease;
			    display: inline-flex;
			    align-items: center;
			    gap: 0.5rem;
			    font-weight: bold;
			    text-decoration: none;  /* URLの下線を消す */
			}

			/* スライド効果のための設定 */
			.fancy-back-btn::before {
			    content: '';
			    position: absolute;
			    width: 100%;
			    height: 100%;
			    top: 0;
			    left: -100%;  /* 初期位置を左外に設定 */
			    background-color: #6c757d;  /* 先生一覧ボタンの背景はグレー */
			    transition: left 0.3s ease;
			    z-index: 0;
			}

			/* ホバー時にスライド */
			.fancy-back-btn:hover::before {
			    left: 0;  /* ボタンがスライドして表示される位置 */
			}

			/* ホバー時の色変更 */
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
	</c:param>

	<c:param name="content">

		<div class="text-container" style="position: relative; display: inline-block; width: 100%;">
		    <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
		        先生情報登録
		    </h2>
		    <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
		</div>

		<!-- 登録成功メッセージ -->
		<c:if test="${not empty suc}">
			<div class="alert alert-success text-center fw-bold" role="alert">
				${suc}
			</div>
		</c:if>

		<div class="d-flex justify-content-between col-12 mb-3">
		    <!-- 追加登録ボタン (スライド効果) -->
		    <a href="TeacherCreate.action" class="btn-slide">
		        <span>追加登録</span>
		    </a>

		    <!-- 先生一覧へボタン (スライド効果) -->
		    <a href="TeacherList.action" class="fancy-back-btn">
		        <span><i class="bi bi-arrow-left"></i> 先生一覧へ</span>
		    </a>
		</div>

	</c:param>
</c:import>
