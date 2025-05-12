package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherReviveExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションの取得
        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher.getIsAdmin() == false) {
            req.getRequestDispatcher("teacher_permission_denied.jsp").forward(req, res);
            return;
        }

        // パラメータの取得
        String id = req.getParameter("teachers_id");

        // DAOの用意
        TeacherDao teacherDao = new TeacherDao();

        Teacher teacherToRevive = teacherDao.get(id);

        teacherToRevive.setIsDeleted(false);
        teacherDao.update(teacherToRevive);

        req.getRequestDispatcher("teacher_revive_done.jsp").forward(req, res);
    }
}
