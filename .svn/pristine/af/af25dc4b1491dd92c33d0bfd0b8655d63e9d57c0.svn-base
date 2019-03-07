package com.lifeshs.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SqlSessionFactoryUtils {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	@SuppressWarnings("static-access")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public static SqlSession getSqlSession() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
}
