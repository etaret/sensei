<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
	<div class="col-auto ms-auto text-center despair-container">
	    <h1 class="despair-title">本当に復活させる？</h1>
	    <p class="despair-text">いやだよ？復活させたくないよ!!</p>
	    <p class="despair-text">させなくてよくない</p>
	</div>
	<div class="container">
	    <div class="row d-flex justify-content-between">
	        <!-- 戻るボタン -->
	        <div class="col-auto">
	            <a href="Menu.action" class="btn btn-dark btn-lg text-decoration-none" style="color: #ff6347;">メニューへ戻ろっか</a>
	        </div>

			<!-- 復活ボタン -->
	        <div class="col-auto">
            <a href="#" class="btn btn-outline-warning btn-sm revival-button">復活</a>
        </div>
	    </div>
	</div>

	<!-- 絶望感を演出するCSS -->
	<style>
	    body {
	        background-color: #000000 !important;
	        color: #ffffff;
	    }

	    .despair-container {
	        margin-top: 100px;
	    }

	    .despair-title {
	        font-size: 100px;
	        color: #ff0000;
	        font-weight: bold;
	        text-shadow: 2px 2px 10px #800000;
	        animation: shake 0.4s infinite;
	    }

	    .despair-text {
	        font-size: 50px;
	        color: #cccccc;
	        font-style: italic;
	        margin-top: 20px;
	        text-shadow: 1px 1px 5px #555;
	    }

	    @keyframes shake {
	        0% { transform: translate(0, 0); }
	        20% { transform: translate(-2px, 2px); }
	        40% { transform: translate(-2px, -2px); }
	        60% { transform: translate(2px, 2px); }
	        80% { transform: translate(2px, -2px); }
	        100% { transform: translate(0, 0); }
	    }

	    .revival-button {
	        font-size: 1px; /* フォントサイズを非常に小さく */
	        width: 1px; /* ボタンの幅を小さく */
	        height: 1px; /* ボタンの高さを小さく */
	        padding: 0; /* パディングをゼロに */
	        border-width: 1px; /* 枠線の幅を1px */
	        text-align: center; /* 文字を中央に配置 */
	        line-height: 1px; /* テキストの行間を調整 */
	    }
	</style>
