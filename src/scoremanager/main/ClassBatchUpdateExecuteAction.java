package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class ClassBatchUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        ClassNumDao cNumDao = new ClassNumDao();
        StudentDao s_dao = new StudentDao();

        // 件数をカウント
        int count = 0;
        while (req.getParameter("new_class_nums[" + count + "]") != null) {
            count++;
        }

        List<ClassNum> results = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String oldClassNum = req.getParameter("old_class_nums[" + i + "]");
            String newClassNum = req.getParameter("new_class_nums[" + i + "]");
            int cCount = Integer.parseInt(req.getParameter("c_counts[" + i + "]"));

            ClassNum result = new ClassNum();
            result.setOld_class_num(oldClassNum);
            result.setClass_num(newClassNum);
            result.setC_count(cCount);
            result.setSchool(school);

            String message = "";
            boolean success = false;

            try {
                ClassNum existing = cNumDao.get(newClassNum, school);

                if (!newClassNum.equals(oldClassNum) && existing != null) {
                    message = "クラス番号が重複しています";
                } else if (newClassNum.equals(oldClassNum)) {
                    message = "変更はありませんでした。";
                    success = true;
                } else {
                    if (s_dao.class_count(oldClassNum, teacher.getSchool())) {
                        success = cNumDao.update(result);
                    } else {
                        success = cNumDao.r_update(result);
                    }
                    message = success ? "変更が完了しました。" : "変更に失敗しました。";
                }

            } catch (Exception e) {
                message = "エラーが発生しました：" + e.getMessage();
            }

            result.setMessage(message);
            result.setSuccess(success);
            results.add(result);
        }

        req.setAttribute("results", results);
        req.getRequestDispatcher("class_batch_update_done.jsp").forward(req, res);
    }
}
