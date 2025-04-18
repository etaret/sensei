package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	// arraylistへの格納処理
	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		// list定義
		List<TestListSubject> list = new ArrayList<>();
		try{
			// 渡された値をlistへセット
			while (rSet.next()){
				TestListSubject tl_subject = new TestListSubject();
				tl_subject.setEntYear(rSet.getInt("ent_year"));
				tl_subject.setStudentNo(rSet.getString("no"));
				tl_subject.setStudentName(rSet.getString("name"));
				tl_subject.setClassNum(rSet.getString("class_num"));

				// mapへの格納処理
				Map<Integer, Integer> points = new HashMap<>();
				points.put(1, rSet.getInt("test_1"));
				points.put(2, rSet.getInt("test_2"));

				tl_subject.setPoints(points);

				list.add(tl_subject);
			}
		} catch (SQLException | NullPointerException e){ e.printStackTrace();}
		return list;
	}

	// 入学年、クラス番号、科目番号、学校番号に合うデータ取得
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
		// 定義
		List<TestListSubject> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet set = null;
	    try {
	    	// sql文
	        statement = connection.prepareStatement(""
	        		+ "SELECT student.ent_year, student.class_num, "
	        		+ "student.no, student.name, "
	        		+ "CASE WHEN test.no = 1 THEN test.point END AS test_1, "
	        		+ "CASE WHEN test.no = 2 THEN test.point END AS test_2, "
	        		+ "test.subject_cd "
	        		+ "FROM test LEFT JOIN student "
	        		+ "ON student.no = test.student_no AND student.school_cd = test.school_cd "
	        		+ "AND student.class_num = test.class_num AND test.subject_cd = ? "
	        		+ "WHERE student.ent_year = ? AND student.class_num = ?"
	        		+ "GROUP BY student.ent_year, student.class_num, student.no, student.name, test.subject_cd;"
	        		);

	        statement.setString(1, subject.getCd());
	        statement.setInt(2, entYear);
	        statement.setString(3, classNum);
	        set = statement.executeQuery();
	        list = postFilter(set);

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return list;
	}
}
