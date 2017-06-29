package com.simpleduino.CounterAPI.CounterParts.Runnables;

import com.google.common.util.concurrent.Runnables;
import com.simpleduino.CounterAPI.CounterParts.CounterElement;
import com.simpleduino.CounterAPI.Events.CounterEndEvent;
import com.simpleduino.CounterAPI.Events.CounterProgressEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Simple-Duino on 26/06/2017.
 * Copyrights Simple-Duino, all rights reserved
 */
public class CounterRunnable implements Runnable {

    private int countdown;
    private CounterElement counter;

    public CounterRunnable(CounterElement c)
    {
        this.countdown = c.getCountTime();
        this.counter = c;
        this.counter.countdownTime = countdown;
    }

    @Override
    public void run() {
        boolean looped=false;
        while(countdown>=0 && !looped) {
            if (countdown == 0) {
                Bukkit.getPluginManager().callEvent(new CounterEndEvent(counter));
            } else {
                Bukkit.getPluginManager().callEvent(new CounterProgressEvent(counter));
            }
            this.countdown--;
            this.counter.countdownTime = countdown;
            looped=true;
        }
    }

}
