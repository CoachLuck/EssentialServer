/*
 *     File: EPlayer.java
 *     Last Modified: 11/10/20, 1:25 AM
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

package io.github.coachluck.api;

import io.github.coachluck.EssentialServer;
import io.github.coachluck.utils.ChatUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class EPlayer {

    @Getter @Setter
    private UUID uuid;

    @Getter
    private final Player player;

    @Getter
    private String nickName;

    @Getter
    private List<Location> homeList;

    @Getter
    private boolean isVanished;

    @Getter
    private boolean isGod;

    @Getter
    private boolean isAllowedFlight;

    @Getter
    private YamlConfiguration playerData;

    private final PlayerFile playerDataFile;

    public EPlayer(Player player) {
        EssentialServer plugin = EssentialServer.getPlugin(EssentialServer.class);
        this.player = player;
        this.uuid = player.getUniqueId();

        playerDataFile = new PlayerFile(uuid);
    }

}
