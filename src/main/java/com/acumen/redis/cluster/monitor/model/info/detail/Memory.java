package com.acumen.redis.cluster.monitor.model.info.detail;

import java.io.Serializable;

public class Memory implements Serializable {
    private String usedMemory;
    private String usedMemoryHuman;
    private String usedMemoryRss;
    private String usedMemoryPeak;
    private String usedMemoryPeakHuman;
    private String usedMemoryLua;
    private String memFragmentationRatio;
    private String memAllocator;

    public String getUsedMemory() {
        return usedMemory;
    }

    public Memory setUsedMemory(String usedMemory) {
        this.usedMemory = usedMemory;
        return this;
    }

    public String getUsedMemoryHuman() {
        return usedMemoryHuman;
    }

    public Memory setUsedMemoryHuman(String usedMemoryHuman) {
        this.usedMemoryHuman = usedMemoryHuman;
        return this;
    }

    public String getUsedMemoryRss() {
        return usedMemoryRss;
    }

    public Memory setUsedMemoryRss(String usedMemoryRss) {
        this.usedMemoryRss = usedMemoryRss;
        return this;
    }

    public String getUsedMemoryPeak() {
        return usedMemoryPeak;
    }

    public Memory setUsedMemoryPeak(String usedMemoryPeak) {
        this.usedMemoryPeak = usedMemoryPeak;
        return this;
    }

    public String getUsedMemoryPeakHuman() {
        return usedMemoryPeakHuman;
    }

    public Memory setUsedMemoryPeakHuman(String usedMemoryPeakHuman) {
        this.usedMemoryPeakHuman = usedMemoryPeakHuman;
        return this;
    }

    public String getUsedMemoryLua() {
        return usedMemoryLua;
    }

    public Memory setUsedMemoryLua(String usedMemoryLua) {
        this.usedMemoryLua = usedMemoryLua;
        return this;
    }

    public String getMemFragmentationRatio() {
        return memFragmentationRatio;
    }

    public Memory setMemFragmentationRatio(String memFragmentationRatio) {
        this.memFragmentationRatio = memFragmentationRatio;
        return this;
    }

    public String getMemAllocator() {
        return memAllocator;
    }

    public Memory setMemAllocator(String memAllocator) {
        this.memAllocator = memAllocator;
        return this;
    }
}
