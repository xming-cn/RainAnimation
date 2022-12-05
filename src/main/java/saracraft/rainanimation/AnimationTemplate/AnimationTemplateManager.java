package saracraft.rainanimation.AnimationTemplate;

import org.apache.commons.lang.StringUtils;
import saracraft.rainanimation.RainAnimation;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AnimationTemplateManager {
    private static final AnimationTemplateManager inst = new AnimationTemplateManager();
    private final HashMap<String, AnimationTemplate> templates = new HashMap<>();

    private AnimationTemplateManager() {}
    static public AnimationTemplateManager getInst() {
        return inst;
    }

    public void register(AnimationTemplate template) {
        RainAnimation.plugins.getLogger().info("register template " + template.identifier + " :" + template);
        templates.put(template.identifier, template);
    }

    public void registerAll(String path) {
        List<File> files = getAllFile(path);
        if (Objects.nonNull(files))
            for (File file : files) {
                AnimationTemplate template = new AnimationTemplate(file.getName(), file.getPath());
                register(template);
            }
        else
            RainAnimation.plugins.getLogger().warning("Error when register animation");
    }

    private static List<File> getAllFile(String basePath) {
        if (StringUtils.isBlank(basePath))
            return null;
        File dirFile = new File(basePath);

        File[] childrenFiles = dirFile.listFiles();
        if (Objects.isNull(childrenFiles) || childrenFiles.length == 0)
            return null;

        List<File> files = new ArrayList<>();
        for (File childFile : childrenFiles) {
            if (childFile.isFile()) {
                files.add(childFile);
            } else {
                List<File> cFiles = getAllFile(childFile.getPath());
                if (Objects.isNull(cFiles) || cFiles.isEmpty()) continue;
                files.addAll(cFiles);
            }
        }
        return files;
    }


    @Nullable public AnimationTemplate getTemplate(String identifier) {
        return templates.getOrDefault(identifier, null);
    }
}
