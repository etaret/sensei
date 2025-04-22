// 変更処理
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession(false);
		Teacher teacher = (Teacher) session.getAttribute("user");

		List<Integer> entYearSet = new ArrayList<>();
		List<String> classNumList = null;
		List<Subject> subjectList = null;
		Map<String, String> errors = new HashMap<>();
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();
		TestListSubjectDao tlsDao = new TestListSubjectDao();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		try {
			for (int i = year - 10; i <= year + 1; i++) {
				entYearSet.add(i);
			}
			classNumList = cNumDao.filter(teacher.getSchool());
			subjectList = subDao.filter(teacher.getSchool());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "データの取得中にエラーが発生しました。");
			req.getRequestDispatcher("/error.jsp").forward(req, res);
			return;
		}

		String entYearStr = req.getParameter("f1");
		String classNumStr = req.getParameter("f2");
		String subjectCd = req.getParameter("f3");
		int entYear = 0;
		Subject filterSubject = null;
		List<TestListSubject> subjectTests = null;

		if (entYearStr == null || entYearStr.isEmpty() || entYearStr.equals("0") ||
		    classNumStr == null || classNumStr.isEmpty() || classNumStr.equals("0") ||
		    subjectCd == null || subjectCd.isEmpty() || subjectCd.equals("0")) {

			errors.put("filter", "入学年度とクラスと科目を入力してください");
		} else {
			try {
				entYear = Integer.parseInt(entYearStr);
			} catch (NumberFormatException e) {
				errors.put("filter", "入学年度には数値を入力してください。");
			}
			try {
				filterSubject = subDao.get(subjectCd, teacher.getSchool());
				if (filterSubject == null) {
					errors.put("filter", "指定された科目が存在しません。");
				}
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("filter", "科目の取得中にエラーが発生しました。");
			}
		}

		if (errors.isEmpty()) {
			try {
				subjectTests = tlsDao.filter(entYear, classNumStr, filterSubject, teacher.getSchool());
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("filter", "成績データの取得中にエラーが発生しました。");
				subjectTests = new ArrayList<>();
			}
		} else {
			subjectTests = new ArrayList<>();
		}

		req.setAttribute("subjectTests", subjectTests);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classNumList);
		req.setAttribute("subject_set", subjectList);
		req.setAttribute("f1", entYearStr);
		req.setAttribute("f2", classNumStr);
		req.setAttribute("f3", subjectCd);
		req.setAttribute("selectedSubject", filterSubject);
		req.setAttribute("errors", errors);

		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}

}
