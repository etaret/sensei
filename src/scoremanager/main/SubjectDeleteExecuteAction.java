package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストから科目コードを取得
        String cd = req.getParameter("cd");

        // セッションから学校情報を取得
        School school = (School) req.getSession().getAttribute("school");

        // 科目情報を取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, school);

        boolean result = false;

        if (subject != null) {
            // 削除処理実行
            result = dao.delete(subject);
        }

        req.setAttribute("deleteSuccess", result);

        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
    }
}
