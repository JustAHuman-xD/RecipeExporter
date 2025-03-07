package me.justahuman.slimefun_server_essentials.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.justahuman.slimefun_server_essentials.implementation.core.DefaultDisplays;
import me.justahuman.slimefun_server_essentials.util.JsonUtils;
import org.bukkit.inventory.ItemStack;

public class RecipeCategoryBuilder {
    private ItemStack item = null;
    private int speed = -1;
    private int energy = -1;
    private String display = null;
    private final JsonArray recipes = new JsonArray();

    public void item(ItemStack item) {
        this.item = item;
    }

    public void speed(int speed) {
        this.speed = speed;
    }

    public void energy(int energy) {
        this.energy = energy;
    }

    public void display(DefaultDisplays display) {
        this.display = display.id();
    }

    public void display(String display) {
        this.display = display;
    }

    public void recipe(RecipeBuilder recipe) {
        JsonUtils.addRecipeWithOptimize(this.recipes, recipe.build());
    }

    public boolean isEmpty() {
        return recipes.isEmpty();
    }

    public JsonObject build() {
        JsonObject category = new JsonObject();
        if (item != null) {
            category.add("item", JsonUtils.serializeItem(item));
        }
        if (speed != -1) {
            category.addProperty("speed", this.speed);
        }
        if (energy != -1) {
            category.addProperty("energy", this.energy);
        }
        if (display != null) {
            category.addProperty("display", this.display);
        }
        JsonUtils.sortJsonArray(recipes);
        category.add("recipes", recipes.size() == 1 ? recipes.get(0) : recipes);
        return category;
    }
}
