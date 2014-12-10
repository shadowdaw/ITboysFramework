package org.itboys.product.constant;

import org.itboys.product.entity.mysql.Order;

import com.google.common.base.Function;

public class TradeConstants {

	public static Function<Order, Long> getOrderId = new Function<Order, Long>() {
		@Override
		public Long apply(Order input) {
			return input.getId();
		}
	};
	public static Function<Order, Long> getMemberId = new Function<Order, Long>() {
		@Override
		public Long apply(Order input) {
			return input.getMemberId();
		}
	};
}
