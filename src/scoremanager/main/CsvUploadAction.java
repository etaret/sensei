// classlist一覧表示
package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tool.Action;

@MultipartConfig
public class CsvUploadAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		 // セレクトボックスの値を取得
        String selectedGrade = req.getParameter("grade");

        // CSVファイルのパート取得
        Part filePart = req.getPart("csvFile");
        List<String[]> csvData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(", ");
                csvData.add(row);
            }
        }

        // CSVデータが正しく読み込まれているか確認
        System.out.println("読み込んだCSVデータ:");
        for (int i = 0; i < csvData.size(); ++i) {
            String[] row = csvData.get(i);
            System.out.println("行 " + (i + 1) + ": " + String.join(", ", row));
        }
	}
}
