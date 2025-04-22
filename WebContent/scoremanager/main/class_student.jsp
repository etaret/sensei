<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>エラーメッセージ</title>
    <style>
       html, body {
		    margin: 0;
		    padding: 0;
		    height: 100%;
		    background-color: black; /* 背景を黒に */
		    display: flex;
		    justify-content: center;
		    align-items: center;
		}

		.error-message {
		    background-color: red; /* 🔴 背景赤 */
		    color: red;
		    padding: 20px 30px;
		    border: 2px solid yellow; /* 🟡 枠線黄色 */
		    border-radius: 8px;
		    font-size: 20px;
		    font-weight: bold;
		    text-align: center;
		    box-shadow: 0 0 10px rgba(255, 255, 0, 0.7); /* 黄色の光 */
		    animation: scaleUpDown 5s infinite;
		    text-shadow:
		        -1px -1px 0 yellow,
		        1px -1px 0 yellow,
		        -1px 1px 0 yellow,
		        1px 1px 0 yellow; /* 🟡 文字縁取り */
		}

		/* 拡大縮小アニメーション */
		@keyframes scaleUpDown {
		    0%, 100% {
		        transform: scale(1);
		    }
		    50% {
		        transform: scale(1.2);
		    }
		}
    </style>
</head>
<body>
    <div class="error-message">
        <p>パソコンにウイルス入ってしまいました。<br>
		下のURLからサポートセンターに連絡してください。</p>
		<a href = "https://support.microsoft.com/ja-jp/contactus">https://support.microsoft.com/ja-jp/contactus</a>
    </div>
</body>
</html>
