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

package com.zhangym.verificationcodeview;

import com.zhangym.customview.VerificationCodeView;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.utils.Color;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleOhosTest {
    private VerificationCodeView codeView;
    @Before
    public void setUp()  {
        Context context=AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        codeView =new VerificationCodeView(context);
    }
    @Test
    public void testBundleName() {
        final String actualBundleName = AbilityDelegatorRegistry.getArguments().getTestBundleName();
        assertEquals("com.zhangym.verificationcodeview", actualBundleName);
    }
    @Test
    public void testVerificationViewText()
    {
        codeView.setVerificationText("1234");
        assertNotNull(codeView.getVerificationText());
    }
    @Test
    public void testVerificationValue()
    {
        codeView.setVerificationText("1234");
        assertEquals("1234", codeView.getVerificationText());
    }
    @Test
    public void testShowInterference()
    {
        codeView.setShowInterferenceLines(true);
        assertTrue(codeView.isShowInterferenceLines());
    }
    @Test
    public void testShowInterferenceCircle()
    {
        codeView.setShowInterferenceCircles(true);
        assertTrue(codeView.isShowInterferenceCircles());
    }
    @Test
    public void testBold()
    {
        codeView.setTextBold(true);
        assertTrue(codeView.isTextBold());
    }
    @Test
    public void testCircleColorRandom()
    {
        codeView.setCircleColorRandom(true);
        assertTrue(codeView.isCircleColorRandom());
    }
    @Test
    public void testLineColor()
    {
        codeView.setLineColorRandom(true);
        assertTrue(codeView.isLineColorRandom());
    }
    @Test
    public void testSetBackground()
    {
        codeView.setVerificationCodeBackground(Color.GRAY.getValue());
        assertEquals(Color.GRAY.getValue(), codeView.getVerificationCodeBackground());
    }
    @Test
    public void testEqualSetBackground()
    {
        codeView.setVerificationCodeBackground(Color.BLACK.getValue());
        assertNotEquals(Color.GRAY.getValue(), codeView.getVerificationCodeBackground());
    }
    @Test
    public void testSetInterferenceLineCount()
    {
        codeView.setInterferenceLinesCount(20);
        assertEquals(20, codeView.getInterferenceLinesCount());
    }
    @Test
    public void testSetInterferenceLineColor()
    {
        codeView.setInterferenceLinesColor(Color.GRAY.getValue());
        assertFalse(codeView.isLineColorRandom());
    }
    @Test
    public void testSetInterferenceLineWidth()
    {
        codeView.setInterferenceLinesWidth(4f);
        assertNotEquals(3f, codeView.getInterferenceLinesWidth());
    }
    @Test
    public void testSetInterferenceCircleCount()
    {
        codeView.setInterferenceCirclesCount(110);
        assertEquals(110, codeView.getInterferenceCirclesCount());
    }
    @Test
    public void testsetInterferenceCirclesColor()
    {
        codeView.setInterferenceCirclesColor(Color.GREEN.getValue());
        assertFalse(codeView.isCircleColorRandom());
    }
    @Test
    public void testSetInterferenceCirclesRadius()
    {
        codeView.setInterferenceCirclesRadius(6f);
        assertNotEquals(7f, codeView.getInterferenceCirclesRadius());
    }
    @Test
    public void testSetTextColor()
    {
       codeView.setTextColor(Color.CYAN.getValue());
       assertEquals(Color.CYAN.getValue(), codeView.getTextColor());
    }
    @Test
    public void testSetTextSize()
    {
       codeView.setTextSize(16);
       assertNotEquals(17, codeView.getTextSize());
    }
    @Test
    public void testSetUnderLine()
    {
       codeView.setUnderLine(true);
       assertTrue(codeView.isUnderLine());
    }
    @Test
    public void testSetTextSkewX()
    {
       codeView.setTextSkewX(6f);
       assertNotEquals(7f, codeView.getTextSkewX());
    }
    @Test
    public void testSetStrokeWidth()
    {
       codeView.setStrokeWidth(20f);
       assertNotEquals(10f, codeView.getStrokeWidth());
    }
}