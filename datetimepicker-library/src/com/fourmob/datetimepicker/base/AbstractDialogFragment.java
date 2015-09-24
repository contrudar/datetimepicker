package com.fourmob.datetimepicker.base;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public abstract class AbstractDialogFragment<TResult> extends DialogFragment {

    protected void returnResult(final TResult result) {
        if (getTargetFragment() == null) {
            if (getActivity() instanceof IDialogResultHandler) {
                ((IDialogResultHandler) getActivity()).onDialogResult(getTargetRequestCode(), result);
            }
        } else {
            ((IDialogResultHandler) getTargetFragment()).onDialogResult(getTargetRequestCode(), result);
        }
        dismiss();
    }

    public static <TDialogFragment extends AbstractDialogFragment> void showDialog(final Fragment fragment,
                                                                                   final Class<TDialogFragment> clazz, final int dialogKey) {
        showDialog(fragment, clazz, dialogKey, null);
    }

    public static <TDialogFragment extends AbstractDialogFragment> void showDialog(final Fragment fragment,
                                                                                   final Class<TDialogFragment> clazz,
                                                                                   final int dialogKey, final Bundle arguments) {
        Fragment parentFragment = null;
        while (fragment.getParentFragment() != null) {
            parentFragment = fragment.getParentFragment();
        }

        createDialogFragment(parentFragment == null ? fragment : parentFragment, clazz, dialogKey, arguments);
    }

    public static <TDialogFragment extends AbstractDialogFragment> void showDialog(final FragmentActivity activity,
                                                                                   final Class<TDialogFragment> clazz, final int dialogKey) {
        showDialog(activity, clazz, dialogKey, null);
    }

    public static <TDialogFragment extends AbstractDialogFragment> void showDialog(final FragmentActivity activity,
                                                                                   final Class<TDialogFragment> clazz,
                                                                                   final int dialogKey,
                                                                                   final Bundle arguments) {
        final AbstractDialogFragment dialogFragment = (AbstractDialogFragment) Fragment.instantiate(activity, clazz.getName(), arguments);
        dialogFragment.setTargetFragment(null, dialogKey);
        dialogFragment.show(activity.getSupportFragmentManager(), null);
    }

    private static <TDialogFragment extends AbstractDialogFragment> void createDialogFragment(final Fragment fragment,
                                                                                              final Class<TDialogFragment> clazz,
                                                                                              final int dialogKey,
                                                                                              final Bundle arguments) {
        final AbstractDialogFragment dialogFragment = (AbstractDialogFragment)
                Fragment.instantiate(fragment.getActivity(), clazz.getName(), arguments);
        dialogFragment.setTargetFragment(fragment, dialogKey);
        dialogFragment.show(fragment.getActivity().getSupportFragmentManager(), null);
    }

}
