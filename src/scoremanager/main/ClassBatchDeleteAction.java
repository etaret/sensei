package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassBatchDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("user");
        ClassNumDao cNumDao = new ClassNumDao();

        String[] selectedClasses = req.getParameterValues("selectedClasses");
        List<ClassNum> resultList = new ArrayList<>();

        if (selectedClasses != null && selectedClasses.length > 0) {
            for (String entry : selectedClasses) {
                String[] parts = entry.split(":");
                String classNumStr = parts[0];
                int cCount = Integer.parseInt(parts[1]);

                ClassNum classNum = new ClassNum();
                classNum.setClass_num(classNumStr);
                classNum.setSchool(teacher.getSchool());
                classNum.setC_count(cCount);

                if (cCount > 0) {
                    classNum.setMessage("在籍している生徒を移したのち削除してください。");
                    classNum.setSuccess(false);
                } else {
                    boolean success = cNumDao.delete(classNum);
                    classNum.setSuccess(success);
                    classNum.setMessage(success ? "削除に成功しました。" : "削除に失敗しました。");
                }

                resultList.add(classNum);
            }
        } else {
            // チェックされていない場合
            req.setAttribute("error", "削除対象のクラスが選択されていません。");
        }

        req.setAttribute("results", resultList);
        req.getRequestDispatcher("class_batch_delete_done.jsp").forward(req, res);
    }
}
