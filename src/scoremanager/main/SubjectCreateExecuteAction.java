// 入力内容判定、db登録
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
        String subject_cd, subject_name, error = null;
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

        // パラメータの存在確認
        subject_cd = req.getParameter("subject_cd");
        subject_name = req.getParameter("subject_name");
        if (subject_cd == null || subject_cd.isEmpty() || subject_name == null || subject_name.isEmpty()) {
            req.setAttribute("sderror", "科目コードまたは科目名が入力されていません。");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // 科目コードの文字数チェック（3文字固定）
        if (subject_cd.length() != 3) {
            req.setAttribute("sderror", "科目コードは3文字で入力してください。");
            req.setAttribute("f1", subject_cd);
            req.setAttribute("f2", subject_name);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // 既存科目チェック
        try {
            existingSubject = subjectDao.get(subject_cd, teacher.getSchool());
        } catch (Exception e) {
            req.setAttribute("sderror", "データベースエラーが発生しました。もう一度お試しください。");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // 重複チェック
        if (existingSubject != null) {
            error = "科目コードが重複しています。";
            req.setAttribute("sderror", error);
        }

        // エラーがあればフォームに戻す
        if (error != null) {
            req.setAttribute("f1", subject_cd);
            req.setAttribute("f2", subject_name);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
        } else {
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
}
