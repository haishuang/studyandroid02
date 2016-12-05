//package com.hais.studyandroid02.utils;
//
//import android.view.View;
//import android.view.animation.Interpolator;
//import com.daimajia.androidanimations.library.BaseViewAnimator;
//import com.daimajia.androidanimations.library.Techniques;
//import com.nineoldandroids.animation.Animator.AnimatorListener;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class YoYo {
//    private static final long DURATION = 1000L;
//    private static final long NO_DELAY = 0L;
//    private Techniques techniques;
//    private long duration;
//    private long delay;
//    private Interpolator interpolator;
//    private List<AnimatorListener> callbacks;
//    private View target;
//
//    private YoYo(YoYo.AnimationComposer animationComposer) {
//        this.techniques = animationComposer.techniques;
//        this.duration = animationComposer.duration;
//        this.delay = animationComposer.delay;
//        this.interpolator = animationComposer.interpolator;
//        this.callbacks = animationComposer.callbacks;
//        this.target = animationComposer.target;
//    }
//
//    public static YoYo.AnimationComposer with(Techniques techniques) {
//        return new YoYo.AnimationComposer(techniques);
//    }
//
//    private BaseViewAnimator play() {
//        BaseViewAnimator animator = this.techniques.getAnimator();
//        animator.setDuration(this.duration).setInterpolator(this.interpolator).setStartDelay(this.delay);
//        if(this.callbacks.size() > 0) {
//            Iterator i$ = this.callbacks.iterator();
//
//            while(i$.hasNext()) {
//                AnimatorListener callback = (AnimatorListener)i$.next();
//                animator.addAnimatorListener(callback);
//            }
//        }
//
//        animator.animate(this.target);
//        return animator;
//    }
//
//    public static final class YoYoString {
//        private BaseViewAnimator animator;
//        private View target;
//
//        private YoYoString(BaseViewAnimator animator, View target) {
//            this.target = target;
//            this.animator = animator;
//        }
//
//        public boolean isStarted() {
//            return this.animator.isStarted();
//        }
//
//        public boolean isRunning() {
//            return this.animator.isRunning();
//        }
//
//        public void stop(boolean reset) {
//            this.animator.cancel();
//            if(reset) {
//                this.animator.reset(this.target);
//            }
//
//        }
//    }
//
//    public static final class AnimationComposer {
//        private List<AnimatorListener> callbacks;
//        private Techniques techniques;
//        private long duration;
//        private long delay;
//        private Interpolator interpolator;
//        private View target;
//
//        private AnimationComposer(Techniques techniques) {
//            this.callbacks = new ArrayList();
//            this.duration = 1000L;
//            this.delay = 0L;
//            this.techniques = techniques;
//        }
//
//        public YoYo.AnimationComposer duration(long duration) {
//            this.duration = duration;
//            return this;
//        }
//
//        public YoYo.AnimationComposer delay(long delay) {
//            this.delay = delay;
//            return this;
//        }
//
//        public YoYo.AnimationComposer interpolate(Interpolator interpolator) {
//            this.interpolator = interpolator;
//            return this;
//        }
//
//        public YoYo.AnimationComposer withListener(AnimatorListener listener) {
//            this.callbacks.add(listener);
//            return this;
//        }
//
//        public YoYo.YoYoString playOn(View target) {
//            this.target = target;
//            return new YoYo.YoYoString((new YoYo(this)).play(), this.target);
//        }
//    }
//}
