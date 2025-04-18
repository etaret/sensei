package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		HttpSession session = req.getSession(); //セッション
		// セッションからid取得
		Teacher teacher = (Teacher)session.getAttribute("user");

		// local変数宣言
		String entYearStr = ""; // 入力、入学年度
		String classNum = ""; // 入力、クラス番号
		String isAttendStr = ""; // 入力、フラグ
		int entYear = 0; // 入学年度
		boolean isAttend = false; // フラグ
		List<Student> students = null; // 学生リスト
		LocalDate todaysDate = LocalDate.now(); // LcalDateインスタンス
		int year = todaysDate.getYear(); // 現在年取得
		StudentDao sDao = new StudentDao(); // 学生Dao
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Dao初期化
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		// リクエストパラメーターの取得2
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");


		// ビジネスロジック
		if (entYearStr != null) {
			// int変換
			entYear = Integer.parseInt(entYearStr);
		}
		// リスト初期化
		List<Integer> entYearSet = new ArrayList<>();
		// -10~+1年までlist登録
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		if (isAttendStr != null) {
			// 在学フラグを立てる
			isAttend = true;
			// リクエストに在学フラグをセット
			req.setAttribute("f3", isAttendStr);
		}

		// データ取得
		// ユーザの学校コードをもとにクラス番号一覧取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYear != 0 && !classNum.equals("0")){
			// 入学年度のクラスを指定
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")){
			// 入学年度のみ指定
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			// 指定なしの全学生情報取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			req.setAttribute("errors", errors);
			// 全学年情報取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}

		// レスポンス値セット
		// リクエストに入学年度セット
		req.setAttribute("f1", entYear);
		// リクエストにクラス番号セット
		req.setAttribute("f2", classNum);
		// 在学グラグ送信されている場合
		// リクエストに学生フラグセット
		req.setAttribute("students", students);
		// リクエストにデータセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		// JSPへフォワード
		req.getRequestDispatcher("student_list.jsp").forward(req, res);
	}
}
