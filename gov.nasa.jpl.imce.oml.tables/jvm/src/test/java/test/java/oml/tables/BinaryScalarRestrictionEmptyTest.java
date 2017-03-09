/*
 * Copyright 2016 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * License Terms
 */

package test.java.oml.tables;

import org.junit.Test;
import org.junit.Assert;

import gov.nasa.jpl.imce.oml.tables.BinaryScalarRestriction;
import gov.nasa.jpl.imce.oml.tables.BinaryScalarRestrictionHelper;

public class BinaryScalarRestrictionEmptyTest {

    @Test
    public void creationTest() {
        String graphUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";
        String name = "abc";
        String restrictedRangeUUID = "1245-ABCD-2345-4567";

        BinaryScalarRestriction w1 = new BinaryScalarRestriction(graphUUID, uuid, name, restrictedRangeUUID);

        String s1 = BinaryScalarRestrictionHelper.toJSON(w1);

        String ls = (w1.length().isEmpty()) ? "[]" : w1.length().toString();

        String maxL = (w1.maxLength().isEmpty()) ? "[]" : w1.maxLength().toString();

        String minL = (w1.minLength().isEmpty()) ? "[]" : w1.minLength().toString();

        String t1 = String.format(
                "{\"graphUUID\":\"%s\",\"uuid\":\"%s\",\"name\":\"%s\",\"length\":%s,\"maxLength\":%s,\"minLength\":%s,\"restrictedRangeUUID\":\"%s\"}",
                graphUUID, uuid, name, ls, maxL, minL, restrictedRangeUUID);
        Assert.assertEquals(t1, s1);

        BinaryScalarRestriction r1 = BinaryScalarRestrictionHelper.fromJSON(s1);
        Assert.assertEquals(w1.tboxUUID(), r1.tboxUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.name(), r1.name());
        Assert.assertEquals(w1.length(), r1.length());
        Assert.assertEquals(w1.maxLength(), r1.maxLength());
        Assert.assertEquals(w1.restrictedRangeUUID(), r1.restrictedRangeUUID());
        Assert.assertTrue(w1.equals(r1));
    }
}
