package ru.ckateptb.tablecloth.collision;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import ru.ckateptb.tablecloth.collision.callback.BlockCollisionCallback;
import ru.ckateptb.tablecloth.collision.callback.CollisionCallbackResult;
import ru.ckateptb.tablecloth.collision.callback.EntityCollisionCallback;
import ru.ckateptb.tablecloth.collision.callback.PositionCollisionCallback;
import ru.ckateptb.tablecloth.collision.collider.AxisAlignedBoundingBoxCollider;
import ru.ckateptb.tablecloth.math.ImmutableVector;

import java.util.Objects;
import java.util.function.Predicate;

public interface Collider {
    boolean intersects(Collider collider);

    ImmutableVector getPosition();

    Location getLocation();

    Collider at(Vector point);

    ImmutableVector getHalfExtents();

    boolean contains(Vector point);

    boolean contains(ImmutableVector point);

    boolean contains(Location location);

    World getWorld();

    void setWorld(World world);

    default boolean handleEntityCollision(LivingEntity source) {
        return handleEntityCollision(source, (entity -> CollisionCallbackResult.END));
    }

    default boolean handleEntityCollision(LivingEntity source, EntityCollisionCallback callback) {
        return handleEntityCollision(source, true, callback);
    }

    default boolean handleEntityCollision(LivingEntity source, boolean livingOnly, EntityCollisionCallback callback) {
        return handleEntityCollision(source, livingOnly, false, callback);
    }

    default boolean handleEntityCollision(LivingEntity source, boolean livingOnly, boolean selfCollision, EntityCollisionCallback callback) {
        return handleEntityCollision(source, livingOnly, selfCollision, true, callback, (entity) -> true);
    }

    default boolean handleEntityCollision(LivingEntity source, boolean livingOnly, boolean selfCollision, boolean armorStandCollision, EntityCollisionCallback callback, Predicate<Entity> filter) {
        return handleEntityCollision(livingOnly, armorStandCollision, callback, filter.and(entity -> selfCollision || !entity.equals(source)));
    }

    default boolean handleEntityCollision(boolean livingOnly, boolean armorStandCollision, EntityCollisionCallback callback, Predicate<Entity> filter) {
        
    	
    	
    	World world = getWorld();
        ImmutableVector halfExtents = getHalfExtents();
        ImmutableVector position = getPosition();
        filter = filter.and(entity -> {
            if (livingOnly && !(entity instanceof LivingEntity)) return false;
            if (entity instanceof Player player && player.getGameMode() == GameMode.SPECTATOR) return false;
            return armorStandCollision || (entity instanceof ArmorStand armorStand && Objects.equals(armorStand.getCustomName(), "paralyze|armor|stand"));
        });
        for (Entity entity : world.getNearbyEntities(position.toLocation(world), halfExtents.getX(), halfExtents.getY(), halfExtents.getZ(), filter)) {
            
        	if (intersects(new AxisAlignedBoundingBoxCollider(entity).at(entity.getLocation().toVector()))) {
        		
                if (callback.onCollision(entity) == CollisionCallbackResult.CONTINUE) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    default boolean handleBlockCollision() {
        return handleBlockCollisions(block -> CollisionCallbackResult.END);
    }

    default boolean handleBlockCollisions(boolean ignoreLiquids) {
        return handleBlockCollisions(ignoreLiquids, block -> CollisionCallbackResult.END, block -> true);
    }

    default boolean handleBlockCollisions(BlockCollisionCallback callback) {
        return handleBlockCollisions(callback, (block -> true));
    }

    default boolean handleBlockCollisions(BlockCollisionCallback callback, Predicate<Block> filter) {
        return handleBlockCollisions(true, callback, filter);
    }

    default boolean handleBlockCollisions(boolean ignoreLiquids, BlockCollisionCallback callback) {
        return handleBlockCollisions(true, ignoreLiquids, callback);
    }

    default boolean handleBlockCollisions(boolean ignorePassable, boolean ignoreLiquids, BlockCollisionCallback callback) {
        return handleBlockCollisions(ignorePassable, ignoreLiquids, callback, (block -> true));
    }

    default boolean handleBlockCollisions(boolean ignoreLiquids, BlockCollisionCallback callback, Predicate<Block> filter) {
        return handleBlockCollisions(true, ignoreLiquids, callback, filter);
    }

    default boolean handleBlockCollisions(boolean ignorePassable, boolean ignoreLiquids, BlockCollisionCallback callback, Predicate<Block> filter) {
        World world = getWorld();
        ImmutableVector position = getPosition();
        double maxExtent = getHalfExtents().maxComponent();
        int radius = (int) (Math.ceil(maxExtent) + 1);
        double originX = position.getX();
        double originY = position.getY();
        double originZ = position.getZ();
        boolean result = false;
        for (double x = originX - radius; x <= originX + radius; x++) {
            for (double y = originY - radius; y <= originY + radius; y++) {
                for (double z = originZ - radius; z <= originZ + radius; z++) {
                    ImmutableVector loc = new ImmutableVector(x, y, z);
                    if (position.distance(loc) > radius) {
                        continue;
                    }
                    Block block = loc.toLocation(world).getBlock();
                    if (block.isPassable()) {
                        if (block.isLiquid()) {
                            if (ignoreLiquids) continue;
                        } else if (ignorePassable) continue;
                    }
                    if (filter.test(block)) {
                        AxisAlignedBoundingBoxCollider collider = new AxisAlignedBoundingBoxCollider(block).at(new ImmutableVector(block));
                        if (intersects(collider)) {
                            result = true;
                            if (callback.onCollision(block) == CollisionCallbackResult.END) return true;
                        }
                    }
                }
            }
        }
        return result;
    }

    default boolean handlePositionCollisions(double step, PositionCollisionCallback callback) {
        ImmutableVector position = getPosition();
        double maxExtent = getHalfExtents().maxComponent();
        int radius = (int) (Math.ceil(maxExtent) + 1);
        double originX = position.getX();
        double originY = position.getY();
        double originZ = position.getZ();
        boolean result = false;
        for (double x = originX - radius; x <= originX + radius; x += step) {
            for (double y = originY - radius; y <= originY + radius; y += step) {
                for (double z = originZ - radius; z <= originZ + radius; z += step) {
                    ImmutableVector loc = new ImmutableVector(x, y, z);
                    if (position.distance(loc) > radius) {
                        continue;
                    }
                    if (contains(loc)) {
                        result = true;
                        if (callback.onCollision(loc) == CollisionCallbackResult.END) return true;
                    }
                }
            }
        }
        return result;
    }
}
