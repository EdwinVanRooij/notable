package app.com.example.android.cloudpad_app.classes.adapteritems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import app.com.example.android.cloudpad_app.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

/**
 * Created by Edwin on 11/16/2015.
 */
public class AdapterItemAccount extends AbstractItem<AdapterItemAccount, AdapterItemAccount.ViewHolder> {
    private Account account;

    public AdapterItemAccount(Account a) {
        this.account = a;
    }

    public Account getAccount(){
        return account;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.item_account_root_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_account;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(RecyclerView.ViewHolder holder) {
        //get our viewHolder
        ViewHolder viewHolder = (ViewHolder) holder;

        //set the item selected if it is
        viewHolder.itemView.setSelected(isSelected());

        viewHolder.tvUsername.setText(account.getUsername());
        viewHolder.tvEmail.setText(account.getEmail());
        viewHolder.cb.setVisibility(View.VISIBLE);

        viewHolder.itemView.setSelected(isSelected());

//        UIUtils.setBackground(viewHolder.view, FastAdapterUIUtils.getSelectableBackground(ctx, Color.GREEN, true));
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        @Bind(R.id.tvUsername)
        TextView tvUsername;
        @Bind(R.id.tvEmail)
        TextView tvEmail;
        @Bind(R.id.checkBox)
        CheckBox cb;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}