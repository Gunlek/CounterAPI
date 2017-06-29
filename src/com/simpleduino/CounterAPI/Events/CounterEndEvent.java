package com.simpleduino.CounterAPI.Events;

import com.simpleduino.CounterAPI.CounterParts.CounterElement;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
/**
 * Created by Simple-Duino on 26/06/2017.
 * Copyrights Simple-Duino, all rights reserved
 */
public class CounterEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private CounterElement counter;

    public CounterEndEvent(CounterElement c)
    {
        this.counter = c;
    }

    public CounterElement getCounter()
    {
        return this.counter;
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
