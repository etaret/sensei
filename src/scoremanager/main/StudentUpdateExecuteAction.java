// db変更処理
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 変数定義
		int ent_year = 0;
		String ent_year_str, no, name, class_num;
		boolean isattend, deci;
		// bean,dao
		Student s=new Student();
		StudentDao sDao=new StudentDao();


		// 値取得
		ent_year_str = req.getParameter("ent_year");
		no = req.getParameter("no");
		name = req.getParameter("name");
		class_num = req.getParameter("class_num");
		isattend = req.getParameter("flag") != null;

		// 型変換
		if (ent_year_str != null) {
			ent_year = Integer.parseInt(ent_year_str);
		}

		// 値セット
		s.setNo(no);
		s.setName(name);
		s.setEntYear(ent_year);
		s.setClassNum(class_num);
		s.setAttend(isattend);

		// 変更後の値をdb登録
		try {
			deci = sDao.save(s);
		} catch(Exception e) {
			throw e;
		}

		// 成功判定 未実装
		if (deci) {}


		req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
	}

}

