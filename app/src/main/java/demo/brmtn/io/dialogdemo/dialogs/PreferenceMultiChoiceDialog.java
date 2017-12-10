package demo.brmtn.io.dialogdemo.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bramengton on 18/06/2015.
 */
public class PreferenceMultiChoiceDialog extends AlertDialog.Builder{
    private Builder mBuilder;
    private void add(Builder bilder){
        mBuilder = bilder;
    }

    public interface CustomDialogPositiveListener {
        void Selected(Object o);
    }

    public interface CustomDialogNegativeListener {
        void CancelSelect();
    }

    private PreferenceMultiChoiceDialog(Context context) {
        super(context);
    }

    private AlertDialog init(){
        setCancelable(mBuilder.mCancelable);
        setTitle(mBuilder.mTitle);
        HelperDialog(mBuilder.mItems, mBuilder.mCheckedItems);
        return create();
    }

    private void HelperDialog(final CharSequence[] items, final boolean[] checkedItems){
        final List<Boolean> seletedItems = new ArrayList<Boolean>();
        for (boolean foo : checkedItems){
            seletedItems.add(foo);
        }

        setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                seletedItems.set(indexSelected,isChecked);
            }
        });

        setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mBuilder.listenerPositiveDialog != null) {
                    mBuilder.listenerPositiveDialog.Selected(seletedItems);
                }
            }
        });

        setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick (DialogInterface dialog,int whichButton) {
                if (mBuilder.listenerNegativeDialog != null) {
                    mBuilder.listenerNegativeDialog.CancelSelect();
                }
            }
        });
    }

    public static class Builder{
        private Context mContext;
        private int mTitle;
        private CharSequence[] mItems;
        private boolean[] mCheckedItems;
        private boolean mCancelable=true;
        private PreferenceMultiChoiceDialog mDialog;

        private CustomDialogPositiveListener listenerPositiveDialog;
        private CustomDialogNegativeListener listenerNegativeDialog;

        public Builder(Context context) {
            mContext = context;
        }

        public PreferenceMultiChoiceDialog show() {
            mDialog = new PreferenceMultiChoiceDialog(mContext);
            mDialog.add(this);
            mDialog.init().show();
            return mDialog;
        }

        public void setCancelable(boolean cancelable){
            mCancelable = cancelable;
        }

        public void setTitle(int title){
            mTitle=title;
        }

        public void setItems(final CharSequence[] items, final boolean[] checked) {
            mItems=items;
            mCheckedItems=checked;
        }

        public Builder setOpenCustomDialogListener(CustomDialogPositiveListener listener) {
            listenerPositiveDialog = listener;
            return this;
        }

        public Builder setOpenCustomDialogListener(CustomDialogNegativeListener listener) {
            listenerNegativeDialog = listener;
            return this;
        }
    }
}
