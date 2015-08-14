package swine;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.Constants;
import swine.task.Task;
import swine.task.banking.Bank;


import swine.task.banking.Deposit;
import swine.task.banking.OpenBank;

import swine.task.wine.Wine;

@Script.Manifest(description = "Makes Wine for 300k/h+ Cooking exp! Supports GE", name = "SWine", properties = "client=4")


public class SWine extends PollingScript<org.powerbot.script.rt4.ClientContext> implements PaintListener {

    private final long initTime = System.currentTimeMillis();
    public List<Task> tasks = Collections.synchronizedList(new ArrayList<Task>());
    private final int startXP = ctx.skills.experience(Constants.SKILLS_COOKING);
    public static String Status;

    @Override
    public void start() {
        Status = "Starting up!";
        tasks.add(new OpenBank(ctx));
        tasks.add(new Wine(ctx));
        tasks.add(new Bank(ctx));
        tasks.add(new Deposit(ctx));
    }

    @Override
    public void poll() {
        synchronized(tasks) {
            if (tasks.size() == 0) {
                try {
                    tasks.wait();
                } catch (InterruptedException ignored) {}
            }
        }
        for (Task task : tasks) {
            if (task.activate()) {
                task.execute();
            }
        }
    }

    public String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
    }

    @Override
    public void repaint(Graphics g) {
        int gainedXP = ctx.skills.experience(Constants.SKILLS_COOKING) - startXP;
        int perHourXp = 0;
        long currentTime = System.currentTimeMillis() - initTime;
        long currentTimeSec = (int)(currentTime / 1000);
        if (currentTimeSec > 0)
            perHourXp = (int)( (gainedXP * 3600) /(currentTimeSec));
        g.setColor(Color.GRAY);
        g.fillRect(5,65,150,130);
        g.setColor(Color.WHITE);
        g.drawString("SWine - by SScripts v1.0", 10, 80);
        g.drawString("Status: " + Status, 10, 100);
        g.drawString("Time Run: " + formatTime(getTotalRuntime()), 10, 140);
        g.drawString("EXP gained: " + gainedXP, 10, 160);
        g.drawString("EXP/H:" + perHourXp , 10, 180);
        g.setColor(Color.BLUE);
        final Point p = ctx.input.getLocation();
        g.drawLine(p.x - 5, p.y - 5, p.x + 5, p.y + 5);
        g.drawLine(p.x - 5, p.y + 5, p.x + 5, p.y - 5);
    }


}