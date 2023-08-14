package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis.MybatisConnection;

public class ProductMybatis {
	private SqlSession sqlSession = MybatisConnection.getConnection();
	private static final String NS = "mybatis.product.";

	public int insertProduct(Product product) {
		try {
			return sqlSession.insert(NS + "insertProduct", product);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
		}
		return 0;

	}

	public int productCount() {
		return sqlSession.selectOne(NS + "productCount");
	}

	public List<Product> productList(int pageInt, int limit) {
		Map map = new HashMap();
		map.put("start", (pageInt - 1) * limit + 1);
		map.put("end", pageInt * limit);
		return sqlSession.selectList(NS + "productList", map);
	}

	public Product productOne(int prodnum) {
		return sqlSession.selectOne(NS + "productOne", prodnum);
	}

	public List<Product> productManagement() {
		Map map = new HashMap();
		return sqlSession.selectList(NS + "productManagement", map);
	}

	public int productUpdate(Product product) {
		try {
			return sqlSession.insert(NS + "productUpdate", product);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sqlSession.commit();
		}
		return 0;
	}

	public int productDelete(int prodnum) {
		try {
			return sqlSession.delete(NS + "boardDelete", prodnum);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sqlSession.commit();
		}
		return 0;

	}

	public List<Product> orderProductList(int prodnum) { // 주문서에서 찍어낼 product list

		return sqlSession.selectList(NS + "orderProductList", prodnum);
	}
	
	public int stockUpdate(Product product) {
		try {
			return sqlSession.insert(NS + "stockUpdate", product);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sqlSession.commit();
		}
		return 0;
	}

}