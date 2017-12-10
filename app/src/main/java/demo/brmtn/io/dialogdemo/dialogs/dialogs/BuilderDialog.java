package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Looper;

/**
 * @author Bramengton on 26/07/2017.
 */
abstract class BuilderDialog<T extends FragmentDialog> extends Fields {
    private T mDialog;
    public abstract T setDialog();
    private FragmentManager mFragmentManager;

    BuilderDialog(FragmentManager manager) {
        super();
        mFragmentManager = manager;
    }

    public FragmentManager getFragmentManager(){
        return mFragmentManager;
    }

    public T getDialog(){
        return mDialog;
    }

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public T show() {
        mDialog = setDialog();
        mDialog.add(this);
        mDialog.setCancelable(isCancelable());
        mDialog.show(mFragmentManager, "BuilderDialog");
        return mDialog;
    }

    @Deprecated
    private T showWithStateLoss(T dialog) {
        //Not good method
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(dialog, "BuilderDialog");
        ft.commitAllowingStateLoss();
        return mDialog;
    }

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public T showAsync(){
        mDialog = setDialog();
        mDialog.add(this);
        mDialog.setCancelable(isCancelable());
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            //in these methods this solution have
            // no knowledge of the current state of the Activity lifecycle when they are called.
            public void run() {
                try {
                    mDialog.show(mFragmentManager, null);
                }catch (IllegalStateException ex){
                    mDialog = showWithStateLoss(mDialog);
                }
            }
        });
        return mDialog;
    }

    public void dismiss() {
        if(mDialog!=null) mDialog.dismiss();
    }
}