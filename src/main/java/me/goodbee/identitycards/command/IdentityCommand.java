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

package me.goodbee.identitycards.command;

import me.goodbee.identitycards.command.interfaces.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IdentityCommand implements CommandExecutor {
    Map<String, Subcommand> subcommands = new HashMap<>();

    public void registerSubcommand(Subcommand subcommand, String label) {
        subcommands.put(label, subcommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(subcommands.containsKey(args[0])) {
            subcommands.get(args[0]).onCommand(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
        } else {
            sender.sendMessage(ChatColor.RED + "Unknown subcommand: " + args[0]);
        }

        return false;
    }
}
