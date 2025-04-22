package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストから科目コードを取得
        String cd = req.getParameter("cd");

        // セッションから学校情報を取得
        School school = (School) req.getSession().getAttribute("school");

        Subject subject = null;
        String error = null; // エラーメッセージ用

        // cdとschoolが両方取得できているか確認
        if (cd != null && !cd.isEmpty() && school != null) {
            SubjectDao dao = new SubjectDao();
            subject = dao.get(cd, school);

            if (subject == null) {
                error = "指定された科目は存在しません。";
                // エラー時もJSP用に値をセット (エラーメッセージとハイフン)
                req.setAttribute("subjectName", error);
                req.setAttribute("subjectcd", "---");
            } else {
                // 正常時：JSPが期待する属性名で科目名とコードをセット
                req.setAttribute("subjectName", subject.getName());
                req.setAttribute("subjectcd", subject.getCd());
            }
        } else {
            // パラメータが不足している場合のエラーメッセージを設定
            if (school == null) {
                 error = "学校情報が取得できませんでした。";
            } else { // cdがnullまたは空
                 error = "科目コードが指定されていません。";
            }
            // エラー時もJSP用に値をセット (エラーメッセージとハイフン)
            req.setAttribute("subjectName", error);
            req.setAttribute("subjectcd", "---");
        }

        // デバッグ用にエラーメッセージ自体もセットしておく (JSPでは未使用)
        req.setAttribute("error", error);

        // 確認画面にフォワード
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}