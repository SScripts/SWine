package swine.methods;

import org.powerbot.script.rt4.ClientContext;

/**
 * Created by Sebastian on 09.08.2015.
 */
public class MethodFacade {

    private Inventory inventoryMethods;
    public MethodFacade(ClientContext ctx){
        this.inventoryMethods = new Inventory(ctx);
    }
    public Inventory inventoryMethods() {
        return this.inventoryMethods;
    }
}
