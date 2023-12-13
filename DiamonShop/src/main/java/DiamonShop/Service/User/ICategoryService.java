package DiamonShop.Service.User;

import java.util.List;

import org.springframework.stereotype.Service;

import DiamonShop.Dto.ProductsDto;
@Service
public interface ICategoryService {
	
	public List<ProductsDto> getAllProductsByID(int id);
	
	public List<ProductsDto> getDataProductsPaginate(int id, int start, int totalPage);
}
