package net.dove.eggsandgrits.world.biome;

import net.dove.eggsandgrits.EggsAndGrits;
import net.dove.eggsandgrits.world.biome.surface.ModMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.of(EggsAndGrits.MOD_ID, "overworld"),4));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, EggsAndGrits.MOD_ID, ModMaterialRules.makeRules());
    }
}
