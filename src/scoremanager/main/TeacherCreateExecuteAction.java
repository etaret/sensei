package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 変数定義
        String Id, Name, Password, IsAdminStr;
        boolean IsAdmin, result;

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
        TeacherDao teacherDao = new TeacherDao();
        Teacher existingTeacher = null;

        // パラメータの取得
        Id = req.getParameter("id");
        Name = req.getParameter("name");
        Password = req.getParameter("password");
        IsAdminStr = req.getParameter("isAdmin");

        boolean hasError = false;

        // 入力チェック
        if (Id == null || Id.isEmpty()) {
            req.setAttribute("id_error", "先生コードが入力されていません。");
            hasError = true;
        }

        if (Name == null || Name.isEmpty()) {
            req.setAttribute("name_error", "先生名が入力されていません。");
            hasError = true;
        }

        if (Password == null || Password.isEmpty()) {
            req.setAttribute("password_error", "パスワードが入力されていません。");
            hasError = true;
        }

        if (IsAdminStr != null && IsAdminStr.equals("true")) {
        	IsAdmin = true;
        }else{
        	IsAdmin = false;
        }

        req.setAttribute("id", Id);
        req.setAttribute("name", Name);
        req.setAttribute("password", Password);
        req.setAttribute("isAdmin", IsAdmin);

        if (hasError) {
            req.getRequestDispatcher("teacher_create.jsp").forward(req, res);
            return;
        }

        // 重複チェック
        try {
            existingTeacher = teacherDao.get(Id);
        } catch (Exception e) {
            req.setAttribute("id_error", "データベースエラーが発生しました。もう一度お試しください。");
            req.getRequestDispatcher("teacher_create.jsp").forward(req, res);
            return;
        }

        if (existingTeacher != null) {
            req.setAttribute("id_error", "先生コードが重複しています。");
            req.getRequestDispatcher("teacher_create.jsp").forward(req, res);
            return;
        }

        // 登録処理
        Teacher newteacher = new Teacher();
        newteacher.setId(Id);
        newteacher.setName(Name);
        newteacher.setPassword(Password);
        newteacher.setIsAdmin(IsAdmin);
        newteacher.setSchool(teacher.getSchool());
        newteacher.setIsDeleted(false);
        result = teacherDao.create(newteacher);

        // デバック
//        System.out.println(newteacher.getId());
//        System.out.println(newteacher.getName());
//        System.out.println(newteacher.getPassword());
//        System.out.println(newteacher.getIsAdmin());
//        System.out.println(newteacher.getSchool().getCd());


        if (result) {
            req.setAttribute("suc", "登録が完了しました。");
        } else {
            req.setAttribute("suc", "登録に失敗しました。内容確認の上もう一度お願いします。");
        }

        req.getRequestDispatcher("teacher_create_done.jsp").forward(req, res);
    }
}
