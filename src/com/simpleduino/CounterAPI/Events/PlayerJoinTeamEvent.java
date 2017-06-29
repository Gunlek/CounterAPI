package com.simpleduino.CounterAPI.Events;

import com.simpleduino.CounterAPI.TeamManagerParts.TeamElement;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Simple-Duino on 28/06/2017.
 * Copyrights Simple-Duino, all rights reserved
 */
public class PlayerJoinTeamEvent extends Event {

    private static HandlerList handlerList = new HandlerList();
    private Player player;
    private TeamElement teamElement;

    public PlayerJoinTeamEvent(Player p, TeamElement te)
    {
        this.player = p;
        this.teamElement = te;
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public TeamElement getTeamElement()
    {
        return this.teamElement;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList()
    {
        return handlerList;
    }
}
