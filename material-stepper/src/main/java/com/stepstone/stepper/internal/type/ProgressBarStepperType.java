/*
Copyright 2016 StepStone Services

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.stepstone.stepper.internal.type;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.View;

import com.stepstone.stepper.R;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.adapter.StepAdapter;
import com.stepstone.stepper.internal.widget.ColorableProgressBar;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;

/**
 * Stepper type which displays a mobile step progress bar.
 */
@RestrictTo(LIBRARY)
public class ProgressBarStepperType extends AbstractStepperType {

    private final ColorableProgressBar mProgressBar;

    public ProgressBarStepperType(StepperLayout stepperLayout) {
        super(stepperLayout);
        mProgressBar = (ColorableProgressBar) stepperLayout.findViewById(R.id.ms_stepProgressBar);
        mProgressBar.setProgressColor(getSelectedColor());
        mProgressBar.setProgressBackgroundColor(getUnselectedColor());
        if (stepperLayout.isInEditMode()) {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgressCompat(1, false);
            mProgressBar.setMax(3);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStepSelected(int newStepPosition, boolean userTriggeredChange) {
        mProgressBar.setProgressCompat(newStepPosition + 1, userTriggeredChange);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onNewAdapter(@NonNull StepAdapter stepAdapter) {
        super.onNewAdapter(stepAdapter);
        final int stepCount = stepAdapter.getCount();
        mProgressBar.setMax(stepAdapter.getCount());
        mProgressBar.setVisibility(stepCount > 1 ? View.VISIBLE : View.GONE);
    }
}
