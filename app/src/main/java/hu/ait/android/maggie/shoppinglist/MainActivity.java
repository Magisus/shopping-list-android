package hu.ait.android.maggie.shoppinglist;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.maggie.shoppinglist.adapter.ItemAdapter;
import hu.ait.android.maggie.shoppinglist.data.Item;


public class MainActivity extends ActionBarActivity {

    public static final int REQUEST_CREATE = 100;

    private ListView itemList;
    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = new ArrayList<>();
//        items.add(new Item("Milk", Item.ItemType.DAIRY, 210.0, 1));
//        items.add(new Item("Oranges", Item.ItemType.PRODUCE, 160, 3));
//        items.add(new Item("Soap", Item.ItemType.HOUSEHOLD, 160, 3));
//        items.add(new Item("Chocolate", Item.ItemType.CANDY, 160, 3));
//        items.add(new Item("Soda", Item.ItemType.DRINKS, 160, 3));
//        items.add(new Item("Bread", Item.ItemType.BAKERY, 160, 3));

        itemList = (ListView) findViewById(R.id.itemList);
        itemList.setAdapter(new ItemAdapter(this, items));

        emptyText = (TextView) findViewById(R.id.noItemsText);
        if(itemList.getAdapter().getCount() == 0){
            emptyText.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CREATE && resultCode == RESULT_OK){
            ((ItemAdapter) itemList.getAdapter()).addItem((Item) data.getSerializableExtra
                    (CreateItemActivity.KEY_ITEM));
            ((ItemAdapter) itemList.getAdapter()).notifyDataSetChanged();
            emptyText.setVisibility(View.GONE);
            Toast.makeText(this, getString(R.string.item_added_alert), Toast.LENGTH_LONG);
        } else if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, getString(R.string.canceled_alert), Toast.LENGTH_LONG);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new) {
            startActivityForResult(new Intent(this, CreateItemActivity.class), REQUEST_CREATE);
        }

        return super.onOptionsItemSelected(item);
    }
}
