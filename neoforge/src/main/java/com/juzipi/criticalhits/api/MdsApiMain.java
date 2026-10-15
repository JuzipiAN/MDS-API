package com.juzipi.criticalhits.api;

import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MDS API 主入口类 (NeoForge版本)。
 */
@Mod("mds_api")
public class MdsApiMain {

    public static final String MOD_ID = "mds_api";
    public static final String MOD_NAME = "MDS API";
    public static final String VERSION = "1.1.0-neoforge";
    public static final String API_VERSION = "1.1.0";
    public static final String AUTHOR = "Juzipi_AN";
    public static final String LICENSE = "MIT";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private static boolean initialized = false;
    private static long initializationTime = 0;

    public MdsApiMain() {
        initialized = true;
        initializationTime = System.currentTimeMillis();
        LOGGER.info("[{}] v{} 初始化完成 - 作者: {}", MOD_NAME, VERSION, AUTHOR);
        LOGGER.info("[{}] 开源协议: {} - 欢迎其他mod使用本API", MOD_NAME, LICENSE);
        LOGGER.info("[{}] API版本: {} - 支持Minecraft 26.2 NeoForge", MOD_NAME, API_VERSION);
    }

    public static boolean isApiAvailable() { return initialized; }
    public static String getApiVersion() { return API_VERSION; }
    public static String getFullVersion() { return VERSION; }
    public static String getAuthor() { return AUTHOR; }
    public static String getLicense() { return LICENSE; }
    public static long getInitializationTime() { return initializationTime; }

    public static boolean isVersionCompatible(String requiredVersion) {
        if (requiredVersion == null || requiredVersion.isEmpty()) return true;
        String[] required = requiredVersion.split("\\.");
        String[] current = API_VERSION.split("\\.");
        int length = Math.max(required.length, current.length);
        for (int i = 0; i < length; i++) {
            int req = i < required.length ? Integer.parseInt(required[i]) : 0;
            int cur = i < current.length ? Integer.parseInt(current[i]) : 0;
            if (cur > req) return true;
            if (cur < req) return false;
        }
        return true;
    }

    public static void logApiUsage(String modId, String feature) {
        LOGGER.info("[{}] Mod '{}' 使用了MDS API功能: {}", MOD_NAME, modId, feature);
    }
}
