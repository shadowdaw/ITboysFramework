package org.itboys.product.constant;

import org.itboys.product.entity.mysql.Order;
import org.itboys.product.entity.mysql.OrderItem;
import org.itboys.product.entity.mysql.product.Product;
import org.itboys.product.entity.mysql.product.ProductTagRel;

import com.google.common.base.Function;

public class Constants {
	
	//项目id
	public static final Long PROJECT_ID=0L;
	
	public interface CookieKey{
		/**
		 *cookie中的member用户key
		 */
		public final String USER_ID = "userId";//手机端用户登录时的cookie key
	}
	
	//订单状态
	public interface OrderStatus{
		public static final Integer ORDER_CREATE = 0; 					//等待买家付款
		public static final Integer ORDER_PAY = 1;                   	//买家已付款
		public static final Integer ORDER_SEND = 2;						//卖家已发货
		public static final Integer ORDER_SUCCESS = 3;					//交易成功
		public static final Integer ORDER_CLOSE = 4;				    //交易关闭
		public static final Integer ORDER_BACK = 5;						//退款中的订单
		public static final Integer ORDER_CANCLE = 6;					//交易取消
		public static final Integer ORDER_DELETE = 7;					//会员删除
		public static final Integer ORDER_LOSE_EFFICACY=8;					//失效的
	}
	
	//付款方式
	public interface OrderPayType{
		public static final Integer PAY_TYPE0 = 0;  //银行卡
		public static final Integer PAY_TYPE1 = 1;  //午苇卡
		public static final Integer PAY_TYPE3 = 3;  //支付宝
	}
	
	
	public interface ProductPriceType{
		public static final Integer PRODUCT_PRICE = 1;  //原价
		public static final Integer PRODUCT_CX= 2;  //促销
		public static final Integer PRODUCT_QG = 3;  //抢购
		
		//购物车里面的商品作为失效状态
		public static final Integer PRODUCT_SX = -1;  //抢购
	}
	
	public static final Integer BLOG_TYPE=1;//表示精华帖子
	
	//能量计算常量        1当量=57kcal
	public static final int ENERGY_CONSTANT = 57;
	/**
	 * 会员状态
	 *
	 */
	
	public static final Integer PRO_OBJ_TYPE1=-1;
	
	public static final String PARAM_SPLIT="-";
	
	public interface MemberLevel{
		
		public static final Integer VIP = 3;//VIP会员
		
		public static final Integer COMMON = 2;//普通会员
		
		public static final Integer NON_ACTIVATED =1;//未激活(未通过手机验证)
	
	}
	
	public interface MemberPart{
		
		public static final String MEMBER_PART = "memberpart";//VIP会员组别
		
	}
	
	public interface SendMessage{
		
		public static final String CONTENT_CODE = "contentcode";//内容类别
		
	}
	
	public interface Map{
		//--默认经纬度 北京的
		public static final String SHOP_BEIJING_LNG = "116.395645"; //经度
		public static final String SHOP_BEIJING_LAT = "39.929986"; //纬度
	}
	
	
	/**
	 * 留言状态
	 *
	 */
	public interface AskStatus{
		
		public static final Integer INVALID = 0;//无效的
		
	}
	
	/**
	 * 提问类型
	 */
	public static final String ASKTYPE="product";
	
	
	/**
	 * 积分状态
	 *
	 */
	public interface ScoreStatus{
		
		public static final Integer UNUSED  = 0;//未使用
		
		public static final Integer USED_PART = 1;//部分使用
		
		public static final Integer USED = 2;//全部使用
		
		public static final Integer INVALID = 3;//已失效
		
	}
	
	
	/**
	 * 积分类型
	 *
	 */
	public interface ScoreType{
		
		public static final Integer REGISTER  = 0;//注册
		
		public static final Integer VIP  = 1;//VIP激活
		
		public static final Integer BUY_PRODUCT = 2;//购买产品
		
		public static final Integer JOIN_ACTIVITY = 3;//参加活动				
		
		public static final Integer OTHER = 4;//其他
		
	}
	
	/**
	 * vip激活码的状态
	 *
	 */
	public interface CDkeyStatus{
		
		public static final Integer USED  = 1;//已被使用
		
		public static final Integer NON_USED= 0;//可用的		
	}
	
	public interface kvConfig{
		public static String INDEX_INFORMATION_KEY="indexInformation";//首页滚动资讯图片
		public static String INDEX2_INFORMATION_KEY="index2Information";//首页滚动图下面的资讯图片
		public static String PRODUCT_DAYCX_KEY="proTagd";//当日促销key
		public static String PRODUCT_XXRM_KEY="proTagx";//新新热卖key
		public static String PRODUCT_WWTJ_KEY="proTagw";//午苇推荐key
		public static String CATEGORY_SX_KEY="proCats";//生鲜食品的分类
		public static String CATEGORY_NYXX_KEY="proCatn";//酿油休闲食品的分类
		public static String SALAD_PRODUCT_KEY="saladPro";//凉拌菜的商品
		public static String SMS_TEMPLATE_KEY="smsTemplateId";//注册用户时发送验证码的信息模板kay
		public static String WWK_CATEGORY_KEY= "wwkCatId";//午苇卡这个商品分类的key
		public static String WULIU_FEE_KEY="expressFee";//包邮底线
		public static String WULIU_MONEY_KEY="expressMoney";//商品超出多少后额外出多少费用
		public static String SMS_FORGETPASS_KEY="forgetPassTemplateId";//用户找回密码发送信息的的模板kay
		public static String SALAD_CATEGORY_KEY="saladCategoryId";//凉拌菜的分类id
		public static String BISCUITS_PRODUCT_KEY="BiscuitsPro";//零食吧的商品biscuitsCategoryId
		public static String BISCUITS_CATEGORY_KEY="biscuitsCategoryId";//零食吧的分类id
		public static String GIFT_CATEGORY_KEY="lpkCategoryId";//礼品卡的分类id
	}
	
	public interface Codes{
		public static String 	PRODUCT_PRICE="proPrice";//商品价格
		public static String 	PEISONG_TYPE="peisongType";//配送方式
		public static String  PRODUCT_JLFS="jlfs";//商品计量方式
		public static String  ASK_TYPE="askType";//会员提问类型
	}
	
	public interface ProductStauts{
		public static Integer ISNEW_YES=1;//是新品		
		public static Integer CXTYPE_YES=0;//是促销
		public static Integer TYPE_NO=2;//已下架
		
	}
	
	//商品关联文章的类型
	public static final Integer RELOBJTYPE=22;
	//商品关联食谱的类型
	public static final Integer RELMENUTYPE=33;
	
	
	public interface 	MemberAccountType{
		public static final Integer MEMBER_ACCOUNT_TYPE=1;//账户金额关联的类型
		public static final Integer MEMBER_ACCOUNT_INTO=0;//表示账户金额是进的
		public static final Integer MEMBER_ACCCOUNT_OUT=1;//表示账户金额是出的
	}
	
	
	public interface MealType{
		public static Integer  MEAL_TYPE1=1;//组合套餐类型
		public static Integer MEAL_TYPE2=2;//也是组合套餐，针对凉拌菜
	}
	
	public static final Integer PRODUCT_TYPE=2;//表示商品下架
	
	//根据list对象得到list 的ids
	public final static Function<OrderItem, Long> getProductIds= new Function<OrderItem, Long>() {
		@Override
		public Long apply(OrderItem input) {
			return input.getProductId();
		}
	};
	
	public final static Function<ProductTagRel, Long> getObjIds= new Function<ProductTagRel, Long>() {
		@Override
		public Long apply(ProductTagRel input) {
			return input.getObjId();
		}
	};
	
	public final static Function<Order, Long> getOrderIds= new Function<Order, Long>() {
		@Override
		public Long apply(Order input) {
			return input.getId();
		}
	};
	
	public final static Function<Product, Long> getPids= new Function<Product, Long>() {
		@Override
		public Long apply(Product input) {
			return input.getId();
		}
	};
	
	
	public interface SiteCurrent{
		public static final String SITE_CURRENT_SHOW = "site_current";
		public static final String SITE_INDEX_CURRENT_1 = "1";  //首页
		public static final String SITE_INDEX_CURRENT_2 = "2";  //热卖推荐
		public static final String SITE_INDEX_CURRENT_3 = "3";  //今日特价
		public static final String SITE_INDEX_CURRENT_4 = "4";  //新品推荐
		public static final String SITE_INDEX_CURRENT_5 = "5";  //积分兑换
		public static final String SITE_INDEX_CURRENT_6 = "6";  //食谱
		public static final String SITE_INDEX_CURRENT_7 = "7";  //公司介绍
		public static final String SITE_INDEX_CURRENT_8 = "8";  //午苇之家
	}
	
	public interface ProductParam{
		public static final String PARAM_PL = "配料";  //配料
		public static final String PARAM_BZ= "产品执行标准";  //产品执行标准
		public static final String PARAM_XKZ= "生产许可证";  //生产许可证
		public static final String PARAM_BZQ = "保质期";  //保质期
		public static final String PARAM_SCRQ = "生产日期";  //生产日期
		public static final String PARAM_SYFF = "食用方法";  //食用方法
		public static final String PARAM_CCFF = "储存方法";  //储存方法
		public static final String PARAM_CD = "产地";  //产地
		public static final String PARAM_ZZS = "制造商";  //制造商
		public static final String PARAM_ADDRESS = "地址";  //地址
		public static final String PARAM_PHONE = "电话";  //电话
	}

	
	public interface WapKvConfig{
		public static String WAP_INFORMATION_KEY="wapIndexInformation";//手机端首页轮播图片
	}
}
