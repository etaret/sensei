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

        // DAOの用意
        SubjectDao subjectDao = new SubjectDao();

        // 科目取得
        Subject subject = subjectDao.get(subject_cd, teacher.getSchool());

        if (subject == null) {
            // 削除されていた or 存在しない場合
            req.setAttribute("sderror", "指定された科目は存在しません。");
            req.setAttribute("cd", subject_cd);
            req.setAttribute("name", subject_name);
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            return;
        }

        // 更新処理
        subject.setName(subject_name);
        boolean result = subjectDao.save(subject);

        if (!result) {
            // 更新失敗 → 再取得して削除確認
            Subject checkSubject = subjectDao.get(subject_cd, teacher.getSchool());
            if (checkSubject == null) {
                // 他画面などで削除されていた
                req.setAttribute("sderror", "指定された科目は存在しません。");
            } else {
                // 削除ではないけど更新失敗
                req.setAttribute("sderror", "科目の変更に失敗しました。");
            }
            req.setAttribute("cd", subject_cd);
            req.setAttribute("name", subject_name);
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            return;
        }

        // 更新成功
        req.setAttribute("suc", "変更が完了しました。");
        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
    }
}
