package com.acumen.redis.cluster.monitor.cluster;

public interface ClusterService {
    //cluster
    void info();

    void slots();

    void nodes();

    void activeMasters();

    //node
    void nodesInfo();

    void nodeInfo(String node);
}
