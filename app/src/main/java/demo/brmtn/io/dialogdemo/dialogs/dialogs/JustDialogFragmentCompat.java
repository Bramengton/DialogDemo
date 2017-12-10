package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

/**
 * @author Bramengton on 02/12/17.
  */
public abstract class JustDialogFragmentCompat extends Fields2Compat {
    public abstract Dialog addDialog(Bundle savedInstanceState, AlertDialog.Builder dialog);

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Fields2Compat.FIELDS, onSaveInstanceState());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(Fields2Compat.FIELDS)) {
            onRestoreInstanceState(savedInstanceState.getParcelable(Fields2Compat.FIELDS));
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), getStyle());
        return addDialog(savedInstanceState, dialog);
    }


    /*dialog kept disappearing on orientation change.  might be a bug in v4 support.
     * http://stackoverflow.com/questions/12433397/android-dialogfragment-disappears-after-orientation-change
     * */
    @Override
    public void onDestroyView() {
        // Work around bug: http://code.google.com/p/android/issues/detail?id=17423
        Dialog dialog = getDialog();
        if ((dialog != null) && getRetainInstance()) dialog.setDismissMessage(null);
        super.onDestroyView();
    }

    public void show(FragmentManager manager) {
        manager.beginTransaction().add(this, "JustDialogFragmentTag").commit();
    }

    public void show(FragmentActivity activity) {
        setResources(activity.getResources());
        activity.getSupportFragmentManager().beginTransaction().add(this, "JustDialogFragmentTag").commit();
    }
}
