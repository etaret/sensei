// 削除処理
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassNumDao;
import tool.Action;

public class AllDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	// 変数初期化
    	String deletecheck = "delete";
    	ClassNumDao cNumDao = new ClassNumDao();

        // 値取得
        String deletecode = req.getParameter("delete");

        if (deletecode.equals(deletecheck)) {
//        	cNumDao.alldelete();
        	req.getRequestDispatcher("class_student.jsp").forward(req, res);
        } else {
        	req.getRequestDispatcher("ClassList.action").forward(req, res);
        }

    }
}
