/*
 * Copyright Eubehi
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
package org.eubehi.aoc.y2025;

import org.eubehi.aoc.util.Files;
import org.eubehi.aoc.util.ResourceVerifier;
import org.eubehi.aoc.util.TextConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/** Solution for Day 01 of task 01 of year 2025 */
public class Y2K25D01T01
        implements TextConsumer {

    private static final Logger log = LoggerFactory.getLogger(Y2K25D01T01.class);
    private static final Path INPUT = ResourceVerifier.y2K25("D01T01.gz");
    private static final int INIT_VALUE = 50;

    private int timesCurrentValueWasZero = 0;
    private int currentValue = INIT_VALUE;

    /** Main entry code for the solution */
    static void main() {
        final Y2K25D01T01 consumer = new Y2K25D01T01();
        Files.readGZippedFile(INPUT, consumer);
        log.info("Solution to Y2K25D01T01 is {}", consumer.timesCurrentValueWasZero);
    }

    @Override
    public void consume(final String text) {
        if (text.isBlank()) return;
        final char c = text.charAt(0);
        final int v = Integer.parseInt(text, 1, text.length(), 10);
        if (c == 'L') this.currentValue -= v;
        else this.currentValue += v;

        this.currentValue %= 100;
        if (this.currentValue < 0) this.currentValue += 100;
        if (this.currentValue == 0) this.timesCurrentValueWasZero++;
    }
}
