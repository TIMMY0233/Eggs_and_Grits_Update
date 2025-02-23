package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.entity.custom.HotDogRollerEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class HotDogRollerRenderer extends EntityRenderer<HotDogRollerEntity> {
    public HotDogRollerRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(HotDogRollerEntity entity) {
        return null;
    }

    @Override
    public boolean shouldRender(HotDogRollerEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
