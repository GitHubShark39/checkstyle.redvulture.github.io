////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2017 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package com.puppycrawl.tools.checkstyle.checks.metrics;

import static com.puppycrawl.tools.checkstyle.checks.metrics.CyclomaticComplexityCheck.MSG_KEY;

import org.junit.Assert;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtils;

public class CyclomaticComplexityCheckTest
    extends AbstractModuleTestSupport {
    @Override
    protected String getPackageLocation() {
        return "com/puppycrawl/tools/checkstyle/checks/metrics/cyclomaticcomplexity";
    }

    @Test
    public void testSwitchBlockAsSingleDecisionPointSetToTrue() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(CyclomaticComplexityCheck.class);
        checkConfig.addAttribute("max", "0");
        checkConfig.addAttribute("switchBlockAsSingleDecisionPoint", "true");

        final String[] expected = {
            "4:5: " + getCheckMessage(MSG_KEY, 2, 0),
        };

        verify(checkConfig, getPath("InputCyclomaticComplexitySwitchBlocks.java"), expected);
    }

    @Test
    public void testSwitchBlockAsSingleDecisionPointSetToFalse() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(CyclomaticComplexityCheck.class);
        checkConfig.addAttribute("max", "0");
        checkConfig.addAttribute("switchBlockAsSingleDecisionPoint", "false");

        final String[] expected = {
            "4:5: " + getCheckMessage(MSG_KEY, 5, 0),
        };

        verify(checkConfig, getPath("InputCyclomaticComplexitySwitchBlocks.java"), expected);
    }

    @Test
    public void testEqualsMaxComplexity() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(CyclomaticComplexityCheck.class);
        checkConfig.addAttribute("max", "5");

        final String[] expected = CommonUtils.EMPTY_STRING_ARRAY;

        verify(checkConfig, getPath("InputCyclomaticComplexitySwitchBlocks.java"), expected);
    }

    @Test
    public void test() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(CyclomaticComplexityCheck.class);

        checkConfig.addAttribute("max", "0");

        final String[] expected = {
            "5:5: " + getCheckMessage(MSG_KEY, 2, 0),
            "10:17: " + getCheckMessage(MSG_KEY, 2, 0),
            "22:5: " + getCheckMessage(MSG_KEY, 6, 0),
            "35:5: " + getCheckMessage(MSG_KEY, 3, 0),
            "45:5: " + getCheckMessage(MSG_KEY, 5, 0),
            "63:5: " + getCheckMessage(MSG_KEY, 3, 0),
            "76:5: " + getCheckMessage(MSG_KEY, 3, 0),
            "88:5: " + getCheckMessage(MSG_KEY, 3, 0),
            "100:5: " + getCheckMessage(MSG_KEY, 1, 0),
            "104:13: " + getCheckMessage(MSG_KEY, 2, 0),
        };

        verify(checkConfig, getPath("InputCyclomaticComplexity.java"), expected);
    }

    @Test
    public void testGetAcceptableTokens() {
        final CyclomaticComplexityCheck cyclomaticComplexityCheckObj =
            new CyclomaticComplexityCheck();
        final int[] actual = cyclomaticComplexityCheckObj.getAcceptableTokens();
        final int[] expected = {
            TokenTypes.CTOR_DEF,
            TokenTypes.METHOD_DEF,
            TokenTypes.INSTANCE_INIT,
            TokenTypes.STATIC_INIT,
            TokenTypes.LITERAL_WHILE,
            TokenTypes.LITERAL_DO,
            TokenTypes.LITERAL_FOR,
            TokenTypes.LITERAL_IF,
            TokenTypes.LITERAL_SWITCH,
            TokenTypes.LITERAL_CASE,
            TokenTypes.LITERAL_CATCH,
            TokenTypes.QUESTION,
            TokenTypes.LAND,
            TokenTypes.LOR,
        };
        Assert.assertArrayEquals("Invalid acceptable tokens", expected, actual);
    }

    @Test
    public void testHighMax() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(CyclomaticComplexityCheck.class);
        checkConfig.addAttribute("max", "100");
        final String[] expected = CommonUtils.EMPTY_STRING_ARRAY;

        verify(checkConfig, getPath("InputCyclomaticComplexitySwitchBlocks.java"), expected);
    }
}
