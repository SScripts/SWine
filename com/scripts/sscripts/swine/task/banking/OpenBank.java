package swine.task.banking;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import swine.SWine;
import swine.methods.Methods;
import swine.task.Task;

import java.util.concurrent.Callable;

/**
 * Created by Sebastian on 06.08.2015.
 */


public class OpenBank extends Task {

    public OpenBank(ClientContext ctx) {
        super(ctx);
    }

    private final int bankerID[] = {5454,5453};

    @Override
    public boolean activate() {
        return !ctx.bank.opened() && !methods.inventoryMethods().inventoryContainsGrapes();
    }

    @Override
    public void execute() {
        Npc Bank = ctx.npcs.select().id(bankerID).nearest().poll();
        SWine.Status = "Opening Bank";
        Bank.interact("Bank");
        Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.bank.opened();
                }}, 500, 2);
    }
}


