package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションの取得
        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータの取得
        String subject_cd = req.getParameter("cd");

        // 科目情報の取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subject_cd, teacher.getSchool());

        // 更新画面へ渡すデータをセット
        req.setAttribute("cd", subject.getCd());
        req.setAttribute("name", subject.getName());

        // JSPへフォワード
        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}
