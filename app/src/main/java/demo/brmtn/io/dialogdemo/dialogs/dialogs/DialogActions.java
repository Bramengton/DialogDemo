package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.support.annotation.Nullable;
import android.widget.ListAdapter;

/**
 * @author Bramengton on 27/07/2017.
 */
public final class DialogActions {

    public interface OnPositiveActionListener {
        void onSelected();
    }

    public interface OnNeutralActionListener {
        void onNeutral();
    }

    public interface OnCancelActionListener {
        void onCancel();
    }

    public interface OnPositiveSelectActionListener<T> {
        void onSelected(T obj);
    }

    public interface OnPositiveSelectActionsListener<T> {
        void onSelected(T obj1, T obj2);
    }

    public interface OnPositiveSelectItemsListener {
        void onSelected(ListAdapter adapter);
    }

    public interface OnPositiveSelectItemListener {
        void onSelected(int position, @Nullable Object obj);
    }

    public interface OnAsyncTaskListener<Params, Progress, Result>{
        void onPostExecute(Result value);
        void onProgressUpdate(Progress... values);
        Result doInBackground(Params... values);
    }
}
