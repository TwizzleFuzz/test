package dev.vili.haiku.ui.component;

import dev.vili.haiku.ui.font.FontManager;
import net.minecraft.client.gui.DrawContext;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Window extends Component {
    private final String title;
    private final List<Tab> tabs = new ArrayList<>();
    private Tab activeTab;
    private boolean dragging = false;
    private double dragStartX, dragStartY;

    public Window(String title, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.title = title;
    }

    public void addTab(Tab tab) {
        tabs.add(tab);
        if (activeTab == null) {
            activeTab = tab;
            tab.setActive(true);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Отрисовка фона окна (темный прямоугольник)
        context.fill((int)x, (int)y, (int)(x + width), (int)(y + height), new Color(21, 21, 21).getRGB());
        
        // Отрисовка "шапки" окна
        context.fill((int)x, (int)y, (int)(x + width), (int)(y + 20), new Color(30, 30, 30).getRGB());
        FontManager.drawStringWithShadow(context, title, x + 5, y + 6, Color.WHITE.getRGB());
        
        // Обновление позиции при перетаскивании
        if (dragging) {
            this.x = (float)(mouseX - dragStartX);
            this.y = (float)(mouseY - dragStartY);
        }

        // Рендер вкладок
        float tabX = x;
        for (Tab tab : tabs) {
            tab.x = tabX;
            tab.y = y + 20;
            tab.width = 50; // example width
            tab.height = 20; // example height
            tab.render(context, mouseX, mouseY, delta);
            tabX += tab.width;
        }

        // Рендер активной вкладки
        if (activeTab != null) {
            activeTab.render(context, mouseX, mouseY, delta);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        // Перетаскивание
        if (button == 0 && mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 20) {
            dragging = true;
            dragStartX = mouseX - x;
            dragStartY = mouseY - y;
            return;
        }

        // Клик по вкладкам
        for (Tab tab : tabs) {
            if (mouseX >= tab.x && mouseX <= tab.x + tab.width && mouseY >= tab.y && mouseY <= tab.y + tab.height) {
                if (activeTab != null) {
                    activeTab.setActive(false);
                }
                activeTab = tab;
                activeTab.setActive(true);
                return;
            }
        }

        // Клик по компонентам активной вкладки
        if (activeTab != null) {
            activeTab.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            dragging = false;
        }
        
        if (activeTab != null) {
            activeTab.mouseReleased(mouseX, mouseY, button);
        }
    }
}
