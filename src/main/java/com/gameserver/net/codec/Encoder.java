package com.gameserver.net.codec;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.gameserver.net.Message;
import com.gameserver.net.MsgType;

/**
 * 编码器
 * 
 * @author zhaohui
 *
 */
public abstract class Encoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		Message message = (Message) msg;
		if (message.getHeader().getType() == MsgType.JSON.getValue()) {
			return msg;
		}
		Object buffer = transformData(message.getData());
		message.setData(buffer);
		return msg;
	}

	/**
	 * 将逻辑对象转换成二进制数据
	 * 
	 * @param msg
	 * @throws Exception
	 */
	protected abstract Object transformData(Object msg) throws Exception;

}
