package library.model.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * ## mybatis SqlSessionFactory
 * 
 * ## Factory Pattern
 * -- 공장 객체 : DB 연결객체 제공, 자원 해제 제공
 * -- DAO Pattern
 * -- Singleton Pattern
 * 
 */
public class FactoryDao {
	private static FactoryDao instance = new FactoryDao();
	private static SqlSessionFactory factory;
	
	private FactoryDao() {}
	
	public static FactoryDao getInstance() {
		return instance;
	}
	
	static {
		String resource = "conf/library-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch(IOException e) {
				System.out.println("Error Message : " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public SqlSession getSqlSession() {
		return getSqlSession(false);
	}
	
	public SqlSession getSqlSession(boolean autocommit) {
		return factory.openSession(autocommit);
	}
}
