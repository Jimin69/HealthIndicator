package me.jimin.healthindicator;

import me.jimin.healthindicator.utils.Utils;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.api.ChatSentEvent;
import net.weavemc.api.ModInitializer;
import net.weavemc.api.event.EventBus;
import net.weavemc.api.event.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import java.lang.instrument.Instrumentation;

public class ExampleMod implements ModInitializer {

    @Override
    public void preInit(@NotNull Instrumentation instrumentation) {
        HealthIndicator.getInstance().Init(System.getProperty("user.home") + "/.weave/mods");
        System.out.println("Hello from HealthIndicator. Mod ported to modern weave by jiminnn.");
        System.out.println("All credits for the mod go to svxf. original mod: https://github.com/svxf/HealthIndicator");
    }

    @Override
    public void init() {
        EventBus.subscribe(this);
    }

    @SubscribeEvent
    public void onChat(ChatSentEvent e) {
        String[] parts = e.getMessage().split(" ");
        String cmd = parts[0].toLowerCase();
        Config config = HealthIndicator.getInstance().getConfig();

        switch (cmd) {
            case "/heart":
            case "/hearthelp":
            case "/healthindicator":
                e.setCancelled(true);
                Utils.print("by " + EnumChatFormatting.RED + "Svxf");
                Utils.print(EnumChatFormatting.WHITE + "Ported to " + EnumChatFormatting.LIGHT_PURPLE + "Weave-1.1.0 " + EnumChatFormatting.WHITE + "by " + EnumChatFormatting.GOLD + "jiminnn " + EnumChatFormatting.DARK_GRAY + "(claude </3)");
                Utils.print(EnumChatFormatting.WHITE + "Commands:\n/togglehealthindicator (/togglehi)\n/heartdistance [n] (/hd)\n/toggleinvis (/invis)\n/heartscale [n] (/scale)\n/heartoffset [n] (/offset)");
                break;
            case "/togglehealthindicator":
            case "/togglehi":
                e.setCancelled(true);
                config.enabled = !config.enabled;
                HealthIndicator.getInstance().saveConfig();
                Utils.print("Visibility set to " + EnumChatFormatting.RED + config.enabled + EnumChatFormatting.RESET + ".");
                break;
            case "/toggleinvis":
            case "/invis":
                e.setCancelled(true);
                config.showInvis = !config.showInvis;
                HealthIndicator.getInstance().saveConfig();
                Utils.print("ShowInvis set to " + EnumChatFormatting.RED + config.showInvis + EnumChatFormatting.RESET + ".");
                break;
            case "/heartdistance":
            case "/hd":
                e.setCancelled(true);
                if (parts.length < 2) { Utils.print("Usage: /heartdistance [number]"); break; }
                config.distance = Float.parseFloat(parts[1]);
                HealthIndicator.getInstance().saveConfig();
                Utils.print("Distance set to " + EnumChatFormatting.RED + config.distance + EnumChatFormatting.RESET + ".");
                break;
            case "/heartscale":
            case "/scale":
                e.setCancelled(true);
                if (parts.length < 2) { Utils.print("Usage: /heartscale [number]"); break; }
                config.scale = Float.parseFloat(parts[1]);
                HealthIndicator.getInstance().saveConfig();
                Utils.print("Scale set to " + EnumChatFormatting.RED + config.scale + EnumChatFormatting.RESET + ".");
                break;
            case "/heartoffset":
            case "/offset":
                e.setCancelled(true);
                if (parts.length < 2) { Utils.print("Usage: /heartoffset [number]"); break; }
                config.offset = Float.parseFloat(parts[1]);
                HealthIndicator.getInstance().saveConfig();
                Utils.print("Offset set to " + EnumChatFormatting.RED + config.offset + EnumChatFormatting.RESET + ".");
                break;
        }
    }
}