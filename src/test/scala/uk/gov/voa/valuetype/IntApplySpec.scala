/*
 * Copyright 2017 HM Revenue & Customs
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
 */

package uk.gov.voa.valuetype

import uk.gov.voa.valuetype.tooling.UnitSpec

class IntApplySpec extends UnitSpec {

  sealed trait TestValue extends IntValue

  object TestValue extends IntOptions[TestValue] with IntApply[TestValue] {

    case object TestOption1 extends TestValue {
      val value = 6
    }

    case object TestOption2 extends TestValue {
      val value = 7
    }

    val all = Seq(TestOption1, TestOption2)
  }

  "The apply method" should {

    "return the correct option for a valid value" in {
      TestValue(7) shouldBe TestValue.TestOption2
    }

    "throw an IllegalArgumentException for an invalid value" in {
      an [IllegalArgumentException] should be thrownBy TestValue(2)
    }
  }
}
