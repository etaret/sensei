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
        String subject_cd = req.getParameter("subject_cd");
        String subject_name = req.getParameter("subject_name");

        // エラーチェック
        if (subject_cd == null || subject_cd.isEmpty() || subject_name == null || subject_name.isEmpty()) {
            req.setAttribute("sderror", "科目コードまたは科目名が入力されていません。");
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            return;
        }

        // 科目情報の取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subject_cd, teacher.getSchool());

        if (subject == null) {
            req.setAttribute("sderror", "指定された科目が見つかりません。");
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            return;
        }

        // 更新処理の準備
        subject.setName(subject_name);
        req.setAttribute("subject", subject);
        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
    }
}
