package com.acumen.redis.cluster.monitor.util.convert;

import com.acumen.redis.cluster.monitor.model.cluster.node.Master;
import com.acumen.redis.cluster.monitor.model.cluster.node.Node;
import com.acumen.redis.cluster.monitor.model.cluster.node.Range;
import com.acumen.redis.cluster.monitor.model.cluster.node.Slave;
import com.acumen.redis.cluster.monitor.model.cluster.slot.Slot;
import com.acumen.redis.cluster.monitor.model.info.Info;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisClusterNode.SlotRange;
import org.springframework.data.redis.connection.convert.Converters;

import java.util.Properties;
import java.util.Set;

public class AppConverters extends Converters {
    private static final Converter<Set<RedisClusterNode>, Set<Node>> CLUSTER_NODES_TO_NODES = new ClusterNodesToNodesConvert();
    private static final Converter<Set<RedisClusterNode>, Set<Slot>> CLUSTER_NODE_TO_SLOTS = new ClusterNodesToSlotsConvert();
    private static final Converter<SlotRange, Range> SLOT_RANGE_TO_RANGE = new SlotRangeToRangeConvert();
    private static final Converter<Properties, Info> PROPERTIES_TO_INFO = new PropertiesToInfoConvert();

    public static Converter<Set<RedisClusterNode>, Set<Node>> toSetOfNode() {
        return CLUSTER_NODES_TO_NODES;
    }

    public static Converter<Set<RedisClusterNode>, Set<Slot>> toSetOfSlot() {
        return CLUSTER_NODE_TO_SLOTS;
    }

    public static Converter<SlotRange, Range> toRange() {
        return SLOT_RANGE_TO_RANGE;
    }

    public static Converter<Properties, Info> toInfo() {
        return PROPERTIES_TO_INFO;
    }

    public static RedisClusterNode getMasterById(String id, Set<RedisClusterNode> source) {
        for (RedisClusterNode cnode : source) {
            if (cnode.getId().equals(id)) {
                return cnode;
            }
        }
        return null;
    }

    public static Node convertMaster(RedisClusterNode cnode) {
        Master master = new Master(cnode);
        master.setRange(AppConverters.toRange().convert(cnode.getSlotRange()));
        return master;
    }

    public static Node convertSlave(RedisClusterNode cnode, String father) {
        Slave slave = new Slave(cnode);
        slave.setMasterId(cnode.getMasterId());
        slave.setMasterHostPort(father);
        return slave;
    }
}
