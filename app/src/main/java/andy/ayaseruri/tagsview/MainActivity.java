package andy.ayaseruri.tagsview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import andy.ayaseruri.tagsviewlib.TagsView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TagsView tagsView = (TagsView)findViewById(R.id.tags_view);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagsView.init(new ArrayList<String>() {{
                    add("tags");
                    add("tags_tow");
                    add("tags_hello");
                    add("tags_withtagsview");
                    add("t");
                    add("ta");
                    add("tags");
                    add("tags_withtagsview");
                    add("tags_withtagsview");
                }}
                        , new TagsView.ITagOnClick() {
                    @Override
                    public void onClick(int postion) {
                        Toast.makeText(MainActivity.this, "tags " + postion + " has been clicked.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
