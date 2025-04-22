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
		// studentNoをキー、TestListSubjectを値とするマップを作成（以前のロジックを活用）
		Map<String, TestListSubject> studentMap = new HashMap<>();

		try{
			// 渡された値をlistへセット
			while (rSet.next()){
				String studentNo = rSet.getString("no"); // 学生番号
				TestListSubject tl_subject = studentMap.get(studentNo);

				// マップに学生が存在しない場合、新規作成して追加
				if (tl_subject == null) {
					tl_subject = new TestListSubject();
					tl_subject.setEntYear(rSet.getInt("ent_year"));
					tl_subject.setStudentNo(studentNo);
					tl_subject.setStudentName(rSet.getString("name"));
					tl_subject.setClassNum(rSet.getString("class_num"));

					// pointsマップを初期化 (nullを入れておく)
					Map<Integer, Integer> points = new HashMap<>();
					points.put(1, null); // 1回目の点数用
					points.put(2, null); // 2回目の点数用
					tl_subject.setPoints(points);

					studentMap.put(studentNo, tl_subject); // マップに追加
				}

				// 現在の行からテスト番号(test_no)と点数(point)を取得
				int testNo = rSet.getInt("test_no");
				int point = rSet.getInt("point");
				boolean pointIsNull = rSet.wasNull(); // 点数がSQL NULLかチェック

				// testNoが1か2の場合、対応するキーに点数を設定
				if (testNo == 1 || testNo == 2) {
					Map<Integer, Integer> points = tl_subject.getPoints();
					if (!pointIsNull) {
						points.put(testNo, point); // testNo (1 or 2) をキーとして点数を設定
					} else {
						// SQL NULLの場合はJavaのnullを設定（既に入っているのでそのままでも可）
						points.put(testNo, null);
					}
				}
			}

			// マップの値をリストに変換
			list.addAll(studentMap.values());

		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
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
	    	// sql文 - TESTテーブルのNOカラムも選択する
	        statement = connection.prepareStatement(""
	        		+ "SELECT s.ent_year, s.class_num, s.no, s.name, "
	        		+ "t.point, t.subject_cd, t.no AS test_no " // t.noをtest_noとして取得
	        		+ "FROM student s "
	        		+ "LEFT JOIN test t ON s.no = t.student_no "
	        		+ "AND s.school_cd = t.school_cd "
	        		+ "AND s.class_num = t.class_num "
	        		+ "AND t.subject_cd = ? " // 結合条件で科目を絞る
	        		+ "WHERE s.ent_year = ? AND s.class_num = ? "
	        		+ "ORDER BY s.no, t.no" // 学生番号、テスト番号順でソート
	        		);

	        statement.setString(1, subject.getCd());
	        statement.setInt(2, entYear);
	        statement.setString(3, classNum);
	        set = statement.executeQuery();
	        list = postFilter(set); // postFilterで処理

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
