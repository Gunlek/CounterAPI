package com.simpleduino.CounterAPI.TeamManagerParts;

import com.simpleduino.CounterAPI.Events.PlayerAddedToTeamEvent;
import com.simpleduino.CounterAPI.Events.PlayerRemovedFromTeamEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Simple-Duino on 26/06/2017.
 * Copyrights Simple-Duino, all rights reserved
 */
public class TeamManagerElement {

    private ArrayList<TeamElement> teams;
    public static HashMap<Player, TeamElement> playerTeams = new HashMap<>();

    public TeamManagerElement(ArrayList<TeamElement> te)
    {
        this.teams = te;
    }

    public void addPlayer(Player p, TeamElement team)
    {
        team.addMember(p);
        playerTeams.put(p, team);
        Bukkit.getPluginManager().callEvent(new PlayerAddedToTeamEvent(p, team));
    }

    public void removePlayer(Player p, TeamElement team)
    {
        team.delMember(p);
        playerTeams.remove(p);
        Bukkit.getPluginManager().callEvent(new PlayerRemovedFromTeamEvent(p, team));
    }

    public TeamElement getPlayerTeam(Player p)
    {
        return playerTeams.getOrDefault(p, null);
    }

    public TeamElement getTeam(String tn)
    {
        for(TeamElement te : teams)
        {
            if(te.getTeamName().equalsIgnoreCase(tn))
                return te;
        }
        return null;
    }

    public void clearPlayerTeam(Player p)
    {
        for(TeamElement te : teams)
        {
            if(te.getMembers().contains(p))
                te.delMember(p);
        }
    }

    public void autoRepart(ArrayList<Player> playerList, ArrayList<TeamElement> teamList, int maxPlayerPerTeam)
    {
        int TotalPlayerCount = playerList.size();
        for(TeamElement te : teamList)
        {
            TotalPlayerCount+=te.getMembersCount();
        }
        if(TotalPlayerCount%teamList.size()==0)
        {
            ArrayList<TeamElement> nonFullTeams = new ArrayList<>();
            for(TeamElement te : teamList)
            {
                if(te.getMembersCount()<maxPlayerPerTeam)
                    nonFullTeams.add(te);
            }
            for(Player p : playerList)
            {
                Random r = new Random();
                int Low = 0;
                int High = nonFullTeams.size()-1;
                int randomValue = r.nextInt(High-Low) + Low;
                while(nonFullTeams.get(randomValue).getMembersCount()>=maxPlayerPerTeam)
                {
                    if(randomValue==nonFullTeams.size()-1)
                        randomValue=0;
                    else
                        randomValue++;
                }
                nonFullTeams.get(randomValue).addMember(p);
            }
        }
        else
        {
            int oversize = playerList.size()%teamList.size(); // Number of teams that will get more player than "normal"
            int currentOversized = 0;
            ArrayList<TeamElement> nonFullTeams = new ArrayList<>();
            for(TeamElement te : teamList)
            {
                if(te.getMembersCount()<maxPlayerPerTeam)
                    nonFullTeams.add(te);
            }
            for(Player p : playerList)
            {
                Random r = new Random();
                int Low = 0;
                int High = nonFullTeams.size()-1;
                int randomValue = r.nextInt(High-Low) + Low;
                while(nonFullTeams.get(randomValue).getMembersCount()>=maxPlayerPerTeam || (currentOversized<oversize && nonFullTeams.get(randomValue).getMembersCount()<=maxPlayerPerTeam))
                {
                    if(randomValue==nonFullTeams.size()-1)
                        randomValue=0;
                    else
                        randomValue++;
                }
                nonFullTeams.get(randomValue).addMember(p);
                if((currentOversized<oversize && nonFullTeams.get(randomValue).getMembersCount()<=maxPlayerPerTeam))
                    currentOversized++;
            }
        }
    }

    public ArrayList<TeamElement> getTeams()
    {
        return this.teams;
    }

}
