package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションの取得
        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータの取得
        String subject_cd = req.getParameter("cd");
        String subject_name = req.getParameter("name");

        // 科目情報の取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subject_cd, teacher.getSchool());

        if (subject == null) {
            req.setAttribute("sderror", "指定された科目が見つかりません。");
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            return;
        }

        // 更新処理
        subject.setName(subject_name);
        boolean result = subjectDao.save(subject);

        if (!result) {
            // 更新失敗時にもう一度取得して削除されたかを確認
            Subject checkSubject = subjectDao.get(subject_cd, teacher.getSchool());
            if (checkSubject == null) {
                req.setAttribute("sderror", "科目が存在していません。");
                req.getRequestDispatcher("subject_update.jsp").forward(req, res);
                return;
            } else {
                req.setAttribute("suc", "変更に失敗しました。");
            }
        } else {
            req.setAttribute("suc", "変更が完了しました。");
        }

        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
    }
}
