package com.example.javachallenge;

import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

/**
 * Service responsible for collecting system metrics
 * such as memory usage and CPU load.
 */
@Service
public class MetricsService {

    /**
     * Returns the amount of used heap memory in bytes.
     */
    public long usedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    /**
     * Returns the system CPU load (0.0 - 1.0).
     */
    public double cpuLoad() {
        OperatingSystemMXBean os =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return os.getSystemCpuLoad(); // between 0.0 and 1.0
    }
}