package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherReturnListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		HttpSession session = req.getSession(); //セッション
		// セッションからid取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		if (teacher.getIsAdmin() == false) {
			req.getRequestDispatcher("teacher_permission_denied.jsp").forward(req, res);
			return;
		}

		// local変数宣言
		List<Teacher> teachers = null; // ★ 科目リスト
		TeacherDao teaDao = new TeacherDao();

		// ビジネスロジック
		teachers = teaDao.filterDeleted(teacher.getSchool());

		// レスポンス値セット
		req.setAttribute("teachers", teachers);

		// JSPへフォワード
		req.getRequestDispatcher("teacher_return_list.jsp").forward(req, res);
	}
}