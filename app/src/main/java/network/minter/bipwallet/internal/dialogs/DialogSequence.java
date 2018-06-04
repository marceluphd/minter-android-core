package network.minter.bipwallet.internal.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;

import java.util.HashMap;
import java.util.Map;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * MinterWallet. 2018
 *
 * @author Eduard Maximovich <edward.vstock@gmail.com>
 */
public class DialogSequence {
    public final static int NO_ACTION = -1;
    private Dialog.OnDismissListener mDismissListener;
    @SuppressLint("UseSparseArrays") private Map<Integer, DialogWrapper> mDialogs = new HashMap<>();
    private DialogWrapper mMain;

    public DialogSequence() {
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog) {
        setMain(id, dialog, positiveDialog, NO_ACTION, NO_ACTION);
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog, int negativeDialog) {
        setMain(id, dialog, positiveDialog, negativeDialog, NO_ACTION);
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog, int negativeDialog,
                   int neutralDialog) {
        setMain(id, dialog, positiveDialog, negativeDialog, neutralDialog);
    }

    public void setOnDismissListener(Dialog.OnDismissListener listener) {
        mDismissListener = listener;
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence setMain(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog) {
        return setMain(id, dialog, positiveDialog, NO_ACTION, NO_ACTION);
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence setMain(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog, int negativeDialog) {
        return setMain(id, dialog, positiveDialog, negativeDialog, NO_ACTION);
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence setMain(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog, int negativeDialog,
                           int neutralDialog) {
        mMain = new DialogWrapper<>(id, dialog, positiveDialog, negativeDialog, neutralDialog);
        mDialogs.put(id, mMain);
        return this;
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence addDialog(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog) {
        return addDialog(id, dialog, positiveDialog, NO_ACTION, NO_ACTION);
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence addDialog(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog, int negativeDialog) {
        return addDialog(id, dialog, positiveDialog, negativeDialog, NO_ACTION);
    }

    public <D extends WalletDialog, B extends WalletDialogBuilder>
    DialogSequence addDialog(int id, WalletDialogBuilder<D, B> dialog, int positiveDialog, int negativeDialog,
                             int neutralDialog) {
        mDialogs.put(id, new DialogWrapper<>(id, dialog, positiveDialog, negativeDialog, neutralDialog));
        return this;
    }

    public void show() {
        setupDialog(mMain).show();
    }

    private <T extends WalletDialog> void setupAction(int which, final DialogWrapper wrapper) {
        final WalletDialogBuilder builder = wrapper.getDialog();
        final Dialog.OnClickListener old = builder.getActionListener(which);
        builder.setAction(which, builder.getActionTitle(which), (d, w) -> {
            if (old != null) {
                old.onClick(d, w);
            }
            if (mDialogs.containsKey(wrapper.getActionId(which))) {
                T next = setupDialog(mDialogs.get(wrapper.getActionId(which)));
                if (next != null) {
                    next.show();
                }
                d.dismiss();
            } else {
                if (mDismissListener != null) {
                    mDismissListener.onDismiss(null);
                    d.dismiss();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private <D extends WalletDialog, B extends WalletDialogBuilder> D setupDialog(final DialogWrapper wrapper) {
        setupAction(BUTTON_POSITIVE, wrapper);
        setupAction(BUTTON_NEGATIVE, wrapper);
        setupAction(BUTTON_NEUTRAL, wrapper);

        //        out.setOnDismissListener(mDismissListener);

        final D out = (D) wrapper.<D, B>getDialog().create();
//        out.setOnDismissListener(mDismissListener);
        return out;
    }

    private final static class DialogWrapper<D extends WalletDialog, B extends WalletDialogBuilder> {
        private WalletDialogBuilder<D, B> mDialog;
        private int mId;
        private int mPositive;
        private int mNegative;
        private int mNeutral;

        DialogWrapper(int id, WalletDialogBuilder<D, B> dialog, int positive, int negative, int neutral) {
            mId = id;
            mDialog = dialog;
            mPositive = positive;
            mNegative = negative;
            mNeutral = neutral;
        }

        private WalletDialogBuilder<D, B> getDialog() {
            return mDialog;
        }

        private int getId() {
            return mId;
        }

        private int getNegative() {
            return mNegative;
        }

        private int getPositive() {
            return mPositive;
        }

        private int getNeutral() {
            return mNeutral;
        }

        private int getActionId(int which) {
            switch (which) {
                case BUTTON_POSITIVE:
                    return getPositive();
                case BUTTON_NEGATIVE:
                    return getNegative();
                case BUTTON_NEUTRAL:
                    return getNeutral();
            }

            return NO_ACTION;
        }
    }
}