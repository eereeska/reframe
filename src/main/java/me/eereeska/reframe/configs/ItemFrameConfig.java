package me.eereeska.reframe.configs;

import me.eereeska.reframe.ReFrame;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ItemFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ItemFrameConfig {

    private final File file;
    private final FileConfiguration config;

    public ItemFrameConfig(final ReFrame plugin) {
        this.file = new File(plugin.getDataFolder(), "frames.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public final List<String> getFunctionsList(final ItemFrame itemFrame) {
        return this.config.getStringList("items." + itemFrame.getLocation().toString() + ".functions");
    }

    public final boolean toggleFunction(final ItemFrame itemFrame, final String function) {
        final List<String> functions = this.config.getStringList("items." + itemFrame.getLocation().toString() + ".functions");

        if (functions.contains(function)) {
            functions.remove(function);
        } else {
            functions.add(function);
        }

        this.config.set("items." + itemFrame.getLocation().toString() + ".functions", functions);

        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return functions.contains(function);
    }
}
