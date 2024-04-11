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

package me.goodbee.identitycards;

import me.goodbee.identitycards.command.IdentityCommand;
import me.goodbee.identitycards.command.subcommands.IdentityHelpCommand;
import me.goodbee.identitycards.command.subcommands.IdentityNewCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class IdentityCards extends JavaPlugin {
    private static IdentityCards instance;

    @Override
    public void onEnable() {
        instance = this;

        registerCommand();
    }

    private void registerCommand() {
        IdentityCommand identityCommand = new IdentityCommand();

        identityCommand.registerSubcommand(new IdentityHelpCommand(), "help");
        identityCommand.registerSubcommand(new IdentityNewCommand(), "new");

        this.getCommand("identitycards").setExecutor(identityCommand);
        this.getCommand("identitycards").setTabCompleter(identityCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static IdentityCards getInstance() {
        return instance;
    }
}
