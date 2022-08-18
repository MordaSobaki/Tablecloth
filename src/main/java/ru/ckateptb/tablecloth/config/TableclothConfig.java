package ru.ckateptb.tablecloth.config;

import org.bukkit.plugin.Plugin;

import ru.ckateptb.tablecloth.Tablecloth;
import ru.ckateptb.tablecloth.ioc.annotation.Component;

@Component
public class TableclothConfig extends YamlConfig {
   @ConfigField(
      name = "debug.collider",
      comment = "This function is necessary for debugging collisions (do not touch if you do not understand what it is about)"
   )
   private boolean debugCollider = false;
   @ConfigField(
      name = "paralyze.name"
   )
   private String paralyzeName = "§b§l<==§2§l Paralyzed §b§l==>";
   @ConfigField(
      name = "paralyze.commandHandler",
      comment = "Сообщение, отправляющееся игроку в том случае, если он пытается написать команду, находясь в обездвиженном состоянии (Paralyze)"
   )
   private String paralyzeCommand = "Вы не можете отправлять команды в обездвиженном состоянии!";
   
   private Integer paralyzeKnockback = 100;

   public Plugin getPlugin() {
      return Tablecloth.getInstance();
   }

   public boolean isDebugCollider() {
      return this.debugCollider;
   }

   public String getParalyzeName() {
      return this.paralyzeName;
   }

   public String getParalyzeCommand() {
      return this.paralyzeCommand;
   }

   public Integer getParalyzeKnockback() {
      return this.paralyzeKnockback;
   }
}
