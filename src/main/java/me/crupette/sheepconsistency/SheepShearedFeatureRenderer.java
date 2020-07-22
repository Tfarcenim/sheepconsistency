package me.crupette.sheepconsistency;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.crupette.sheepconsistency.SheepConsistency;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;

public class SheepShearedFeatureRenderer extends LayerRenderer<SheepEntity, SheepModel<SheepEntity>> {

    private final SheepModel<SheepEntity> model = new SheepModel<>();
    private static final ResourceLocation SKIN = new ResourceLocation(SheepConsistency.MOD_ID, "textures/entity/sheep/sheep_sheared.png");

    public SheepShearedFeatureRenderer(IEntityRenderer<SheepEntity, SheepModel<SheepEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int i, SheepEntity sheepEntity, float f, float g, float h, float j, float k, float l) {
        float v;
        float w;
        float x;
        if (sheepEntity.hasCustomName() && "jeb_".equals(sheepEntity.getName().getString())) {
            int n = sheepEntity.ticksExisted / 25 + sheepEntity.getEntityId();
            int o = DyeColor.values().length;
            int p = n % o;
            int q = (n + 1) % o;
            float r = (sheepEntity.ticksExisted % 25 + h) / 25.0F;
            float[] fs = SheepEntity.getDyeRgb(DyeColor.byId(p));
            float[] gs = SheepEntity.getDyeRgb(DyeColor.byId(q));
            v = fs[0] * (1.0F - r) + gs[0] * r;
            w = fs[1] * (1.0F - r) + gs[1] * r;
            x = fs[2] * (1.0F - r) + gs[2] * r;
        } else {
            float[] hs = SheepEntity.getDyeRgb(sheepEntity.getFleeceColor());
            v = hs[0];
            w = hs[1];
            x = hs[2];
        }
        renderCopyCutoutModel(this.getEntityModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, sheepEntity, f, g, j, k, l, h, v, w, x);
    }
}
