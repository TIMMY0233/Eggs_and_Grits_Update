package net.dove.eggsandgrits.sound;

import net.dove.eggsandgrits.EggsAndGrits;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.client.sound.Sound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent CHISEL_USE = registerSoundEvent("chisel_use");

    public static final SoundEvent MAGIC_BLOCK_BREAK = registerSoundEvent("magic_block_break");
    public static final SoundEvent MAGIC_BLOCK_STEP = registerSoundEvent("magic_block_step");
    public static final SoundEvent MAGIC_BLOCK_PLACE = registerSoundEvent("magic_block_place");
    public static final SoundEvent MAGIC_BLOCK_HIT = registerSoundEvent("magic_block_hit");
    public static final SoundEvent MAGIC_BLOCK_FALL = registerSoundEvent("magic_block_fall");

    public static final BlockSoundGroup MAGIC_BLOCK_SOUNDS = new BlockSoundGroup(1f,1f,
            MAGIC_BLOCK_BREAK, MAGIC_BLOCK_STEP, MAGIC_BLOCK_PLACE, MAGIC_BLOCK_HIT, MAGIC_BLOCK_FALL);


    public static final SoundEvent BAR_BRAWL = registerSoundEvent("bar_brawl");
    public static final RegistryKey<JukeboxSong> BAR_BRAWL_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(EggsAndGrits.MOD_ID, "bar_brawl"));

    public static final SoundEvent TOOT = registerSoundEvent("toot");
    public static final SoundEvent TOOT2 = registerSoundEvent("toot2");


    public static final SoundEvent COUGH = registerSoundEvent("cough");
    public static final SoundEvent COUGH2 = registerSoundEvent("cough2");

    public static final SoundEvent DO_NOT_EAT = registerSoundEvent("do_not_eat");
    public static final SoundEvent HE_DID_WHAT = registerSoundEvent("he_did_what");

    public static final SoundEvent GIT_R_DONE = registerSoundEvent("git_r_done");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(EggsAndGrits.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds(){
        EggsAndGrits.LOGGER.info("Registering Mod Sounds For" + EggsAndGrits.MOD_ID);
    }
}


//Sounds must be .ogg files and mono

//Sounds must be .ogg files and mono