package hu.ait.android.maggie.shoppinglist.data;

import hu.ait.android.maggie.shoppinglist.R;

/**
 * Created by Magisus on 3/8/2015.
 */
public class Item {

    public enum ItemType {
        PRODUCE(0, R.drawable.fruit), DAIRY(1, R.drawable.dairy), BAKERY(2, R.drawable.bread),
        CANDY(3, R.drawable.candy), HOUSEHOLD(4, R.drawable.cleaners), DRINKS(5, R.drawable.drinks);

        private int value;
        private int iconId;

        private ItemType(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }

        public static ItemType fromInt(int value) {
            for (ItemType p : ItemType.values()) {
                if (p.value == value) {
                    return p;
                }
            }
            return PRODUCE;
        }

        public int getIconId() {
            return iconId;
        }
    }

    private String name;
    private boolean purchased;
    private float priceEstimate;
    private int quantity;
    private ItemType type;

    public Item(String name, ItemType type, float priceEstimate, int quantity) {
        purchased = false;
        this.name = name;
        this.priceEstimate = priceEstimate;
        this.quantity = quantity;
        this.type = type;

    }

    public void togglePurchased() {
        purchased = !purchased;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {

        return name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public float getPriceEstimate() {
        return priceEstimate;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemType getType() {
        return type;
    }
}
