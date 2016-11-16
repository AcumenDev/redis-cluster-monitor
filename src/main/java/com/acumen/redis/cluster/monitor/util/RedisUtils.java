package com.acumen.redis.cluster.monitor.util;

import org.springframework.data.redis.connection.RedisClusterNode;

import java.nio.charset.Charset;

/**
 * Created by vstalmakov on 16.11.16.
 */
public class RedisUtils {
    public static RedisClusterNode buildFromHostAndPort(String node) {
        String[] hostAndPort = node.split(":");
        return new RedisClusterNode(hostAndPort[0], Integer.parseInt(hostAndPort[1]), null);
    }

    public static String decode(byte[] bytes) {
        return new String(bytes, Charset.defaultCharset());
    }
}
