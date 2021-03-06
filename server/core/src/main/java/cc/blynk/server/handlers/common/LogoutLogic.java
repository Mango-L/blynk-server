package cc.blynk.server.handlers.common;

import cc.blynk.server.core.model.auth.User;
import cc.blynk.server.core.protocol.model.messages.StringMessage;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cc.blynk.utils.BlynkByteBufUtil.ok;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 2/1/2015.
 *
 */
public final class LogoutLogic {

    private static final Logger log = LogManager.getLogger(LogoutLogic.class);

    private LogoutLogic() {
    }

    public static void messageReceived(ChannelHandlerContext ctx, User user, StringMessage msg) {
        user.isLoggedOut = true;
        log.debug("User {}-{} did logout.", user.email, user.appName);
        ctx.writeAndFlush(ok(msg.id), ctx.voidPromise());
        ctx.close();
    }

}
