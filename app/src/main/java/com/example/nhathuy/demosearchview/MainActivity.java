package com.example.nhathuy.demosearchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView lsvTieuBang;
    List<String> dsTieuBang;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsvTieuBang = (ListView) findViewById(R.id.lsvTieuBang);
        CaiDatListView();
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private void CaiDatListView() {
        dsTieuBang = new ArrayList<>();
        dsTieuBang.addAll(Arrays.asList(getResources().getStringArray(R.array.states)));
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dsTieuBang);
        lsvTieuBang.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuSearchView = menu.findItem(R.id.itSearchView);
        SearchView searchView = (SearchView) menuSearchView.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // nhập tới đâu thì gợi ý tới đó
            @Override
            public boolean onQueryTextSubmit(String query) {
                // để trống cũng dc
                return false;
            }

            // bắt sự kiện thay đổi Text Field
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);    // cập nhật text mới để lọc các phần tử của ListView
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itSearchView:
                break;
            case R.id.itSetting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show(); break;
            case R.id.itThoat:
                finish(); break;
            default:
                break;
        }
        return true;
    }
}
