<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>エラーメッセージとモーダルウィンドウ</title>
    <style>
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            background-color: black; /* 背景を黒に */
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
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

        /* モーダルウィンドウのスタイル */
        .modal {
            position: absolute;
            width: 150px; /* 小さめのサイズ */
            height: 80px; /* 小さめのサイズ */
            background-color: rgba(255, 255, 255, 0.9);
            color: black;
            padding: 10px 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            font-size: 14px;
            text-align: center; /* テキストを中央に配置 */
        }
    </style>
</head>
<body>
    <div class="error-message">
        <p>パソコンにウイルス入ってしまいました。<br>
        下のURLからサポートセンターに連絡してください。</p>
        <a href="https://support.microsoft.com/ja-jp/contactus">https://support.microsoft.com/ja-jp/contactus</a>
    </div>

    <script>
        // 初期の表示間隔
        var intervalTime = 1000; // 2秒
        var minInterval = 100; // 最短間隔（500ミリ秒）
        var decrement = 100; // 速度短縮量

        // モーダルウィンドウをランダムな位置に追加する関数（エラーメッセージ用）
        function showErrorModal() {
            var modal = document.createElement("div");
            modal.classList.add("modal");
            modal.innerHTML = "<p>エラーです。</p>"; // モーダル内にエラーメッセージ

            // ランダムな位置を設定（画面内に収める）
            var randomTop = Math.floor(Math.random() * (window.innerHeight - 100)); // 高さの範囲
            var randomLeft = Math.floor(Math.random() * (window.innerWidth - 300)); // 幅の範囲（右に寄らないように）

            // 画面外にはみ出さないように調整
            randomTop = Math.min(randomTop, window.innerHeight - 100); // 高さ制限
            randomLeft = Math.min(randomLeft, window.innerWidth - 150); // 幅制限

            modal.style.top = randomTop + "px";
            modal.style.left = randomLeft + "px";

            // モーダルを画面に追加
            document.body.appendChild(modal);
        }

        // もう一つのモーダル（別の文言）
        function showSecondModal() {
            var modal = document.createElement("div");
            modal.classList.add("modal");
            modal.innerHTML = "<p>警告！不正な操作が行われました。</p>"; // 別の文言

            // ランダムな位置を設定（画面内に収める）
            var randomTop = Math.floor(Math.random() * (window.innerHeight - 100)); // 高さの範囲
            var randomLeft = Math.floor(Math.random() * (window.innerWidth - 300)); // 幅の範囲（右に寄らないように）

            // 画面外にはみ出さないように調整
            randomTop = Math.min(randomTop, window.innerHeight - 100); // 高さ制限
            randomLeft = Math.min(randomLeft, window.innerWidth - 150); // 幅制限

            modal.style.top = randomTop + "px";
            modal.style.left = randomLeft + "px";

            // モーダルを画面に追加
            document.body.appendChild(modal);
        }

        // モーダルを表示し、間隔を短縮する
        function startModalInterval() {
            setInterval(function() {
                showErrorModal(); // 1つ目のエラーモーダルを表示
                showSecondModal(); // 2つ目の警告モーダルを表示
                if (intervalTime > minInterval) {
                    intervalTime -= decrement; // 表示間隔を短縮
                }
            }, intervalTime);
        }

        // 最初のモーダルを表示開始
        startModalInterval();
    </script>
</body>
</html>
