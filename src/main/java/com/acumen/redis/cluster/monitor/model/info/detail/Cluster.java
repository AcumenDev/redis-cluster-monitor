package com.acumen.redis.cluster.monitor.model.info.detail;

import java.io.Serializable;

public class Cluster implements Serializable {
    private String clusterEnabled;

    public String getClusterEnabled() {
        return clusterEnabled;
    }

    public Cluster setClusterEnabled(String clusterEnabled) {
        this.clusterEnabled = clusterEnabled;
        return this;
    }
}
