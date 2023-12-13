<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layouts/user/taglib.jsp"%>
<head>
<title>List cart</title>
</head>
<body>
	<div class="row">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="index.html">Home</a> <span class="divider">/</span></li>
				<li class="active">Check Out</li>
			</ul>
			<div class="well well-small">
				<h1>
					Check Out <small class="pull-right"> ${TotalQuantityCart} Items are in the
						cart </small>
				</h1>
				<hr class="soften" />

				<table class="table table-bordered table-condensed">
					<thead>
						<tr>
							<th>Product</th>
							<th>Description</th>
							<th>Color</th>
							<th>Unit price</th>
							<th>Qty</th>
							<th>Edit</th>
							<th>Delete</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
					
							<c:forEach var="item" items="${Cart}">
								<tr>
									<td><img width="100" src="assets/user/img/${item.value.product.img}" alt=""></td>
									<td>${item.value.product.title}</td>
									<td><span class="shopBtn" style="background-color: ${item.value.product.code_color}"><span class="icon-ok"></span></span>
									</td>
									<td>$<fmt:formatNumber type="number" groupingUsed="true" value="${item.value.product.price}" /></td>
									<td><input type="number" min="1" class="span1"
										style="max-width: 34px" placeholder="1"
										id="quantity-cart-${item.key}" size="16" type="text" value="${item.value.quantity}">
									</td>
									
									<td><button data-id="${item.key}"
										class="btn btn-mini btn-danger edit-cart" type="button"><span
											class="icon-edit"></span></button></td>
											
									<td><a href="<c:url value="/deleteCart/${item.key}" />"
										class="btn btn-mini btn-danger" type="button"><span
											class="icon-remove"></span></a></td>
									<td>$<fmt:formatNumber type="number" groupingUsed="true" value="${item.value.totalPrice}" /></td>
								</tr>
							</c:forEach>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>$<fmt:formatNumber type="number" groupingUsed="true" value="${TotalPriceCart}" /></td>
							</tr>
					</tbody>
				</table>
				<br /> <a href="products.html" class="shopBtn btn-large"><span
					class="icon-arrow-left"></span> Continue Shopping </a> <a
					href="checkout" class="shopBtn btn-large pull-right">Next <span
					class="icon-arrow-right"></span></a>

			</div>
		</div>
	</div>
	
<content tag="scipt">	
	<script>
		$(".edit-cart").on("click", function(){
			var id = $(this).data("id");
			var quantity = $("#quantity-cart-"+id).val();
			window.location = "editCart/" + id + "/" + quantity;
		});
	</script>
</content>
</body>
