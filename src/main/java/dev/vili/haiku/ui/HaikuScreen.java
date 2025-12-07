package dev.vili.haiku.ui;

import dev.vili.haiku.ui.component.Button;
import dev.vili.haiku.ui.component.Tab;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import dev.vili.haiku.ui.component.Component;
import dev.vili.haiku.ui.component.Window;

public class HaikuScreen extends Screen {
    private static HaikuScreen INSTANCE;
    private final List<Component> components = new ArrayList<>();

    public HaikuScreen() {
        super(Text.of("Haiku GUI"));
        
        Window window = new Window("Haiku", 100, 100, 400, 300);

        Tab combatTab = new Tab("Combat", 0, 0, 0, 0);
        combatTab.addComponent(new Button("KillAura", window.x + 10, window.y + 50, 100, 20, (b) -> {
            System.out.println("KillAura button clicked");
        }));

        Tab visualTab = new Tab("Visuals", 0, 0, 0, 0);
        visualTab.addComponent(new Button("ESP", window.x + 10, window.y + 50, 100, 20, (b) -> {
            System.out.println("ESP button clicked");
        }));
        
        window.addTab(combatTab);
        window.addTab(visualTab);
        
        components.add(window);
    }

    public static HaikuScreen getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HaikuScreen();
        }
        return INSTANCE;
    }
    
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // A semi-transparent background
        context.fill(0, 0, this.width, this.height, 0x60000000);
        for (Component component : components) {
            component.render(context, mouseX, mouseY, delta);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public boolean isPauseScreen() {
        return false; // Чтобы игра не ставилась на паузу
    }
}
