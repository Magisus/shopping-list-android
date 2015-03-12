package hu.ait.android.maggie.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import hu.ait.android.maggie.shoppinglist.data.Item;


public class CreateItemActivity extends ActionBarActivity {

    public static final String KEY_ITEM = "KEY_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        final Spinner typeSpinner = (Spinner) findViewById(R.id.spinnerItemType);
        final EditText editItemName = (EditText) findViewById(R.id.editItemName);
        final EditText editItemPrice = (EditText) findViewById(R.id.editItemPrice);
        Button saveButton = (Button) findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item(editItemName.getText().toString(),
                        Item.ItemType.fromInt(typeSpinner.getSelectedItemPosition()),
                        Double.parseDouble(editItemPrice.getText().toString()),
                        1);
                setResult(RESULT_OK, new Intent().putExtra(KEY_ITEM,
                        item));

                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_item, menu);
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
