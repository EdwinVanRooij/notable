package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Adapters.ProductAdapter;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.ConfirmDialog;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Decorators.DividerItemDecoration;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Handlers.ProductHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Interfaces.MyOnItemClickListener;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Interfaces.ReturnBoolean;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.GroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.Product;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.NewProduct;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.R;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;


public class FragmentGroceryList extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //region Fields
    private List<Product> products;
    private ProductHandler productHandler;
    private ProductAdapter adapter;
    private static final String TAG = "FragmentGroceryList";

    @Bind(R.id.recyclerView)
    RecyclerView rv;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.ivEmptyList)
    ImageView ivEmptyList;

    private Account thisAccount;
    private GroceryList thisList;
    //endregion

    public FragmentGroceryList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFields();
    }

    private void initializeFields() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(this);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        productHandler = new ProductHandler(getActivity());

        thisAccount = getArguments().getParcelable(Config.KEY_ACCOUNT);
        thisList = getArguments().getParcelable(Config.KEY_LIST);

        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        Intent i = new Intent(getActivity(), NewProduct.class);
        i.putExtra(Config.KEY_ACCOUNT, thisAccount);
        i.putExtra(Config.KEY_LIST, thisList);
        startActivity(i);
    }

    @Override
    public void onRefresh() {
        try {
            swipeRefreshLayout.setRefreshing(true);
            AsyncResponse a = new AsyncResponse() {

                @Override
                public void processFinish(String output) {
                    try {
                        products = GeneralHandler.getProductsFromJSON(output);
                        Log.d(TAG, "processFinish: got " + products.size() + " products from db.");
                        adapter = new ProductAdapter(products);

                        setVisibility();

                        adapter.SetOnItemClickListener(new MyOnItemClickListener() {
                            @Override
                            public void onItemClick(View view, final int position) {
                                ReturnBoolean r = new ReturnBoolean() {
                                    @Override
                                    public void processFinish(Boolean result) {
                                        if (result) {
                                            final Product p = adapter.getItem(position);
                                            AsyncResponse asy = new AsyncResponse() {
                                                @Override
                                                public void processFinish(String output) {
                                                    onRefresh();
                                                }
                                            };
                                            productHandler.deleteProduct(asy, p, false);
                                        }
                                    }
                                };
                                new ConfirmDialog(getActivity(), r).show(getResources().getString(R.string.product_delete_warning));
                            }
                        });
                        rv.setAdapter(adapter);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            };
            productHandler.getAllProducts(a, thisAccount, thisList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setVisibility() {
        if (adapter.getItemCount() == 0) {
            rv.setVisibility(View.GONE);
            ivEmptyList.setVisibility(View.VISIBLE);
        }
        else {
            rv.setVisibility(View.VISIBLE);
            ivEmptyList.setVisibility(View.GONE);
        }
    }
}