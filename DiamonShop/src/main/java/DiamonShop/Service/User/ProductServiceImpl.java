package DiamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamonShop.Dao.ProductsDao;
import DiamonShop.Dto.ProductsDto;
@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	ProductsDao productDao = new ProductsDao();
	
	public ProductsDto getProductByID(int id) {
		List<ProductsDto> listProduct = productDao.getProductByID(id);
		return listProduct.get(0);
	}

	public List<ProductsDto> getProductByIDCategory(int id) {		
		return productDao.getAllProductsByID(id);
	}

}
