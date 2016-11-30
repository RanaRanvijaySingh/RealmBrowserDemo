package example.com.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvData)
    TextView tvData;
    private Realm mRealm;
    private int mCount = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRealm = Realm.getInstance(this);
    }

    @OnClick(R.id.btnAddNewRecord)
    void onClickAddNewRecord() {
        mRealm.beginTransaction();
        Book book = mRealm.createObject(Book.class);
        book.setTitle("Title_" + mCount);
        book.setCost(mCount);
        mCount++;
        mRealm.commitTransaction();
        displayDbData();
    }

    /**
     * Function to display the database content.
     */
    private void displayDbData() {
        mRealm.beginTransaction();
        RealmResults<Book> realmResults = mRealm.where(Book.class)
                .findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : realmResults) {
            stringBuilder.append(book.getTitle())
                    .append("   ")
                    .append(book.getCost())
                    .append("\n");
        }
        tvData.setText(stringBuilder.toString());
        mRealm.commitTransaction();
    }

    @OnClick(R.id.btnClearAllRecord)
    void onClickClearButton() {
        mCount = 100;
        mRealm.beginTransaction();
        mRealm.clear(Book.class);
        mRealm.commitTransaction();
        tvData.setText("");
    }
}
