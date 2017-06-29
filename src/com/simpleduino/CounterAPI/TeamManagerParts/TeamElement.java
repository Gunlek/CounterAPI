package com.simpleduino.CounterAPI.TeamManagerParts;

import com.simpleduino.CounterAPI.Events.PlayerJoinTeamEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Simple-Duino on 26/06/2017.
 * Copyrights Simple-Duino, all rights reserved
 */
public class TeamElement {

    private String teamName;
    private ArrayList<Player> members = new ArrayList<>();

    public TeamElement(String tn)
    {
        this.teamName = tn;
    }

    public void addMember(Player p)
    {
        if(!this.members.contains(p)) {
            this.members.add(p);
            TeamManagerElement.playerTeams.put(p, this);
            Bukkit.getPluginManager().callEvent(new PlayerJoinTeamEvent(p, this));
        }
    }

    public void delMember(Player p)
    {
        if(this.members.contains(p)) {
            this.members.remove(p);
            TeamManagerElement.playerTeams.remove(p);
        }
    }

    public ArrayList<Player> getMembers()
    {
        return this.members;
    }

    public int getMembersCount()
    {
        return this.members.size();
    }

    public String getTeamName()
    {
        return this.teamName;
    }

}
