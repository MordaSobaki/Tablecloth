package ru.ckateptb.tablecloth.gui.chest;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.ckateptb.tablecloth.gui.ItemButton;
import ru.ckateptb.tablecloth.ioc.IoC;

import java.util.Optional;

@Getter
public class ChestGui implements InventoryHolder {
    private final Player player;
    private final ItemButton[] buttons;
    private final Inventory inventory;
    private final int slots;
    @Getter
    public InventoryCloseHandler inventoryCloseHandler = (event) -> {
    };
    @Setter
    private boolean ignoreCloseEvent;

    public ChestGui(Player player, String title, int rows) {
        Validate.notBlank(title, "Title can't be null");
        Validate.inclusiveBetween(1, 6, rows, "Rows must be from 1 to 6! ");
        this.slots = rows * 9;
        this.inventory = Bukkit.createInventory(this, slots, title);
        this.buttons = new ItemButton[slots];
        this.player = player;

    }

    public ChestGui setSlot(int slot, ItemButton button) {
        this.buttons[slot] = button;
        this.inventory.setItem(slot, button.get());
        return this;
    }

    public ChestGui clearSlot(int slot) {
        this.buttons[slot] = null;
        this.inventory.setItem(slot, null);
        return this;
    }

    public ChestGui fill(ItemButton button) {
        for (int slot = 0; slot < slots; slot++) {
            this.buttons[slot] = button;
            this.inventory.setItem(slot, button.get());
        }
        return this;
    }

    public void onClose(InventoryCloseHandler handler) {
        this.inventoryCloseHandler = handler;
    }

    public void open() {
        player.openInventory(this.inventory);
        IoC.get(ChestGuiService.class).register(this);
    }

    public Optional<ItemButton> getButton(int slot) {
        return Optional.ofNullable(this.buttons[slot]);
    }


    public interface InventoryCloseHandler {
        void handle(InventoryCloseEvent event);
    }
}
