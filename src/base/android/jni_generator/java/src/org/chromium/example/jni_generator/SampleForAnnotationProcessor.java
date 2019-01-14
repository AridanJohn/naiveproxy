// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.example.jni_generator;

import org.chromium.base.annotations.JniStaticNatives;

/**
 * Sample class that uses the JNI annotation processor for static methods.
 * See generated files at bottom.
 */
class SampleForAnnotationProcessor {
    /**
     * Static methods declared here, each of these refer to a native method
     * which will have its declaration generated by our annotation processor.
     * There will also be a class generated to wrap these native methods
     * with the name SampleForAnnotationProcessorJni which will implement
     * Natives.
     */
    @JniStaticNatives
    interface Natives {
        void foo(String a, int b, char c, int[] d);
        SampleForAnnotationProcessor bar(SampleForAnnotationProcessor sample);
        String revString(String stringToReverse);
        String[] sendToNative(String[] strs);
        SampleForAnnotationProcessor[] sendSamplesToNative(SampleForAnnotationProcessor[] strs);
        boolean hasPhalange();
    }

    void test() {
        int[] x = new int[] {1, 2, 3, 4, 5};
        String[] strs = new String[] {"the", "quick", "brown", "fox"};
        strs = SampleForAnnotationProcessorJni.get().sendToNative(strs);

        SampleForAnnotationProcessor[] samples =
                new SampleForAnnotationProcessor[] {this, this, this};
        samples = SampleForAnnotationProcessorJni.get().sendSamplesToNative(samples);

        // Instance of Natives accessed through (classname + "Jni").get().
        SampleForAnnotationProcessorJni.get().foo("Test", 5, 'c', x);
        SampleForAnnotationProcessor sample = SampleForAnnotationProcessorJni.get().bar(this);
        boolean hasPhalange = SampleForAnnotationProcessorJni.get().hasPhalange();
        String s = SampleForAnnotationProcessorJni.get().revString("abcd");
    }
}
