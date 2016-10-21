package com.acumen.redis.cluster.monitor.model.info.detail;

import java.io.Serializable;

public class CPU implements Serializable {
    private String usedCpuSys;
    private String usedCpuUser;
    private String usedCpuSysChildren;
    private String usedCpuUserChildren;

    public String getUsedCpuSys() {
        return usedCpuSys;
    }

    public CPU setUsedCpuSys(String usedCpuSys) {
        this.usedCpuSys = usedCpuSys;
        return this;
    }

    public String getUsedCpuUser() {
        return usedCpuUser;
    }

    public CPU setUsedCpuUser(String usedCpuUser) {
        this.usedCpuUser = usedCpuUser;
        return this;
    }

    public String getUsedCpuSysChildren() {
        return usedCpuSysChildren;
    }

    public CPU setUsedCpuSysChildren(String usedCpuSysChildren) {
        this.usedCpuSysChildren = usedCpuSysChildren;
        return this;
    }

    public String getUsedCpuUserChildren() {
        return usedCpuUserChildren;
    }

    public CPU setUsedCpuUserChildren(String usedCpuUserChildren) {
        this.usedCpuUserChildren = usedCpuUserChildren;
        return this;
    }
}
