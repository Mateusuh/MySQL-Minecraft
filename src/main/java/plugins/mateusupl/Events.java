package plugins.mateusupl;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {

    @EventHandler
    public void onKillMob(EntityDeathEvent e){
        if(e.getEntity() instanceof Creature) {
            if (e.getEntity().getKiller() != null) {
                MySQL.addKill(e.getEntity().getKiller());
                e.getEntity().getKiller().sendMessage("Kill adicionada ao Banco de Dados.");
            }
        }
    }

    @EventHandler
    public void onEnter(PlayerJoinEvent e){
        if(!MySQL.hasJogador(e.getPlayer())){
            MySQL.addJogador(e.getPlayer());
            e.getPlayer().sendMessage("Jogador adicionado ao Banco de Dados.");
        }
    }

}
