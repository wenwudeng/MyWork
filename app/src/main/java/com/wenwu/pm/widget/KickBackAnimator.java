package com.wenwu.pm.widget;

import android.animation.TypeEvaluator;

/**
 * 弹窗动画
 */
public class KickBackAnimator implements TypeEvaluator<Float> {
	private final float s = 1.70158f;
	private float mDuration = 0f;

	public void setDuration(float duration) {
		mDuration = duration;
	}

	public Float evaluate(float fraction, Float startValue, Float endValue) {
		float t = mDuration * fraction;
		float b = startValue.floatValue();
		float c = endValue.floatValue() - startValue.floatValue();
		float d = mDuration;
		float result = calculate(t, b, c, d);
		return result;
	}

	private Float calculate(float t, float b, float c, float d) {
		return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
	}
}
