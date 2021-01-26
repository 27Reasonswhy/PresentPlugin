package present.me.reasonswhy.presents.Me.reasonswhy.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import present.me.reasonswhy.presents.Me.reasonswhy.Elf;
import present.me.reasonswhy.presents.Me.reasonswhy.menu.PlayerMenuUtility;
import present.me.reasonswhy.presents.Me.reasonswhy.menu.menus.NewPresentMenu;
import sun.jvm.hotspot.debugger.posix.elf.ELFException;

public class PresentCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /present <to> <message>

        if (sender instanceof Player){

            Player p = (Player) sender;

            Object presents;
            if (args.length >= 1){

                String to = args[0];
                Player recipient = Bukkit.getPlayer(to);

                if (recipient == null){
                    p.sendMessage("The player you specified does not exist. Command: /present <to> <message>");
                    p.sendMessage("Here is an example: /present Billybob123 I like your moms eyes");
                }else{

                    PlayerMenuUtility playerMenuUtility = Elf.getPlayerMenuUtility(p);
                    if (args.length == 1){
                        playerMenuUtility.setRecipient(recipient.getDisplayName());
                    }else if (args.length > 1){

                        playerMenuUtility.setRecipient(recipient.getDisplayName());

                        //attempt to get the message from the sent command
                        StringBuilder message = new StringBuilder();
                        for (int i = 1; i < args.length; i++){
                            message.append(args[i] + " ");
                        }

                        playerMenuUtility.setPresentMessage(message.toString());
                    }

                    new NewPresentMenu(Elf.getPlayerMenuUtility(p)).open();
                }
            }else{
                new NewPresentMenu(Elf.getPlayerMenuUtility(p)).open();
            }



        }


        return true;
    }
}
