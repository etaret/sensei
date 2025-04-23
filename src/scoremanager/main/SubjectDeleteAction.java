package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストから科目コードを取得
        String cd = req.getParameter("cd");

        // セッションから学校情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        // DAOで科目情報を取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, teacher.getSchool());

        // JSPで使う属性名にあわせてセット
        req.setAttribute("subjectcd", subject.getCd());
        req.setAttribute("subjectName", subject.getName());

        // 確認画面へフォワード
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}

