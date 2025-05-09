package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {
	/**
	 * getメソッド 教員IDを指定して教員インスタンスを1件取得する
	 *
	 * @param id:String
	 *            教員ID
	 * @return 教員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public Teacher get(String id) throws Exception {
		// 教員インスタンスを初期化
		Teacher teacher = new Teacher();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from teacher where id=?");
			// プリペアードステートメントに教員IDをバインド
			statement.setString(1, id);
			// プリペアードステートメントを実行
			ResultSet resultSet = statement.executeQuery();

			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (resultSet.next()) {
				// リザルトセットが存在する場合
				// 教員インスタンスに検索結果をセット
				teacher.setId(resultSet.getString("id"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setName(resultSet.getString("name"));
				// 学校フィールドには学校コードで検索した学校インスタンスをセット
				teacher.setSchool(schoolDao.get(resultSet.getString("school_cd")));
				teacher.setIsAdmin(resultSet.getBoolean("is_admin"));
			} else {
				// リザルトセットが存在しない場合
				// 教員インスタンスにnullをセット
				teacher = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return teacher;
	}

	/**
	 * loginメソッド 教員IDとパスワードで認証する
	 *
	 * @param id:String
	 *            教員ID
	 * @param password:String
	 *            パスワード
	 * @return 認証成功:教員クラスのインスタンス, 認証失敗:null
	 * @throws Exception
	 */
	public Teacher login(String id, String password) throws Exception {
		// 教員クラスのインスタンスを取得
		Teacher teacher = get(id);
		// 教員がnullまたはパスワードが一致しない場合
		if (teacher == null || !teacher.getPassword().equals(password)) {
			return null;
		}
		return teacher;
	}

	public List<Teacher> getList() throws Exception {
		List<Teacher> teacherList = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("select * from teacher where is_deleted = false");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getString("id"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setName(resultSet.getString("name"));
				teacher.setIsAdmin(resultSet.getBoolean("is_admin"));
				teacher.setIsDeleted(resultSet.getBoolean("is_deleted"));
				teacherList.add(teacher);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		return teacherList;
	}

	public boolean create(Teacher teacher) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("insert into teacher (id, password, name, school_cd, is_admin, is_deleted) values (?, ?, ?, ?, ?, ?)");
			statement.setString(1, teacher.getId());
			statement.setString(2, teacher.getPassword());
			statement.setString(3, teacher.getName());
			statement.setString(4, teacher.getSchool().getCd());
			statement.setBoolean(5, teacher.getIsAdmin());
			statement.setBoolean(6, teacher.getIsDeleted());
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public boolean update(Teacher teacher) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("update teacher set password = ?, name = ?, is_admin = ?, is_deleted = ? where id = ?");
			statement.setString(1, teacher.getPassword());
			statement.setString(2, teacher.getName());
			statement.setBoolean(3, teacher.getIsAdmin());
			statement.setBoolean(4, teacher.getIsDeleted());
			statement.setString(5, teacher.getId());
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public boolean delete(String id) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("update teacher set is_deleted = true where id = ?");
			statement.setString(1, id);
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public boolean restore(String id) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("update teacher set is_deleted = false where id = ?");
			statement.setString(1, id);
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public List<Teacher> filterDeleted(School school) throws Exception {
		List<Teacher> teacherList = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("select * from teacher where is_deleted = true AND school_cd = ?");
			statement.setString(1, school.getCd());
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getString("id"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setName(resultSet.getString("name"));
				teacher.setIsAdmin(resultSet.getBoolean("is_admin"));
				teacher.setIsDeleted(resultSet.getBoolean("is_deleted"));
				teacherList.add(teacher);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return teacherList;
	}

	public List<Teacher> filterActive(School school) throws Exception {
		List<Teacher> teacherList = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("select * from teacher where is_deleted = false AND school_cd = ?");
			statement.setString(1, school.getCd());
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getString("id"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setName(resultSet.getString("name"));
				teacher.setIsAdmin(resultSet.getBoolean("is_admin"));
				teacher.setIsDeleted(resultSet.getBoolean("is_deleted"));
				teacherList.add(teacher);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return teacherList;
	}
}
