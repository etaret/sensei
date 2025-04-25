package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassBatchUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションの取得
        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            req.setAttribute("error", "セッションがタイムアウトしました。再ログインしてください。");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        // クラス番号の取得
        String[] oldClassNums = req.getParameterValues("old_class_num");
        String[] newClassNums = req.getParameterValues("new_class_nums");
        List<Integer> classCounts = (List<Integer>) req.getAttribute("c_counts"); // 在籍数のリスト

        // クラス番号の重複をチェックするための DAO
        ClassNumDao cNumDao = new ClassNumDao();
        boolean isUpdated = false;
        String message = null;
        String error = null;

        // 更新処理の開始
        for (int i = 0; i < oldClassNums.length; i++) {
            String oldClassNum = oldClassNums[i];
            String newClassNum = newClassNums[i];
            int cCount = classCounts.get(i);

            // 古いクラス番号と新しいクラス番号が異なる場合は、重複チェック
            if (!oldClassNum.equals(newClassNum)) {
                // 新しいクラス番号がすでに存在しているか確認
                ClassNum existingClass = cNumDao.get(newClassNum, teacher.getSchool());
                if (existingClass != null) {
                    error = "クラス番号 " + newClassNum + " はすでに存在しています。";
                    req.setAttribute("cderror", error);
                    break;
                }
            }

            // クラス番号が同じであれば変更不要
            if (!oldClassNum.equals(newClassNum)) {
                // クラス番号の更新処理
                ClassNum cNum = new ClassNum();
                cNum.setOld_class_num(oldClassNum);
                cNum.setClass_num(newClassNum);
                cNum.setSchool(teacher.getSchool());

                // 学生がいる場合、一括更新
                if (cCount > 0) {
                    isUpdated = cNumDao.update(cNum);
                } else {
                    // 学生がいない場合、クラス番号のみ変更
                    isUpdated = cNumDao.r_update(cNum);
                }

                if (isUpdated) {
                    message = "クラス番号 " + oldClassNum + " を " + newClassNum + " に更新しました。";
                } else {
                    error = "クラス番号 " + oldClassNum + " の更新に失敗しました。";
                    break;
                }
            } else {
                message = "クラス番号 " + oldClassNum + " は変更されませんでした。";
            }
        }

        // 結果処理
        if (error != null) {
            req.setAttribute("c_counts", classCounts);
            req.setAttribute("old_class_nums", oldClassNums);
            req.setAttribute("new_class_nums", newClassNums);
            req.getRequestDispatcher("class_batch_update.jsp").forward(req, res);
        } else {
            req.setAttribute("suc", message != null ? message : "一括更新が完了しました。");
            req.getRequestDispatcher("class_batch_update_done.jsp").forward(req, res);
        }
    }
}
