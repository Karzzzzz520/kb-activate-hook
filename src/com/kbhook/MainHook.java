package com.kbhook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {

    private static final String TARGET_PKG = "com.oplus.keyboard";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam == null || !TARGET_PKG.equals(lpparam.packageName)) return;
        try {
            XposedHelpers.findAndHookMethod(
                "com.oplus.keyboard.input.activate.ActivateResult",
                lpparam.classLoader,
                "getSuccess",
                new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                        return Boolean.TRUE;
                    }
                }
            );
        } catch (Throwable ignored) {
        }
    }
}
