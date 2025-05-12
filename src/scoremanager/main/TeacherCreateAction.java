// 新規登録のためjspへ
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Teacher;

import tool.Action;

public class TeacherCreateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest req, HttpServletResponse res
    ) throws Exception {

        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher.getIsAdmin() == false) {
            req.getRequestDispatcher("teacher_permission_denied.jsp").forward(req, res);
            return;
        }

        req.getRequestDispatcher("teacher_create.jsp").forward(req, res);
    }

}
