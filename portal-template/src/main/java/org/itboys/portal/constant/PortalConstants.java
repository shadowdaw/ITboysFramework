package org.itboys.portal.constant;


public class PortalConstants {
	
	public interface ItemParam{
		public static final Integer IS_LEAF=1;
		
		public final static Integer TYPE_0=0;//还是栏目
		public final static Integer TYPE_1=1;//单个信息
		public final static Integer TYPE_2=2;//多个信息
		public final static Integer TYPE_3=3;//连接地址
	}

	/**
	 * codes类型
	 */
	public interface CodesType{
		public static final String NAV_APP="navAppkey";//应用导航
		public static final String NAV_CHANNEL="channel";//应用导航
		public static final String PRO_NAV_APP="proNavAppKey";//项目应用导航
	}
	
	public interface SessionKey{
		/**
		 * session中的projectId
		 */
		public final String PROJECT_ID="pi";
	}
	
	//通用图片水印的key
	public static final String DEFAULT_WATERMARK="defaultWaterMark";
	
	//标签类型(1表示信息类型的)
	public static final String TAG_TYPE="1";
	
	
	//数据字典配置字段（用于西安高新网）
	public interface Code{
		public static final String INDEX_INFO="mlgxsy"; 
	}
	
	//信息关联对象类型
	public static final String OBJTYPE="1";
}
