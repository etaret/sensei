// 新規登録のためjspへ
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class TeacherCreateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest req, HttpServletResponse res
    ) throws Exception {

        req.getRequestDispatcher("teacher_create.jsp").forward(req, res);
    }

}
