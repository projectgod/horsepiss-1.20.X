package acc.buv.horsepiss.mixin;

import acc.buv.horsepiss.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin extends Item {

    public BucketItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (entity instanceof HorseEntity && entity.isAlive() && !entity.isBaby() && player.isSneaking()) {
            this.milk(stack, player, ModItems.BUCKET_OF_HORSE_PISS.getDefaultStack(), hand);
            return ActionResult.success(player.getWorld().isClient);
        }
        return ActionResult.PASS;
    }

    protected void milk(ItemStack bucketStack, PlayerEntity player, ItemStack milkStack, Hand hand) {
        player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
        if ((bucketStack.getCount() == 1) && !player.isCreative()){
            player.setStackInHand(hand, milkStack);
        }
        else {
            ItemStack itemStack = ItemUsage.exchangeStack(bucketStack, player, milkStack);
            player.setStackInHand(hand, itemStack);
        }
    }
}

