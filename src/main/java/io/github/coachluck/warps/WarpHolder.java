/*
 *     File: WarpHolder.java
 *     Last Modified: 6/28/20, 12:31 PM
 *     Project: EssentialServer
 *     Copyright (C) 2020 CoachL_ck
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

package io.github.coachluck.warps;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Sound;

public class WarpHolder {

    @Getter @Setter Location location;
    @Getter @Setter Sound warpSound;
    @Getter @Setter String warpMessage;
    @Getter @Setter String displayName;

    public WarpHolder(Location location, Sound warpSound, String warpMessage, String displayName) {
        this.location = location;
        this.warpSound = warpSound;
        this.warpMessage = warpMessage;
        this.displayName = displayName;
    }

    public WarpHolder() { }
}
