package demo.brmtn.io.dialogdemo;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
import demo.brmtn.io.dialogdemo.dialogs.CustomMessageDialogCompat;
import demo.brmtn.io.dialogdemo.dialogs.dialogs.DialogActions;

/**
 * @author by Bramengton
 * @date 02.12.17.
 */
public abstract class ButtonHelper extends ContextWrapper  {

    private Fragment localFragment;
    private FragmentManager localfragmentManager;
    public ButtonHelper(Context base, final FragmentManager fragmentManager) {
        super(base);
        if(fragmentManager!=null) localfragmentManager = fragmentManager;
    }

    public ButtonHelper(Context context, @NonNull final Fragment fragment){
        this(context, fragment.getFragmentManager());
        localFragment = fragment;
    }

    public final FragmentManager getFragmentManager(){
        return localfragmentManager;
    }

    //make some leaks
    protected void ShowDialog(final Context context){
        CustomMessageDialogCompat.Builder builder = new CustomMessageDialogCompat.Builder(getFragmentManager());
        builder.setTitle("Dialog demo");
        builder.setMessage("This is demo for leaks catch..");
        builder.setEnableNavigationButtons();
        builder.setCustomDialogListener(new DialogActions.OnPositiveActionListener() {
            @Override
            public void onSelected() {
                Toast.makeText(context, "Good job", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
