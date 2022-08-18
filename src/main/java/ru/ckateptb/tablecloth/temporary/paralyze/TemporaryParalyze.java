package ru.ckateptb.tablecloth.temporary.paralyze;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import ru.ckateptb.tablecloth.Tablecloth;
import ru.ckateptb.tablecloth.config.TableclothConfig;
import ru.ckateptb.tablecloth.ioc.IoC;
import ru.ckateptb.tablecloth.temporary.AbstractTemporary;
import ru.ckateptb.tablecloth.temporary.Temporary;
import ru.ckateptb.tablecloth.temporary.TemporaryBossBar;
import ru.ckateptb.tablecloth.temporary.TemporaryService;
import ru.ckateptb.tablecloth.temporary.TemporaryUpdateState;

public class TemporaryParalyze extends AbstractTemporary {
	
	public static final ProtocolManager protocolManager;

    static {
        protocolManager = ProtocolLibrary.getProtocolManager();
        
        protocolManager.addPacketListener(new PacketAdapter(Tablecloth.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.LOOK) {
            public void onPacketReceiving(PacketEvent e) {
               if (TemporaryParalyze.isParalyzed(e.getPlayer())) {
                  e.getPacket().getFloat().write(0, TemporaryParalyze.sourceLoc.getYaw());
                  e.getPacket().getFloat().write(1, TemporaryParalyze.sourceLoc.getPitch());
               }

            }
         });
    }

    private static final Map<UUID, Cache<UUID, Boolean>> cache = new HashMap<>();

    public static boolean isParalyzed(Entity entity) {
        UUID uuid = entity.getUniqueId();
        return cache.containsKey(uuid) && cache.computeIfAbsent(uuid, key ->
                Caffeine.newBuilder().expireAfterAccess(Duration.ofMillis(1000)).build()
        ).get(uuid, id -> entity.hasMetadata("tablecloth:paralyze"));
    }
	
	
   private final TemporaryService temporaryService;
   private final Tablecloth plugin;
   private final LivingEntity livingEntity;
   private final long duration;
   private TemporaryBossBar temporaryBossBar;
   private TableclothConfig config;
   private Integer food;
   private static Location sourceLoc;
   private Float saturation;
   
   
   public TemporaryParalyze(LivingEntity livingEntity, long duration) {
      this.livingEntity = livingEntity;
      sourceLoc = livingEntity.getLocation().clone();
      this.duration = duration;
      this.config = (TableclothConfig)IoC.get(TableclothConfig.class);
      this.temporaryService = (TemporaryService)IoC.get(TemporaryService.class);
      this.plugin = Tablecloth.getInstance();
      
      if (this.livingEntity.hasMetadata("tablecloth:paralyze")) {
          List.copyOf(this.livingEntity.getMetadata("tablecloth:paralyze")).forEach(metadataValue -> {
              if (metadataValue.value() instanceof Temporary temporary) {
                  temporaryService.revert(temporary);
              }
          });
      }

      this.setRevertTime(duration + System.currentTimeMillis());
      this.register();
   }

   public void init() {
      try {
         
    	  
    	  
    	  
    	  
    	  
         this.livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).addModifier(new AttributeModifier(UUID.randomUUID(), "tablecloth:Paralyze", -1.0D, Operation.MULTIPLY_SCALAR_1));
         this.livingEntity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).addModifier(new AttributeModifier(UUID.randomUUID(), "tablecloth:Paralyze", 1, Operation.ADD_NUMBER));
         
         if (livingEntity instanceof Player player) {
            String paralyzeName = this.config.getParalyzeName();
            this.livingEntity.setMetadata("tablecloth:paralyze", new FixedMetadataValue(this.plugin, this));
            UUID uuid = player.getUniqueId();
            
            
            cache.put(uuid, Caffeine.newBuilder().expireAfterAccess(Duration.ofMillis(duration)).build());
            cache.get(uuid).get(uuid, key -> true);
            
            
            //BendingPlayer.getBendingPlayer(player).toggleBending();
            this.food = ((Player)this.livingEntity).getFoodLevel();
            this.saturation = ((Player)this.livingEntity).getSaturation();
            this.livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (int)(this.duration / 50L), 128));
            ((Player)this.livingEntity).setSaturation(0.0F);
            ((Player)this.livingEntity).setFoodLevel(2);
            temporaryBossBar = new TemporaryBossBar(paralyzeName, duration, player);
         }

      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   public TemporaryUpdateState update() {
      return TemporaryUpdateState.CONTINUE;
   }

   public void revert() {
      if (this.livingEntity.hasMetadata("tablecloth:paralyze")) this.livingEntity.removeMetadata("tablecloth:paralyze", this.plugin);
      

      cache.remove(this.livingEntity.getUniqueId());
      
      
      this.livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getModifiers().forEach((a) -> { if (a.getName() == "tablecloth:Paralyze") this.livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).removeModifier(a); });
      this.livingEntity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getModifiers().forEach((a) -> { if (a.getName() == "tablecloth:Paralyze1") this.livingEntity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).removeModifier(a); });
      
      
      if (livingEntity instanceof Player player) {
         player.setSaturation(this.saturation);
         player.setFoodLevel(this.food);
      }

      if (this.temporaryBossBar != null) {
         this.temporaryService.revert(this.temporaryBossBar);
      }

   }

   public LivingEntity getLivingEntity() {
      return this.livingEntity;
   }

}
