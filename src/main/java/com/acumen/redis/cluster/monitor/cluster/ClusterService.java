package com.acumen.redis.cluster.monitor.cluster;

import com.acumen.redis.cluster.monitor.model.Nodes;
import com.acumen.redis.cluster.monitor.model.cluster.node.Node;
import com.acumen.redis.cluster.monitor.model.cluster.slot.Slot;
import com.acumen.redis.cluster.monitor.model.info.Info;
import org.springframework.data.redis.connection.ClusterInfo;

import java.util.Map;
import java.util.Set;

public interface ClusterService {
    //cluster
    ClusterInfo info();

    Set<Slot> slots();

    Nodes nodes();

    Set<Node> activeMasters();

    //node
    Map<String, Info> nodesInfo();

    Info nodeInfo(String node);
}
