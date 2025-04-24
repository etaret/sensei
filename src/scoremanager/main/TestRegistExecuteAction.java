package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
// import dao.StudentDao; // StudentDao は現時点では不要なのでコメントアウト
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッション取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool(); // teacherからSchoolを取得

        // 送信されたエントリ数を特定
        int count = 0;
        while (req.getParameter("studentNos[" + count + "]") != null) {
            count++;
        }

        List<Test> testsToSave = new ArrayList<>(); // 保存用リストを初期化

        // 各エントリのパラメータをループで取得してTestオブジェクトを作成
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                // パラメータ取得
                String studentNo = req.getParameter("studentNos[" + i + "]");
                String subjectCd = req.getParameter("subjectCds[" + i + "]");
                String testNoStr = req.getParameter("testNos[" + i + "]");
                String classNum = req.getParameter("classNums[" + i + "]");
                String pointStr = req.getParameter("points[" + i + "]");

                // 数値に変換 (エラーハンドリングなし)
                int testNo = Integer.parseInt(testNoStr);
                int point = Integer.parseInt(pointStr); // 空文字や数値以外だと例外発生

                //パラメータ取得時のエラー表示
                if (point > 0 || point >= 101) {
                	req.setAttribute("status", "error");

                }
                req.setAttribute("status", "success");

                // Testオブジェクト生成
                Test test = new Test();
                Student student = new Student();
                student.setNo(studentNo);
                student.setSchool(school); // studentにもschoolを設定

                Subject subject = new Subject();
                subject.setCd(subjectCd);
                subject.setSchool(school); // 必要であればsubjectにもschoolを設定

                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(testNo);
                test.setPoint(point);
                test.setClassNum(classNum);

                testsToSave.add(test); // リストに追加
            }
        }

        TestDao tDao = new TestDao();
        boolean result = tDao.save(testsToSave); // saveメソッドにリストを渡す
        System.out.println("Save result: " + result); // 実行結果をログに出力

        // 完了ページへフォワード
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
