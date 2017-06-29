package com.simpleduino.CounterAPI.CounterParts;

import com.simpleduino.CounterAPI.CounterAPIPlugin;
import com.simpleduino.CounterAPI.CounterParts.Runnables.CounterRunnable;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by Simple-Duino on 26/06/2017.
 * Copyrights Simple-Duino, all rights reserved
 */
public class CounterElement {

    private int countTime;
    private Runnable counter;
    public int countdownTime = -1;

    public CounterElement(int c)
    {
        this.countTime = c;
    }

    public Runnable startCounter()
    {
        Runnable runnable = new CounterRunnable(this);
        Bukkit.getScheduler().runTaskTimer(CounterAPIPlugin.getPlugin(CounterAPIPlugin.class), runnable, 1L, 20L);
        this.counter = runnable;

        return runnable;
    }

    public void stopCounter()
    {
        if(this.counter != null)
            this.countdownTime = -1;
    }

    public int getCountdownTime()
    {
        return this.countdownTime;
    }

    public int getCountTime()
    {
        return this.countTime;
    }

}
