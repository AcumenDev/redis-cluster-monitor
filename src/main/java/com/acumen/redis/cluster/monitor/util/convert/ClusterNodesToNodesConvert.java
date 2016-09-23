package com.acumen.redis.cluster.monitor.util.convert;


import com.acumen.redis.cluster.monitor.model.cluster.node.Master;
import com.acumen.redis.cluster.monitor.model.cluster.node.Node;
import com.acumen.redis.cluster.monitor.model.cluster.node.Slave;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisClusterNode;

import java.util.HashSet;
import java.util.Set;

public class ClusterNodesToNodesConvert implements Converter<Set<RedisClusterNode>, Set<Node>> {

    @Override
    public Set<Node> convert(Set<RedisClusterNode> source) {
        Set<Node> ret = new HashSet<Node>();
        for (RedisClusterNode cnode : source) {
            if (cnode.isMaster()) {
                Master master = new Master(cnode);
                master.setRange(AppConverters.toRange().convert(cnode.getSlotRange()));
                ret.add(master);
            } else {
                Slave slave = new Slave(cnode);
                slave.setMasterId(cnode.getMasterId());
                RedisClusterNode father = AppConverters.getMasterById(cnode.getMasterId(), source);
                slave.setMasterHostPort(father.asString());
                ret.add(slave);
            }
        }
        return ret;
    }
}
