package hu.ait.android.maggie.shoppinglist;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.maggie.shoppinglist.adapter.ItemAdapter;
import hu.ait.android.maggie.shoppinglist.data.Item;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = new ArrayList<>();
        items.add(new Item("Milk", Item.ItemType.DAIRY, 210.0, 1));
        items.add(new Item("Oranges", Item.ItemType.PRODUCE, 160, 3));
        items.add(new Item("Soap", Item.ItemType.HOUSEHOLD, 160, 3));
        items.add(new Item("Chocolate", Item.ItemType.CANDY, 160, 3));
        items.add(new Item("Soda", Item.ItemType.DRINKS, 160, 3));
        items.add(new Item("Bread", Item.ItemType.BAKERY, 160, 3));

        setListAdapter(new ItemAdapter(this, items));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
