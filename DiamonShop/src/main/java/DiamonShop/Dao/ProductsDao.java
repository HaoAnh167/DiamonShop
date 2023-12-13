package DiamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamonShop.Dto.MapperProductsDto;
import DiamonShop.Dto.ProductsDto;
@Repository
public class ProductsDao extends BaseDao{
	
	private StringBuffer sqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("p.id as id_product ");
		sql.append(", p.id_category ");
		sql.append(", p.sizes ");
		sql.append(", p.name ");
		sql.append(", p.price ");
		sql.append(", p.sale ");
		sql.append(", p.title ");
		sql.append(", p.highlight ");
		sql.append(", p.new_product ");
		sql.append(", p.details ");
		sql.append(", c.id_color ");
		sql.append(", c.name_color ");
		sql.append(", c.code_color ");
		sql.append(", c.img ");
		sql.append(", p.created_at ");
		sql.append(", p.updated_at ");
		sql.append("FROM ");
		sql.append("products AS p ");
		sql.append("INNER JOIN ");
		sql.append("colors AS c ");
		sql.append("ON p.id = c.id_product ");
//		sql.append("SELECT * FROM products as p inner join colors as c on p.id = c.id_product where p.highlight = true and p.new_product = true GROUP BY p.id,c.id_product ORDER BY rand() limit 12");
		return sql;
	}
	
	private String sqlProducts(boolean newProduct, boolean highlight) {
		StringBuffer sql = sqlString();
		sql.append("WHERE 1 = 1 ");
		if (highlight) {
			sql.append("AND p.highlight = true ");
		}
		if (newProduct) {
			sql.append("AND p.new_product = true ");
		}
		sql.append("GROUP BY p.id, c.id_product ");
		sql.append("ORDER BY RAND() ");
		if (highlight) {
			sql.append("LIMIT 9 ");
		}
		if (newProduct) {
			sql.append("LIMIT 12 ");
		}
		return sql.toString();
	}
	
	private StringBuffer sqlProductsByID(int id) {
		StringBuffer sql = sqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND id_category = " + id + " ");
		return sql;
	}

	private String sqlProductsPaginate(int id, int start, int totalPage) {
		StringBuffer sql = sqlProductsByID(id);
		sql.append("LIMIT " + start + "," + totalPage );
		return sql.toString();
	}
	
	public List<ProductsDto> getDataProductsDto() {
		List<ProductsDto> list = new ArrayList<ProductsDto>();
		String sql = sqlProducts(true, false);
		list = _jdbcTemplate.query(sql, new MapperProductsDto());
		return list;
	}
	
	public List<ProductsDto> getAllProductsByID(int id) {
		List<ProductsDto> list = new ArrayList<ProductsDto>();
		String sql = sqlProductsByID(id).toString();
		list = _jdbcTemplate.query(sql, new MapperProductsDto());
		return list;
	}
	
	public List<ProductsDto> getDataProductsPaginate(int id, int start, int totalPage) {
		StringBuffer sqlGetDataByID = sqlProductsByID(id);
		List<ProductsDto> listProductsByID = _jdbcTemplate.query(sqlGetDataByID.toString(), new MapperProductsDto());
		List<ProductsDto> listProducts = new ArrayList<ProductsDto>();
		if (listProductsByID.size() > 0) {
			String sql = sqlProductsPaginate(id,start,totalPage);
			listProducts = _jdbcTemplate.query(sql, new MapperProductsDto());
		}	
		return listProducts;
	}
	
	private String sqlProductByID(int id) {
		StringBuffer sql = sqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND p.id = " + id + " ");
		sql.append("LIMIT 1 ");
		return sql.toString();
	}
	
	public List<ProductsDto> getProductByID(int id) {
		String sql = sqlProductByID(id);
		List<ProductsDto> listProduct = _jdbcTemplate.query(sql, new MapperProductsDto());
		return listProduct;	
	}
	
	public ProductsDto findProductByID(int id) {
		String sql = sqlProductByID(id);
		ProductsDto product = _jdbcTemplate.queryForObject(sql, new MapperProductsDto());
		return product;	
	}
}
	