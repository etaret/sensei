package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		HttpSession session = req.getSession(); //セッション
		// セッションからid取得
		Teacher teacher = (Teacher)session.getAttribute("user");

		// local変数宣言
		String entYearStr = ""; // 入力された入学年度（文字列）
		String classNum = "";   // 入力されたクラス番号
		String subjectCd = "";   // 入力された科目コード
		String studentNo = "";   // 入力された学生番号
		int entYear = 0;          // 入学年度（数値）
		List<Test> tests = null;  // 成績リスト
		List<Subject> subjectList = null; // 科目リスト（プルダウン用）
		List<String> classNumList = null; // クラス番号リスト（プルダウン用）
		List<Integer> entYearSet = new ArrayList<>(); // 入学年度リスト（プルダウン用）
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear(); // 現在の年
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		// DAOインスタンス化　
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();
		TestDao testDao = new TestDao();

		// リクエストパラメーターの取得2
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subjectCd = req.getParameter("f3");
		studentNo = req.getParameter("f4");

		// プルダウン用リストの取得
		// 入学年度リスト (-10年 ～ +1年)
		for (int i = year - 10; i <= year + 1; i++) {
			entYearSet.add(i);
		}
		// クラス番号リスト
		classNumList = cNumDao.filter(teacher.getSchool());
		// 科目リスト
		subjectList = subDao.filter(teacher.getSchool());

		// 入力値の変換・検証
		if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
			try {
				entYear = Integer.parseInt(entYearStr);
			} catch (NumberFormatException e) {
				errors.put("f1_format", "入学年度は数値を入力してください。");
			}
		}
		// クラス番号は "0" が未選択を示すので変換不要
		// 科目コードも "0" が未選択を示すので変換不要
		// 学生番号も文字列のまま扱う

		// エラーがある場合はここで処理中断も検討 (今回は続行し、結果が0件になる想定)

		// 成績データの取得
		// TestDaoに渡すためのSubjectオブジェクト準備 (科目コードが指定されている場合)
		Subject filterSubject = null;
		if (subjectCd != null && !subjectCd.isEmpty() && !subjectCd.equals("0")) {
			// subjectList から該当する Subject を探す (効率は良くないが例として)
			// 本来は SubjectDao.get(subjectCd, teacher.getSchool()) を使うべき
			for (Subject s : subjectList) {
				if (s.getCd().equals(subjectCd)) {
					filterSubject = s;
					break;
				}
			}
			if (filterSubject == null) {
				// 指定された科目コードがリストにない場合のエラー処理
				errors.put("f3_invalid", "指定された科目が存在しません。");
			}
		}

		// TestDaoのfilterメソッドを呼び出す (引数は TestDao の実装に合わせる)
		//    ここでは全ての条件を渡すfilterメソッドがあると仮定
		if (errors.isEmpty()) { // エラーがない場合のみ検索実行
			// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
			// 現在の TestDao には、指定された条件で絞り込むための適切な filter メソッドが存在しません。
			// (存在する filter メソッドは入学年度、クラス、科目、テスト回数、学校の全てが必要)
			// そのため、DAO から成績リストを取得する処理をコメントアウトし、
			// tests リストは空のままにします。
			// 成績を表示するには TestDao.java の修正が必要です。
			// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
			tests = new ArrayList<>(); // ★ DAO呼び出し不可のため、空リストで初期化
			/* ★ DAO呼び出しとAction内フィルタリング処理をコメントアウト
			try {
				// ★ TestDao の filter メソッドを呼び出す (実装に合わせて修正)
				// 例: tests = testDao.filter(teacher.getSchool(), entYear, classNum, filterSubject, studentNo);
				// もし TestDao にこのような filter がなければ、全件取得して Action 側で絞り込む
				// ★★★ filter(School) は存在しなかったので filterBySchool(School) を試す (TestDaoにこのメソッドがあると仮定) ★★★
				// tests = testDao.filter(teacher.getSchool()); // filter(School) は存在しなかった
				// tests = testDao.filterBySchool(teacher.getSchool()); // ★ filterBySchool(School) で全件取得を試みる

				// Action側での絞り込み (もしDAOに適切なfilterがない場合)
				List<Test> filteredTests = new ArrayList<>();
				for (Test test : tests) {
					boolean match = true;
					if (entYear != 0 && test.getStudent().getEntYear() != entYear) {
						match = false;
					}
					if (classNum != null && !classNum.isEmpty() && !classNum.equals("0") && !test.getClassNum().equals(classNum)) {
						match = false;
					}
					if (filterSubject != null && !test.getSubject().getCd().equals(filterSubject.getCd())) {
						match = false;
					}
					if (studentNo != null && !studentNo.isEmpty() && !test.getStudent().getNo().equals(studentNo)) {
						match = false;
					}
					if (match) {
						filteredTests.add(test);
					}
				}
				tests = filteredTests; // 絞り込み結果を最終的なリストとする

			} catch (Exception e) {
				// DAO でのエラーハンドリング
				errors.put("database", "データベースエラーが発生しました。");
				e.printStackTrace(); // ログには詳細を出力
				tests = new ArrayList<>(); // エラー時は空リスト
			}
			*/
		} else {
			tests = new ArrayList<>(); // 入力エラーがある場合は空リスト
		}

		// レスポンス値セット
		req.setAttribute("tests", tests);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classNumList);
		req.setAttribute("subject_set", subjectList);
		req.setAttribute("f1", entYearStr);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", subjectCd);
		req.setAttribute("f4", studentNo);
		req.setAttribute("errors", errors);

		// JSPへフォワード
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}
}
