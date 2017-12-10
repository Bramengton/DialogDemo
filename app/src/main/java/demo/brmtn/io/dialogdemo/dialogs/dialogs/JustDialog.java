package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.app.FragmentManager;

/**
 * @author Bramengton on 31/07/2017.
 */
public abstract class JustDialog extends FragmentDialog<JustDialog.Builder> {
    public abstract static class Builder extends BuilderDialog<JustDialog>{
        public Builder(FragmentManager manager) {
            super(manager);
        }
    }
}
