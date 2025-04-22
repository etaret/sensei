package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

	// 科目選択取得
	public Subject get(String cd, School school) throws Exception {
		// インスタンス初期化
		Subject subject = new Subject();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("SELECT school_cd, cd, name FROM subject WHERE school_cd = ? AND cd = ? ORDER BY cd");
			statement.setString(1, school.getCd());
			statement.setString(2, cd);
			ResultSet rSet = statement.executeQuery();

			SchoolDao schoolDao = new SchoolDao(); // 学校Dao初期化

			if (rSet.next()) {
				// リザルトセットが存在時、検索結果セット
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
			} else {
				subject = null;
			}
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
		return subject;
	}

	// 科目の一覧取得
	public List<Subject> filter(School school) throws Exception {
		// 定義
		List<Subject> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet set = null;
	    try {
	    	// sql処理
	        statement = connection.prepareStatement("SELECT school_cd, cd, name FROM subject WHERE school_cd = ? ORDER BY cd");
	        statement.setString(1, school.getCd());
	        set = statement.executeQuery();

	        while (set.next()) {
	        	Subject subject = new Subject();
	        	SchoolDao schoolDao = new SchoolDao();
	        	subject.setSchool(schoolDao.get(set.getString("school_cd")));
	        	subject.setCd(set.getString("cd"));
	        	subject.setName(set.getString("name"));
	            list.add(subject);
	        }

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

	// 登録、変更処理
	public boolean save(Subject subject) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// 科目が存在しているかどうかのチェック用データ
			Subject old = get(subject.getSchool().getCd(), subject.getSchool());
			// 存在してない場合入力データ登録
			if (old == null) {
				statement = connection.prepareStatement(
						"INSERT INTO subject(school_cd, cd, name) values(?, ?, ?)");
				statement.setString(1, subject.getSchool().getCd());
				statement.setString(2, subject.getCd());
				statement.setString(3, subject.getName());
			// 存在している場合データの変更登録
			} else {
				statement = connection
						.prepareStatement("UPDATE subject SET name = ? WHERE school_cd = ?, cd = ?");
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getSchool().getCd());
				statement.setString(3, subject.getCd());
			}
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		}  finally {
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

		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 削除処理
	public boolean delete(Subject subject) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			statement = connection.prepareStatement(
					"DELETE FROM subject WHERE school_cd = ? AND cd =  ?");
			statement.setString(1, subject.getSchool().getCd());
			statement.setString(2, subject.getCd());
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		}  finally {
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
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}




}
