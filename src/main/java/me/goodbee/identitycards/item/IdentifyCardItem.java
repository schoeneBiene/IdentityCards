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

package me.goodbee.identitycards.item;

import me.goodbee.identitycards.enums.Gender;

public class IdentifyCardItem {
    private final String name;
    private final String DOB;
    private final Gender gender;

    public IdentifyCardItem(String name, String DOB, Gender gender) {
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
    }
}
