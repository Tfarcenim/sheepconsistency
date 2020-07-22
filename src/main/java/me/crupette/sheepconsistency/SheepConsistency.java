package me.crupette.sheepconsistency;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SheepConsistency.MOD_ID)
public class SheepConsistency {

    public static final String MOD_ID = "sheepconsistency";

    public SheepConsistency() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::client);
    }

    private void client(FMLClientSetupEvent e) {
        EntityRendererManager entityRendererManager = Minecraft.getInstance().getRenderManager();
        MobRenderer<SheepEntity, SheepModel<SheepEntity>> renderer = (MobRenderer<SheepEntity, SheepModel<SheepEntity>>) entityRendererManager.renderers.get(EntityType.SHEEP);
        renderer.addLayer(new SheepShearedFeatureRenderer(renderer));
    }
}