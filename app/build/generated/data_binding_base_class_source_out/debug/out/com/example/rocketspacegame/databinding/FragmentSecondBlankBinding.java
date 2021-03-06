// Generated by view binder compiler. Do not edit!
package com.example.rocketspacegame.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.rocketspacegame.R;
import com.example.rocketspacegame.mySurfaceView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSecondBlankBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView classIdDispaly;

  @NonNull
  public final ConstraintLayout frameLayout2;

  @NonNull
  public final TextView nameDisplay;

  @NonNull
  public final mySurfaceView view;

  private FragmentSecondBlankBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView classIdDispaly, @NonNull ConstraintLayout frameLayout2,
      @NonNull TextView nameDisplay, @NonNull mySurfaceView view) {
    this.rootView = rootView;
    this.classIdDispaly = classIdDispaly;
    this.frameLayout2 = frameLayout2;
    this.nameDisplay = nameDisplay;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSecondBlankBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSecondBlankBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_second_blank, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSecondBlankBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.classIdDispaly;
      TextView classIdDispaly = rootView.findViewById(id);
      if (classIdDispaly == null) {
        break missingId;
      }

      ConstraintLayout frameLayout2 = (ConstraintLayout) rootView;

      id = R.id.nameDisplay;
      TextView nameDisplay = rootView.findViewById(id);
      if (nameDisplay == null) {
        break missingId;
      }

      id = R.id.view;
      mySurfaceView view = rootView.findViewById(id);
      if (view == null) {
        break missingId;
      }

      return new FragmentSecondBlankBinding((ConstraintLayout) rootView, classIdDispaly,
          frameLayout2, nameDisplay, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
