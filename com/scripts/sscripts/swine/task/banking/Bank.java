package swine.task.banking;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import swine.SWine;
import swine.task.Task;

import java.util.concurrent.Callable;

/**
 * Created by Sebastian on 06.08.2015.
 */
public class Bank extends Task {
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    private final int SeedID = 1937;
    private final int GrapeID = 1987;
    private final int Jug = 1993;

    @Override
    public boolean activate() {
        final Item j = ctx.inventory.select().id(Jug).first().poll();
        return ctx.bank.opened() && !ctx.inventory.select().contains(j);
    }

    @Override
    public void execute() {
        if (ctx.inventory.isEmpty()){
            SWine.Status = "Withdrawing";
            ctx.bank.withdraw(SeedID, 14);
            ctx.bank.withdraw(GrapeID, org.powerbot.script.rt4.Bank.Amount.ALL);
            ctx.bank.close();
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !ctx.bank.opened();
                }
            }, 500, 2);
        }
    }
}