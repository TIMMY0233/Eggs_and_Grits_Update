package net.dove.eggsandgrits.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.world.World;
import net.minecraft.block.Blocks;

public class CustomExperienceOrbEntity extends ExperienceOrbEntity {

    public CustomExperienceOrbEntity(EntityType<? extends ExperienceOrbEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();

            // Apply downward force (sink) when in water
        this.setVelocity(this.getVelocity().x, -0.1, this.getVelocity().z);
    }

}
