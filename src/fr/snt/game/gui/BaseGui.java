package fr.snt.game.gui;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseGui {
    protected final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected final int offsetX = (screenSize.width/15);
    protected final int offsetY = screenSize.height/12;
    Path currentRelativePath = Paths.get("");
    protected String imPath = currentRelativePath.toAbsolutePath().toString();
}
