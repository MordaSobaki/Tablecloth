package ru.ckateptb.tablecloth.collision.debug;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.RandomUtils;
import ru.ckateptb.tablecloth.Tablecloth;
import ru.ckateptb.tablecloth.async.AsyncService;
import ru.ckateptb.tablecloth.collision.Collider;
import ru.ckateptb.tablecloth.collision.callback.CollisionCallbackResult;
import ru.ckateptb.tablecloth.collision.collider.CompositeCollider;
import ru.ckateptb.tablecloth.config.TableclothConfig;
import ru.ckateptb.tablecloth.ioc.annotation.Component;
import ru.ckateptb.tablecloth.ioc.annotation.Scheduled;
import ru.ckateptb.tablecloth.math.ImmutableVector;
import ru.ckateptb.tablecloth.particle.Particle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class DebugColliderService {
    private final List<Collider> colliders = new ArrayList<>();
    private boolean collidersLocked = false;
    private final AsyncService asyncService;
    private final TableclothConfig config;

    public DebugColliderService(AsyncService asyncService, TableclothConfig config) {
        this.asyncService = asyncService;
        this.config = config;
        Bukkit.getScheduler().runTaskTimer(Tablecloth.getInstance(), this::debug, 0, 5);
    }

    @Scheduled(period = 5)
    public void debug() {
        if (!config.isDebugCollider()) return;
        double step = 0.1;
        if (this.colliders.isEmpty()) return;
        List<Collider> colliders = new ArrayList<>(this.colliders);
        CompletableFuture.runAsync(() -> {
            for (Collider collider : colliders) {
                World world = collider.getWorld();
                for (Collider other : colliders) {
                    boolean isOther = other != collider;
                    Collider finalCollider = isOther ? new CompositeCollider(world, collider.getPosition(), collider, other) {
                        @Override
                        public boolean contains(ImmutableVector point) {
                            return super.allContains(point);
                        }
                    } : collider;
                    finalCollider.handlePositionCollisions(step, vector -> {
                        if (RandomUtils.nextInt(0, 50) == 0) {
                            (isOther && collider.intersects(other) ? Particle.FLAME : Particle.SOUL_FIRE_FLAME).display(vector.toLocation(world));
                        }
                        return CollisionCallbackResult.CONTINUE;
                    });
                }
            }
        });
    }

    public void addCollider(Collider collider) {
        if (!config.isDebugCollider()) return;
        asyncService.supplyAsync(() -> {
            while (true) {
                if (!collidersLocked) {
                    collidersLocked = true;
                    colliders.add(collider);
                    return true;
                }
            }
        }, result -> collidersLocked = false);
    }

    public void removeCollider(Collider collider) {
        if (!config.isDebugCollider()) return;
        asyncService.supplyAsync(() -> {
            while (true) {
                if (!collidersLocked) {
                    collidersLocked = true;
                    colliders.remove(collider);
                    return true;
                }
            }
        }, result -> collidersLocked = false);
    }

    public void clearColliders() {
        if (!config.isDebugCollider()) return;
        asyncService.supplyAsync(() -> {
            while (true) {
                if (!collidersLocked) {
                    collidersLocked = true;
                    colliders.clear();
                    return true;
                }
            }
        }, result -> collidersLocked = false);
    }
}
