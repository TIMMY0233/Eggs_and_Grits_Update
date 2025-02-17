package net.dove.eggsandgrits.entity.client;

import net.dove.eggsandgrits.entity.custom.ChairEntity;
import net.dove.eggsandgrits.entity.custom.StoolEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class StoolRenderer extends EntityRenderer<StoolEntity> {
    public StoolRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(StoolEntity entity) {
        return null;
    }

    @Override
    public boolean shouldRender(StoolEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
