package dev.vili.haiku.ui.component;

import dev.vili.haiku.ui.font.FontManager;
import net.minecraft.client.gui.DrawContext;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Tab extends Component {

    private final String name;
    private final List<Component> components = new ArrayList<>();
    private boolean active = false;

    public Tab(String name, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.name = name;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Render the tab button
        Color color = active ? new Color(45, 45, 45) : new Color(30, 30, 30);
        context.fill((int) x, (int) y, (int) (x + width), (int) (y + height), color.getRGB());
        FontManager.drawString(context, name, x + 5, y + 5, Color.WHITE.getRGB());

        // If active, render the components of this tab
        if (active) {
            for (Component component : components) {
                component.render(context, mouseX, mouseY, delta);
            }
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        // The logic for activating the tab will be in the Window,
        // which manages all the tabs.
        if (active) {
            for (Component component : components) {
                component.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (active) {
            for (Component component : components) {
                component.mouseReleased(mouseX, mouseY, button);
            }
        }
    }

    public void addComponent(Component component) {
        components.add(component);
    }
    
    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
