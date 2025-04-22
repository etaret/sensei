package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		HttpSession session = req.getSession(); //セッション
		// セッションからid取得
		Teacher teacher = (Teacher)session.getAttribute("user");

		// local変数宣言
		List<Subject> subjects = null; // ★ 科目リスト
		SubjectDao subDao = new SubjectDao();

		// ビジネスロジック
		subjects = subDao.filter(teacher.getSchool());

		// レスポンス値セット
		req.setAttribute("subjects", subjects);

		// JSPへフォワード
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}
}