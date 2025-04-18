// 選択された学生情報取得、jspへ

package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {


		// セッション取得
		HttpSession session = req.getSession(); //セッション
		// セッションからid取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		// ローカル変数定義
		String s_no;
		Student students = null;
		List<String> clist = null;
		// bean,dao
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();


		s_no = req.getParameter("no"); // 値取得

		students = sDao.get(s_no); // dbデータ取得

		clist = cNumDao.filter(teacher.getSchool()); // クラスデータ取得

		// データセット
		req.setAttribute("students", students);
		req.setAttribute("class_num_set", clist);
		req.setAttribute("s_no", s_no);


		req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}

}

