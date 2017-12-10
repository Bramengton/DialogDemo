package demo.brmtn.io.dialogdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import demo.brmtn.io.dialogdemo.dialogs.CustomMessageDialogCompat;
import demo.brmtn.io.dialogdemo.dialogs.dialogs.DialogActions;

/**
 * @author by Bramengton
 * @date 02.12.17.
 */
public class SomeFragment extends Fragment {

    public someUi mUi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUi = new someUi(getContext(), getFragmentManager());

        view.findViewById(R.id.call_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUi.ShowDialog(v.getContext());
            }
        });
    }


    //not leaks
    private void ShowDialog() {
        CustomMessageDialogCompat.Builder builder = new CustomMessageDialogCompat.Builder(getFragmentManager());
        builder.setTitle("Dialog demo");
        builder.setMessage("This is demo for leaks catch..");
        builder.setEnableNavigationButtons();
        builder.setCustomDialogListener(new DialogActions.OnPositiveActionListener() {
            @Override
            public void onSelected() {
                Log.i("QQQQQQQQQQQQ", "= On selected call =");
                //Toast.makeText(getActivity(), "Good job", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
