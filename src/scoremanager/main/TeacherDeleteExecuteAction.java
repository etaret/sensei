package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストから教員IDを取得
        String id = req.getParameter("id");

        // DAOを使用して教員情報を取得
        TeacherDao dao = new TeacherDao();
        Teacher targetTeacher = dao.get(id);

        // 教員が存在しない場合の処理
        if (targetTeacher == null) {
            req.setAttribute("deleteSuccess", false);
            req.setAttribute("errorMessage", "指定された教員が存在しません。");
            req.getRequestDispatcher("teacher_delete_done.jsp").forward(req, res);
            return;
        }

        // 削除処理実行
        boolean result = dao.delete(id);

        // 削除結果をリクエスト属性に設定
        req.setAttribute("deleteSuccess", result);
        if (!result) {
            req.setAttribute("errorMessage", "削除に失敗しました。");
        }

        // 完了ページへフォワード
        req.getRequestDispatcher("teacher_delete_done.jsp").forward(req, res);
    }
}
