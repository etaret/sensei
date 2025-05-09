package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.TeacherDao;
import dao.SchoolDao;
import java.util.List;
import bean.School;
import tool.Action;

public class TeacherUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションの取得
        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータの取得
        String teacher_id = req.getParameter("teachers_id");

        // 教員情報の取得
        TeacherDao teacherDao = new TeacherDao();
        Teacher teacher_data = teacherDao.get(teacher_id);

        // 学校リストの取得
        SchoolDao schoolDao = new SchoolDao();
        List<School> school_list = schoolDao.getList();

        // 更新画面へ渡すデータをセット
        req.setAttribute("id", teacher_data.getId());
        req.setAttribute("name", teacher_data.getName());
        req.setAttribute("current_school_cd", teacher_data.getSchool().getCd()); // 現在の学校コードをセット
        req.setAttribute("isAdmin", teacher_data.getIsAdmin());
        req.setAttribute("isDeleted", teacher_data.getIsDeleted());
        req.setAttribute("school_list", school_list); // 学校リストをセット

        // JSPへフォワード
        req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
    }
}
