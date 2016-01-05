package recycler.example.com.usingrecyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {
    //-------Recycler Types-----------------
    public static final int VERTICAL_LIST = 1;
    public static final int HORIZONTAL_LIST = 2;
    public static final int VERTICAL_GRID = 3;
    public static final int HORIZONTAL_GRID = 4;
    public static final int VERTICAL_STAGGERED_GRID = 5;
    public static final int HORIZONTAL_STAGGERED_GRID = 6;
    //-------LayoutManagers-----------------
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutMnaager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    //-------Views--------------------------
    private RecyclerView rvExample;
    private EditText edtAddText;
    private Button btnAdd;
    //-------Instance Variables-------------
    private MyRecyclerAdapter mAdapter;
    private List<String> mList;

    public RecyclerViewFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecyclerViewFragment.
     */
    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        initViews(layout);
        initLayoutManager();
        mList = new ArrayList<String>();
        for(int i = 0; i < 100; i++){
            mList.add(String.valueOf(i));
        }
        mAdapter = new MyRecyclerAdapter(mList,getActivity());
        rvExample.setAdapter(mAdapter);
        rvExample.setItemAnimator(new DefaultItemAnimator());
        changeRecyclerViewTo(VERTICAL_LIST);

        return layout;
    }

    private void initViews(View layout){
        rvExample = (RecyclerView) layout.findViewById(R.id.myRecyclerView);
        edtAddText = (EditText) layout.findViewById(R.id.edtAddText);
        btnAdd = (Button) layout.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtAddText.getText().toString();
                if (!TextUtils.isEmpty(text)){
                    mList.add(0,text);
                    mAdapter.notifyDataSetChanged();
                    edtAddText.setText("");
                }
            }
        });
    }

    public void changeRecyclerViewTo(int recyclerType){
        rvExample.setLayoutManager(changeRecyclerType(recyclerType));
        mAdapter.notifyDataSetChanged();
    }

    private void initLayoutManager(){
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mGridLayoutMnaager = new GridLayoutManager(getActivity(),3);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
    }


    private RecyclerView.LayoutManager changeRecyclerType(int typeOfRecycler){
        switch (typeOfRecycler){
            case VERTICAL_LIST:
                mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                return mLinearLayoutManager;
            case HORIZONTAL_LIST:
                mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                return mLinearLayoutManager;

            case VERTICAL_GRID:
                mGridLayoutMnaager.setOrientation(GridLayoutManager.VERTICAL);
                return mGridLayoutMnaager;

            case HORIZONTAL_GRID:
                mGridLayoutMnaager.setOrientation(GridLayoutManager.HORIZONTAL);
                return mGridLayoutMnaager;

            case VERTICAL_STAGGERED_GRID:
                mStaggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
                return mStaggeredGridLayoutManager;

            case HORIZONTAL_STAGGERED_GRID:
                mStaggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
                return mStaggeredGridLayoutManager;

            default:
                mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                return mLinearLayoutManager;
        }
    }

}
