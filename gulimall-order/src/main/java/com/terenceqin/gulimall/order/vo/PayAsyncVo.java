package com.terenceqin.gulimall.order.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PayAsyncVo {

	private Date gmt_create;

	private String charset;

	private String gmt_payment;

	private Date notify_time;

	private String subject;

	private String sign;

	private String buyer_id;//支付者的id

	private String body;//订单的信�?
	private String invoice_amount;//支付金额

	private String version;

	private String notify_id;//通知id

	private String fund_bill_list;

	private String notify_type;//通知类型�?trade_status_sync

	private String out_trade_no;//订单�?
	private String total_amount;//支付的总额

	private String trade_status;//交易状�? TRADE_SUCCESS

	private String trade_no;//流水�?
	private String auth_app_id;

	private String receipt_amount;//商家收到的款

	private String point_amount;//

	private String app_id;//应用id

	private String buyer_pay_amount;//最终支付的金额

	private String sign_type;//签名类型

	private String seller_id;//商家的id

	@Override
	public String toString() {
		return "gmt_create --> '" + gmt_create + '\'' +
				"\ncharset --> '" + charset + '\'' +
				"\ngmt_payment --> '" + gmt_payment + '\'' +
				"\nnotify_time --> '" + notify_time + '\'' +
				"\nsubject --> '" + subject + '\'' +
				"\nsign --> '" + sign + '\'' +
				"\nbuyer_id --> '" + buyer_id + '\'' +
				"\nbody --> '" + body + '\'' +
				"\ninvoice_amount --> '" + invoice_amount + '\'' +
				"\nversion --> '" + version + '\'' +
				"\nnotify_id --> '" + notify_id + '\'' +
				"\nfund_bill_list --> '" + fund_bill_list + '\'' +
				"\nnotify_type --> '" + notify_type + '\'' +
				"\nout_trade_no --> '" + out_trade_no + '\'' +
				"\ntotal_amount --> '" + total_amount + '\'' +
				"\ntrade_status --> '" + trade_status + '\'' +
				"\ntrade_no --> '" + trade_no + '\'' +
				"\nauth_app_id --> '" + auth_app_id + '\'' +
				"\nreceipt_amount --> '" + receipt_amount + '\'' +
				"\npoint_amount --> '" + point_amount + '\'' +
				"\napp_id --> '" + app_id + '\'' +
				"\nbuyer_pay_amount --> '" + buyer_pay_amount + '\'' +
				"\nsign_type --> '" + sign_type + '\'' +
				"\nseller_id --> '" + seller_id + '\'';
	}
}
