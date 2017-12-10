package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;


/**
 * @author by Bramengton
 * @date 04.12.17.
 */
public abstract class JustDialogFragment extends Fields2 {
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

    public void show(Context context, FragmentManager manager) {
        setContext(context);
        manager.beginTransaction().add(this, "JustDialogFragmentTag").commit();
    }

    public void show(Activity activity) {
        setContext(activity.getBaseContext());
        activity.getFragmentManager().beginTransaction().add(this, "JustDialogFragmentTag").commit();
    }
}
