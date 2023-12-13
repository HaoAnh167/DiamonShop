package DiamonShop.Dao;

import org.springframework.stereotype.Repository;

import DiamonShop.Entity.BillDetail;
import DiamonShop.Entity.Bills;

@Repository
public class BillsDao extends BaseDao{
	
	public int addBills(Bills bill) {	
		StringBuffer  sql = new StringBuffer();
		sql.append("insert into bills ");
		sql.append("( ");
		sql.append("	`user`, `phone`, `display_name`, `address`, `total`, `quantity`, `note` ");
		sql.append(") ");
		sql.append("values ");
		sql.append("( ");
		sql.append("	'" + bill.getUser() + "', ");
		sql.append("	'" + bill.getPhone() + "', ");
		sql.append("	'" + bill.getDisplay_name() + "', ");
		sql.append("	'" + bill.getAddress() + "', ");
		sql.append("	" + bill.getTotal() + ", ");
		sql.append("	" + bill.getQuantity() + ", ");
		sql.append("	'" + bill.getNote() + "' ");
		sql.append(")");
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}
	
	public int getIDLastBill() {
		StringBuffer sql = new StringBuffer();
		sql.append("select max(id) from bills");
		int id = _jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, Integer.class);
		return id;
	}
	
	public int addBillDetail(BillDetail billDetail) {
		StringBuffer  sql = new StringBuffer();
		sql.append("insert into billdetail ");
		sql.append("( ");
		sql.append("	id_product, ");
		sql.append("	id_bills, ");
		sql.append("	quantity, ");
		sql.append("	total ");
		sql.append(") ");
		sql.append("values ");
		sql.append("( ");
		sql.append("	" + billDetail.getId_product() + ", ");
		sql.append("	" + billDetail.getId_bills() + ", ");
		sql.append("	" + billDetail.getQuantity() + ", ");
		sql.append("  " + billDetail.getTotal() + " ");
		sql.append(")");
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}
}
