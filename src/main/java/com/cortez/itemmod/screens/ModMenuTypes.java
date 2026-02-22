package com.cortez.itemmod.screens;

import com.cortez.itemmod.ItemMod;
import com.cortez.itemmod.screens.custom.IncineratorMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, ItemMod.MOD_ID);

    public static final RegistryObject<MenuType<IncineratorMenu>> INCINERATOR_MENU =
            MENUS.register("incinerator_menu", () -> IForgeMenuType.create(IncineratorMenu::new));
    public static void register(IEventBus bus){
        MENUS.register(bus);
    }

}
