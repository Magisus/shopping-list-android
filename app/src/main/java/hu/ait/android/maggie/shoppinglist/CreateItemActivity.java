package hu.ait.android.maggie.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

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
}
