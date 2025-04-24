// 削除処理
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassStudentDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 変数定義
        String class_num, student_no;
        boolean deci = false;
        // セッション
        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher)session.getAttribute("user");
        // bean,dao
        Student stu = new Student();
        ClassNumDao cNumDao = new ClassNumDao();

        // 値取得
        class_num = req.getParameter("class_num");
        student_no = req.getParameter("student_no");

    	// beanに値セット
    	stu.setClassNum(class_num);
    	stu.setNo(student_no);
        stu.setSchool(teacher.getSchool());

        // db処理結果取得
        deci = cNumDao.st_delete(stu);

        // 結果判定、文字登録
        if (deci) {
            req.setAttribute("suc", "削除が完了しました。");
        } else {
            req.setAttribute("suc", "削除に失敗しました。内容確認の上もう一度お願いします。");
        }

        req.getRequestDispatcher("class_student_delete_done.jsp").forward(req, res);
    }
}
