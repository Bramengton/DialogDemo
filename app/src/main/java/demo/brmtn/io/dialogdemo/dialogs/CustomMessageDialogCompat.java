package demo.brmtn.io.dialogdemo.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import demo.brmtn.io.dialogdemo.dialogs.dialogs.DialogActions;
import demo.brmtn.io.dialogdemo.dialogs.dialogs.JustDialogCompat;


/**
 * @author Bramengton on 25/07/2017.
 */
public class CustomMessageDialogCompat extends JustDialogCompat {

    @Override
    public Dialog createDialog(Bundle savedInstanceState, AlertDialog.Builder dialog, JustDialogCompat.Builder builder) {
        builder.setContext(getContext());
        dialog.setTitle(builder.getTitleChar());
        dialog.setMessage(builder.getMessageChar());
        dialog.setCancelable(builder.isCancelable());
        HelperDialog(dialog);
        return dialog.create();
    }


    private void HelperDialog(final AlertDialog.Builder builder) {
        final Builder mBuilder = (Builder) super.getBuilder();
        if(mBuilder.isPositiveVisible())
            builder.setPositiveButton(mBuilder.getPositiveButtonLabel(),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mBuilder.listenerPositiveDialog != null) {
                                mBuilder.listenerPositiveDialog.onSelected();
                            }
                        }
                    });

        if(mBuilder.isNegativeVisible())
            builder.setNegativeButton(mBuilder.getNegativeButtonLabel(),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if (mBuilder.listenerNegativeDialog != null) {
                                mBuilder.listenerNegativeDialog.onCancel();
                            }
                        }
                    });

        if(mBuilder.listenerNeutralDialog != null)
            builder.setNeutralButton(mBuilder.getNeutralButtonLabel(),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if (mBuilder.listenerNeutralDialog != null) {
                                mBuilder.listenerNeutralDialog.onNeutral();
                            }
                        }
                    });

        if(!mBuilder.isPositiveVisible() && !mBuilder.isNegativeVisible()) setCancelable(true);
    }

    public static class Builder extends JustDialogCompat.Builder {

        DialogActions.OnPositiveActionListener listenerPositiveDialog;
        DialogActions.OnCancelActionListener listenerNegativeDialog;
        DialogActions.OnNeutralActionListener listenerNeutralDialog;

        @Override
        public JustDialogCompat setFragmentDialog() {
            return new CustomMessageDialogCompat();
        }

        public Builder(FragmentManager manager) {
            super(manager);
        }

        public Builder setCustomDialogListener(DialogActions.OnPositiveActionListener listener) {
            listenerPositiveDialog = listener;
            return this;
        }

        public Builder setCustomDialogListener(DialogActions.OnCancelActionListener listener) {
            listenerNegativeDialog = listener;
            return this;
        }

        public Builder setCustomDialogListener(DialogActions.OnNeutralActionListener listener) {
            listenerNeutralDialog = listener;
            return this;
        }
    }
}
