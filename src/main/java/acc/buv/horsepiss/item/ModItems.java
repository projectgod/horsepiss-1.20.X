package acc.buv.horsepiss.item;

import acc.buv.horsepiss.HorsePiss;
import acc.buv.horsepiss.item.custom.BucketOfHorsePissItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.IdentityHashMap;

public class ModItems {
    // public static final Item BUCKET_OF_HORSE_PISS = registerItem("bucket_of_horse_piss", new Item(new FabricItemSettings()));
    public static final Item BUCKET_OF_HORSE_PISS = registerItem("bucket_of_horse_piss", new BucketOfHorsePissItem(new FabricItemSettings()));

    private static void addItemsToToolsTabItemGroup(FabricItemGroupEntries entries){
        entries.add(BUCKET_OF_HORSE_PISS);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HorsePiss.MOD_ID, name), item);
    }
    public static void registerModItems(){
        HorsePiss.LOGGER.info("Registering Mod Items for" + HorsePiss.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsTabItemGroup);
    }
}
