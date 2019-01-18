package com.zhaohe.demo.util.privatesocket;

/**
 * 协议类，客户端和服务器都要保留
 * @author zhaohe
 *
 */
public interface CrazyitProtocol {
	//协议字符串长度
	int PROTOCOL_LEN = 2;
	//协议字符串，服务端和客户端交换的信息都应该在前后添加这种字符
	String MSG_ROUND ="@M";
	String USER_ROUND="@U";
	String LOGIN_SUCCESS="1";
	String NAME_REP="-1";
	String PRIVATE_ROUND="@*";
	String SPLIT_SING="@#";
	

}
