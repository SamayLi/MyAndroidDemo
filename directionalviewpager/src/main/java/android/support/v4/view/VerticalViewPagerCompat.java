package android.support.v4.view;


import android.support.v4.view.PagerAdapter;

/**
 * Created by samay on 5/19/15.
 */
public final class VerticalViewPagerCompat {
    private VerticalViewPagerCompat() {}

    public interface DataSetObserver extends PagerAdapter.DataSetObserver {}

    public static void setDataSetObserver(PagerAdapter adapter, DataSetObserver observer) {
        adapter.setDataSetObserver(observer);
    }
}