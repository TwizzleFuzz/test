package dev.vili.haiku.ui.font;

// This is a placeholder for a more complex font rendering system.
// For a proper implementation, you would typically use a library or write
// a more detailed implementation that handles font loading and rendering.
// For now, we will just pass through to the default text renderer.

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class FontManager {

    // In a real implementation, you would load your custom fonts here.
    // For example:
    // public static TextRenderer josefinSans;
    // public static TextRenderer josefinSansBold;

    public static void init() {
        // Load fonts here
        System.out.println("FontManager: Skipping custom font loading for now.");
    }

    public static TextRenderer getTextRenderer() {
        // Return the default renderer as a fallback
        return MinecraftClient.getInstance().textRenderer;
    }

    public static void drawString(DrawContext context, String text, float x, float y, int color) {
        // In a real implementation, you would use your custom font renderer.
        // For now, we use the default one.
        context.drawText(getTextRenderer(), text, (int)x, (int)y, color, false);
    }
    
    public static void drawStringWithShadow(DrawContext context, String text, float x, float y, int color) {
        context.drawTextWithShadow(getTextRenderer(), text, (int)x, (int)y, color);
    }
}
