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

package uk.gov.voa.valuetype.constraints

import org.scalatest.prop.PropertyChecks
import uk.gov.voa.valuetype.tooling.UnitSpec
import uk.gov.voa.valuetype.tooling.generators.GeneratorOf._

class PositiveIntSpec extends UnitSpec with PropertyChecks {

  private case class TestPositiveInt(value: Int) extends PositiveInt

  "PositiveInt" should {

    "allow instantiation with any positive number" in {
      forAll(positiveInt) { value =>
        TestPositiveInt(value).value shouldBe value
      }
    }

    "throw an IllegalArgumentException when instantiating with zero" in {
      val exception = the[IllegalArgumentException] thrownBy TestPositiveInt(0)
      exception.getMessage shouldBe "requirement failed: TestPositiveInt's value has to be positive"
    }

    "throw an IllegalArgumentException when instantiating with a negative value" in {
      val exception = the[IllegalArgumentException] thrownBy TestPositiveInt(-1)
      exception.getMessage shouldBe "requirement failed: TestPositiveInt's value has to be positive"
    }
  }
}
