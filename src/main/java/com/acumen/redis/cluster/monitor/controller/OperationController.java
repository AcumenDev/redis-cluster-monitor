package com.acumen.redis.cluster.monitor.controller;

import com.acumen.redis.cluster.monitor.cluster.ClusterOperationService;
import com.acumen.redis.cluster.monitor.model.Keys;
import com.acumen.redis.cluster.monitor.model.NodeKeys;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vstalmakov on 16.11.16.
 */
@RestController
public class OperationController {

    private ClusterOperationService operationService;

    public OperationController(ClusterOperationService operationService) {
        this.operationService = operationService;
    }

    @RequestMapping(value = "/operation/keys/get", method = RequestMethod.GET)
    public List<NodeKeys> getKeys(String node) {
        return operationService.getKeys(node);
    }
}
