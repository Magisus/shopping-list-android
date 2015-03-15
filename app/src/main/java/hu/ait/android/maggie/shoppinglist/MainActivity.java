package hu.ait.android.maggie.shoppinglist;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.maggie.shoppinglist.adapter.ItemAdapter;
import hu.ait.android.maggie.shoppinglist.data.Item;


public class MainActivity extends ActionBarActivity {

    public static final int REQUEST_CREATE = 100;
    public static final int REQUEST_EDIT = 101;
    public static final int CONTEXT_ACTION_DELETE = 10;
    public static final int CONTEXT_ACTION_EDIT = 11;

    private ListView itemList;
    private ItemAdapter adapter;
    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = Item.listAll(Item.class);

        itemList = (ListView) findViewById(R.id.itemList);
        adapter = new ItemAdapter(this, items);
        itemList.setAdapter(adapter);

        emptyText = (TextView) findViewById(R.id.noItemsText);
        if(itemList.getAdapter().getCount() == 0){
            emptyText.setVisibility(View.VISIBLE);
        }

        registerForContextMenu(itemList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Menu");
        menu.add(0, CONTEXT_ACTION_DELETE, 0, "Delete");
        menu.add(0, CONTEXT_ACTION_EDIT, 0, "Edit");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == CONTEXT_ACTION_DELETE) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Item selectedItem = (Item) adapter.getItem(info.position);
            selectedItem.delete();

            adapter.removeItem(info.position);
            adapter.notifyDataSetChanged();
            if (adapter.getCount() == 0) {
                emptyText.setVisibility(View.VISIBLE);
            }
        } else if (item.getItemId() == CONTEXT_ACTION_EDIT){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Item selectedItem = (Item) adapter.getItem(info.position);
            Intent intent = new Intent(this, CreateItemActivity.class);
            intent.putExtra(CreateItemActivity.KEY_EDIT_ITEM, selectedItem);
            intent.putExtra(CreateItemActivity.KEY_EDIT_ID, info.position);
            startActivityForResult(intent, REQUEST_EDIT);
        } else {
            return false;
        }
        return true;
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
            Item item = (Item) data.getSerializableExtra
                    (CreateItemActivity.KEY_ITEM);
            item.save();
            ((ItemAdapter) itemList.getAdapter()).addItem(item);
            ((ItemAdapter) itemList.getAdapter()).notifyDataSetChanged();
            emptyText.setVisibility(View.GONE);
            Toast.makeText(this, getString(R.string.item_added_alert), Toast.LENGTH_LONG);
        } else if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK) {
            int index = data.getIntExtra(CreateItemActivity.KEY_EDIT_ID, -1);
            if(index != -1){
                Item item = (Item) data.getSerializableExtra(CreateItemActivity.KEY_ITEM);
                item.setId(adapter.getItem(index).getId());
                item.save();

                adapter.updateItem(index, item);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Item updated in the list!", Toast.LENGTH_LONG).show();
            }
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
