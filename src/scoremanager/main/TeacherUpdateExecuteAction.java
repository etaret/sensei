package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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
        String isAdminStr = req.getParameter("is_admin");

        // DAOの用意
        TeacherDao teacherDao = new TeacherDao();
        SchoolDao schoolDao = new SchoolDao();
        List<School> school_list = schoolDao.getList();

        Teacher teacherToUpdate = teacherDao.get(id);

        if (teacherToUpdate == null) {
            req.setAttribute("tcherror", "指定された教員は存在しません。");
            req.setAttribute("id", id);
            req.setAttribute("name", name);
            req.setAttribute("password", password);
            req.setAttribute("school_list", school_list);
            req.setAttribute("is_admin", isAdminStr);
            req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
            return;
        }

        teacherToUpdate.setName(name);
        if (password != null && !password.isEmpty()) {
            teacherToUpdate.setPassword(password);
        }

        School school = schoolDao.get(schoolCd);
        if (school == null) {
            req.setAttribute("tcherror", "指定された学校コードが存在しません。");
            req.setAttribute("id", id);
            req.setAttribute("name", name);
            req.setAttribute("school_list", school_list);
            req.setAttribute("current_school_cd", schoolCd);
            req.setAttribute("is_admin", isAdminStr);
            req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
            return;
        } else {
            teacherToUpdate.setSchool(school);
        }

        boolean isAdmin = "on".equals(isAdminStr);
        teacherToUpdate.setIsAdmin(isAdmin);

        boolean result = teacherDao.update(teacherToUpdate);

        if (!result) {
            req.setAttribute("tcherror", "教員情報の変更に失敗しました。");
            req.setAttribute("id", id);
            req.setAttribute("name", name);
            req.setAttribute("password", password);
            req.setAttribute("school_list", school_list);
            req.setAttribute("is_admin", isAdminStr);
            req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
            return;
        }
        req.getRequestDispatcher("teacher_update_done.jsp").forward(req, res);
    }
}
