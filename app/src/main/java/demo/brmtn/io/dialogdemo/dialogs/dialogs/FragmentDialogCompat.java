package demo.brmtn.io.dialogdemo.dialogs.dialogs;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * @author Bramengton on 25/07/2017.
 */
abstract class FragmentDialogCompat<T extends BuilderDialogCompat> extends DialogFragment {
    public abstract Dialog createDialog(Bundle savedInstanceState, AlertDialog.Builder dialog, T builder);

    private T mBuilder;
    public FragmentDialogCompat add(T bilder){
        mBuilder = bilder;
        return this;
    }

    public T getBuilder(){
        return mBuilder;
    }

    private final static String PARCELABLE = "BuilderDialogCompatParcel";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PARCELABLE, mBuilder);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.containsKey(PARCELABLE)) {
            mBuilder = savedInstanceState.getParcelable(PARCELABLE);
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), mBuilder.getStyle());
        return createDialog(savedInstanceState, dialog, mBuilder);
    }

    /*dialog kept disappearing on orientation change.  might be a bug in v4 support.
	 * http://stackoverflow.com/questions/12433397/android-dialogfragment-disappears-after-orientation-change
	 * */
    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
//            getDialog().setOnDismissListener(null);  --causes a crash on some devices.
        super.onDestroyView();
    }
}
