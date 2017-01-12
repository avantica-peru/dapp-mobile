package net.avantica.xinef.dapp.view.fragment;

import android.support.v4.app.DialogFragment;

import net.avantica.xinef.dapp.di.HasComponent;


public abstract class BaseDialogFragment extends DialogFragment {
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
