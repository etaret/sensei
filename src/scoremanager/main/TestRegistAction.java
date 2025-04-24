package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		HttpSession session = req.getSession(); //セッション
		// セッションからid取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool(); // school を取得
		LocalDate todaysDate = LocalDate.now(); // LcalDateインスタンス
		int year = todaysDate.getYear();

		// 検索用にリクエストパラメータを取得
		String entYearStr = req.getParameter("ent_year");
		String classNumStr = req.getParameter("class_num");
		String subjectCd = req.getParameter("subject");
		String numStr = req.getParameter("num");

		System.out.println(entYearStr);
		System.out.println(classNumStr);
		System.out.println(subjectCd);
		System.out.println(numStr);


		// DAO のインスタンス化
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao(); // SubjectDao をインスタンス化
		TestDao tDao = new TestDao(); // TestDao をインスタンス化

		// リスト変数の宣言と初期化()
		List<Integer> entYearSet = new ArrayList<>();
		List<String> classNumList = null; // クラス番号リスト
		List<Subject> subjectList = null; // 科目リスト
		List<Integer> testNoList = new ArrayList<>(); // テスト回数リスト


		// 入学年度リストの作成
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// 各種リストの取得
		classNumList = cNumDao.filter(teacher.getSchool());
		subjectList = sDao.filter(teacher.getSchool());
		// テスト回数リストの設定 (固定値)
		testNoList.add(1);
		testNoList.add(2);

		System.out.println(entYearSet);
		System.out.println(classNumList);
		System.out.println(subjectList);
		System.out.println(testNoList);

		// リクエスト属性にリストを設定
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classNumList);
		req.setAttribute("subject_set", subjectList);
		req.setAttribute("test_no_set", testNoList);

		// 検索後の処理
		List<Test> tests = null; // testリストの初期化
		if (entYearStr != null && classNumStr != null && subjectCd != null && numStr != null) {
			// パラメータを適切な型に変換
			int entYear = Integer.parseInt(entYearStr);
			String classNum = classNumStr; // classNum は String のまま
			Subject subject = new Subject();
			subject.setCd(subjectCd); // Subject インスタンスを作成して CD を設定
			int num = Integer.parseInt(numStr);

			// filterメソッドを呼び出し (ループは一旦コメントアウト)
			tests = tDao.filter(entYear, classNum, subject, num, school);
			req.setAttribute("tests", tests); // 取得したリストをリクエスト属性に設定
			req.setAttribute("type", "list"); // type属性を設定
		}
		System.out.println(tests);

		// 成績登録ページ (test_regist.jsp を想定) にフォワード
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
