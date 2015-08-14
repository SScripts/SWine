package swine.task.banking;

import org.powerbot.script.rt4.ClientContext;
import swine.task.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;

/**
 * Created by Sebastian on 14.08.2015.
 */
public class Deposit extends Task {
    public Deposit(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened() && !ctx.inventory.isEmpty();
    }

    @Override
    public void execute() {

        ctx.bank.depositInventory();
        org.powerbot.script.Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.isEmpty();
            }
        }, 500, 2);


    }
}
