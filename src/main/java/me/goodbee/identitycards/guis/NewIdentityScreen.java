/*
 *     IdentityCards is a plugin for Spigot servers for creating and managing identities.
 *
 *     Copyright (C) 2024, goodbee
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.goodbee.identitycards.guis;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import me.goodbee.identitycards.IdentityCards;
import me.goodbee.identitycards.enums.Gender;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class NewIdentityScreen {
    private final Player player;

    public NewIdentityScreen(Player player) {
        this.player = player;
    }

    public void prompt() {
        promptName();
    }

    private String name;
    private Gender gender;

    private void promptName() {
        AnvilGUI.Builder builder = new AnvilGUI.Builder()
                .plugin(IdentityCards.getInstance())
                .text("Set name")
                .itemLeft(new ItemStack(Material.PAPER))
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT) {
                        List<AnvilGUI.ResponseAction> list = new ArrayList<>();

                        list.add(AnvilGUI.ResponseAction.run(new Runnable() {
                            @Override
                            public void run() {
                                name = stateSnapshot.getOutputItem().getItemMeta().getDisplayName();

                                promptGender();
                            }
                        }));

                        list.add(AnvilGUI.ResponseAction.close());

                        return list;
                    }
                    return Collections.emptyList();
                })
                .interactableSlots(AnvilGUI.Slot.OUTPUT);

        builder.open(player);

    }

    private void promptGender() {
        ChestGui gui = new ChestGui(3, "Set gender");

        Pattern backgroundPattern = new Pattern(
                "111111111",
                "000000000",
                "111111111"
        );

        PatternPane backgroundPane = new PatternPane(0, 0, 9,3, backgroundPattern);
        backgroundPane.setPriority(Pane.Priority.LOWEST);
        backgroundPane.bindItem('1', new GuiItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), event -> event.setCancelled(true)));

        Pattern genderPattern = new Pattern(
                "000000000",
                "0M00X00F0",
                "000000000"
        );

        PatternPane genderPane = new PatternPane(0, 0, 9, 3, genderPattern);

        genderPane.bindItem('M', new GuiItem(getGenderStack('M'), (event -> {
            event.setCancelled(true);
            gender = Gender.MALE;
        })));
        genderPane.bindItem('X', new GuiItem(getGenderStack('X'), (event -> {
            event.setCancelled(true);
            gender = Gender.OTHER;
        })));
        genderPane.bindItem('F', new GuiItem(getGenderStack('F'), (event -> {
            event.setCancelled(true);
            gender = Gender.FEMALE;
        })));

        gui.addPane(backgroundPane);
        gui.addPane(genderPane);

        gui.show(player);
    }

    private ItemStack getGenderStack(char genderchar) {
        return switch (genderchar) {
            case 'M':
                ItemStack mItem = new ItemStack(Material.LIGHT_BLUE_WOOL);
                ItemMeta mMeta = mItem.getItemMeta();

                mMeta.setDisplayName(ChatColor.BLUE + "Male");

                mItem.setItemMeta(mMeta);

                yield mItem;
            case 'X':
                ItemStack xItem = new ItemStack(Material.YELLOW_WOOL);
                ItemMeta xMeta = xItem.getItemMeta();

                xMeta.setDisplayName(ChatColor.YELLOW + "Other");

                xItem.setItemMeta(xMeta);

                yield xItem;
            case 'F':
                ItemStack fItem = new ItemStack(Material.PINK_WOOL);
                ItemMeta fMeta = fItem.getItemMeta();

                fMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Female");

                fItem.setItemMeta(fMeta);

                yield fItem;
            default:
                throw new IllegalStateException("Unexpected value: " + genderchar);
        };
    }
}
