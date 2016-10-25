package com.acumen.redis.cluster.monitor.controller;

import com.acumen.redis.cluster.monitor.cluster.ClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusterController {
    @Autowired
    private ClusterService clusterService;

    @RequestMapping(value = "/cluster/info", method = RequestMethod.GET)
    public Object clusterInfo() {
        return clusterService.info();
    }

    @RequestMapping(value = "/cluster/nodes", method = RequestMethod.GET)
    public Object clusterNodes() {
        return clusterService.nodes();
    }

    @RequestMapping(value = "/cluster/slots", method = RequestMethod.GET)
    public Object clusterSlots() {
        return clusterService.slots();
    }

    @RequestMapping(value = "/nodes/info", method = RequestMethod.GET)
    public Object infos() {
        return clusterService.nodesInfo();
    }

    @RequestMapping(value = "/{node}/info", method = RequestMethod.GET)
    public Object info(@PathVariable("node") String node) {
        return clusterService.nodeInfo(node);
    }

    @RequestMapping(value = "/cluster/master", method = RequestMethod.GET)
    public Object clusterMaster() {
        return clusterService.activeMasters();
    }

    @RequestMapping(value = "/cluster/executeCommand", method = RequestMethod.POST)
    public Object executeCommand(String command) {
        return clusterService.executeCommand(command);
    }
}
