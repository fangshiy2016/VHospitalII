package com.yuyang.VRHospital.view.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.CaseBean;
import com.yuyang.VRHospital.bean.CaseItemBean;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowColorizers;


public class SortableCaseTableView extends SortableTableView<CaseItemBean> {


    public SortableCaseTableView(Context context) {
        this(context, null);
    }

    public SortableCaseTableView(Context context, AttributeSet attributes) {
        this(context, attributes, 0);
    }

    public SortableCaseTableView(Context context, AttributeSet attributes, int styleAttributes) {
        super(context, attributes, styleAttributes);


        SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context,
                getResources().getString(R.string.case_table_title_name),
                getResources().getString(R.string.case_table_title_age),
                getResources().getString(R.string.case_table_title_sex),
                getResources().getString(R.string.case_table_title_class),
                getResources().getString(R.string.case_table_title_time));
        simpleTableHeaderAdapter.setTextColor(context.getResources().getColor(R.color.white));
        simpleTableHeaderAdapter.setTextSize(16);
        setHeaderAdapter(simpleTableHeaderAdapter);

        int rowColorEven = context.getResources().getColor(R.color.table_data_row_even);
        int rowColorOdd = context.getResources().getColor(R.color.table_data_row_odd);
        setDataRowColoriser(TableDataRowColorizers.alternatingRows(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        setColumnWeight(0, 3);
        setColumnWeight(1, 2);
        setColumnWeight(2, 2);
        setColumnWeight(3, 4);
        setColumnWeight(4, 4);

    }

}
