package swine.methods;

import org.powerbot.script.rt4.ClientContext;

/**
 * Created by Sebastian on 09.08.2015.
 */
public class Inventory extends Methods {

    public Inventory(ClientContext ctx) {
        super(ctx);
    }

    private final int Grape = 1987;
    private final int finalJug = 1993;

    public boolean inventoryContainsGrapes(){
        //System.out.println("count " + clientContext.inventory.select().id(ItemConstants.ESSENCE_NORMAL).count());
        return ctx.inventory.select().id(Grape).count() > 0 ;
    }

    public boolean inventoryContainsJug(int jug){
        //System.out.println("count " + clientContext.inventory.select().id(ItemConstants.ESSENCE_NORMAL).count());
        return ctx.inventory.select().id(finalJug).count() > 0 ;
    }
}
