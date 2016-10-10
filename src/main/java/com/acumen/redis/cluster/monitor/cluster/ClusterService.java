package com.acumen.redis.cluster.monitor.cluster;

import com.acumen.redis.cluster.monitor.model.cluster.node.Node;
import com.acumen.redis.cluster.monitor.model.cluster.slot.Slot;
import com.acumen.redis.cluster.monitor.model.info.Info;
import org.springframework.data.redis.connection.ClusterInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ClusterService {
    //cluster
    ClusterInfo info();

    Set<Slot> slots();

    List<Node> nodes();

    Set<Node> activeMasters();

    //node
    Map<String, Info> nodesInfo();

    Info nodeInfo(String node);
}
