package com.acumen.redis.cluster.monitor.controller;

import com.acumen.redis.cluster.monitor.cluster.ClusterInformationService;
import com.acumen.redis.cluster.monitor.model.RedisClusterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusterController {

    @Autowired
    private ClusterInformationService informationService;

    @RequestMapping(value = "/cluster/info", method = RequestMethod.GET)
    public RedisClusterInfo clusterInfo() {
        return informationService.info();
    }

    @RequestMapping(value = "/cluster/nodes", method = RequestMethod.GET)
    public Object clusterNodes() {
        return informationService.nodes();
    }

    @RequestMapping(value = "/cluster/slots", method = RequestMethod.GET)
    public Object clusterSlots() {
        return informationService.slots();
    }

    @RequestMapping(value = "/nodes/info", method = RequestMethod.GET)
    public Object infos() {
        return informationService.nodesInfo();
    }

    @RequestMapping(value = "/{node}/info", method = RequestMethod.GET)
    public Object info(@PathVariable("node") String node) {
        return informationService.nodeInfo(node);
    }

    @RequestMapping(value = "/cluster/master", method = RequestMethod.GET)
    public Object clusterMaster() {
        return informationService.activeMasters();
    }

    @RequestMapping(value = "/cluster/executeCommand", method = RequestMethod.POST)
    public Object executeCommand(String command) {
        return informationService.executeCommand(command);
    }
}
