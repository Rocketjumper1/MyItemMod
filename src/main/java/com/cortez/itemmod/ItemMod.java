package com.cortez.itemmod;

import com.cortez.itemmod.enchantment.ModEnchantmentEffects;
import com.cortez.itemmod.modSounds.ModSounds;
import com.cortez.itemmod.block.entity.ModBlockEntitys;
import com.cortez.itemmod.creativeModeTabs.ModTabs;
import com.cortez.itemmod.block.ModBlocks;
import com.cortez.itemmod.item.ModItems;
import com.cortez.itemmod.screens.ModMenuTypes;
import com.cortez.itemmod.screens.custom.IncineratorScreen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ItemMod.MOD_ID)
public class ItemMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "itemmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    public ItemMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // register custom sounds
        ModSounds.register(modEventBus);
        // register creative mode tabs
        ModTabs.register(modEventBus);
        // Register Ruby Block
        ModBlocks.register(modEventBus);
        // register ruby items
        ModItems.register(modEventBus);
        // Register Block Entities
        ModBlockEntitys.register(modEventBus);
        // Register Mod Menus
        ModMenuTypes.register(modEventBus);
        // Register Mod Enchantment
        ModEnchantmentEffects.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code

    }

    // Add added items to correct creative mode tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            MenuScreens.register(ModMenuTypes.INCINERATOR_MENU.get(), IncineratorScreen::new);
        }
    }
}
