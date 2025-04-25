package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");
        Map<String, String> errors = new HashMap<>();
        StudentDao sDao = new StudentDao();
        TestListStudentDao tlsDao = new TestListStudentDao();
        List<TestListStudent> studentTests = null;
        Student selectedStudent = null;

        String studentNo = req.getParameter("f4");

        if (studentNo == null || studentNo.isEmpty()) {
            errors.put("student_search", "学生番号を入力してください");
        } else {
            selectedStudent = sDao.get(studentNo);

            if (selectedStudent == null) {
                errors.put("student_search", "指定された学生番号の学生は存在しません");
            } else {
                studentTests = tlsDao.filter(selectedStudent);
            }
        }

        req.setAttribute("selectedStudent", selectedStudent);
        req.setAttribute("studentTests", studentTests);
        req.setAttribute("f4", studentNo);
        req.setAttribute("errors", errors);
        req.setAttribute("resultType", "student");

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}