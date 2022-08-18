package ru.ckateptb.tablecloth.particle;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.RandomUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.data.color.ParticleColor;
import xyz.xenondevs.particle.data.texture.BlockTexture;
import xyz.xenondevs.particle.data.texture.ItemTexture;
import xyz.xenondevs.particle.data.texture.ParticleTexture;

import java.awt.*;
import java.util.Collection;
import java.util.function.Predicate;

/*
 * Regex for replace from {@link ParticleEffect}
 * Regex: '^    (.+)\(version ->.+\)'
 * Replace: '$1\(ParticleEffect.$1, org.bukkit.Particle.$1\)'
 * Regex for remove comments
 * Regex: '//.*|("(?:\\[^"]|\\"|.)*?")|(?s)/\*.*?\*\/'
 * Replace: ''
 */
public enum Particle {
    ASH(ParticleEffect.ASH, org.bukkit.Particle.ASH),

    BARRIER(ParticleEffect.BARRIER, org.bukkit.Particle.BARRIER),

    BLOCK_CRACK(ParticleEffect.BLOCK_CRACK, org.bukkit.Particle.BLOCK_CRACK),

    BLOCK_DUST(ParticleEffect.BLOCK_DUST, org.bukkit.Particle.BLOCK_DUST),

    BUBBLE_COLUMN_UP(ParticleEffect.BUBBLE_COLUMN_UP, org.bukkit.Particle.BUBBLE_COLUMN_UP),

    BLOCK_MARKER(ParticleEffect.BLOCK_MARKER, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    BUBBLE_POP(ParticleEffect.BUBBLE_POP, org.bukkit.Particle.BUBBLE_POP),

    CAMPFIRE_COSY_SMOKE(ParticleEffect.CAMPFIRE_COSY_SMOKE, org.bukkit.Particle.CAMPFIRE_COSY_SMOKE),

    CAMPFIRE_SIGNAL_SMOKE(ParticleEffect.CAMPFIRE_SIGNAL_SMOKE, org.bukkit.Particle.CAMPFIRE_SIGNAL_SMOKE),

    CLOUD(ParticleEffect.CLOUD, org.bukkit.Particle.CLOUD),

    COMPOSTER(ParticleEffect.COMPOSTER, org.bukkit.Particle.COMPOSTER),

    CRIMSON_SPORE(ParticleEffect.CRIMSON_SPORE, org.bukkit.Particle.CRIMSON_SPORE),

    CRIT(ParticleEffect.CRIT, org.bukkit.Particle.CRIT),

    CRIT_MAGIC(ParticleEffect.CRIT_MAGIC, org.bukkit.Particle.CRIT_MAGIC),

    CURRENT_DOWN(ParticleEffect.CURRENT_DOWN, org.bukkit.Particle.CURRENT_DOWN),

    DAMAGE_INDICATOR(ParticleEffect.DAMAGE_INDICATOR, org.bukkit.Particle.DAMAGE_INDICATOR),

    DOLPHIN(ParticleEffect.DOLPHIN, org.bukkit.Particle.DOLPHIN),

    DRAGON_BREATH(ParticleEffect.DRAGON_BREATH, org.bukkit.Particle.DRAGON_BREATH),

    DRIP_LAVA(ParticleEffect.DRIP_LAVA, org.bukkit.Particle.DRIP_LAVA),

    DRIP_WATER(ParticleEffect.DRIP_WATER, org.bukkit.Particle.DRIP_WATER),

    DRIPPING_DRIPSTONE_LAVA(ParticleEffect.DRIPPING_DRIPSTONE_LAVA, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    DRIPPING_DRIPSTONE_WATER(ParticleEffect.DRIPPING_DRIPSTONE_WATER, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    DRIPPING_HONEY(ParticleEffect.DRIPPING_HONEY, org.bukkit.Particle.DRIPPING_HONEY),

    DRIPPING_OBSIDIAN_TEAR(ParticleEffect.DRIPPING_OBSIDIAN_TEAR, org.bukkit.Particle.DRIPPING_OBSIDIAN_TEAR),

    DUST_COLOR_TRANSITION(ParticleEffect.DUST_COLOR_TRANSITION, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    ELECTRIC_SPARK(ParticleEffect.ELECTRIC_SPARK, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    ENCHANTMENT_TABLE(ParticleEffect.ENCHANTMENT_TABLE, org.bukkit.Particle.ENCHANTMENT_TABLE),

    END_ROD(ParticleEffect.END_ROD, org.bukkit.Particle.END_ROD),

    EXPLOSION_HUGE(ParticleEffect.EXPLOSION_HUGE, org.bukkit.Particle.EXPLOSION_HUGE),

    EXPLOSION_LARGE(ParticleEffect.EXPLOSION_LARGE, org.bukkit.Particle.EXPLOSION_LARGE),

    EXPLOSION_NORMAL(ParticleEffect.EXPLOSION_NORMAL, org.bukkit.Particle.EXPLOSION_NORMAL),

    FALLING_DRIPSTONE_LAVA(ParticleEffect.FALLING_DRIPSTONE_LAVA, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    FALLING_DRIPSTONE_WATER(ParticleEffect.FALLING_DRIPSTONE_WATER, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    FALLING_DUST(ParticleEffect.FALLING_DUST, org.bukkit.Particle.FALLING_DUST),

    FALLING_HONEY(ParticleEffect.FALLING_HONEY, org.bukkit.Particle.FALLING_HONEY),

    FALLING_NECTAR(ParticleEffect.FALLING_NECTAR, org.bukkit.Particle.FALLING_NECTAR),

    FALLING_OBSIDIAN_TEAR(ParticleEffect.FALLING_OBSIDIAN_TEAR, org.bukkit.Particle.FALLING_OBSIDIAN_TEAR),

    FALLING_SPORE_BLOSSOM(ParticleEffect.FALLING_SPORE_BLOSSOM, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    FIREWORKS_SPARK(ParticleEffect.FIREWORKS_SPARK, org.bukkit.Particle.FIREWORKS_SPARK),

    FLAME(ParticleEffect.FLAME, org.bukkit.Particle.FLAME),

    FLASH(ParticleEffect.FLASH, org.bukkit.Particle.FLASH),

    FOOTSTEP(ParticleEffect.FOOTSTEP, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    GLOW(ParticleEffect.GLOW, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    GLOW_SQUID_INK(ParticleEffect.GLOW_SQUID_INK, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    HEART(ParticleEffect.HEART, org.bukkit.Particle.HEART),

    ITEM_CRACK(ParticleEffect.ITEM_CRACK, org.bukkit.Particle.ITEM_CRACK),

    LANDING_HONEY(ParticleEffect.LANDING_HONEY, org.bukkit.Particle.LANDING_HONEY),

    LANDING_OBSIDIAN_TEAR(ParticleEffect.LANDING_OBSIDIAN_TEAR, org.bukkit.Particle.LANDING_OBSIDIAN_TEAR),

    LAVA(ParticleEffect.LAVA, org.bukkit.Particle.LAVA),

    LIGHT(ParticleEffect.LIGHT, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    MOB_APPEARANCE(ParticleEffect.MOB_APPEARANCE, org.bukkit.Particle.MOB_APPEARANCE),

    NAUTILUS(ParticleEffect.NAUTILUS, org.bukkit.Particle.NAUTILUS),

    NOTE(ParticleEffect.NOTE, org.bukkit.Particle.NOTE),

    PORTAL(ParticleEffect.PORTAL, org.bukkit.Particle.PORTAL),

    REDSTONE(ParticleEffect.REDSTONE, org.bukkit.Particle.REDSTONE),

    REVERSE_PORTAL(ParticleEffect.REVERSE_PORTAL, org.bukkit.Particle.REVERSE_PORTAL),

    SCRAPE(ParticleEffect.SCRAPE, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    SLIME(ParticleEffect.SLIME, org.bukkit.Particle.SLIME),

    SMOKE_LARGE(ParticleEffect.SMOKE_LARGE, org.bukkit.Particle.SMOKE_LARGE),

    SMOKE_NORMAL(ParticleEffect.SMOKE_NORMAL, org.bukkit.Particle.SMOKE_NORMAL),

    SNEEZE(ParticleEffect.SNEEZE, org.bukkit.Particle.SNEEZE),

    SNOWBALL(ParticleEffect.SNOWBALL, org.bukkit.Particle.SNOWBALL),

    SNOWFLAKE(ParticleEffect.SNOWFLAKE, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    SNOW_SHOVEL(ParticleEffect.SNOW_SHOVEL, org.bukkit.Particle.SNOW_SHOVEL),

    SOUL(ParticleEffect.SOUL, org.bukkit.Particle.SOUL),

    SOUL_FIRE_FLAME(ParticleEffect.SOUL_FIRE_FLAME, org.bukkit.Particle.SOUL_FIRE_FLAME),

    SPELL(ParticleEffect.SPELL, org.bukkit.Particle.SPELL),

    SPELL_INSTANT(ParticleEffect.SPELL_INSTANT, org.bukkit.Particle.SPELL_INSTANT),

    SPELL_MOB(ParticleEffect.SPELL_MOB, org.bukkit.Particle.SPELL_MOB),

    SPELL_MOB_AMBIENT(ParticleEffect.SPELL_MOB_AMBIENT, org.bukkit.Particle.SPELL_MOB_AMBIENT),

    SPELL_WITCH(ParticleEffect.SPELL_WITCH, org.bukkit.Particle.SPELL_WITCH),

    SPIT(ParticleEffect.SPIT, org.bukkit.Particle.SPIT),

    SPORE_BLOSSOM_AIR(ParticleEffect.SPORE_BLOSSOM_AIR, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    SQUID_INK(ParticleEffect.SQUID_INK, org.bukkit.Particle.SQUID_INK),

    SUSPENDED(ParticleEffect.SUSPENDED, org.bukkit.Particle.SUSPENDED),

    SUSPENDED_DEPTH(ParticleEffect.SUSPENDED_DEPTH, org.bukkit.Particle.SUSPENDED_DEPTH),

    SWEEP_ATTACK(ParticleEffect.SWEEP_ATTACK, org.bukkit.Particle.SWEEP_ATTACK),

    TOTEM(ParticleEffect.TOTEM, org.bukkit.Particle.TOTEM),

    TOWN_AURA(ParticleEffect.TOWN_AURA, org.bukkit.Particle.TOWN_AURA),

    VIBRATION(ParticleEffect.VIBRATION, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    VILLAGER_ANGRY(ParticleEffect.VILLAGER_ANGRY, org.bukkit.Particle.VILLAGER_ANGRY),

    VILLAGER_HAPPY(ParticleEffect.VILLAGER_HAPPY, org.bukkit.Particle.VILLAGER_HAPPY),

    WARPED_SPORE(ParticleEffect.WARPED_SPORE, org.bukkit.Particle.WARPED_SPORE),

    WATER_BUBBLE(ParticleEffect.WATER_BUBBLE, org.bukkit.Particle.WATER_BUBBLE),

    WATER_DROP(ParticleEffect.WATER_DROP, org.bukkit.Particle.WATER_DROP),

    WATER_SPLASH(ParticleEffect.WATER_SPLASH, org.bukkit.Particle.WATER_SPLASH),

    WATER_WAKE(ParticleEffect.WATER_WAKE, org.bukkit.Particle.WATER_WAKE),

    WAX_OFF(ParticleEffect.WAX_OFF, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    WAX_ON(ParticleEffect.WAX_ON, org.bukkit.Particle.values()[RandomUtils.nextInt(0, org.bukkit.Particle.values().length - 1)]),

    WHITE_ASH(ParticleEffect.WHITE_ASH, org.bukkit.Particle.WHITE_ASH);

    private final ParticleEffect effect;
    private final org.bukkit.Particle particle;
    private final Class<?> dataClass;

    Particle(ParticleEffect effect, org.bukkit.Particle particle) {
        this.effect = effect;
        this.particle = particle;
        this.dataClass = particle.getDataType();
    }


    public void display(ParticleEffect effect, Location loc, int amount) {
        display(effect, loc, amount, 0, 0, 0);
    }

    public void display(ParticleEffect effect, Location loc, int amount, double offsetX, double offsetY, double offsetZ) {
        display(effect, loc, amount, offsetX, offsetY, offsetZ, 0);
    }

    public void display(ParticleEffect effect, Location loc, int amount, double offsetX, double offsetY, double offsetZ, double extra) {
        effect.display(loc, (float) offsetX, (float) offsetY, (float) offsetZ, (float) extra, amount, null);
    }

    public void display(ParticleEffect effect, Location loc, int amount, double offsetX, double offsetY, double offsetZ, ItemStack itemStack) {
        display(effect, loc, amount, offsetX, offsetY, offsetZ, 0, new ItemTexture(itemStack));
    }

    public void display(ParticleEffect effect, Location loc, int amount, double offsetX, double offsetY, double offsetZ, Block block) {
        display(effect, loc, amount, offsetX, offsetY, offsetZ, 0, new BlockTexture(block.getType(), block.getData()));
    }

    public void display(ParticleEffect effect, Location loc, int amount, double offsetX, double offsetY, double offsetZ, BlockData data) {
        display(effect, loc, amount, offsetX, offsetY, offsetZ, 0, new BlockTexture(data.getMaterial()));
    }

    public void display(ParticleEffect effect, Location loc, int amount, double offsetX, double offsetY, double offsetZ, ParticleTexture data) {
        display(effect, loc, amount, offsetX, offsetY, offsetZ, 0, data);
    }

    public void display(ParticleEffect effect, Location loc, int amount, double offsetX, double offsetY, double offsetZ, double extra, ParticleTexture data) {
        effect.display(loc, (float) offsetX, (float) offsetY, (float) offsetZ, (float) extra, amount, data);
    }

    public void display(Location location, ParticleColor color, Player... players) {
        effect.display(location, color, players);
    }

    public void display(Location location, Color color, Player... players) {
        effect.display(location, color, players);
    }

    public void display(Location location, ParticleColor color, Predicate filter) {
        effect.display(location, color, filter);
    }

    public void display(Location location, Color color, Predicate filter) {
        effect.display(location, color, filter);
    }

    public void display(Location location, ParticleColor color, Collection<? extends Player> players) {
        effect.display(location, color, players);
    }

    public void display(Location location, Color color, Collection<? extends Player> players) {
        effect.display(location, color, players);
    }

    public void display(Location location, ParticleColor color) {
        effect.display(location, color);
    }

    public void display(Location location, Color color) {
        effect.display(location, color);
    }

    public void display(Location location, Player... players) {
        effect.display(location, players);
    }

    public void display(Location location, Predicate filter) {
        effect.display(location, filter);
    }

    public void display(Location location, Collection<? extends Player> players) {
        effect.display(location, players);
    }

    public void display(Location location) {
        effect.display(location);
    }

    public void display(Location location, Vector vector, float speed, int amount, ParticleData data, Player... players) {
        effect.display(location, vector, speed, amount, data, players);
    }

    public void display(Location location, Vector vector, float speed, int amount, ParticleData data, Predicate filter) {
        effect.display(location, vector, speed, amount, data, filter);
    }

    public void display(Location location, Vector vector, float speed, int amount, ParticleData data, Collection<? extends Player> players) {
        effect.display(location, vector, speed, amount, data, players);
    }

    public void display(Location location, Vector vector, float speed, int amount, ParticleData data) {
        effect.display(location, vector, speed, amount, data);
    }

    public void display(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData data, Player... players) {
        effect.display(location, offsetX, offsetY, offsetZ, speed, amount, data, players);
    }

    public void display(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData data, Predicate<Player> filter) {
        effect.display(location, offsetX, offsetY, offsetZ, speed, amount, data, filter);
    }

    public void display(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData data) {
        effect.display(location, offsetX, offsetY, offsetZ, speed, amount, data);
    }

    public void display(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount, ParticleData data, Collection<? extends Player> players) {
        effect.display(location, offsetX, offsetY, offsetZ, speed, amount, data, players);
    }

    public void display(Location loc, int amount) {
        display(loc, amount, 0, 0, 0);
    }

    public void display(Location loc, int amount, double offsetX, double offsetY, double offsetZ) {
        display(loc, amount, offsetX, offsetY, offsetZ, 0);
    }

    public void display(Location loc, int amount, double offsetX, double offsetY, double offsetZ, double extra) {
        effect.display(loc, (float) offsetX, (float) offsetY, (float) offsetZ, (float) extra, amount, null);
    }

    public void display(Location loc, int amount, double offsetX, double offsetY, double offsetZ, Object data) {
        display(loc, amount, offsetX, offsetY, offsetZ, 0, data);
    }

    public void display(Location loc, int amount, double offsetX, double offsetY, double offsetZ, double extra, Object data) {
        if (dataClass == null || dataClass.isAssignableFrom(Void.class) || data == null || !dataClass.isAssignableFrom(data.getClass())) {
            display(loc, amount, offsetX, offsetY, offsetZ, extra);
        } else {
            loc.getWorld().spawnParticle(particle, loc, amount, offsetX, offsetY, offsetZ, extra, data, true);
        }
    }
}
