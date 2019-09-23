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

package com.stepstone.stepper.internal.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.stepstone.stepper.internal.widget.pagetransformer.StepPageTransformerFactory;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;

/**
 * A non-swipeable viewpager with RTL support.<br>
 * <a href="http://stackoverflow.com/questions/9650265/how-do-disable-paging-by-swiping-with-finger-in-viewpager-but-still-be-able-to-s">Source</a>
 */
@RestrictTo(LIBRARY)
public class StepViewPager extends ViewPager {

    private boolean mBlockTouchEventsFromChildrenEnabled;

    public StepViewPager(Context context) {
        this(context, null);
    }

    public StepViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, StepPageTransformerFactory.createPageTransformer(context));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return mBlockTouchEventsFromChildrenEnabled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return mBlockTouchEventsFromChildrenEnabled;
    }

    /**
     * @param blockTouchEventsFromChildrenEnabled true if children should not receive touch events
     */
    public void setBlockTouchEventsFromChildrenEnabled(boolean blockTouchEventsFromChildrenEnabled) {
        this.mBlockTouchEventsFromChildrenEnabled = blockTouchEventsFromChildrenEnabled;
    }
}
