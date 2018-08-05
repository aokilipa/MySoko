package com.antony.MyShop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import com.antony.myshop.data.DataManager;
import com.antony.myshop.data.local.DatabaseHelper;
import com.antony.myshop.data.local.PreferencesHelper;
import com.antony.myshop.data.model.Product;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock DatabaseHelper mMockDatabaseHelper;
    @Mock PreferencesHelper mMockPreferencesHelper;
    @Mock RibotsService mMockRibotsService;
    private DataManager mDataManager;

    @Before
    public void setUp() {
        mDataManager = new DataManager(mMockRibotsService, mMockPreferencesHelper,
                mMockDatabaseHelper);
    }

    @Test
    public void syncRibotsEmitsValues() {
        List<Product> products = Arrays.asList(TestDataFactory.makeRibot("r1"),
                TestDataFactory.makeRibot("r2"));
        stubSyncRibotsHelperCalls(products);

        TestObserver<Product> result = new TestObserver<>();
        mDataManager.syncRibots().subscribe(result);
        result.assertNoErrors();
        result.assertValueSequence(products);
    }

    @Test
    public void syncRibotsCallsApiAndDatabase() {
        List<Product> products = Arrays.asList(TestDataFactory.makeRibot("r1"),
                TestDataFactory.makeRibot("r2"));
        stubSyncRibotsHelperCalls(products);

        mDataManager.syncRibots().subscribe();
        // Verify right calls to helper methods
        verify(mMockRibotsService).getRibots();
        verify(mMockDatabaseHelper).setRibots(products);
    }

    @Test
    public void syncRibotsDoesNotCallDatabaseWhenApiFails() {
        when(mMockRibotsService.getRibots())
                .thenReturn(Observable.<List<Product>>error(new RuntimeException()));

        mDataManager.syncRibots().subscribe(new TestObserver<Product>());
        // Verify right calls to helper methods
        verify(mMockRibotsService).getRibots();
        verify(mMockDatabaseHelper, never()).setRibots(ArgumentMatchers.<Product>anyList());
    }

    private void stubSyncRibotsHelperCalls(List<Product> products) {
        // Stub calls to the ribot service and database helper.
        when(mMockRibotsService.getRibots())
                .thenReturn(Observable.just(products));
        when(mMockDatabaseHelper.setRibots(products))
                .thenReturn(Observable.fromIterable(products));
    }

}
