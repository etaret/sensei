package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class ClassStudentListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		HttpSession session = req.getSession(); //セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String classNum = req.getParameter("class_num");

		// 変数宣言
		List<Student> activeStudents = null;
		List<Student> graduatedStudents = null;

		//bean,dao
		StudentDao sDao = new StudentDao();

		// データ取得
		activeStudents = sDao.filter(classNum, teacher.getSchool(), true);
		graduatedStudents = sDao.filter(classNum, teacher.getSchool(), false);

		// レスポンス値セット
		req.setAttribute("activeStudents", activeStudents);
		req.setAttribute("graduatedStudents", graduatedStudents);


		// JSPへフォワード
		req.getRequestDispatcher("class_student_list.jsp").forward(req, res);
	}
}