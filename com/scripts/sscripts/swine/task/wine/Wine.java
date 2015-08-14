package swine.task.wine;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import swine.SWine;
import swine.task.Task;

import java.util.concurrent.Callable;

/**
 * Created by Sebastian on 04.08.2015.
 */
public class Wine extends Task{


    public Wine(ClientContext ctx) {
        super(ctx);
    }

    private final int emptyID = 1937;
    private final int grapesID = 1987;

    @Override
    public boolean activate() {
        return !ctx.bank.opened();
    }

    @Override
    public void execute() {
        final Item e = ctx.inventory.select().id(emptyID).first().poll();
        final Item g = ctx.inventory.select().id(grapesID).first().poll();
        if (ctx.inventory.select().count() == 28) {
            SWine.Status = "Making Wine";
            e.interact("Use");
            g.interact("Use");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.widgets.widget(309).component(3).valid();
                }
            }, 1000, 20);
        }
        if (ctx.widgets.widget(309).component(3).valid()) {
            ctx.widgets.component(309, 3).interact("Make All");
            Condition.sleep(3500);
        }
        if (ctx.widgets.widget(233).component(2).valid()) {
            ctx.widgets.component(233, 2).interact("Continue");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !ctx.widgets.widget(233).component(2).valid();
                }
            }, 1000, 20);
        }
        if (ctx.widgets.widget(11).component(3).valid()) {
            ctx.widgets.component(11, 3).interact("Continue");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !ctx.widgets.widget(11).component(3).valid();
                }
            }, 1000, 20);
        }

    }
}


