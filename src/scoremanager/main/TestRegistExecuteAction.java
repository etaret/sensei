package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッション取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータから入力情報取得
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String entYearStr = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");
        Subject subject = new Subject();
        subject.setCd(req.getParameter("subject"));
        String numStr = req.getParameter("num");
        School school = teacher.getSchool();


        // 年度は整数として扱う（バリデーション必要なら追加）
        int entYear = Integer.parseInt(entYearStr);
        int num = Integer.parseInt(numStr);
        // Studentインスタンス生成・設定
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setSchool(teacher.getSchool()); // 所属学校も設定



        // DAOで登録処理
        StudentDao sDao = new StudentDao();


        TestDao tDao = new TestDao();
        List<Test> tests = tDao.filter(entYear, classNum, subject, num, school);
        System.out.print(tests);

        req.getRequestDispatcher("menu.jsp").forward(req, res);
    }
}
