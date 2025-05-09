package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションの取得
        HttpSession session = req.getSession(false);

        // パラメータの取得
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String schoolCd = req.getParameter("school_cd");
        String isAdminStr = req.getParameter("is_admin"); // "on" または null

        // DAOの用意
        TeacherDao teacherDao = new TeacherDao();
        SchoolDao schoolDao = new SchoolDao();

        // 更新対象の教員情報を取得
        Teacher teacherToUpdate = teacherDao.get(id);

        if (teacherToUpdate == null) {
            req.setAttribute("tcherror", "指定された教員は存在しません。");
            req.setAttribute("id", id);
            req.setAttribute("name", name);
            req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
            return;
        }

        teacherToUpdate.setName(name);
        if (password != null && !password.isEmpty()) {
            teacherToUpdate.setPassword(password);
        }

        School school = schoolDao.get(schoolCd);
        if (school != null) {
            teacherToUpdate.setSchool(school);
            req.setAttribute("tcherror", "指定された学校コードが存在しません。");
            req.setAttribute("id", id);
            req.setAttribute("name", name);
            req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
            return;
        }

        boolean isAdmin = "on".equals(isAdminStr);
        teacherToUpdate.setIsAdmin(isAdmin);

        boolean result = teacherDao.update(teacherToUpdate);

        if (!result) {
            req.setAttribute("tcherror", "教員情報の変更に失敗しました。");
            req.setAttribute("id", id);
            req.setAttribute("name", name);
            req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
            return;
        }
        res.sendRedirect("TeacherList.action");
    }
}
