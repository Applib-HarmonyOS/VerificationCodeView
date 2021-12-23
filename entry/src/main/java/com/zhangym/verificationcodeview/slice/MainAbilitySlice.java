/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhangym.verificationcodeview.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.TextField;
import ohos.agp.utils.TextTool;
import ohos.agp.window.dialog.ToastDialog;
import com.zhangym.customview.VerificationCodeView;
import com.zhangym.verificationcodeview.ResourceTable;
import java.security.SecureRandom;
import java.util.Random;

/**
 * MainAbilitySlice.
 */
public class MainAbilitySlice extends AbilitySlice {
    private static final int duration = 3000;
    private final Random mRandom = new SecureRandom();
    private VerificationCodeView mCodeView;
    private TextField etUsername;
    private TextField etPassword;
    private TextField etVerification;
    private Button btnLogin;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initViews();
        login();
        mCodeView.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                String s = String.valueOf(mRandom.nextInt(10))
                        + String.valueOf(mRandom.nextInt(10))
                        + String.valueOf(mRandom.nextInt(10))
                        + String.valueOf(mRandom.nextInt(10));

                mCodeView.setVerificationText(s);
            }
        });

        btnLogin.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                login();
            }
        });
    }

    private void login() {
        String userName = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String verification = etVerification.getText().toString().trim();
        ToastDialog toastDialog = new ToastDialog(getContext());
        if (TextTool.isNullOrEmpty(userName) || TextTool.isNullOrEmpty(password)
                || TextTool.isNullOrEmpty(verification)) {
            toastDialog.setText("Please enter complete information").setDuration(duration).show();
        } else {
            if (!mCodeView.getVerificationText().equals(verification)) {
                toastDialog.setText("verfication code error").setDuration(duration).show();
            } else {
                toastDialog.setText("login successful!").setDuration(duration).show();
            }
        }
    }

    private void initViews() {
        mCodeView = (VerificationCodeView) findComponentById(ResourceTable.Id_verificationCodeView);
        etUsername = (TextField) findComponentById(ResourceTable.Id_et_username);
        etPassword = (TextField) findComponentById(ResourceTable.Id_et_password);
        etVerification = (TextField) findComponentById(ResourceTable.Id_et_verification);
        btnLogin = (Button) findComponentById(ResourceTable.Id_btn_login);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
