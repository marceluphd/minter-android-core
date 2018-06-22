/*
 * Copyright (C) 2018 by MinterTeam
 * @link https://github.com/MinterTeam
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package network.minter.bipwallet.addresses;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.MvpView;

import dagger.Module;
import network.minter.bipwallet.addresses.models.AddressItem;
import network.minter.bipwallet.internal.mvp.ProgressView;

/**
 * MinterWallet. 2018
 *
 * @author Eduard Maximovich <edward.vstock@gmail.com>
 */
@Module
public class AddressManageModule {

    public interface AddressItemView extends MvpView {
        void setAddress(String addressName);
        void setSecuredBy(String securedByVal);
        void setOnClickDelete(View.OnClickListener listener);
        void startRemoveDialog(CharSequence attention, CharSequence description, String yes, String no, Dialog.OnClickListener onYesListener);
        void finishWithResult(int resultCode);
        void showProgress(CharSequence text);
        void hideProgress();
        void setOnCopy(View.OnClickListener listener);
        void hideActions();
        void setDescription(CharSequence description);
    }

    public interface AddressListView extends MvpView, ProgressView {
        void setAdapter(RecyclerView.Adapter<?> adapter);
        void startAddressItem(int requestCode, AddressItem address);
        void startCreateAddress(int requestCode);
        void scrollToPosition(int position);
    }

}
