package com.awecode.muscn.views.recentresults;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awecode.muscn.R;
import com.awecode.muscn.adapter.MatchResultExpandableRecyclerviewAdapter;
import com.awecode.muscn.model.Item;
import com.awecode.muscn.model.http.recentresults.RecentResultsResponse;
import com.awecode.muscn.model.listener.RecyclerViewScrollListener;
import com.awecode.muscn.util.Util;
import com.awecode.muscn.util.retrofit.MuscnApiInterface;
import com.awecode.muscn.util.retrofit.ServiceGenerator;
import com.awecode.muscn.views.HomeActivity;
import com.awecode.muscn.views.MasterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by suresh on 9/26/16.
 */

public class MatchResultFragment extends MasterFragment {
    @BindView(R.id.matchResultRecyclerview)
    RecyclerView mMatchResultRecyclerview;

    private List<Item> data;
    private MatchResultExpandableRecyclerviewAdapter mMatchResultExpandableRecyclerviewAdapter;
    private MuscnApiInterface mApiInterface;
    public RecyclerViewScrollListener recyclerViewScrollListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewScrollListener = (RecyclerViewScrollListener) this.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<Item>();
        mActivity.setCustomTitle(R.string.recent_results);
        if (Util.checkInternetConnection(mContext))
            requestMatchResults();
        else {
            ((HomeActivity) mContext).noInternetConnectionDialog(mContext);
        }
    }

    /**
     * populate match result, opponent name and match score
     */
    public void setupMatchResultRecyclerview() {
        mMatchResultRecyclerview.setHasFixedSize(true);
        mMatchResultRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMatchResultExpandableRecyclerviewAdapter = new MatchResultExpandableRecyclerviewAdapter(getActivity(), data);
        mMatchResultRecyclerview.setAdapter(Util.getAnimationAdapter(mMatchResultExpandableRecyclerviewAdapter));
        recyclerViewScrollListener.onRecyclerViewScrolled(mMatchResultRecyclerview);
    }


    /**
     * fetch manutd recent match results list
     */
    public void requestMatchResults() {
        showProgressView(getString(R.string.loading_recent_results));
        mApiInterface = ServiceGenerator.createService(MuscnApiInterface.class);
        Observable<RecentResultsResponse> call = mApiInterface.getResults();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecentResultsResponse>() {
                    @Override
                    public void onCompleted() {
                        mActivity.showContentView();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        mActivity.showErrorView(e.getMessage() + ". Try again");
                        mActivity.noInternetConnectionDialog(mContext);
                    }

                    @Override
                    public void onNext(RecentResultsResponse fixturesResponse) {
                        for (int i = 0; i < fixturesResponse.getResults().size(); i++) {
                            Item demand = new Item(getActivity(), MatchResultExpandableRecyclerviewAdapter.HEADER, fixturesResponse);
                            demand.invisibleChildren = new ArrayList<>();
                            demand.invisibleChildren.add(new Item(getActivity(), MatchResultExpandableRecyclerviewAdapter.CHILD));
                            data.add(demand);
                        }
                        setupMatchResultRecyclerview();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
