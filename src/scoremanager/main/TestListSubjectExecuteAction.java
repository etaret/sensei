// 変更処理
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
// import java.util.HashMap; // 不要
import java.util.List;
// import java.util.Map; // 不要

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// import bean.ClassNum; // ClassNumDao で使用
import bean.Subject;
import bean.Teacher;
// import bean.Test; // 不要
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession(false);
		Teacher teacher = (Teacher) session.getAttribute("user");

		List<Integer> entYearSet = new ArrayList<>();
		List<String> classNumList = null;
		List<Subject> subjectList = null;
		// Map<String, String> errors = new HashMap<>(); // 削除
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();
		TestListSubjectDao tlsDao = new TestListSubjectDao();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		for (int i = year - 10; i <= year + 1; i++) {
			entYearSet.add(i);
		}

		classNumList = cNumDao.filter(teacher.getSchool());
		subjectList = subDao.filter(teacher.getSchool());

		String entYearStr = req.getParameter("f1");
		String classNumStr = req.getParameter("f2");
		String subjectCd = req.getParameter("f3");

		// リクエストパラメータをコンソールに出力 (これは残す)
		System.out.println("入学年度: " + entYearStr);
		System.out.println("クラス: " + classNumStr);
		System.out.println("科目コード: " + subjectCd);

		// パラメータチェックとエラーハンドリングを削除
		// try-catch ブロックも削除

		// 必須パラメータから値を取得して検索実行 (エラーチェックなし)
		int entYear = Integer.parseInt(entYearStr);
		Subject filterSubject = subDao.get(subjectCd, teacher.getSchool());
		List<TestListSubject> subjectTests = tlsDao.filter(entYear, classNumStr, filterSubject, teacher.getSchool());

		System.out.println("entYear (int): " + entYear); // 確認用
		System.out.println("filterSubject: " + (filterSubject != null ? filterSubject.getName() : "null")); // 確認用
		System.out.println("total count: " + (subjectTests != null ? subjectTests.size() : "null")); // 件数確認用
		System.out.println("subjectTests: " + subjectTests); // subjectTests リストの内容を出力 (末尾のドットを削除)

		// JSP に渡す属性を設定
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classNumList);
		req.setAttribute("subject_set", subjectList);
		req.setAttribute("f1", entYearStr);
		req.setAttribute("f2", classNumStr);
		req.setAttribute("f3", subjectCd);
		req.setAttribute("selectedSubject", filterSubject); // 取得した科目オブジェクトを設定
		req.setAttribute("subjectTests", subjectTests); // 検索結果を設定
		// req.setAttribute("errors", errors); // 削除

		// 検索タイプを設定
		req.setAttribute("resultType", "subject");

		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}

}
