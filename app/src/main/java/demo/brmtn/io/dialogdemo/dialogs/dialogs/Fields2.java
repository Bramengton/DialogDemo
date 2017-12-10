package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

/**
 * @author by Bramengton
 * @date 04.12.17.
 */
public class Fields2 extends DialogFragment {
    protected static final String FIELDS ="_bundle_fields";

    private CharSequence mTitleChar;
    private CharSequence mMessageChar;

    private int mTitleRes;
    private Object[] mTitleArgs;

    private int mMessageRes;
    private Object[] mMessageArgs;

    private int mStyle; // = R.style.Base_v21_DialogTheme;
    private boolean mCancelable = false;
    private boolean mIndeterminate = false;

    //    private JustAdapter mAdapter = null;
//    private int mChoiceMode = AbsListView.CHOICE_MODE_NONE;
    private @LayoutRes int mRes = 0;

    private Context mContext;
    void setContext(Context resources){
        mContext = resources;
    }

    public Context getContext(){
        return mContext;
    }

    public Fields2 setMessage(@StringRes int resId, Object... formatArgs) {
        mMessageRes = resId;
        mMessageArgs = formatArgs;
        return this;
    }

    public Fields2 setMessage(@StringRes int resId) {
        mMessageRes = resId;
        return this;
    }

    public Fields2 setMessage(CharSequence message) {
        mMessageChar = message;
        return this;
    }

    public Fields2 setTitle(@StringRes int resId, Object... formatArgs) {
        mTitleRes = resId;
        mTitleArgs = formatArgs;
        return this;
    }

    public Fields2 setTitle(@StringRes int resId) {
        mTitleRes = resId;
        return this;
    }

    public Fields2 setTitle(CharSequence title) {
        mTitleChar = title;
        return this;
    }

    public CharSequence getTitleChar() {
        if((mTitleChar==null || mTitleChar.length()<=0) && (mTitleRes>0))
            mTitleChar = mContext.getString(mTitleRes, mTitleArgs);
        return mTitleChar;
    }

    public CharSequence getMessageChar() {
        if((mMessageChar==null || mMessageChar.length()<=0) && (mMessageRes>0))
            mMessageChar = mContext.getString(mMessageRes, mMessageArgs);
        return mMessageChar;
    }

    public Fields2 setIndeterminate(boolean indeterminate){
        mIndeterminate = indeterminate;
        return this;
    }

    public boolean isIndeterminate() {
        return mIndeterminate;
    }

    public Fields2 setStyle(int style){
        mStyle=style;
        return this;
    }

    public int getStyle() {
        return mStyle;
    }

    public Fields2 setAllowCancelable(){
        mCancelable=true;
        return this;
    }

    public boolean isCancelable() {
        return mCancelable;
    }

    public Fields2 setView(@LayoutRes int reslayout){
        mRes =reslayout;
        return this;
    }

    public @LayoutRes int getViewRes(){
        return mRes;
    }

//    public Fields2 setListAdapter(JustAdapter adapter){
//        return setListAdapter(adapter, AbsListView.CHOICE_MODE_NONE);
//    }

//    public Fields2 setListAdapter(JustAdapter adapter, int choiceMode){
//        mAdapter = adapter;
//        mChoiceMode = choiceMode;
//        return this;
//    }
//
//    public int getChoiceMode(){
//        return mChoiceMode;
//    }
//
//    public JustAdapter getAdapter() {
//        return mAdapter;
//    }
//
//    public boolean isAdapterNotEmpty() {
//        return mAdapter!=null && !mAdapter.isEmpty();
//    }


    private int mPositiveButton = android.R.string.ok;
    private int mNegativeButton = android.R.string.cancel;
    private int mNeutralButton = android.R.string.no;
    private boolean mPositiveShow = false;
    private boolean mNegativeShow = false;
    private boolean mNeutralShow = false;

    public Fields2 setCustomButtonLabel(@StringRes int PositiveButton){
        Fields2 r = setButtonLabel(PositiveButton, 0, 0);
        mPositiveShow = true;
        return r;
    }

    public Fields2 setCustomButtonLabel(@StringRes int PositiveButton, @StringRes int NegativeButton){
        Fields2 r = setButtonLabel(PositiveButton, NegativeButton, 0);
        mPositiveShow = true;
        mNegativeShow = true;
        return r;
    }

    public Fields2 setCustomButtonLabel(@StringRes int PositiveButton, @StringRes int NegativeButton, @StringRes int NeutralButton){
        Fields2 r = setButtonLabel(PositiveButton, NegativeButton, NeutralButton);
        mPositiveShow = true;
        mNegativeShow = true;
        mNeutralShow = true;
        return r;
    }

    private Fields2 setButtonLabel(@StringRes int... buttons){
        if(buttons.length<=0) return this;
        mPositiveButton = buttons[0];
        mNegativeButton = buttons[1];
        mNeutralButton = buttons[2];
        return this;
    }

    public Fields2 allowCloseOnPositiveClick(){
        mPositiveShow = true;
        return this;
    }

    public Fields2 allowCloseOnNegativeClick(){
        mNegativeShow = true;
        return this;
    }

    @Deprecated
    public Fields2 setEnableNavigationButtons(){
        mPositiveShow = true;
        mNegativeShow = true;
        return this;
    }

    public int getPositiveButtonLabel() {
        return mPositiveButton;
    }

    public int getNegativeButtonLabel() {
        return mNegativeButton;
    }

    public int getNeutralButtonLabel() {
        return mNeutralButton;
    }

    public boolean isNegativeVisible() {
        return mNegativeShow;
    }

    public boolean isPositiveVisible(){
        return mPositiveShow;
    }

    public boolean isNeutralVisible(){
        return mNeutralShow;
    }

    public int getPositiveButton() {
        return mPositiveButton;
    }

    public Fields2 onRestoreInstanceState(@Nullable Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            setTitle(ss.mTitleChar);
            setMessage(ss.mMessageChar);
            setStyle(ss.mStyle);
            if (ss.mCancelable) setAllowCancelable();
            setIndeterminate(ss.mIndeterminate);

            setView(ss.mRes);

//            mChoiceMode = ss.mChoiceMode;

            mPositiveButton = ss.mPositiveButton;
            mNegativeButton = ss.mNegativeButton;
            mNeutralButton = ss.mNeutralButton;
            mPositiveShow = ss.mPositiveShow;
            mNegativeShow = ss.mNegativeShow;
            mNeutralShow = ss.mNeutralShow;
        }
        return this;
    }

    @NonNull
    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState();
        ss.mTitleChar = getTitleChar();
        ss.mMessageChar = getMessageChar();
        ss.mStyle = getStyle();
        ss.mCancelable = isCancelable();
        ss.mIndeterminate = isIndeterminate();
        ss.mRes = getViewRes();
//        ss.mChoiceMode = getChoiceMode();

        ss.mPositiveButton = getPositiveButtonLabel();
        ss.mNegativeButton = getNegativeButtonLabel();
        ss.mNeutralButton = getNeutralButtonLabel();
        ss.mPositiveShow = isPositiveVisible();
        ss.mNegativeShow = isNegativeVisible();
        ss.mNeutralShow = isNeutralVisible();
        return ss;
    }


    static class SavedState implements Parcelable {

        SavedState() {}

        CharSequence mTitleChar;
        CharSequence mMessageChar;
        int mStyle;
        boolean mCancelable;
        boolean mIndeterminate;

        int mChoiceMode;
        int mRes;

        int mPositiveButton;
        int mNegativeButton;
        int mNeutralButton;
        boolean mPositiveShow;
        boolean mNegativeShow;
        boolean mNeutralShow;

        @Override
        public int describeContents() {
            return 0;
        }

        @SuppressWarnings("rawtypes")
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        @SuppressWarnings("unchecked")
        SavedState(@NonNull Parcel source) {
            mTitleChar = source.readString().subSequence(0, source.readString().length());
            mMessageChar = source.readString().subSequence(0, source.readString().length());



            mStyle = source.readInt();
            mCancelable = source.readByte() != 0;
            mIndeterminate = source.readByte() != 0;
            mChoiceMode = source.readInt();
            mRes = source.readInt();

            mPositiveButton = source.readInt();
            mNegativeButton = source.readInt();
            mNeutralButton = source.readInt();
            mPositiveShow = source.readByte() != 0;
            mNegativeShow = source.readByte() != 0;
            mNeutralShow = source.readByte() != 0;

        }

        @Override
        public void writeToParcel(Parcel arg0, int arg1) {
            arg0.writeString(mTitleChar!=null ? mTitleChar.toString() : "");
            arg0.writeString(mMessageChar!=null ? mMessageChar.toString() : "");
            arg0.writeInt(mStyle);
            arg0.writeByte((byte)(mCancelable ? 1:0));
            arg0.writeByte((byte)(mIndeterminate ? 1:0));
            arg0.writeInt(mChoiceMode);
            arg0.writeInt(mRes);

            arg0.writeInt(mPositiveButton);
            arg0.writeInt(mNegativeButton);
            arg0.writeInt(mNeutralButton);

            arg0.writeByte((byte)(mPositiveShow ? 1:0));
            arg0.writeByte((byte)(mNegativeShow ? 1:0));
            arg0.writeByte((byte)(mNeutralShow ? 1:0));
        }
    }
}
