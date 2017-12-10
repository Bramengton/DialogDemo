package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author Bramengton on 25/07/2017.
 */

abstract class BuilderDialogCompat<T extends FragmentDialogCompat> extends Fields implements Cloneable {
    private T mDialog;
    public abstract T setFragmentDialog();

    private FragmentManager mFragmentManager;

    BuilderDialogCompat(FragmentManager manager) {
        super();
        mFragmentManager = manager;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public FragmentManager getFragmentManager(){
        return mFragmentManager;
    }

    public T getDialog(){
        return mDialog;
    }

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public T show() {
        mDialog = setFragmentDialog();
        mDialog.add(this);
        mDialog.setCancelable(isCancelable());
        mDialog.show(mFragmentManager, "BuilderDialogCompat");
        return mDialog;
    }

    @Deprecated
    private T showWithStateLoss(T dialog) {
        //Not good method
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(dialog, "BuilderDialogCompat");
        ft.commitAllowingStateLoss();
        return mDialog;
    }

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public T showAsync(){
        mDialog = setFragmentDialog();
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
