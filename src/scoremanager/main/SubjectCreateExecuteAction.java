package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 変数定義
        String subject_cd, subject_name;
        boolean result;

        // セッションの存在確認
        HttpSession session = req.getSession(false);
        if (session == null) {
            req.setAttribute("sderror", "セッションが存在しません。ログインしてください。");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        // ユーザー情報の確認
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            req.setAttribute("sderror", "ユーザー情報が見つかりません。ログインしてください。");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        // bean, dao
        Subject subject = new Subject();
        SubjectDao subjectDao = new SubjectDao();
        Subject existingSubject = null;

        // パラメータの取得
        subject_cd = req.getParameter("subject_cd");
        subject_name = req.getParameter("subject_name");

        boolean hasError = false;

        // 入力チェック
        if (subject_cd == null || subject_cd.isEmpty()) {
            req.setAttribute("subject_cd_error", "科目コードが入力されていません。");
            hasError = true;
        } else if (subject_cd.length() != 3) {
            req.setAttribute("subject_cd_error", "科目コードは3文字で入力してください。");
            hasError = true;
        }

        if (subject_name == null || subject_name.isEmpty()) {
            req.setAttribute("subject_name_error", "科目名が入力されていません。");
            hasError = true;
        }

        req.setAttribute("f1", subject_cd);
        req.setAttribute("f2", subject_name);

        if (hasError) {
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // 重複チェック
        try {
            existingSubject = subjectDao.get(subject_cd, teacher.getSchool());
        } catch (Exception e) {
            req.setAttribute("sderror", "データベースエラーが発生しました。もう一度お試しください。");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        if (existingSubject != null) {
            req.setAttribute("subject_cd_error", "科目コードが重複しています。");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // 登録処理
        subject.setCd(subject_cd);
        subject.setName(subject_name);
        subject.setSchool(teacher.getSchool());
        result = subjectDao.save(subject);

        if (result) {
            req.setAttribute("suc", "登録が完了しました。");
        } else {
            req.setAttribute("suc", "登録に失敗しました。内容確認の上もう一度お願いします。");
        }

        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}
