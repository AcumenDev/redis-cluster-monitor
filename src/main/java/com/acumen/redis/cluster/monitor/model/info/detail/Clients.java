package com.acumen.redis.cluster.monitor.model.info.detail;

import java.io.Serializable;

public class Clients implements Serializable {

    private String connectedClients;
    private String clientLongestOutputList;
    private String clientBiggestInputBuf;
    private String blockedClients;

    public String getConnectedClients() {
        return connectedClients;
    }

    public Clients setConnectedClients(String connectedClients) {
        this.connectedClients = connectedClients;
        return this;
    }

    public String getClientLongestOutputList() {
        return clientLongestOutputList;
    }

    public Clients setClientLongestOutputList(String clientLongestOutputList) {
        this.clientLongestOutputList = clientLongestOutputList;
        return this;
    }

    public String getClientBiggestInputBuf() {
        return clientBiggestInputBuf;
    }

    public Clients setClientBiggestInputBuf(String clientBiggestInputBuf) {
        this.clientBiggestInputBuf = clientBiggestInputBuf;
        return this;
    }

    public String getBlockedClients() {
        return blockedClients;
    }

    public Clients setBlockedClients(String blockedClients) {
        this.blockedClients = blockedClients;
        return this;
    }
}
