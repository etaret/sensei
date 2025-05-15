// classlist一覧表示
package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import dao.SchoolDao;   // School情報取得に必要に応じて使用
import dao.StudentDao;
import dao.SubjectDao;
import dao.TeacherDao;
import dao.TestDao;

import tool.Action;

@MultipartConfig
public class CsvUploadAction extends Action {

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
            if (selectedType.equals("学生")) {
                StudentDao studentDao = new StudentDao();
                List<Student> students = new ArrayList<>();
                for (String[] row : csvData) {
                    // [0] 学生番号, [1] 氏名, [2] 入学年度, [3] クラス番号, [4] 在学フラグ
                    Student student = new Student();
                    student.setNo(row[0].trim());
                    student.setName(row[1].trim());
                    student.setEntYear(Integer.parseInt(row[2].trim()));
                    student.setClassNum(row[3].trim());
                    student.setAttend(Boolean.parseBoolean(row[4].trim()));
                    student.setSchool(currentUserSchool);
                    students.add(student);
                }
                for (Student student : students) {
                    studentDao.save(student);
                }
            } else if (selectedType.equals("科目")) {
                SubjectDao subjectDao = new SubjectDao();
                List<Subject> subjects = new ArrayList<>();
                // [0] 科目コード, [1] 科目名
                for (String[] row : csvData) {
                    Subject subject = new Subject();
                    subject.setSchool(currentUserSchool);
                    subject.setName(row[1].trim());
                    subject.setCd(row[0].trim());
                    subjects.add(subject);
                }
                for (Subject subject : subjects) {
                    subjectDao.save(subject);
                }
            } else if (selectedType.equals("テスト")) {
                TestDao testDao = new TestDao();
                StudentDao studentDaoForTest = new StudentDao();
                SubjectDao subjectDaoForTest = new SubjectDao();
                List<Test> tests = new ArrayList<>();
                for (String[] row : csvData) {
                    // [0] 学生番号, [1] 科目コード, [3] テスト回数, [4] 点数, [5] クラス番号
                    Test test = new Test();
                    Student student = studentDaoForTest.get(row[0].trim());
                    Subject subject = null;
                    if (currentUserSchool != null) {
                         subject = subjectDaoForTest.get(row[1].trim(), currentUserSchool);
                    }
                    if (student == null || subject == null) {
                        System.err.println("テストデータのCSVパースエラー: 学生または科目が存在しません。学生番号:" + row[0] + ", 科目コード:" + row[1]);
                        continue;
                    }
                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setNo(Integer.parseInt(row[3].trim()));
                    test.setPoint(Integer.parseInt(row[4].trim()));
                    test.setClassNum(row[5].trim());
                    test.setSchool(currentUserSchool);
                    tests.add(test);
                }
                testDao.save(tests); // TestDaoはList<Test>を直接受け取るsaveメソッド想定
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
            } else if (selectedType.equals("先生")) {
                TeacherDao teacherDao = new TeacherDao();
                List<Teacher> teachers = new ArrayList<>();
                for (String[] row : csvData) {
                    // [0] 教員ID, [1] パスワード, [2] 氏名, [3] 管理者フラグ
                    Teacher teacher = new Teacher();
                    teacher.setId(row[0].trim());
                    teacher.setPassword(row[1].trim());
                    teacher.setName(row[2].trim());
                    teacher.setSchool(currentUserSchool);
                    teacher.setIsAdmin(Boolean.parseBoolean(row[4].trim()));
                    teacher.setIsDeleted(false);
                    teachers.add(teacher);
                }
                for (Teacher teacher : teachers) {
                    try {
                        teacherDao.create(teacher);
                    } catch (Exception e) {
                        System.err.println("先生データの保存中にエラーが発生しました: " + e.getMessage());
                    }
                }
            } else {
                 req.setAttribute("error", "無効な項目が選択されました。");
                 req.getRequestDispatcher("menu.jsp").forward(req, res);
                 return;
            }

            res.sendRedirect("csv_done.jsp");

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
