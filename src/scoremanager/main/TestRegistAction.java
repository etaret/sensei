package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		HttpSession session = req.getSession(); //セッション
		// セッションからid取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		LocalDate todaysDate = LocalDate.now(); // LcalDateインスタンス
		int year = todaysDate.getYear();

		// DAO のインスタンス化
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao(); // SubjectDao をインスタンス化

		// リスト変数の宣言と初期化
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

		// 成績登録ページ (test_regist.jsp を想定) にフォワード
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
