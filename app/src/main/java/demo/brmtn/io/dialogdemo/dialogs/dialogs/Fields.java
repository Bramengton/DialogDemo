package demo.brmtn.io.dialogdemo.dialogs.dialogs;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;


/**
 * @author Bramengton on 26/07/2017.
 */
class Fields implements Parcelable{

    Fields(){

    }

    public Fields setContext(Context context){
        mContext = context;
        return this;
    }

    private Context mContext;
    private CharSequence mTitleChar;
    private CharSequence mMessageChar;

    private int mTitleRes;
    private Object[] mTitleArgs;

    private int mMessageRes;
    private Object[] mMessageArgs;

    private int mStyle; // = R.style.Base_v21_DialogTheme;
    private boolean mCancelable = false;
    private boolean mIndeterminate = false;


    private @LayoutRes int mRes = 0;

//    public Fields setMessage(@StringRes int resId, Object... formatArgs) {
//        return setMessage(mContext.getString(resId, formatArgs));
//    }
//
//    public Fields setMessage(@StringRes int message) {
//        return setMessage(mContext.getString(message));
//    }
//
//    public Fields setMessage(CharSequence message) {
//        mMessageChar = message;
//        return this;
//    }
//
//    public Fields setTitle(@StringRes int title) {
//        return setTitle(mContext.getString(title));
//    }
//
//    public Fields setTitle(CharSequence title) {
//        mTitleChar = title;
//        return this;
//    }
//
//    public CharSequence getTitleChar() {
//        return mTitleChar;
//    }
//
//    public CharSequence getMessageChar() {
//        return mMessageChar;
//    }

    public Fields setMessage(@StringRes int resId, Object... formatArgs) {
        mMessageRes = resId;
        mMessageArgs = formatArgs;
        return this;
    }

    public Fields setMessage(@StringRes int resId) {
        mMessageRes = resId;
        return this;
    }

    public Fields setMessage(CharSequence message) {
        mMessageChar = message;
        return this;
    }

    public Fields setTitle(@StringRes int resId, Object... formatArgs) {
        mTitleRes = resId;
        mTitleArgs = formatArgs;
        return this;
    }

    public Fields setTitle(@StringRes int resId) {
        mTitleRes = resId;
        return this;
    }

    public Fields setTitle(CharSequence title) {
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

    public Fields setIndeterminate(boolean indeterminate){
        mIndeterminate = indeterminate;
        return this;
    }

    public boolean isIndeterminate() {
        return mIndeterminate;
    }

    public Fields setStyle(int style){
        mStyle=style;
        return this;
    }

    public int getStyle() {
        return mStyle;
    }

    public Fields setAllowCancelable(){
        mCancelable=true;
        return this;
    }

    public boolean isCancelable() {
        return mCancelable;
    }

    public Fields setView(@LayoutRes int reslayout){
        mRes =reslayout;
        return this;
    }

    public @LayoutRes int getViewRes(){
        return mRes;
    }

//    public Fields setListAdapter(ListAdapter adapter){
//        return setListAdapter(adapter, AbsListView.CHOICE_MODE_NONE);
//    }
//
//    public Fields setListAdapter(ListAdapter adapter, int choiceMode){
//        mAdapter = adapter;
//        mChoiceMode = choiceMode;
//        return this;
//    }
//
//    public int getChoiceMode(){
//        return mChoiceMode;
//    }
//
//    public ListAdapter getAdapter() {
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

    public Fields setCustomButtonLabel(@StringRes int PositiveButton){
        Fields r = setButtonLabel(PositiveButton, 0, 0);
        mPositiveShow = true;
        return r;
    }

    public Fields setCustomButtonLabel(@StringRes int PositiveButton, @StringRes int NegativeButton){
        Fields r = setButtonLabel(PositiveButton, NegativeButton, 0);
        mPositiveShow = true;
        mNegativeShow = true;
        return r;
    }

    public Fields setCustomButtonLabel(@StringRes int PositiveButton, @StringRes int NegativeButton, @StringRes int NeutralButton){
        Fields r = setButtonLabel(PositiveButton, NegativeButton, NeutralButton);
        mPositiveShow = true;
        mNegativeShow = true;
        mNeutralShow = true;
        return r;
    }

    private Fields setButtonLabel(@StringRes int... buttons){
        if(buttons.length<=0) return this;
        mPositiveButton = buttons[0];
        mNegativeButton = buttons[1];
        mNeutralButton = buttons[2];
        return this;
    }

    public Fields allowCloseOnPositiveClick(){
        mPositiveShow = true;
        return this;
    }

    public Fields allowCloseOnNegativeClick(){
        mNegativeShow = true;
        return this;
    }

    @Deprecated
    public Fields setEnableNavigationButtons(){
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

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator<Fields> CREATOR = new Parcelable.Creator<Fields>() {
        @Override
        public Fields createFromParcel(Parcel in) {
            return new Fields(in);
        }

        @Override
        public Fields[] newArray(int size) {
            return new Fields[size];
        }
    };

    @SuppressWarnings("unchecked")
    private Fields(@NonNull Parcel source) {
        mTitleChar = source.readString().subSequence(0, source.readString().length());
        mMessageChar = source.readString().subSequence(0, source.readString().length());

        mStyle = source.readInt();
        mCancelable = source.readByte() != 0;
        mIndeterminate = source.readByte() != 0;
//        mChoiceMode = source.readInt();
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
//        arg0.writeInt(mChoiceMode);
        arg0.writeInt(mRes);

        arg0.writeInt(mPositiveButton);
        arg0.writeInt(mNegativeButton);
        arg0.writeInt(mNeutralButton);

        arg0.writeByte((byte)(mPositiveShow ? 1:0));
        arg0.writeByte((byte)(mNegativeShow ? 1:0));
        arg0.writeByte((byte)(mNeutralShow ? 1:0));
    }
}
