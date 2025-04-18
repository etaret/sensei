// 入力内容判定、db登録
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 変数定義
		int ent_year = 0;
		String ent_year_str, no, name, class_num, error = null;
		// セッション
		HttpSession session = req.getSession(false);
		// bean,dao
		Student s=new Student();
		StudentDao sDao=new StudentDao();
		Student student_cd = null;

		// ローカル変数定義
		LocalDate todaysDate = LocalDate.now(); // LcalDateインスタンス
		int year = todaysDate.getYear(); // 現在年取得
		// Map<String, String> errors = new HashMap<>(); // エラーメッセージ
		ClassNumDao cNumDao=new ClassNumDao(); // クラスDao初期化
		Teacher teacher = (Teacher)session.getAttribute("user");

		// 値取得
		ent_year_str = req.getParameter("ent_year");
		no = req.getParameter("no");
		name = req.getParameter("name");
		class_num = req.getParameter("class_num");

		// 入力された年をint型へ
		if (ent_year_str != null) {
			ent_year = Integer.parseInt(ent_year_str);
		}
		// 選択されていなかった場合
		if (ent_year == 0) {
			error = "入学年度を選択してください";
			req.setAttribute("ageerror", error);
		}

		// 学生番号取得
		try {
			student_cd = sDao.get(no);
		} catch(Exception e) {
			throw e;
		}
		// 学生番号の重複チェック
		if (student_cd != null) {
			error = "学生番号が重複しています";
			req.setAttribute("cderror", error);
		}

		// errorがnullではない場合
		if (error != null) {
			// もう一度必要情報取得後入力フォームへ
			List<String> c_list = null;

			// リスト初期化
			List<Integer> entYearSet = new ArrayList<>();
			// -10~+10年までlist登録
			for (int i = year - 10; i < year + 10; i++) {
				entYearSet.add(i);
			}
			// ユーザの学校コードをもとにクラス番号一覧取得
			c_list = cNumDao.filter(teacher.getSchool());

			req.setAttribute("f1", ent_year);
			req.setAttribute("f2", no);
			req.setAttribute("f3", name);
			req.setAttribute("f4",class_num);
			req.setAttribute("class_num_set", c_list);
			req.setAttribute("ent_year_set", entYearSet);

			req.getRequestDispatcher("student_create.jsp").forward(req, res);
		// errorがnullの場合
		} else {
			// beanへの登録
			s.setNo(no);
			s.setName(name);
			s.setEntYear(ent_year);
			s.setClassNum(class_num);
			s.setAttend(true);
			s.setSchool(teacher.getSchool());

			// dbへの登録
			boolean deci = sDao.save(s);
			if (deci) {} // 成功判定未実装

			// 結果画面へ
			req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
		}

	}

}
