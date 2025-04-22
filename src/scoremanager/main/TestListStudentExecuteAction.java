package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログイン中の教員情報を取得

        String studentNo = req.getParameter("f4"); // リクエストパラメータから学生番号を取得 (test_list.jspのf4と想定)
        Map<String, String> errors = new HashMap<>();
        StudentDao sDao = new StudentDao();
        TestListStudentDao tlsDao = new TestListStudentDao();
        Student student = null;
        List<TestListStudent> tests = null;

        if (studentNo == null || studentNo.isEmpty()) {
            errors.put("student_no", "学生番号が指定されていません。");
        } else {
            try {
                // 学生番号を使って学生情報を取得
                student = sDao.get(studentNo);

                if (student != null) {
                    // 学生が見つかった場合、その学生の成績リストを取得
                    // ★注意: 学生が所属する学校とログイン教員の学校が一致するかチェックするロジックが追加で必要になる場合があります
                    // if (student.getSchool().getCd().equals(teacher.getSchool().getCd())) {
                        tests = tlsDao.filter(student);
                    // } else {
                    //    errors.put("student_no", "指定された学生はこの学校の生徒ではありません。");
                    //    student = null; // 他校の生徒情報は表示しない
                    // }
                } else {
                    errors.put("student_no", "指定された学生番号の学生が見つかりませんでした。");
                }
            } catch (Exception e) {
                e.printStackTrace();
                errors.put("database", "データベースエラーが発生しました。");
                // 必要に応じてログ出力などのエラーハンドリングを追加
            }
        }

        // 取得したデータとエラー情報をリクエスト属性にセット
        req.setAttribute("student", student); // 検索対象の学生情報
        req.setAttribute("tests", tests);     // 学生の成績リスト
        req.setAttribute("errors", errors);   // エラーメッセージ

        // test_list_student.jsp にフォワード
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
} 