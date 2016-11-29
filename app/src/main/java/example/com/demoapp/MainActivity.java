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
        BookModel bookModel = mRealm.createObject(BookModel.class);
        bookModel.setTitle("Book title " + mCount);
        bookModel.setCost(mCount);
        mCount++;
        mRealm.commitTransaction();
        displayDbData();
    }

    /**
     * Function to display the database content.
     */
    private void displayDbData() {
        mRealm.beginTransaction();
        RealmResults<BookModel> realmResults = mRealm.where(BookModel.class)
                .findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (BookModel bookModel : realmResults) {
            stringBuilder.append(bookModel.getTitle())
                    .append("   ")
                    .append(bookModel.getCost())
                    .append("\n");
        }
        tvData.setText(stringBuilder.toString());
        mRealm.commitTransaction();
    }

    @OnClick(R.id.btnClearAllRecord)
    void onClickClearButton() {
        mCount = 100;
        mRealm.beginTransaction();
        mRealm.clear(BookModel.class);
        mRealm.commitTransaction();
        tvData.setText("");
    }
}
