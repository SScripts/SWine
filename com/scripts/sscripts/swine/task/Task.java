package swine.task;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import swine.methods.MethodFacade;

/**
 * Created by Sebastian on 02.08.2015.
 */
public abstract class Task extends ClientAccessor  {
    protected MethodFacade methods;
    public Task(ClientContext ctx) {
        super(ctx);
        methods = new MethodFacade(ctx);

    }
    public abstract boolean activate();
    public abstract void execute();
}
