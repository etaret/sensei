// 変更のためjspへ
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ClassBatchUpdateAction extends Action {

	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {
		// 変数定義
		String[] selectedClasses;
		List<String> classNums = new ArrayList<>();
		List<Integer> counts = new ArrayList<>();


		// 値取得
		// チェックボックスで選択されたクラス番号の取得
        selectedClasses = req.getParameterValues("selectedClasses");

        // 値分解
        if (selectedClasses != null && selectedClasses.length > 0) {
            for (String selectedClass : selectedClasses) {
                // クラス番号と在籍数を ":" で分割
                String[] classData = selectedClass.split(":");
                String classNum = classData[0];  // クラス番号
                int classCount = Integer.parseInt(classData[1]);  // 在籍数
     
                // リストに追加
                classNums.add(classNum);
                counts.add(classCount);
            }


	        // 値セット
	        req.setAttribute("old_class_nums", classNums);
	        req.setAttribute("c_counts", counts);

        } else {
        	req.setAttribute("error", "更新するクラスが選択されていません。");
        }

        req.getRequestDispatcher("class_batch_update.jsp").forward(req, res);
	}

}
