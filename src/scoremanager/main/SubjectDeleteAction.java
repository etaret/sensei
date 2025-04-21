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

        // cdとschoolが両方取得できているか確認
        if (cd != null && !cd.isEmpty() && school != null) {
            // DAOを使って対象科目を取得
            SubjectDao dao = new SubjectDao();
            Subject subject = dao.get(cd, school);

            // 取得したSubjectをリクエストにセット
            req.setAttribute("subject", subject);
        }

        // 確認画面にフォワード
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}
