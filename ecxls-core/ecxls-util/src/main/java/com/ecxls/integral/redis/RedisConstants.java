package com.ecxls.integral.redis;

public class RedisConstants {	
	/**
	 * system数据库
	 */
	public final static int SYSTEM = 0;
	
	/**
	 * category数据库
	 */
	public final static int CATEGORY =1 ;	
	/**
	 * product数据库
	 */
	public final static int PRODUCT = 2;
	
	/**
	 * 属性数据库
	 */
	public final static int ATTRIBUTE = 3;
	
	/**
	 * 购物车数据库
	 */
	public final static int PURCHASE = 4;
	/**
	 * user数据库
	 */
	public final static int USER = 5;
	
	/**
	 * 订单数据库
	 */
	public final static int ORDER = 6;
	
	/**
	 * 产品浏览轨迹和分析数据库
	 */
	public final static int DA = 7;
	
	/**
	 * 秒杀
	 */
	public final static int SECOND = 11;
	
	/**
	 * 短信
	 */
	public final static int SMS_MESSAGE = 12;
	
	/**
	 * 退款原因
	 */
	public final static int REFUND_REASON = 13;
	
	/**
	 * 分润
	 */
	public final static int FACTORING_SYSTEM = 14;
	
	/**
	 * IM
	 */
	public final static int IMREGISTERED_FLAG = 15;
	
	/***
	 * 常量属性
	 */
	public final static String EVICT_ALL_TYPE = "all";
	/***
	 * 常量属性
	 */
	public final static String EVICT_KEY_TYPE = "key";
	/***
	 * 常量属性
	 */
	public final static String EVICT_PREFIX_TYPE = "prefix";
}
