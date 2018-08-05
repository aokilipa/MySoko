package com.antony.MyShop;

import android.database.Cursor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;
import com.antony.myshop.data.local.DatabaseHelper;
import com.antony.myshop.data.local.Db;
import com.antony.myshop.data.local.DbOpenHelper;
import com.antony.myshop.data.model.Product;
import com.antony.MyShop.util.DefaultConfig;
import com.antony.MyShop.util.RxSchedulersOverrideRule;

import static junit.framework.Assert.assertEquals;

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    private DatabaseHelper mDatabaseHelper;

    @Before
    public void setup() {
        if (mDatabaseHelper == null)
            mDatabaseHelper = new DatabaseHelper(new DbOpenHelper(RuntimeEnvironment.application),
                    mOverrideSchedulersRule.getScheduler());
    }

    @Test
    public void setRibots() {
        Product product1 = TestDataFactory.makeRibot("r1");
        Product product2 = TestDataFactory.makeRibot("r2");
        List<Product> products = Arrays.asList(product1, product2);

        TestObserver<Product> result = new TestObserver<>();
        mDatabaseHelper.setRibots(products).subscribe(result);
        result.assertNoErrors();
        result.assertValueSequence(products);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        for (Product product : products) {
            cursor.moveToNext();
            assertEquals(product.profile(), Db.RibotProfileTable.parseCursor(cursor));
        }
    }

    @Test
    public void getRibots() {
        Product product1 = TestDataFactory.makeRibot("r1");
        Product product2 = TestDataFactory.makeRibot("r2");
        List<Product> products = Arrays.asList(product1, product2);

        mDatabaseHelper.setRibots(products).subscribe();

        TestObserver<List<Product>> result = new TestObserver<>();
        mDatabaseHelper.getRibots().subscribe(result);
        result.assertNoErrors();
        result.assertValue(products);
    }

}
