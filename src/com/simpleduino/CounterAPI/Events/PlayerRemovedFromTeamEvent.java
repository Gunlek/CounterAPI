package com.simpleduino.CounterAPI.Events;

import com.simpleduino.CounterAPI.CounterParts.CounterElement;
import com.simpleduino.CounterAPI.TeamManagerParts.TeamElement;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
/**
 * Created by Simple-Duino on 26/06/2017.
 * Copyrights Simple-Duino, all rights reserved
 */
public class PlayerRemovedFromTeamEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private TeamElement team;

    public PlayerRemovedFromTeamEvent(Player p, TeamElement t)
    {
        this.player = p;
        this.team = t;
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public TeamElement getTeam()
    {
        return this.team;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
