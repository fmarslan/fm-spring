/**
 * 
 * Copyright 2019 FMARSLAN
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 */

package com.fmarslan.spring.base.common.utils;

import java.util.Random;

/**
 * @author fmarslan
 *
 */
public class RandomUtils {

  public static final char[] ALPHABET_NUMBER = new char[] {'Q', 'A', 'Z', 'W', 'S', 'X', 'E', 'D',
      'C', 'R', 'F', 'V', 'T', 'G', 'B', 'Y', 'H', 'N', 'U', 'J', 'M', 'I', 'K', 'O', 'L', 'P', '1',
      '2', '3', '4', '5', '6', '7', '8', '9', '0'};

  public static final char[] ALPHABET = new char[] {'Q', 'A', 'Z', 'W', 'S', 'X', 'E', 'D', 'C',
      'R', 'F', 'V', 'T', 'G', 'B', 'Y', 'H', 'N', 'U', 'J', 'M', 'I', 'K', 'O', 'L', 'P'};

  public static final char[] NUMBER = new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

  public static String generateString(int minLength, int maxLength, char[] charset) {
    int length = minLength;
    if (minLength < 0)
      length = (new Random()).nextInt(100);
    if (minLength >= 0 && minLength < maxLength)
      length = (new Random()).nextInt(maxLength - minLength) + minLength;
    String generatedString = "";
    for (int i = 0; i < length; i++)
      generatedString += charset[(new Random()).nextInt(charset.length)];
    return generatedString;
  }

  public static String generateString(int minLength, int maxLength) {
    return generateString(minLength, maxLength, ALPHABET_NUMBER);
  }

  public static String generateString(int length) {
    return generateString(length, -1);
  }

  public static String generateString() {
    return generateString(-1, -1);
  }

  public static String padRightRandom(String txt, int size, char[] charset) {
    if (txt.length() >= size)
      return txt;
    return txt + generateString(size - txt.length(), -1, charset);
  }

  public static String padRightRandom(String txt, int size) {
    return padRightRandom(txt, size, ALPHABET_NUMBER);
  }

  public static String padLeftRandom(String txt, int size, char[] charset) {
    if (txt.length() >= size)
      return txt;
    return generateString(size - txt.length(), -1, charset) + txt;
  }

  public static String padLeftRandom(String txt, int size) {
    return padLeftRandom(txt, size, ALPHABET_NUMBER);
  }

}
