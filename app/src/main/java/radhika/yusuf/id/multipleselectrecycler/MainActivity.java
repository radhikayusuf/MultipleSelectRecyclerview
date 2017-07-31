package radhika.yusuf.id.multipleselectrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private MainAdapter adapter;
    private List<MainData> mData;
    private MenuInflater menuInflater;
    private Menu menu;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        toolbar.getMenu().getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                for (int i = adapter.getSelectedIndexs().size() - 1; i >= 0; i--) {
                    int index = adapter.getSelectedIndexs().get(i);
                    mData.remove(index);
                }
                adapter.getSelectedIndexs().clear();
                adapter.setMode(false);
                adapter.notifyDataSetChanged();

                clearDeleteAction();
                break;
        }
        return true;
    }

    private void initData() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mData = new ArrayList<>();
        mData.addAll(generateDummy());

        adapter = new MainAdapter(mData, this);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView = (RecyclerView)findViewById(R.id.rc_main);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void showDeleteAction(){
        toolbar.setTitle(getString(R.string.selected_item, String.valueOf(adapter.getSelectedIndexs().size())));
        toolbar.getMenu().getItem(0).setVisible(true);
    }

    public void clearDeleteAction() {
        if (adapter.getSelectedIndexs().size() == 0) {
            toolbar.setTitle(getString(R.string.app_name));
            toolbar.getMenu().getItem(0).setVisible(false);
        } else{
            toolbar.setTitle(getString(R.string.selected_item, String.valueOf(adapter.getSelectedIndexs().size())));
        }
    }

    private List<MainData> generateDummy(){
        List<MainData> data = new ArrayList<>();

        data.add(new MainData(0,"index 0"));
        data.add(new MainData(1,"index 1"));
        data.add(new MainData(2,"index 2"));
        data.add(new MainData(3,"index 3"));
        data.add(new MainData(4,"index 4"));
        data.add(new MainData(5,"index 5"));
        data.add(new MainData(6,"index 6"));
        data.add(new MainData(7,"index 7"));
        data.add(new MainData(8,"index 8"));
        data.add(new MainData(9,"index 9"));
        data.add(new MainData(10,"index 10"));
        data.add(new MainData(11,"index 11"));
        return data;
    }
}
