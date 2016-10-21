package com.acumen.redis.cluster.monitor.model.info.detail;

import java.io.Serializable;

public class Keyspace implements Serializable{
    private String db0;

    public String getDb0() {
        return db0;
    }

    public Keyspace setDb0(String db0) {
        this.db0 = db0;
        return this;
    }
}
