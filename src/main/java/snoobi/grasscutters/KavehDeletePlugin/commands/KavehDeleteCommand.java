package snoobi.grasscutters.KavehDeletePlugin.commands;

import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.command.Command.TargetRequirement;
import emu.grasscutter.command.Command;

import emu.grasscutter.game.player.Player;

import java.util.*;

// Command usage
@Command(label = "kavehdelete", aliases = {"kvd","kd","kv"} , usage = "\n/kv [\n    on(die) |\n    off(no die) |\n    log(toggle logging)\n    boom(toggle explode effect)\n    gadgetId(spawn this on explosion)\n]", targetRequirement = TargetRequirement.PLAYER)
public class KavehDeleteCommand implements CommandHandler {

    public static boolean state = true;
    public static boolean log = false;
    public static boolean isBoomBoom = true;
    public static int boomGadgetId = 71700322;

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        //satan check
        if (args.size() < 1) {
            sendUsageMessage(targetPlayer);
            return;
        }
        //else check for arg 1
        String arg = args.get(0);
        switch (arg) {
            case "on" -> {state = true;}
            case "off" -> {state = false;}
            case "log" -> {log = log ? false : true;}
            case "boom" -> {isBoomBoom = isBoomBoom ? false : true;}
            default -> {
                try {
                    boomGadgetId = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    CommandHandler.sendMessage(targetPlayer, "INVALID INPUT, NOT NUM EITHER FOR GADGETID BAKOODAN");
                }
            }
        }

        CommandHandler.sendMessage(targetPlayer, "KavehDeletePlugin STATUS:\n\n  STATE : " + state + "\n  LOG_STATUS : " + log + "\n BOOM_EFFECT : " + isBoomBoom + "\n BOOM_GADGET_ID : " + boomGadgetId);
    }
} // KavehDeleteCommand
