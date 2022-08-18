package ru.ckateptb.tablecloth.config;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class YamlConfigLoadEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    private final Plugin plugin;
    @Getter
    private final YamlConfig yamlConfig;
    @Getter
    private final YamlConfiguration bukkitConfig;
    @Getter
    private final Map<Class<?>, Pair<Object, Function<String, String>>> toScanMap = new HashMap<>();

    public YamlConfigLoadEvent(Plugin plugin, YamlConfig yamlConfig, YamlConfiguration bukkitConfig) {
        this.plugin = plugin;
        this.yamlConfig = yamlConfig;
        this.bukkitConfig = bukkitConfig;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public void scan(Class<?> clazz, Object instance) {
        scan(clazz, instance, (path) -> path);
    }

    public void scan(Class<?> clazz, Object instance, Function<String, String> pathFunction) {
        toScanMap.put(clazz, Pair.of(instance, pathFunction));
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}