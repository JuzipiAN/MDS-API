package com.juzipi.criticalhits.api;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * MDS API 客户端入口类 (NeoForge版本)。
 */
@EventBusSubscriber(modid = "mds_api", value = Dist.CLIENT)
public class MdsApiClient {

    private static boolean clientInitialized = false;

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        clientInitialized = true;
        MdsApiMain.LOGGER.info("[{}] 客户端初始化完成", MdsApiMain.MOD_NAME);
    }

    public static boolean isClientApiAvailable() { return clientInitialized; }

    public static boolean isInGame() {
        Minecraft mc = Minecraft.getInstance();
        return mc != null && mc.player != null && mc.level != null;
    }

    public static String getCurrentLanguage() {
        try {
            Minecraft mc = Minecraft.getInstance();
            if (mc == null) return "en_us";
            java.lang.reflect.Method getLanguageManagerMethod = mc.getClass().getMethod("getLanguageManager");
            Object languageManager = getLanguageManagerMethod.invoke(mc);
            if (languageManager == null) return "en_us";
            java.lang.reflect.Method getSelectedMethod = languageManager.getClass().getMethod("getSelected");
            Object selected = getSelectedMethod.invoke(languageManager);
            if (selected instanceof String) return (String) selected;
        } catch (Exception e) { }
        return "en_us";
    }

    public static boolean isChineseLanguage() {
        String lang = getCurrentLanguage();
        return lang != null && lang.toLowerCase().startsWith("zh");
    }

    public static boolean isGamePaused() {
        Minecraft mc = Minecraft.getInstance();
        return mc != null && mc.isPaused();
    }
}
