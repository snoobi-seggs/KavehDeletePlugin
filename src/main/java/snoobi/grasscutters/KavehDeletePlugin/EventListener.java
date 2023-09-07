package snoobi.grasscutters.KavehDeletePlugin;

import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.entity.EntityGadget;
import emu.grasscutter.game.entity.GameEntity;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ElementType;
import emu.grasscutter.game.props.LifeState;
import emu.grasscutter.game.world.Position;
import emu.grasscutter.game.world.Scene;
import emu.grasscutter.net.proto.VisionTypeOuterClass.VisionType;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.entity.EntityDamageEvent;
import emu.grasscutter.server.packet.send.PacketLifeStateChangeNotify;
import emu.grasscutter.server.packet.send.PacketSceneEntityAppearNotify;
import emu.grasscutter.server.packet.send.PacketSceneEntityDisappearNotify;
import snoobi.grasscutters.KavehDeletePlugin.commands.KavehDeleteCommand;


/**
 * A class containing all event handlers.
 * Syntax in event handler methods are similar to CraftBukkit.
 * To register an event handler, create a new instance of {@link EventHandler}.
 * Pass through the event class you want to handle. (ex. `new EventHandler<>(PlayerJoinEvent.class);`)
 * You can change the point at which the handler method is invoked with {@link EventHandler#priority(HandlerPriority)}.
 * You can set whether the handler method should be invoked when another plugin cancels the event with {@link EventHandler#ignore(boolean)}.
 */
public final class EventListener {
    public static void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getDamager() instanceof EntityAvatar) {
            EntityAvatar avatar = ((EntityAvatar) event.getDamager());
            int attackerAvatarId = avatar.getAvatar().getAvatarId();
            ElementType element = event.getAttackElementType();

            if (attackerAvatarId == 10000081 && element == ElementType.Grass) {
                // is skill or burst from kaveh
                GameEntity victim = event.getEntity();
                Scene scene = avatar.getPlayer().getScene();
                
                if (KavehDeleteCommand.state) {
                    scene.broadcastPacket(new PacketLifeStateChangeNotify(victim, LifeState.LIFE_DEAD));
                    scene.broadcastPacket(new PacketSceneEntityDisappearNotify(victim, VisionType.VISION_TYPE_DIE));
                }

                if (KavehDeleteCommand.isBoomBoom) {
                    EntityGadget boom = new EntityGadget(scene, KavehDeleteCommand.boomGadgetId, victim.getPosition(), new Position());
                    scene.broadcastPacket(new PacketSceneEntityAppearNotify(boom));
                }

                if (KavehDeleteCommand.log) {
                    avatar.getPlayer().dropMessage("Killed Entity of ID : " + victim.getId());
                }
            }
        }
    }
}
