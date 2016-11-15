package com.acumen.redis.cluster.monitor.util.convert;

import com.acumen.redis.cluster.monitor.model.RedisClusterInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by vladimir akummail@gmail.com on 11/15/16.
 */
@Component
public class ClusterInfoToRedisClusterInfoConvert implements Converter<org.springframework.data.redis.connection.ClusterInfo, RedisClusterInfo> {
    @Override
    public RedisClusterInfo convert(org.springframework.data.redis.connection.ClusterInfo source) {
        return new RedisClusterInfo()
                .setCurrentEpoch(source.getCurrentEpoch())
                .setKnownNodes(source.getKnownNodes())
                .setMyEpoch(source.getCurrentEpoch())
                .setClusterSize(source.getClusterSize())
                .setSlotsAssigned(source.getSlotsAssigned())
                .setSlotsFail(source.getSlotsFail())
                .setSlotsPfail(source.getSlotsPfail())
                .setSlotsOk(source.getSlotsOk())
                .setState(source.getState())
                .setMessagesReceived(source.getMessagesReceived())
                .setMessagesSent(source.getMessagesSent());
    }
}
