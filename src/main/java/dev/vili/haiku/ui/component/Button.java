package dev.vili.haiku.ui.component;

import dev.vili.haiku.ui.font.FontManager;
import net.minecraft.client.gui.DrawContext;

import java.awt.Color;
import java.util.function.Consumer;

public class Button extends Component {

    private final String text;
    private final Consumer<Button> action;

    public Button(String text, float x, float y, float width, float height, Consumer<Button> action) {
        super(x, y, width, height);
        this.text = text;
        this.action = action;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        Color color = hovered ? new Color(50, 50, 50) : new Color(40, 40, 40);
        context.fill((int) x, (int) y, (int) (x + width), (int) (y + height), color.getRGB());
        FontManager.drawString(context, text, x + 5, y + 5, Color.WHITE.getRGB());
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            action.accept(this);
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
    }
}
