// 新規登録のため必要な情報取得、jspへ
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {

	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {


		// session
		HttpSession session = req.getSession(false);
		Teacher teacher = (Teacher)session.getAttribute("user");
		// ローカル変数定義
		LocalDate todaysDate = LocalDate.now(); // LcalDateインスタンス
		int year = todaysDate.getYear(); // 現在年取得
		// Map<String, String> errors = new HashMap<>(); // エラーメッセージ
		ClassNumDao cNumDao=new ClassNumDao(); // クラスDao初期化

		List<String> c_list = null;

		// リスト初期化
		List<Integer> entYearSet = new ArrayList<>();
		// -10~+10年までlist登録
		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}
		// ユーザの学校コードをもとにクラス番号一覧取得
		c_list = cNumDao.filter(teacher.getSchool());
		session.setAttribute("school_cd", teacher.getSchool());


		req.setAttribute("class_num_set", c_list);
		req.setAttribute("ent_year_set", entYearSet);

		req.getRequestDispatcher("student_create.jsp").forward(req, res);
	}

}
