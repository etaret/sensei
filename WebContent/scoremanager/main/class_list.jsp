<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="m-4">
            <div class="text-container" style="position: relative; display: inline-block; width: 100%;">
                <h2 class="h3 mb-3 fw-norma py-2 px-4" style="position: relative; z-index: 1; background-color: #fff; display: inline-block; margin-left: 20px;">
                    クラス管理
                </h2>
                <div style="background-image: linear-gradient(to right, #000000, #FFFFFF); height: 2px; position: absolute; bottom: 9px; left: 0; right: 0; z-index: 0;"></div>
            </div>
            <c:choose>
                <c:when test="${studs.size()>0}">
                    <form method="post" onsubmit="return validateSelection();">
                        <!-- エラーメッセージ表示部分 -->
                        <div id="error-message" class="alert alert-danger d-none" role="alert" style="max-width: 500px; margin: 0 auto 15px;">
                            <!-- メッセージはJavaScriptで変更します -->
                        </div>

                        <div class="my-2 text-end px-4">
                            <!-- 新規登録ボタン -->
                            <input type="button"
                                   value="新規登録"
                                   class="btn btn-outline-primary"
                                   onclick="window.location.href='ClassCreate.action'" />

                            <!-- 一括更新ボタン -->
                            <input type="submit"
                                   formaction="ClassBatchUpdate.action"
                                   value="一括更新"
                                   class="btn btn-outline-warning" />

                            <!-- 一括削除ボタン -->
                            <input type="submit"
                                   formaction="ClassBatchDelete.action"
                                   value="一括削除"
                                   class="btn btn-outline-danger"
                                   onclick="return validateDeletion();" />
                        </div>

                        <div>表示数：${studs.size()}件</div>
                        <table class="table table-hover">
                            <tr>
                                <th>
								    <div class="d-flex align-items-center">
									    <label for="checkAllBox" class="me-2 mb-0">
									        All
									    </label>
									    <div class="form-check form-switch">
									        <input class="form-check-input" type="checkbox" role="switch" id="checkAllBox" onclick="toggleCheckboxes()" />
									        <label class="form-check-label" for="checkAllBox"></label>
									    </div>
									</div>

								</th>
                                <th>クラス番号</th>
                                <th>在籍数</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <c:forEach var="studs" items="${studs}">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="selectedClasses" value="${studs.class_num}:${studs.c_count}" />
                                    </td>
                                    <td>
                                        <a href="ClassStudentList.action?class_num=${studs.class_num}"
                                           class="text-decoration-none text-dark">
                                           ${studs.class_num}
                                        </a>
                                    </td>
                                    <td>${studs.c_count }</td>
                                    <td>
                                        <a href="ClassUpdate.action?class_num=${studs.class_num}&c_count=${studs.c_count}"
                                           class="btn btn-outline-warning btn-sm">更新</a>
                                    </td>
                                    <td>
                                        <a href="ClassDelete.action?class_num=${studs.class_num}&c_count=${studs.c_count}"
										   onclick="return confirmSingleDeletion('${studs.class_num}', '${studs.c_count}');"
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
            </c:choose>
        </section>

        <script>
            function validateSelection() {
                var checkboxes = document.getElementsByName('selectedClasses');
                var isChecked = false;

                // チェックボックスが選択されているか確認
                for (var i = 0; i < checkboxes.length; i++) {
                    if (checkboxes[i].checked) {
                        isChecked = true;
                        break;
                    }
                }

                if (!isChecked) {
                    // エラーメッセージを更新
                    document.getElementById('error-message').innerHTML = "一括更新を実行するには、少なくとも1つのクラスを選択してください。";
                    document.getElementById('error-message').classList.remove('d-none');
                    return false; // フォーム送信を防ぐ
                }

                // エラーメッセージを非表示にする
                document.getElementById('error-message').classList.add('d-none');
                return true; // フォーム送信を許可
            }

            function validateDeletion() {
                var checkboxes = document.getElementsByName('selectedClasses');
                var selected = [];

                // 選択されたクラスを整形して配列に格納
                for (var i = 0; i < checkboxes.length; i++) {
                    if (checkboxes[i].checked) {
                        var parts = checkboxes[i].value.split(":"); // parts[0] = class_num, parts[1] = c_count
                        selected.push("クラス番号: " + parts[0] + "（在籍数: " + parts[1] + "）");
                    }
                }

                if (selected.length === 0) {
                    document.getElementById('error-message').innerHTML = "一括削除を実行するには、少なくとも1つのクラスを選択してください。";
                    document.getElementById('error-message').classList.remove('d-none');
                    return false;
                }

                // 「削除します」と入力する確認ダイアログ
                var message = "次のクラスを削除します。\n\n" + selected.join("\n") + "\n\n続行するには「削除します」と入力してください。\n※ 退学者がいる場合は退学者も一緒に削除されます";
                var confirmation = prompt(message);

                if (confirmation === null) {
                    return false;
                }

                if (confirmation.trim() !== "削除します") {
                    alert("入力が正しくありません。削除を中止しました。");
                    return false;
                }

                return true;
            }

            function confirmSingleDeletion(classNum, cCount) {
                var message = "クラス番号: " + classNum + "（在籍数: " + cCount + "）を削除します。\n続行するには「削除します」と入力してください。\n※ 退学者がいる場合は退学者も一緒に削除されます";
                var confirmation = prompt(message);

                if (confirmation === null) {
                    return false; // キャンセルされた
                }

                if (confirmation.trim() !== "削除します") {
                    alert("入力が正しくありません。削除を中止しました。");
                    return false;
                }

                return true;
            }

            var allChecked = false;

            function toggleCheckboxes() {
                var checkboxes = document.getElementsByName('selectedClasses');
                var checkAll = document.getElementById('checkAllBox').checked;
                var label = document.querySelector('label[for="checkAllBox"]');

                for (var i = 0; i < checkboxes.length; i++) {
                    checkboxes[i].checked = checkAll;
                }
            }


        </script>

    </c:param>
</c:import>
