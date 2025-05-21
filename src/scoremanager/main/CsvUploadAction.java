// classlist一覧表示
package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao; // ClassDao の代わりに ClassNumDao を使用
import dao.StudentDao;
import dao.SubjectDao;
import dao.TeacherDao;
import dao.TestDao;
import tool.Action;

@MultipartConfig
public class CsvUploadAction extends Action {

	private static final String contine = null;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		 HttpSession session = req.getSession();
		 Teacher currentUser = (Teacher) session.getAttribute("user");
         School currentUserSchool = null;
         if (currentUser != null) {
             currentUserSchool = currentUser.getSchool();
         }
         System.out.println("currentUserSchool: " + currentUserSchool.getCd());

		 // セレクトボックスの値を取得
        String selectedType = req.getParameter("type");

        // CSVファイルのパート取得
        Part filePart = req.getPart("csvFile");
        // ファイル名を取得
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        List<String[]> csvData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(", ");
                csvData.add(row);
            }
        }

        System.out.println("選択された項目:" + selectedType);
        System.out.println("読み込んだCSVデータ:");
        for (int i = 0; i < csvData.size(); ++i) {
            String[] row = csvData.get(i);
            System.out.println("行 " + (i + 1) + ": " + String.join(", ", row));
        }

        try {
            if (selectedType.equals("学生") && fileName.contains("student")) {
                StudentDao studentDao = new StudentDao();
                List<List<String>> data = new ArrayList<>();
                // ヘッダー表示項目定義
                List<String> head = new ArrayList<>();
                head.add("学生番号");
                head.add("学生名");
                head.add("結果");
                for (String[] row : csvData) {
                    // [0] 学生番号, [1] 氏名, [2] 入学年度, [3] クラス番号, [4] 在学フラグ
                    Student student = new Student();
                    student.setNo(row[0].trim());
                    student.setName(row[1].trim());
                    student.setEntYear(Integer.parseInt(row[2].trim()));
                    student.setClassNum(row[3].trim());
                    student.setAttend(Boolean.parseBoolean(row[4].trim()));
                    student.setSchool(currentUserSchool);
                    // 現在処理のデータを登録
                    List<String> internalData = new ArrayList<>();
                    internalData.add(student.getNo());
                	internalData.add(student.getName());
                    // 文字数チェック
                    if (student.getName().length() > 10 ||
                    	student.getNo().length() > 10 ||
                    	String.valueOf(student.getEntYear()).length() > 10 ||
                    	student.getClassNum().length() > 3 ||
                    	student.getSchool().getCd().length() > 3
                    	) {
                    	// エラー文の追加
                    	internalData.add("はぁー長すぎるわ。短く");
                    	data.add(internalData);
                    	continue; // 次のループへ
                    }
                    // 重複チェック、データがあるかの取得
                    Student studentReturn = studentDao.get(student.getNo());
                    if (studentReturn != null) {
                    	// エラー文の追加
                    	internalData.add("ボケナス同じ学生番号追加するな！能無し");
                    	data.add(internalData);
                    	continue; // 次のループへ
                    }
                    // データ登録
                    if (studentDao.save(student)) {
                    	internalData.add("処理は正常に実行されました。");
                    } else {
                    	internalData.add("処理中に問題が発生しました。");
                    }
                    data.add(internalData);
                }
                req.setAttribute("type", "学生登録結果");
                req.setAttribute("head", head);
                req.setAttribute("data", data);
                // デバック
                System.out.println(head);
                System.out.println(data);
            } else if (selectedType.equals("科目") && fileName.contains("subject")) {
                SubjectDao subjectDao = new SubjectDao();
                List<Subject> subjects = new ArrayList<>();
                // ヘッダー表示項目定義
                List<String> head = new ArrayList<>();
                head.add("科目コード");
                head.add("科目名");
                head.add("結果");
                List<List<String>> data = new ArrayList<>();
                // [0] 科目コード, [1] 科目名
                for (String[] row : csvData) {
                    Subject subject = new Subject();
                    subject.setSchool(currentUserSchool);
                    subject.setName(row[1].trim());
                    subject.setCd(row[0].trim());
                    subjects.add(subject);
                    // 現在処理のデータを登録
                    List<String> internalData = new ArrayList<>();
                    internalData.add(subject.getCd());
                	internalData.add(subject.getName());
                    // 文字数チェック
                    if (subject.getName().length() > 20 ||
                    	subject.getCd().length() > 3 ||
                    	subject.getSchool().getCd().length() > 3
                    	) {
                    	// エラー文の追加
                    	internalData.add("はぁー長すぎるわ。短く");
                    	data.add(internalData);
                    	continue; // 次のループへ
                    }
                    // 重複チェック、データがあるかの取得
                    Subject subjectReturn = subjectDao.get(subject.getCd(), currentUserSchool);
                    if (subjectReturn != null) {
                    	// エラー文の追加
                    	internalData.add("ボケナス同じ科目コード追加するな！能無し");
                    	data.add(internalData);
                    	continue; // 次のループへ
                    }
                    // データ登録
                    if (subjectDao.save(subject)) {
                    	internalData.add("処理は正常に実行されました。");
                    } else {
                    	internalData.add("処理中に問題が発生しました。");
                    }
                        data.add(internalData);
                    }
                req.setAttribute("type", "科目登録結果");
                req.setAttribute("head", head);
                req.setAttribute("data", data);
                // デバック
                System.out.println(head);
                System.out.println(data);
            } else if (selectedType.equals("テスト") && fileName.contains("test")) {
                TestDao testDao = new TestDao();
                StudentDao studentDaoForTest = new StudentDao();
                SubjectDao subjectDaoForTest = new SubjectDao();
                List<List<String>> data = new ArrayList<>();
                // ヘッダー表示項目定義
                List<String> head = new ArrayList<>();
                head.add("学生番号");
                head.add("科目コード");
                head.add("テスト回数");
                head.add("点数");
                head.add("結果");
                for (String[] row : csvData) {
                    // [0] 学生番号, [1] 科目コード, [3] テスト回数, [4] 点数, [5] クラス番号
                    Test test = new Test();
                    Student student = studentDaoForTest.get(row[0].trim());
                    Subject subject = null;
                    // 現在処理のデータを登録
                    List<String> internalData = new ArrayList<>();
                    internalData.add(row[0].trim());
                    internalData.add(row[1].trim());
                    internalData.add(row[3].trim());
                    internalData.add(row[4].trim());
                    if (currentUserSchool != null) {
                         subject = subjectDaoForTest.get(row[1].trim(), currentUserSchool);
                    }
                    if (student == null || subject == null) {
                    	// エラー文の追加
                    	internalData.add("テストデータのCSVパースエラー: 学生または科目が存在しません。学生番号:" + row[0] + ", 科目コード:" + row[1]);
                    	data.add(internalData);
                        continue;
                    }

                    test.setStudent(student);
                    test.setClassNum(row[5].trim());
                    test.setSubject(subject);
                    test.setSchool(currentUserSchool);
                    test.setNo(Integer.parseInt(row[3].trim()));
                    test.setPoint(Integer.parseInt(row[4].trim()));

                    // 文字数チェック
                    if (test.getPoint() > 100 ||
                    	test.getPoint() < 0 ||
                    	test.getStudent().getNo().length() > 10 ||
                    	test.getSubject().getCd().length() > 3 ||
                    	test.getSchool().getCd().length() > 10 ||
                    	test.getNo() > 10 ||
                    	test.getClassNum().length() > 5
                    	) {
                    	// エラー文の追加
                    	internalData.add("はぁー長すぎるわ。短く");
                    	data.add(internalData);
                    	continue; // 次のループへ
                    }
                    // 重複チェック、データがあるかの取得
                    Test testReturn = testDao.get(student, subject, currentUserSchool, Integer.parseInt(row[3].trim()));
                    if (testReturn != null) {
                    	// エラー文の追加
                    	internalData.add("ボケナス同じデータ追加するな！能無し");
                    	data.add(internalData);
                    	continue; // 次のループへ
                    }

                    // データ登録
                    if (testDao.save(test)) {
                    	internalData.add("処理は正常に実行されました。");
                    } else {
                    	internalData.add("処理中に問題が発生しました。");
                    }
                    data.add(internalData);
                }
                req.setAttribute("type", "テスト登録結果");
                req.setAttribute("head", head);
                req.setAttribute("data", data);
                // デバック
                System.out.println(head);
                System.out.println(data);
            } else if (selectedType.equals("クラス")) {
                ClassNumDao classNumDao = new ClassNumDao();
                List<ClassNum> classNums = new ArrayList<>();
                for (String[] row : csvData) {
                    // [0] 学校コード, [1] クラス番号
                    ClassNum classNum = new ClassNum();
                    classNum.setClass_num(row[1].trim());
                    classNum.setSchool(currentUserSchool);
                    classNums.add(classNum);
                }
                for (ClassNum classNum : classNums) {
                    classNumDao.save(classNum);
                }
            } else if (selectedType.equals("先生") && fileName.contains("teacher")) {
                TeacherDao teacherDao = new TeacherDao();
                List<Teacher> teachers = new ArrayList<>();
                List<List<String>> data = new ArrayList<>();
                // ヘッダー表示項目定義
                List<String> head = new ArrayList<>();
                head.add("教員ID");
                head.add("名前");
                head.add("管理権限");
                head.add("結果");
                for (String[] row : csvData) {
                    // [0] 教員ID, [1] パスワード, [2] 氏名, [3] 管理者フラグ
                    Teacher teacher = new Teacher();
                    teacher.setId(row[0].trim());
                    teacher.setPassword(row[1].trim());
                    teacher.setName(row[2].trim());
                    teacher.setSchool(currentUserSchool);
                    teacher.setIsAdmin(Boolean.parseBoolean(row[3].trim()));
                    teacher.setIsDeleted(false);
                    // 現在処理のデータを登録
                    List<String> internalData = new ArrayList<>();
                    internalData.add(teacher.getId());
                    internalData.add(teacher.getName());
                    internalData.add(String.valueOf(teacher.getIsAdmin()));
                    // 文字数チェック
                    if (teacher.getId().length() > 10 ||
                    	teacher.getPassword().length() > 30||
                    	teacher.getName().length() > 10 ||
                    	teacher.getSchool().getCd().length() > 3
                    	) {
                    	// エラー文の追加
                    	internalData.add("はぁー長すぎるわ。短く");
                        data.add(internalData);
                        continue; // 次のループへ
                    }
                    // 重複チェック、データがあるかの取得
                    Teacher teacherReturn = teacherDao.get(teacher.getId());
                    if (teacherReturn != null) {
                    	// エラー文の追加
                    	internalData.add("ボケナス同じ教員ID追加するな！能無し");
                        data.add(internalData);
                        continue; // 次のループへ
                    }
                    // データ登録
                    if (teacherDao.create(teacher)) {
                    	internalData.add("処理は正常に実行されました。");
                    } else {
                    	internalData.add("処理中に問題が発生しました。");
                    }
                    data.add(internalData);
                }
                req.setAttribute("type", "教員登録結果");
                req.setAttribute("head", head);
                req.setAttribute("data", data);
                // デバック
                System.out.println(head);
                System.out.println(data);
            } else {
                req.setAttribute("type", "無効な選択");
                List<String> head = new ArrayList<>();
                head.add("エラー");
                List<List<String>> data = new ArrayList<>();
                List<String> internalData = new ArrayList<>();
                internalData.add("CSVのファイル名と選択した項目が一致しません。");
                data.add(internalData);
                req.setAttribute("head", head);
                req.setAttribute("data", data);
                req.getRequestDispatcher("csv_done.jsp").forward(req, res);
                return;
            }


            req.getRequestDispatcher("csv_done.jsp").forward(req, res);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "CSVデータの数値変換中にエラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("menu.jsp").forward(req, res); // エラー画面または元の画面へ
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "CSVデータの処理中にエラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("menu.jsp").forward(req, res); // エラー画面または元の画面へ
        }
	}
}