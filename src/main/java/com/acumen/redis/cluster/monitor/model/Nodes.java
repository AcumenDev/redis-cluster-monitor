package com.acumen.redis.cluster.monitor.model;

import com.acumen.redis.cluster.monitor.model.cluster.node.Node;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vstalmakov on 12.10.16.
 */
public class Nodes implements Serializable {
    List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public Nodes setNodes(List<Node> nodes) {
        this.nodes = nodes;
        return this;
    }
}
