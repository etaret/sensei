package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストから先生コードを取得
        String id = req.getParameter("teachers_id");

        // セッションから学校情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        // DAOで科目情報を取得
        TeacherDao dao = new TeacherDao();
        Teacher teacher1 = dao.get(id);

        // JSPで使う属性名にあわせてセット
        req.setAttribute("teacherid", teacher1.getId());
        req.setAttribute("teacherName", teacher1.getName());

        // 確認画面へフォワード
        req.getRequestDispatcher("teacher_delete.jsp").forward(req, res);
    }
}

