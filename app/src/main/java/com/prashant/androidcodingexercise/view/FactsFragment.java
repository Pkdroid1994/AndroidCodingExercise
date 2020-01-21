package com.prashant.androidcodingexercise.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.prashant.androidcodingexercise.R;
import com.prashant.androidcodingexercise.data.AllFactsResponseModel;
import com.prashant.androidcodingexercise.data.ApiResponse;
import com.prashant.androidcodingexercise.databinding.FragmentFactsBinding;
import com.prashant.androidcodingexercise.utils.ConnectivityUtil;
import com.prashant.androidcodingexercise.utils.ViewModelFactory;


public class FactsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentFactsBinding fragmentFactsBinding;
    private MainActivity mContext;
    private FactsViewModel factsViewModel;
    private FactsAdapter factsAdapter;
    private ConnectivityUtil connectivityUtil;
    private ActionBar actionBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentFactsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_facts, container, false);

        connectivityUtil = new ConnectivityUtil(mContext);

        return fragmentFactsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        actionBar = mContext.getSupportActionBar();

        fragmentFactsBinding.recyclerFacts.setLayoutManager(new LinearLayoutManager(mContext));
        factsAdapter = new FactsAdapter();
        fragmentFactsBinding.recyclerFacts.setAdapter(factsAdapter);


        fragmentFactsBinding.layoutSwipeRefresh.setOnRefreshListener(this);
        factsViewModel = ViewModelProviders.of(mContext, new ViewModelFactory(new FactsRepository(connectivityUtil))).get(FactsViewModel.class);

        factsViewModel.getFactsApiResponse().observe(this, apiResponse -> handleApiResponse(apiResponse));
    }

    private void handleApiResponse(ApiResponse apiResponse) {
        if (apiResponse != null) {
            switch (apiResponse.status) {
                case LOADING:
                    fragmentFactsBinding.layoutSwipeRefresh.setRefreshing(true);
                    break;
                case SUCCESS:
                    fragmentFactsBinding.layoutSwipeRefresh.setRefreshing(false);
                    setData(apiResponse.data);
                    break;
                case ERROR:
                    fragmentFactsBinding.layoutSwipeRefresh.setRefreshing(false);
                    Toast.makeText(mContext, apiResponse.messageId, Toast.LENGTH_SHORT).show();
                    break;
                case COMPLETED:
                    fragmentFactsBinding.layoutSwipeRefresh.setRefreshing(false);
                    break;

            }
        }

    }

    private void setData(AllFactsResponseModel data) {

        if (data.getTitle() != null && !data.getTitle().isEmpty())
            actionBar.setTitle(data.getTitle());
        else actionBar.setTitle(getResources().getString(R.string.not_content));

        if (data.getRows() != null && data.getRows().size() > 0) {
            factsAdapter.setData(data.getRows());
        } else actionBar.setTitle(getResources().getString(R.string.no_data));

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity)
            this.mContext = (MainActivity) context;
    }

    @Override
    public void onRefresh() {
        factsViewModel.refreshData();
    }
}
