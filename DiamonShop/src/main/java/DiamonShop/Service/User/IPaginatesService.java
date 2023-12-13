package DiamonShop.Service.User;

import org.springframework.stereotype.Service;

import DiamonShop.Dto.PaginatesDto;

@Service
public interface IPaginatesService {
	public PaginatesDto getInfoPaginatesDto(int totalData, int limit, int currentPage);
}
