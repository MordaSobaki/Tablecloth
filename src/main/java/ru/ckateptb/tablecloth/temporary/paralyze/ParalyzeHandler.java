package ru.ckateptb.tablecloth.temporary.paralyze;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ru.ckateptb.tablecloth.config.TableclothConfig;
import ru.ckateptb.tablecloth.ioc.annotation.Component;
import ru.ckateptb.tablecloth.temporary.Temporary;
import ru.ckateptb.tablecloth.temporary.TemporaryService;

@Component
public class ParalyzeHandler implements Listener {
   private final TableclothConfig config;
   private final TemporaryService temporaryService;

   public ParalyzeHandler(TableclothConfig config, TemporaryService temporaryService) {
      this.config = config;
      this.temporaryService = temporaryService;
   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(EntityDamageByEntityEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getDamager())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(PlayerCommandPreprocessEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getPlayer())) {
         event.getPlayer().sendMessage(this.config.getParalyzeCommand());
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(PlayerInteractEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getPlayer())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(BlockPlaceEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getPlayer())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(EntityChangeBlockEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(EntityExplodeEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(EntityInteractEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(ProjectileLaunchEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(EntityShootBowEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(SlimeSplitEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(EntityTargetEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(EntityTargetLivingEntityEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(EntityTeleportEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getEntity())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(PlayerInteractAtEntityEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getPlayer())) {
         event.setCancelled(true);
      }

   }

   @EventHandler(
      priority = EventPriority.NORMAL,
      ignoreCancelled = true
   )
   public void on(PlayerMoveEvent event) {
      if (TemporaryParalyze.isParalyzed(event.getPlayer()) && event.getPlayer().getLocation().getBlock().isLiquid() && (event.getTo().getX() != event.getFrom().getX() || event.getTo().getZ() != event.getFrom().getZ() || event.getPlayer().getVelocity().getY() > 0.0D)) {
         Location loc = event.getFrom().clone();
         loc.setY(event.getTo().getY());
         loc.setPitch(event.getTo().getPitch());
         loc.setYaw(event.getTo().getYaw());
         event.setTo(loc);
      }

   }

   @EventHandler(priority = EventPriority.NORMAL)
   public void on(EntityDeathEvent event) {
       LivingEntity entity = event.getEntity();
       if (TemporaryParalyze.isParalyzed(entity)) {
           entity.getMetadata("tablecloth:paralyze").forEach(metadataValue -> {
               if (metadataValue.value() instanceof Temporary temporary) {
                   temporaryService.revert(temporary);
               }
           });
       }
   }

   @EventHandler(priority = EventPriority.NORMAL)
   public void on(PlayerQuitEvent event) {
       Player player = event.getPlayer();
       if (TemporaryParalyze.isParalyzed(player)) {
           player.getMetadata("tablecloth:paralyze").forEach(metadataValue -> {
               if (metadataValue.value() instanceof Temporary temporary) {
                   temporaryService.revert(temporary);
               }
           });
       }
   }
}
