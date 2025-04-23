package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストから科目コードを取得
        String cd = req.getParameter("subjectId");

        // セッションから学校情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        // 科目情報を取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, teacher.getSchool());

        boolean result = false;

        if (subject != null) {
            // 削除処理実行
            result = dao.delete(subject);
        }

        req.setAttribute("deleteSuccess", result);

        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
    }
}

