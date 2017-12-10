package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.support.v4.app.FragmentManager;

/**
 * @author Bramengton on 31/07/2017.
 */
public abstract class JustDialogCompat extends FragmentDialogCompat<JustDialogCompat.Builder> {
    public abstract static class Builder extends BuilderDialogCompat<JustDialogCompat>{
        public Builder(FragmentManager manager) {
            super(manager);
        }
    }
}